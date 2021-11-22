// Class for Armors.

public class Armory extends Item{

    private int defense;

    public Armory(String[] args) {
        setName(args[0]);
        setPrice(Integer.parseInt(args[1]));
        setMinHeroLevel(Integer.parseInt(args[2]));
        defense = Integer.parseInt(args[3]);
    }

    public String toString() {
        return getName() + "  Minimum level: " + getMinHeroLevel() + "  price: " + getPrice() + "  defense: " + defense;
    }

    public int getDefense() {
        return defense;
    }
}
