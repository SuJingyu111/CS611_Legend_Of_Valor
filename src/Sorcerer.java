//Class for sorcerer, inherits Hero

public class Sorcerer extends Hero{

    public Sorcerer(String[] args) {
        super(args);
    }

    public void levelUp() {
        super.levelUp();
        setDexterity(getDexterity() + (int)(getDexterity() * getLEVEL_UP_INCREASE()));
        setAgility(getAgility() + (int)(getAgility() * getLEVEL_UP_INCREASE()));
    }
}
