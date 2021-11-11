//A common cell with chances to create monsters

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommonCell extends Cell {

    private List<Monster> monsters;

    private static MonsterFactory monsterFactory;

    private int MEET_MONSTER_PROB = 4;

    public CommonCell() {
        monsters = new ArrayList<>();
        monsterFactory = MonsterFactory.getInstance();
    }

    public boolean rollDiceAndGenerateMonster(int monsterNum) {
        Random rand = new Random();
        monsters.clear();
        if (rand.nextInt(10) < MEET_MONSTER_PROB) {
            for (int i = 0; i < monsterNum; i++) {
                monsters.add(monsterFactory.produceNewMonster());
            }
            return true;
        }
        return false;
    }

    public boolean hasMonsters() {
        return monsters.size() > 0;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }
}
