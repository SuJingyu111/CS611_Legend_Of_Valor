//Class for the game entity

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

    public void runGame(){
        //TODO
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
