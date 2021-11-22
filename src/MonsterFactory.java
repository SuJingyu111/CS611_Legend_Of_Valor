//Factory class that creates monsters

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.*;

public class MonsterFactory {

    private static MonsterFactory monsterFactory = new MonsterFactory();

    private int count = 0;

    private String[] monsterTypeArr = new String[]{"Dragons", "Spirits", "Exoskeletons"};

    public Monster produce() {
        Random rand = new Random();
        String type = monsterTypeArr[rand.nextInt(monsterTypeArr.length)];
        Monster mob = getMonsterByType(type);
        count += 1;
        mob.setIndex(count);
        return mob;
    }

    public static MonsterFactory getInstance(){
        return monsterFactory;
    }

    private Monster getMonsterByType(String monsterType) {
        try {
            Random rand = new Random();
            String fileName = monsterType + ".txt";
            BufferedReader br = Utils.getBufferedReaderByFileName(fileName);
            br.readLine();
            List<Monster> monsters = new ArrayList<>();
            String s;
            while((s = br.readLine())!=null){
                String[] parameters = s.split("\\s+");
                if (parameters.length > 1) {
                    Class<?> clazz = Class.forName(monsterType);
                    Constructor<?> cons = clazz.getConstructor(String[].class);
                    monsters.add((Monster) cons.newInstance((Object) parameters));
                }
            }
            br.close();
            return monsters.get(rand.nextInt(monsters.size()));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}
