//Class for general figures, inherited by Monster and Hero

public class Figure {

    private String name;

    private int hp;

    private int level;

    private int baseDamage;

    private int baseDefense;

    private String mapName;

    private int index;

    public Figure() {
        this.index = 0;
        if (this instanceof Hero) {
            mapName = "H" + this.index;
        }
        else if (this instanceof Monster) {
            mapName = "M" + this.index;
        }
    }

    public void statusDisplay() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public void setBaseDefense(int baseDefense) {
        this.baseDefense = baseDefense;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        StringBuilder sb = new StringBuilder(mapName);
        sb.setCharAt(sb.length() - 1, (char)('0' + index));
        setMapName(sb.toString());
    }
}
