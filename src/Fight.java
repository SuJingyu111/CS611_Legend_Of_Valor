//Class that initializes and executes the fighting process

import java.util.*;

public class Fight {

    Hero hero;

    Monster monster;

    int bountySum;

    int expSum;

    Set<String> allowedOperationSet;

    String[] allowedOperation = new String[]{"a", "i"};


    public Fight(Hero hero, Monster monster) {
        this.hero = hero;
        this.monster = monster;
        bountySum += monster.getBounty();
        expSum += monster.getExpGain();
        allowedOperationSet = new HashSet<>(Arrays.asList(allowedOperation));
    }

    public void executeFight() {
        showMonster();
        while (hero.isSober() && !monster.isDead()) {
            heroMakeMove();
            monsterAttack();
        }
        reviveAndReward();
    }

    private void heroMakeMove() {
        Random rand = new Random();
        hero.showFightingStat();
        if (hero.isSober()) {
            System.out.println("Choose your move: a - attack, i - check inventory");
            String move = takeOperation();
            if (move.equals("i") && !hero.hasAnyItem()) {
                System.out.println("\u001B[31mNo items! Automatically turn to attack \u001B[0m");
                move = "a";
            }
            if (move.equals("a")) {
                int dodgeCheck = rand.nextInt(100);
                if (dodgeCheck <= monster.getDodgeChance()) {
                    System.out.println("Miss! ");
                }
                else {
                    int damageMade = monster.takeDamage(hero.getDamage());
                    System.out.println(" ");
                    System.out.println("\u001B[31mHero " + hero.getName() + " made " + damageMade +
                            " points damage to monster " + monster.getName() + "\u001B[0m");
                    System.out.println(" ");
                    if (monster.isDead()) {
                        System.out.println("Monster " + monster.getName() + " is dead!");
                    }
                }
            }
            else if (move.equals("i")){
                hero.heroUseItem(monster);
            }
            else {
                System.err.println("sth is wrong");
            }
        }
    }


    private String takeOperation() {
        String input = Utils.takeInput();
        while (!allowedOperationSet.contains(input)) {
            System.out.println("\u001B[33mInvalid move! Try again!\u001B[0m");
            input = Utils.takeInput();
        }
        return input;
    }


    private void showMonster() {
        System.out.println(monster.fightInfo());
    }

    private void monsterAttack() {
        int idx = 0;
        if (hero.isSober()) {
            hero.takeDamage(monster.getBaseDamage());
        }
    }


    private void reviveAndReward() {
        if (!hero.isSober()) {
            System.out.println("R.I.P");
            hero.lostPenalty();
        }
        else {
            System.out.println("Congratulations! ");
        }
        if (!hero.isSober()) {
            System.out.println("Hero " + hero.getName() + " is revived");
        }
        hero.setCurHp(Math.max(hero.getCurHp(), hero.getHp() / 2));
        hero.setCurMana(Math.max(hero.getCurMana(), hero.getMana() / 2));
        hero.incrementExp(Math.max(expSum, 1));
        hero.incrementMoney(Math.max(bountySum, 1));
    }
}
