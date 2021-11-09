//Class for general items, inherited by Armory, Spell, Potion, Weaponry

public class Item {

    private String name;

    private int price;

    private int minHeroLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMinHeroLevel() {
        return minHeroLevel;
    }

    public void setMinHeroLevel(int minHeroLevel) {
        this.minHeroLevel = minHeroLevel;
    }

}
