//Class for the game entity

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LOV extends RPG {

    //private HeroFactory heroFactory;

    private int HERO_TEAM_SIZE_LIMIT = 3;

    private int NUM_LANE = 3;

    private int ENEMY_IS_HERO = 0, ENEMY_IS_MONSTER = 1;

    public LOV() {
        setHeroTeam(new HeroTeam());
        setMonsterTeam(new MonsterTeam());
    }

    protected void runGame(){
        printWelcomingMsg();
        generateWorld();
        World world = getWorld();
        int round = 0;
        while (!(world.monsterWon() || world.heroWon())) {
            System.out.println("Round " + (round + 1) + ":");
            if (round % 8 == 0) {
                world.generateMonsters(getMonsterTeam());
            }
            heroesMove();
            monstersMove();
            round++;
            System.out.println("Hero recovers");
            getHeroTeam().recoverAll();
        }
        if (world.monsterWon()) {
            System.out.println("\u001B[31mYou lost the game!\u001B[0m");
        }
        else {
            System.out.println("\u001B[35mCongratulations! You won!\u001B[0m");
        }
    }

    protected boolean ifPlayAgain() {
        System.out.println("Game ended! Do you want to play again? (Yes/No)");
        return Utils.takeYes();
    }

    protected void printEndingMsg() {
        System.out.println("Thank you for playing Legends and Valor! Have a nice day!");
    }

    private void printWelcomingMsg() {
        System.out.println("\u001B[33m        Welcome to Legends of Valor!\u001B[0m");
    }

    protected void generateWorld() {
        System.out.println("\u001B[33m        Initializing world...\u001B[0m");
        setWorld(new World(NUM_LANE));
        getWorld().placeHeroInit(getHeroTeam());
    }

    private void heroesMove() {
        HeroTeam heroTeam = getHeroTeam();
        for (Hero hero : heroTeam) {
            getWorld().display();
            int lane = hero.getPosL(), r = hero.getPosI(), c = hero.getPosJ();
            System.out.println(hero.getName() + " in lane " + ((lane / 2) + 1));
            Cell curCell = getWorld().getCell(lane, r, c);
            if (curCell instanceof HeroNexus) {
                System.out.println("You are in a Nexus!");
                proposePurchase(hero, (Market) curCell);
            }
            boolean canMove = true;
            while (canMove) {
                hero.showActionsAndNotes();
                String action = hero.takeLegalActionFromInput();
                canMove = makeMove(action, hero);
            }
        }
    }

    private void monstersMove() {
        MonsterTeam monsters = getMonsterTeam();
        List<Monster> deadMonsters = new ArrayList<>();
        for (Monster monster : monsters) {
            HeroTeam heroes = (HeroTeam) surroundingEnemies(ENEMY_IS_HERO, monster);
            if (heroes.size() == 0) {
                monster.forward(getWorld());
            }
            else {
                Hero hero = heroes.getHeroByIdx(0);
                Fight fight = new Fight(hero, monster);
                fight.executeFight();
            }
            if (monster.isDead()) {
                deadMonsters.add(monster);
            }
        }
        for (Monster monster : deadMonsters) {
            removeMonster(monster);
        }
    }

    private boolean makeMove(String action, Hero hero) {
        if (action.equals("stat")) {
            hero.showStat();
            return true;
        }
        else if (action.equals("w") || action.equals("a") || action.equals("s") || action.equals("d")) {
            //getWorld().display();
            return moveHero(action, hero);
        }
        else if (action.equals("b")) {
            return hero.backToOrigNexus(getWorld());
        }
        else if (action.equals("t")) {
            return teleport(hero);
        }
        else if (action.equals("at")) {
            MonsterTeam monsterTeam = (MonsterTeam) surroundingEnemies(ENEMY_IS_MONSTER, hero);
            monsterTeam.showTeam();
            System.out.println("Enter index of monster to attack: ");
            int idx = Utils.takeInteger(1, monsterTeam.size());
            Monster monster = monsterTeam.getMonsterByIdx(idx);
            Fight fight = new Fight(hero, monster);
            fight.executeFight();
            if (!hero.isSober()) {
                hero.backToOrigNexus(getWorld());
            }
            if (monster.isDead()) {
                removeMonster(monster);
            }
        }
        else if (action.equals("i")) {
            hero.showInventory();
            return proposeItemUse(hero);
        }
        return false;
    }

    private void removeMonster(Monster monster) {
        getMonsterTeam().remove(monster);
        int lane = monster.getPosL(), i = monster.getPosI(), j = monster.getPosJ();
        getWorld().removeMonster(lane, i, j);
    }

    private Team surroundingEnemies(int enemyIndicator, Figure fig) {
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        int lane = fig.getPosL(), i = fig.getPosI(), j = fig.getPosJ();
        Team team;
        if (enemyIndicator == ENEMY_IS_MONSTER) {
            team = new MonsterTeam();
            for (int[] direction : directions) {
                int nextI = i + direction[0], nextJ = j + direction[1];
                if (Utils.checkInBound(nextI, getWorld().getROW(), nextJ, getWorld().getCOL()) && getWorld().getCell(lane, nextI, nextJ).getMonster() != null) {
                    ((MonsterTeam)team).add(getWorld().getCell(lane, nextI, nextJ).getMonster());
                }
            }
        }
        else {
            team = new HeroTeam();
            for (int[] direction : directions) {
                int nextI = i + direction[0], nextJ = j + direction[1];
                if (Utils.checkInBound(nextI, getWorld().getROW(), nextJ, getWorld().getCOL()) && getWorld().getCell(lane, nextI, nextJ).getHero() != null) {
                    ((HeroTeam)team).add(getWorld().getCell(lane, nextI, nextJ).getHero());
                }
            }
        }
        return team;
    }

    private boolean moveHero(String direction, Hero hero) {
        return switch (direction) {
            case "w" -> getWorld().moveHeroBy(-1, 0, hero);
            case "a" -> getWorld().moveHeroBy(0, -1, hero);
            case "s" -> getWorld().moveHeroBy(1, 0, hero);
            case "d"-> getWorld().moveHeroBy(0, 1, hero);
            default -> false;
        };
    }

    private boolean teleport(Hero hero) {
        int curLane = hero.getPosL(), i = hero.getPosI(), j = hero.getPosJ();
        System.out.println("Specify the lane you want to teleport to: (q/Q to quit)");
        int lane = Utils.takeInteger(1, getWorld().getDimension());
        if (lane == -1) {
            return true;
        }
        int actualLane = (lane - 1) * 2 + 1;
        while (actualLane == curLane) {
            System.out.println("Cannot teleport in the same lane! Enter again!");
            lane = Utils.takeInteger(1, getWorld().getDimension());
            actualLane = (lane - 1) * 2 + 1;
        }
        System.out.println("Specify the row you want to teleport to: (q/Q to quit)");
        int row = Utils.takeInteger(0, getWorld().getROW() - 1);
        if (row == -1) {
            return true;
        }
        while (behindMonster(actualLane, row) || rowIsFullOfHeroes(actualLane, row) || !visitedRow(actualLane, row)) {
            if (behindMonster(actualLane, row)) {
                System.out.println("Cannot teleport behind the monster! Enter again!");
            }
            else {
                System.out.println("No more place for heroes in this row! Enter again!");
            }
            row = Utils.takeInteger(0, getWorld().getROW() - 1);
        }
        System.out.println("Specify which of the cells you want to teleport to: (q/Q to quit)");
        int col = Utils.takeInteger(0, getWorld().getCOL() - 1);
        if (col == -1) {
            return true;
        }
        while (containsHeroInCell(actualLane, row, col) || !visitedCell(actualLane, row, col)) {
            if (containsHeroInCell(actualLane, row, col)) {
                System.out.println("Cannot teleport to a cell with a hero! Enter again! (q/Q to quit)");
            }
            else {
                System.out.println("Cell not visited! Enter again!");
            }
            col = Utils.takeInteger(0, getWorld().getCOL() - 1);
        }
        //System.out.println("111");
        getWorld().putHeroAt(lane, row, col, hero);
        return false;
    }

    private boolean visitedRow(int lane, int row) {
        return getWorld().visitedRow(lane, row);
    }

    private boolean behindMonster(int lane, int row) {
        return getWorld().behindMonster(lane, row);
    }

    private boolean rowIsFullOfHeroes(int lane, int row) {
        return getWorld().rowIsFullOfHeroes(lane, row);
    }

    private boolean containsHeroInCell(int lane, int row, int col) {
        return getWorld().containsHeroInCell(lane, row, col);
    }

    private boolean visitedCell(int lane, int row, int col) {
        return getWorld().visitedCell(lane, row, col);
    }

    private void proposePurchase(Hero hero, Market market) {
        System.out.println("Hero " + hero.getName() + ", do you want to buy or sell anything? (Yes/No)");
        if (Utils.takeYes()) {
            while (true) {
                System.out.println("You have " + hero.getMoney() + " coins!");
                System.out.println("Do you want to buy or sell? (Buy/Sell) q/Q to quit ");
                String buyOrSell = takeBuy();
                if (buyOrSell.equals("Buy")) {
                    market.heroBuy(hero);
                }
                else if (buyOrSell.equals("Sell")) {
                    market.heroSell(hero);
                }
                else {
                    break;
                }
            }
        }
    }

    private String takeBuy() {
        String input = Utils.takeInput();
        while (!(input.equals("Buy") || input.equals("Sell") || input.equalsIgnoreCase("q"))) {
            System.out.println("Invalid input! Try again");
            input = Utils.takeInput();
        }
        return input;
    }

    //true/false: whether or not hero used up the moving points.
    private boolean proposeItemUse(Hero hero) {
        System.out.println("Do you want to use an item? (Yes/No)");
        String input = Utils.takeInput();
        while (!input.equals("Yes") && !input.equals("No")) {
            System.out.println("Invalid Input! Try again! ");
            input = Utils.takeInput();
        }
        if (input.equals("Yes")) {
            return hero.heroUseItem(null);
        }

        else {
            return false;
        }
    }
 }
