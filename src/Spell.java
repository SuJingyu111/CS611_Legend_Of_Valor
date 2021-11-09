//Abstract class for spells, inherits Item, inherited by FireSpells, IceSpells, LightningSpells

public abstract class Spell extends Item{

    private int damage;

    private int manaCost;

    private double DETEROIATE_RATE = 0.1;

    public Spell(String[] args) {
        setName(args[0]);
        setPrice(Integer.parseInt(args[1]));
        setMinHeroLevel(Integer.parseInt(args[2]));
        damage = Integer.parseInt(args[3]);
        manaCost = Integer.parseInt(args[4]);
    }

    public String toString() {
        return getName() + "  Minimum level: " + getMinHeroLevel() + "  price: " + getPrice() + "  damage: " + damage + "  Mana Cost: " + manaCost;
    }

    public abstract void castInfluence (Figure fig);

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public double getDETEROIATE_RATE() {
        return DETEROIATE_RATE;
    }

    public abstract void printInfluenceMessage();
}
