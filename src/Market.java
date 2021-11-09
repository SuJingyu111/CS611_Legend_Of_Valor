//Class for market cells, inherits Cell

import java.util.Random;

public class Market extends Cell {

    private static ItemFactory itemFactory;

    private Inventory inventory;

    private int money;

    private int INIT_MONEY_BOUND = 2000;

    private int MIN_ITEM_NUM = 3, MAX_ITEM_NUM = 10;

    public Market() {
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
        System.out.println("Market has money: " + money);
        System.out.println("Items: ");
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

    public void buy(Item item) {
        inventory.add(item);
        money -= item.getPrice();
    }
}
