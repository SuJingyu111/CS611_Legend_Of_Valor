//Class for general cells in the map

public class Cell {

    private char icon;

    private Hero hero;

    private Monster monster;

    public static final int DISPLAY_ROW_NUM = 3;

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
            //System.out.println(hero);
            sb.append(hero.getMapName()).append(" ");
        }
        else {
            sb.append("   ");
        }
        if (getMonster() != null) {
            //System.out.println("monster!");//TODO: DELETE THIS
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
        setVisited(true);
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

    public void leave(boolean isHero) {
        if (isHero) {
            setHero(null);
        }
        else {
            setMonster(null);
        }
    }

}
