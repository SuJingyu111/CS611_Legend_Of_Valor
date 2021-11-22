//Class for warriors, inherits Hero

public class Warrior extends Hero {

    public Warrior(String[] args) {
        super(args);
    }

    public void levelUp() {
        super.levelUp();
        setStrength(getStrength() + (int)(getStrength() * getLEVEL_UP_INCREASE()));
        setAgility(getAgility() + (int)(getAgility() * getLEVEL_UP_INCREASE()));
    }
}
