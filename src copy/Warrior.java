//Class for warriors, inherits Hero

public class Warrior extends Hero {

    public Warrior(String[] args) {
        super(args);
    }

    public void levelUp() {
        super.levelUp();
        setStrength(getStrength() + (int)(getStrength() * LEVEL_UP_INCREASE));
        setAgility(getAgility() + (int)(getAgility() * LEVEL_UP_INCREASE));
    }
}
