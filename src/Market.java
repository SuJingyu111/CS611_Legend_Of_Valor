//Class for market cells, inherits Cell

import java.util.Random;

public class Market extends Cell {

    private static ItemFactory itemFactory;

    private Inventory inventory;

    private int money;

    private int INIT_MONEY_BOUND = 2000;

    private int MIN_ITEM_NUM = 3, MAX_ITEM_NUM = 10;

    public Market() {
        super('M');
        init();
    }

    public Market(char icon) {
        super(icon);
        init();
    }

    private void init() {
        Random rand = new Random();
        money = rand.nextInt(INIT_MONEY_BOUND);
        itemFactory = ItemFactory.getInstance();
        inventory = new Inventory();
        populateInventory();
    }

    private void populateInventory() {
        Random rand = new Random();
        int itemCnt = rand.nextInt(MAX_ITEM_NUM - MIN_ITEM_NUM) + MIN_ITEM_NUM;
        for (int i = 0; i < itemCnt; i++) {
            inventory.add(itemFactory.getRandomItem());
        }
    }

    public void display() {
        System.out.println("\u001B[34mMarket has money: " + money);
        System.out.println("Items: \u001B[0m");
        System.out.println(inventory);
    }

    public boolean ifContains(String name) {
        return inventory.ifContains(name);
    }

    public void sold(String name) {
        Item soldItem = inventory.remove(name);
        money += soldItem.getPrice();
    }

    public Item getItemByName(String name) {
        return inventory.getItemByName(name);
    }

    public boolean hasEnoughMoney(int money) {
        return this.money >= money;
    }

    public void heroBuy(Hero hero) {
        while (true) {
            display();
            System.out.println(" ");
            System.out.println("\u001B[34mWhat do you want to buy? (q/Q to quit)\u001B[0m");
            String input = Utils.takeInput();
            if (input.equalsIgnoreCase("q")) {
                break;
            }
            if (ifContains(input)) {
                Item item = getItemByName(input);
                if (hero.getMoney() < item.getPrice()) {
                    System.out.println("Insufficient fund! Try again.");
                }
                else {
                    hero.setMoney(hero.getMoney() - item.getPrice());
                    sold(input);
                    item.setPrice(item.getPrice() / 2);
                    hero.addToInventory(item);
                    System.out.println(" ");
                    System.out.println("\u001B[34m" + input + " bought! \u001B[0m");
                    System.out.println(" ");
                }
            }
            else {
                System.out.println("Invalid item name! Try again!");
            }
        }
    }

    public void heroSell(Hero hero) {
        while (true) {
            hero.showInventory();
            System.out.println("\u001B[34mWhat do you want to sell? (q/Q to quit)\u001B[0m");
            String itemName = Utils.takeInput();
            while (!itemName.equalsIgnoreCase("q") && !hero.hasItem(itemName) && !hasEnoughMoney(hero.getItemByName(itemName).getPrice())) {
                if (!hero.hasItem(itemName)) {
                    System.out.println("Wrong item name, try again!");
                }
                else if (!hasEnoughMoney(hero.getItemByName(itemName).getPrice())){
                    System.out.println("Market does not have enough money! Try again! ");
                }
                itemName = Utils.takeInput();
            }
            if (itemName.equalsIgnoreCase("q")) {
                break;
            }
            Item item = hero.getItemByName(itemName);
            hero.soldItem(itemName);
            System.out.println("\u001B[34mSold " + itemName + "!\u001B[0m");
            buy(item);
        }
    }

    public void buy(Item item) {
        inventory.add(item);
        money -= item.getPrice();
    }
}
