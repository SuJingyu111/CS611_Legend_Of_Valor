//Class for general heroes, inherits Figure, inherited by Warrior, Paladin, and Sorcerer

import java.util.List;

public class Hero extends Figure {

    private int mana;

    private int strength;

    private int dexterity;

    private int agility;

    private int money;

    private int exp;

    private int nextLevelExp;

    private int dodgeChance;

    private Weaponry curWeaponry;

    private Armory curArmory;

    private boolean isFainted;

    private Inventory inventory;

    private boolean madeMove;

    private int curHp;

    private int curMana;

    private int NEXT_LEVEL_EXP_MULT = 10;

    private int INIT_LEVEL = 1;

    private int LEVEL_HP_MULTIPLY = 100;

    private int INIT_DAMAGE = 150;

    private int INIT_DEFENSE = 150;

    private double AGILITY_TO_DODGE = 0.2;

    protected double LEVEL_UP_INCREASE = 0.05;

    public Hero(String[] args) {
        //Name/mana/strength/agility/dexterity/starting money/starting experience
        super();
        inventory = new Inventory();
        setName(args[0]);
        setMana(Integer.parseInt(args[1]));
        setCurMana(getMana());
        setStrength(Integer.parseInt(args[2]));
        setAgility(Integer.parseInt(args[3]));
        setDexterity(Integer.parseInt(args[4]));
        setMoney(Integer.parseInt(args[5]));
        setExp(Integer.parseInt(args[5]));
        setNextLevelExp(NEXT_LEVEL_EXP_MULT * getLevel());
        setLevel(INIT_LEVEL);
        setHp(INIT_LEVEL * LEVEL_HP_MULTIPLY);
        setCurHp(getHp());
        setDodgeChance((int)(AGILITY_TO_DODGE * getAgility()));
        setBaseDamage(INIT_DAMAGE);
        setBaseDefense(INIT_DEFENSE);
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public String toString() {
        return getName() + "   " + getCurHp() + "   " +  getMana() + "   " + getStrength() + "   " + getAgility() + "   "
                + getDexterity() + "   " + getMoney() + "   " + getExp();
    }

    public void displayInventory() {
        System.out.println(inventory.toString());
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getNextLevelExp() {
        return nextLevelExp;
    }

    public void setNextLevelExp(int nextLevelExp) {
        this.nextLevelExp = nextLevelExp;
    }

    public Weaponry getCurWeapon() {
        return curWeaponry;
    }

    public void setCurWeapon(Weaponry curWeaponry) {
        this.curWeaponry = curWeaponry;
    }

    public Armory getCurArmor() {
        return curArmory;
    }

    public void setCurArmor(Armory curArmory) {
        this.curArmory = curArmory;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public int getCurHp() {
        return curHp;
    }

    public void setCurHp(int curHp) {
        this.curHp = curHp;
    }

    public boolean isSober() {
        return curHp > 0;
    }

    public int getDamage() {
        int damage = getStrength();
        if (curWeaponry != null) {
            damage += curWeaponry.getDamage();
        }
        damage *= 0.05;
        return damage;
    }

    public void showInventory() {
        System.out.println(inventory);
    }

    public boolean hasItem(String name) {
        return inventory.ifContains(name);
    }

    public boolean useItem(String name, MonsterTeam monsters) {
        Item item = inventory.getItemByName(name);
        if (item instanceof Armory) {
            Armory prevArmor = getCurArmor();
            setCurArmor((Armory) item);
            inventory.add(prevArmor);
            inventory.remove(name);
        }
        else if (item instanceof Weaponry) {
            Weaponry prevWeapon = getCurWeapon();
            setCurWeapon((Weaponry) item);
            inventory.add(prevWeapon);
            inventory.remove(name);
        }
        else if (item instanceof Spell) {
            int manaCost = ((Spell)item).getManaCost();
            if (getCurMana() < manaCost) {
                System.out.println("Not enough mana! Choose again!");
                return false;
            }
            else {
                if (monsters != null) {
                    setCurMana(getCurMana() - manaCost);
                    System.out.println("Choose your victim monster from : ");
                    showMonsters(monsters);
                    System.out.println("Enter index: ");
                    int idx = Utils.takeInteger(1, monsters.size());
                    Monster monster = monsters.getMonsterByIdx(idx);
                    int damageMade = monster.takeDamage(getSpellDamage(((Spell) item).getDamage()));
                    System.out.println("Hero " + getName() + " caused " + damageMade + " points of damage on " + monster.getName() + " with " + item.getName());
                    if (monster.isDead()) {
                        System.out.println("Monster " + monster.getName() + " is dead!");
                        monsters.remove(monster);
                    }
                    else {
                        ((Spell) item).castInfluence(monster);
                        ((Spell) item).printInfluenceMessage();
                    }
                }
                else {
                    System.out.println("Foolish! You wasted precious mana on nothing!");
                }
            }
        }
        else {
            ((Potions)item).cure(this);
        }
        return true;
    }

    private void showMonsters(MonsterTeam monsters) {
        int idx = 1;
        for (Monster monster : monsters) {
            System.out.println(idx++ + " " + monster.fightInfo());
        }
    }

    private int getSpellDamage(int spellBaseDamage) {
        return spellBaseDamage + (getDexterity() / 10000) * spellBaseDamage;
    }

    public int takeDamage(int damage) {
        int realDamage = damage - getBaseDefense();
        if (curArmory != null) {
            realDamage -= curArmory.getDefense();
        }
        realDamage = Math.max(1, realDamage);
        setCurHp(Math.max(getCurHp() - realDamage, 0));
        return realDamage;
    }

    public int getCurMana() {
        return curMana;
    }

    public void setCurMana(int curMana) {
        this.curMana = curMana;
    }

    public void incrementExp(int moreExp) {
        int exp = getExp() + moreExp;
        if (exp > getNextLevelExp()) {
            levelUp();
            setExp(exp - getNextLevelExp());
        }
    }

    protected void levelUp() {
        setLevel(getLevel() + 1);
        setNextLevelExp(getLevel() * NEXT_LEVEL_EXP_MULT);
        setMana(getMana() + (int)(getMana() * LEVEL_UP_INCREASE));
        setCurMana(getMana());
        setStrength(getStrength() + (int)(getStrength() * LEVEL_UP_INCREASE));
        setAgility(getAgility() + (int)(getAgility() * LEVEL_UP_INCREASE));
        setDexterity(getDexterity() + (int)(getDexterity() * LEVEL_UP_INCREASE));
        setNextLevelExp(NEXT_LEVEL_EXP_MULT * getLevel());
        setHp(getHp() + (int)(getHp() * LEVEL_UP_INCREASE));
        setCurHp(getHp());
        setDodgeChance((int)(AGILITY_TO_DODGE * getAgility()));
    }

    public void incrementMoney(int amount) {
        money += amount;
    }

    public void showFightingStat() {
        System.out.println("Hero " + getName() + " hp: " + getCurHp() + " Mana: " + getMana());
    }

    public Item getItemByName(String name) {
        return inventory.getItemByName(name);
    }

    public void soldItem(String name) {
        setMoney(getMoney() + inventory.getItemByName(name).getPrice());
        inventory.remove(name);
    }

    public boolean hasAnyItem() {
        return !inventory.isEmpty();
    }
}
