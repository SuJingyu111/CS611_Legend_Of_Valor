//Class for paladin, inherits Hero

public class Paladin extends Hero {

    public Paladin(String[] args) {
        super(args);
    }

    public void levelUp() {
        super.levelUp();
        setDexterity(getDexterity() + (int)(getDexterity() * LEVEL_UP_INCREASE));
        setStrength(getStrength() + (int)(getStrength() * LEVEL_UP_INCREASE));
    }

}
