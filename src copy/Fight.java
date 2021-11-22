//Class that initializes and executes the fighting process

import java.util.*;

public class Fight {

    HeroTeam heroTeam;

    MonsterTeam monsterTeam;

    int bountySum;

    int expSum;

    Set<String> allowedOperationSet;

    String[] allowedOperation = new String[]{"a", "i"};

    public Fight(HeroTeam heroTeam, MonsterTeam monsters) {
        this.heroTeam = heroTeam;
        this.monsterTeam = monsters;
        for (Monster monster : monsterTeam) {
            bountySum += monster.getBounty();
            expSum += monster.getExpGain();
        }
        allowedOperationSet = new HashSet<>(Arrays.asList(allowedOperation));
    }

    public void executeFight() {
        showMonsters();
        while (heroTeam.isSober() && monsterTeam.size() > 0) {
            heroMakeMove();
            monsterAttack();
            heroTeam.recoverAll();
        }
        reviveAndReward();
    }

    private void heroMakeMove() {
        Random rand = new Random();
        for (Hero hero : heroTeam) {
            hero.showFightingStat();
            if (hero.isSober()) {
                System.out.println("Choose your move: a -attack, i -check inventory");
                String move = takeOperation();
                if (move.equals("i") && !hero.hasAnyItem()) {
                    System.out.println("No items! Automatically turn to attack");
                    move = "a";
                }
                if (move.equals("a")) {
                    System.out.println("Monsters:");
                    showMonsters();
                    System.out.println("Choose the monster to attack: enter idx");
                    int idx = Utils.takeInteger(1, monsterTeam.size());
                    Monster monster = monsterTeam.getMonsterByIdx(idx - 1);
                    int dodgeCheck = rand.nextInt(100);
                    if (dodgeCheck <= monster.getDodgeChance()) {
                        System.out.println("Miss! ");
                    }
                    else {
                        int damageMade = monster.takeDamage(hero.getDamage());
                        System.out.println("Hero " + hero.getName() + " made " + damageMade + " points damage to monster " + monster.getName());
                        if (monster.isDead()) {
                            System.out.println("Monster " + monster.getName() + " is dead!");
                            monsterTeam.remove(monster);
                        }
                    }
                }
                else if (move.equals("i")){
                    heroUseItem(hero, monsterTeam);
                }
                else {
                    System.err.println("sth is wrong");
                }
            }
        }
    }

    private String takeOperation() {
        String input = Utils.takeInput();
        while (!allowedOperationSet.contains(input)) {
            System.out.println("Invalid move! Try again!");
            input = Utils.takeInput();
        }
        return input;
    }

    private void showMonsters() {
        int idx = 1;
        for (Monster monster : monsterTeam) {
            System.out.println(idx++ + " " + monster.fightInfo());
        }
    }

    private void monsterAttack() {
        int idx = 0;
        for (Monster monster : monsterTeam) {
            if (!heroTeam.isSober()) {
                break;
            }
            else {
                while (!heroTeam.getHeroByIdx(idx & heroTeam.size()).isSober()) {
                    idx++;
                }
                Hero hero = heroTeam.getHeroByIdx(idx);
                hero.takeDamage(monster.getBaseDamage());
            }
        }
    }

    private void reviveAndReward() {
        if (!heroTeam.isSober()) {
            System.out.println("R.I.P");
            heroTeam.lostPenalty();
        }
        else {
            System.out.println("Congratulations! ");
        }
        for (Hero hero : heroTeam) {
            if (!hero.isSober()) {
                System.out.println("Hero " + hero.getName() + " is revived");
            }
            hero.setCurHp(Math.max(hero.getCurHp(), hero.getHp() / 2));
            hero.setCurMana(Math.max(hero.getCurMana(), hero.getMana() / 2));
            hero.incrementExp(Math.max(expSum / heroTeam.size(), 1));
            hero.incrementMoney(Math.max(bountySum / heroTeam.size(), 1));
        }
    }

    private void heroUseItem(Hero hero, MonsterTeam monsters) {
        hero.showInventory();
        if (!hero.hasAnyItem()) {
            System.out.println("Hero has no item. Abort.");
            return;
        }
        System.out.println("What item do you want to use? Enter its name: ");
        String input = Utils.takeInput();
        while (!hero.hasItem(input)) {
            System.out.println("No such item! Try again!");
            input = Utils.takeInput();
        }
        while (!hero.useItem(input, monsters)) {
            hero.showInventory();
            System.out.println("What item do you want to use? Enter its name: ");
            input = Utils.takeInput();
            while (!hero.hasItem(input)) {
                input = Utils.takeInput();
            }
        }
    }
}
