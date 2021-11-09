//Class for weapons, inherits Item

public class Weaponry extends Item{

    private int damage;

    private int useHands;

    public Weaponry(String[] args) {
        setName(args[0]);
        setPrice(Integer.parseInt(args[1]));
        setMinHeroLevel(Integer.parseInt(args[2]));
        damage = Integer.parseInt(args[3]);
        useHands = Integer.parseInt(args[4]);
    }

    public String toString() {
        return getName() + "  Minimum level: " + getMinHeroLevel() + "  price: " + getPrice() + "  damage: " + damage + "  # of hands: " + useHands;
    }

    public int getDamage() {
        return damage;
    }
}
