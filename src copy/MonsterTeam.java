import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MonsterTeam extends Team implements Iterable<Monster> {

    private List<Monster> monsters;

    public MonsterTeam() {
        monsters = new ArrayList<>();
    }

    @Override
    public boolean isSober() {
        int faintedCnt = 0;
        for (Monster mob : monsters) {
            if (mob.isDead()) {
                faintedCnt++;
            }
        }
        return faintedCnt != monsters.size();
    }

    @Override
    public void showTeam() {
        System.out.println("Members of the team: ");
        System.out.println("Name/lvl/hp/dmg/defense");
        int idx = 1;
        for (Monster mob : monsters) {
            System.out.println(idx++ + " " + mob);
        }
    }

    @Override
    public Iterator<Monster> iterator() {
        return monsters.iterator();
    }

    public Monster getMonsterByIdx(int index) {
        return monsters.get(index);
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    @Override
    public int size() {
        return monsters.size();
    }

    public void remove(Monster monster) {
        monsters.remove(monster);
    }
}
