//Class for the game entity
import java.time.*;
import java.util.Scanner;

public class LOV extends RPG {

    private HeroFactory heroFactory;

    private int HERO_TEAM_SIZE_LIMIT = 3;

    private int WORLD_SIZE_UPPER_LIMIT = 1000, WORLD_SIZE_LOWER_LIMIT = 8;

    public LOV() {
        //TODO
    }

    public void play(){
        do {
            runGame();
        }
        while (ifPlayAgain());
        printEndingMsg();
    }

    public void initGame() {
        System.out.println();
        System.out.println("       \u001B[33m LEGEND OF VALOR \u001B[0m");
        System.out.println();
        System.out.println("  \u001B[33mWelcome to the world of Legends!");
        System.out.println();
        System.out.println("  Your goal is to reach Monsters' Nexus while preventing Monsters reaching Hero Nexus.");
        System.out.println("  You may encounter fight during you adventure.");
        System.out.println("  Your heroes gain money and experience when they defeat the monsters.");
        System.out.println("  Hero Nexus are also Markets where you can buy and sell things.");
        System.out.println("  To armed up your heroes, you may want to buy useful weapons, armors, potions, or spells and sell those you no longer needed.");
        System.out.println("  Loading game elements...\u001B[0m");
    }

    public void displayInfo() {
        System.out.println();
        System.out.println("       \u001B[33m CONTROL INFO \u001B[0m");
        System.out.println();
        System.out.println("        W/w - move up");
        System.out.println("        A/a - move left");
        System.out.println("        S/s - move down");
        System.out.println("        D/d - move right");
        System.out.println("        Q/q - quit game");
        System.out.println("        I/i - show information");
        System.out.println();
        System.out.println("       \u001B[33m MAP INFO \u001B[0m");
        System.out.println();
        System.out.println("        H - Hero position");
        System.out.println("        M - Monster position");
        System.out.println("        X - inaccessible tile");
        System.out.println("        N - Nexus");
        System.out.println("        P - Plain cell");
        System.out.println("        B - Bush cell");
        System.out.println("        K - Koulou cell");
        System.out.println("        C - Cave cell");
        System.out.println();
        System.out.print("Enter C/c to continue play or Q/q to exit game:");
        Scanner in = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            String next = in.nextLine();
            if (next.equals("Q") || next.equals("q")) {
                System.exit(0);
            }
            if (next.equals("C") || next.equals("c")) {
                done = true;
            }
        }
    }

    public void runGame(){
        //TODO
        initGame();
    }

    protected boolean ifPlayAgain() {
        System.out.println("Game ended! Do you want to play again? (Yes/No)");
        return Utils.takeYes();
    }

    protected void printEndingMsg() {
        System.out.println("Thank you for playing Heroes And Monsters! Have a nice day!");
    }

    private void printWelcomingMsg() {
        System.out.println("Welcome to Heroes And Monsters!");
    }

    protected void generateWorld() {
        System.out.println("Please enter the dimension of the world (default and minimum 8 * 8, enter one integer only): ");
        int dimension = Utils.takeInteger(WORLD_SIZE_LOWER_LIMIT, WORLD_SIZE_UPPER_LIMIT);
        System.out.println("Initializing world...");
        setWorld(new World(dimension));
    }

    private void proposePurchase(Hero hero, Market market) {
        System.out.println("Hero " + hero.getName() + ", do you want to buy or sell anything? (Yes/No)");
        if (Utils.takeYes()) {
            while (true) {
                System.out.println("You have " + hero.getMoney() + " coins!");
                System.out.println("Do you want to buy or sell? (Buy/Sell) q/Q to quit ");
                String buyOrSell = takeBuy();
                if (buyOrSell.equals("Buy")) {
                    while (true) {
                        System.out.println("What fo you want to buy? (q/Q to quit)");
                        String input = Utils.takeInput();
                        if (input.equalsIgnoreCase("q")) {
                            break;
                        }
                        if (market.ifContains(input)) {
                            Item item = market.getItemByName(input);
                            if (hero.getMoney() < item.getPrice()) {
                                System.out.println("Insufficient fund! Try again.");
                            }
                            else {
                                hero.setMoney(hero.getMoney() - item.getPrice());
                                market.sold(input);
                                item.setPrice(item.getPrice() / 2);
                                hero.addToInventory(item);
                                System.out.println(input + " bought!");
                            }
                        }
                        else {
                            System.out.println("Invalid item name! Try again!");
                        }
                    }
                }
                else if (buyOrSell.equals("Sell")) {
                    while (true) {
                        hero.showInventory();
                        System.out.println("What do you want to sell? (q/Q to quit)");
                        String itemName = Utils.takeInput();
                        while (!itemName.equalsIgnoreCase("q") && !hero.hasItem(itemName) && !market.hasEnoughMoney(hero.getItemByName(itemName).getPrice())) {
                            if (!hero.hasItem(itemName)) {
                                System.out.println("Wrong item name, try again!");
                            }
                            else if (!market.hasEnoughMoney(hero.getItemByName(itemName).getPrice())){
                                System.out.println("Market does not have enough money! Try again! ");
                            }
                            itemName = Utils.takeInput();
                        }
                        if (itemName.equalsIgnoreCase("q")) {
                            break;
                        }
                        Item item = hero.getItemByName(itemName);
                        hero.soldItem(itemName);
                        System.out.println("Sold " + itemName + "!");
                        market.buy(item);
                    }
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

    private void proposeItemUse(HeroTeam heroTeam) {
        while (true) {
            System.out.println("Do you want to use item for a hero or check hero stats? (Yes/No, STAT to show stats of heroes)");
            String input = Utils.takeInput();
            while (!input.equals("Yes") && !input.equals("No") && !input.equals("STAT")) {
                System.out.println("Invalid Input! Try again! ");
                input = Utils.takeInput();
            }
            if (input.equals("Yes")) {
                heroTeam.showTeam();
                System.out.println("Which hero? ");
                int idx = Utils.takeInteger(1, heroTeam.size()) - 1;
                Hero hero = heroTeam.getHeroByIdx(idx);
                heroUseItem(hero);
            }
            else if (input.equals("STAT")) {
                heroTeam.showTeam();
            }
            else break;
        }
    }

    private void heroUseItem(Hero hero) {
        hero.showInventory();
        System.out.println("What item do you want to use? Enter its name: ");
        String input = Utils.takeInput();
        while (!hero.hasItem(input)) {
            input = Utils.takeInput();
        }
        while (!hero.useItem(input, null)) {
            hero.showInventory();
            System.out.println("What item do you want to use? Enter its name: ");
            input = Utils.takeInput();
            while (!hero.hasItem(input)) {
                input = Utils.takeInput();
            }
        }
    }
 }
