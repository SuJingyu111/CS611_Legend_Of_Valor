//Factory class that creates monsters

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.*;

public class MonsterFactory {

    private static MonsterFactory monsterFactory = new MonsterFactory();

    private String[] monsterTypeArr = new String[]{"Dragons", "Spirits", "Exoskeletons"};

    public Monster produce() {
        Random rand = new Random();
        String type = monsterTypeArr[rand.nextInt(monsterTypeArr.length)];
        return getMonsterByType(type);
    }

    public static MonsterFactory getInstance(){
        return monsterFactory;
    }

    private Monster getMonsterByType(String monsterType) {
        try {
            Random rand = new Random();
            String fileName = "src/" + monsterType + ".txt";
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            List<Monster> monsters = new ArrayList<>();
            String s = null;
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
