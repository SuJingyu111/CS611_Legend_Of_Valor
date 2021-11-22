//Class for paladin, inherits Hero

public class Paladin extends Hero {

    public Paladin(String[] args) {
        super(args);
    }

    public void levelUp() {
        super.levelUp();
        setDexterity(getDexterity() + (int)(getDexterity() * getLEVEL_UP_INCREASE()));
        setStrength(getStrength() + (int)(getStrength() * getLEVEL_UP_INCREASE()));
    }

}
