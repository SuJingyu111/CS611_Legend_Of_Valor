//Class for general monsters, inherits Figure, inherited by Dragon, Exoskeleton, and Spirit

public class Monster extends Figure {

    private int bounty;

    private int expGain;

    private int dodgeChance;

    private int BOUNTY_PER_LEVEL = 100, EXP_GAIN_PER_LEVEL = 2, HP_PER_LEVEL = 100;

    public Monster(String[] args) {
        super();
        setName(args[0]);
        setLevel(Integer.parseInt(args[1]));
        setBaseDamage((int)(0.1 * Integer.parseInt(args[2])));
        setBaseDefense((int)(Integer.parseInt(args[3]) * 0.05));
        setDodgeChance(Integer.parseInt(args[4]));
        setBounty(BOUNTY_PER_LEVEL * getLevel());
        setExpGain(EXP_GAIN_PER_LEVEL * getLevel());
        setHp(HP_PER_LEVEL * getLevel());
    }

    public String toString() {
        return getName() + "   " + getLevel() + "   " +  getHp() + "   " + getBaseDamage() + "   " + getBaseDefense();
    }

    public int getBounty() {
        return bounty;
    }

    public void setBounty(int bounty) {
        this.bounty = bounty;
    }

    public int getExpGain() {
        return expGain;
    }

    public void setExpGain(int expGain) {
        this.expGain = expGain;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public String fightInfo() {
        return getName() + "  level: " + getLevel() + "  hp: " + getHp() + "  damage: " + getBaseDamage() + "  defense: " + getBaseDefense();
    }

    public int takeDamage(int damage) {
        int realDamage = Math.max(1, damage - getBaseDefense());
        setHp(getHp() - realDamage);
        return realDamage;
    }

    public boolean isDead() {
        return getHp() <= 0;
    }

    public void forward(World world) {
        world.putMonsterAt(getPosL(), getPosI() + 1, getPosJ(), this);
    }
}
