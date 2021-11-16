//Class for general cells in the map

public class Cell {

    private char icon;

    private Hero hero;

    private Monster monster;

    private int DISPLAY_ROW_NUM = 3;

    private boolean visited;

    public Cell (char icon) {
        this.icon = icon;
        hero = null;
        monster = null;
        visited = false;
    }

    public String[] toStringArrayByRow() {
        String[] stringArr = new String[DISPLAY_ROW_NUM];
        StringBuilder sb = new StringBuilder();
        sb.append(icon).append(" - ").append(icon).append(" - ").append(icon);
        stringArr[0] = sb.toString();
        sb.delete(0, sb.length());
        sb.append('|');
        fillContents(sb);
        sb.append("|");
        stringArr[1] = sb.toString();
        sb.delete(0, sb.length());
        sb.append(icon).append(" - ").append(icon).append(" - ").append(icon);
        stringArr[2] = sb.toString();
        return stringArr;
    }

    protected void fillContents(StringBuilder sb) {
        sb.append(' ');
        if (getHero() != null) {
            sb.append(hero.getMapName()).append(" ");
        }
        else {
            sb.append("   ");
        }
        if (getMonster() != null) {
            sb.append(monster.getMapName()).append(" ");
        }
        else {
            sb.append("   ");
        }
    }

    public boolean isOccupiedByAHero() {
        return hero != null;
    }

    public boolean isOccupiedByAMonster() {
        return monster != null;
    }

    public char getIcon() {
        return icon;
    }

    public void setIcon(char icon) {
        this.icon = icon;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getDISPLAY_ROW_NUM() {
        return DISPLAY_ROW_NUM;
    }

    public void setDISPLAY_ROW_NUM(int DISPLAY_ROW_NUM) {
        this.DISPLAY_ROW_NUM = DISPLAY_ROW_NUM;
    }
}
