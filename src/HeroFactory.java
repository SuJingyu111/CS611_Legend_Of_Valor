//Factory that creates heroes

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.*;

public class HeroFactory{

    private static HeroFactory heroFactory = new HeroFactory();

    private Map<String, List<Hero>> heroTypeMap;

    private int count;

    private String[] heroTypeArr = new String[]{"Warrior", "Sorcerer", "Paladin"};

    private HeroFactory() {
        heroTypeMap = new HashMap<>();
        populateHeroTypeMap();
        count = 0;
    }

    public static HeroFactory getInstance(){
        return heroFactory;
    }

    public Hero produce() {
        String type = getHeroTypeFromInput();
        return getHeroByType(type);
    }

    private Hero getHeroByType(String type) {
        List<Hero> heroes = heroTypeMap.get(type);
        showHeros(type);
        int heroIdx = selectHeroIdx(heroes.size());
        count += 1;
        Hero hero = heroes.remove(heroIdx - 1);
        hero.setIndex(count);
        return hero;
    }

    public Set<String> getHeroTypeSet() {
        return heroTypeMap.keySet();
    }

    private String getHeroTypeFromInput() {
        String input = Utils.takeInput();
        if (!heroTypeMap.containsKey(input)) {
            System.out.println("Invalid hero type! Try again.");
            return getHeroTypeFromInput();
        }
        else if (heroTypeMap.get(input).size() == 0) {
            System.out.println("No more heroes of this type! Try again.");
            return getHeroTypeFromInput();
        }
        else {
            return input;
        }
    }

    private void showHeros(String type) {
        List<Hero> heroes = heroTypeMap.get(type);
        System.out.println("Available Heroes: ");
        System.out.println("Name/mana/strength/agility/dexterity/starting money/starting experience");
        for (int j = 0; j < heroes.size(); j++) {
            System.out.println((j + 1) + " " + heroes.get(j).toString());
        }
    }

    private int selectHeroIdx(int upperBound) {
        System.out.println("Enter the index of the hero you want to hire: ");
        int index = Utils.takeInteger(0, upperBound);
        if (! (index > 0 && index <= upperBound)) {
            System.out.println("Out of bound! Try again");
            return selectHeroIdx(upperBound);
        }
        return index;
    }

    private void populateHeroTypeMap() {
        try {
            for (String heroType : heroTypeArr) {
                String fileName = heroType + "s" + ".txt";
                File file = new File(fileName);
                BufferedReader br = new BufferedReader(new FileReader(file));
                br.readLine();
                List<Hero> heroes = new ArrayList<>();
                heroTypeMap.put(heroType, heroes);
                String s = null;
                while((s = br.readLine())!=null){
                    String[] parameters = s.split("\\s+");
                    if (parameters.length > 1) {
                        Class<?> clazz = Class.forName(heroType);
                        Constructor<?> cons = clazz.getConstructor(String[].class);
                        heroes.add((Hero)cons.newInstance((Object) parameters));
                    }
                }
                br.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
