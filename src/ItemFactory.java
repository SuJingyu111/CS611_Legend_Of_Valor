//Factory class that creates all kinds of items

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.*;

public class ItemFactory {

    private static ItemFactory itemCategory = new ItemFactory();

    String[] itemTypes = new String[] {"Armory", "FireSpells", "IceSpells", "LightningSpells", "Potions", "Weaponry"};

    Set<String> items;

    private ItemFactory() {
        items = new HashSet<>(Arrays.asList(itemTypes));
    }

    public static ItemFactory getInstance(){
        return itemCategory;
    }

    public Item getRandomItem() {
        Random rand = new Random();
        String itemType = itemTypes[rand.nextInt(itemTypes.length)];
        List<String[]> itemParaList = new ArrayList<>();
        String fileName = "src/" + itemType + ".txt";
        Item item = null;
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            String s = null;
            while((s = br.readLine()) != null){
                String[] parameters = s.split("\\s+");
                if (parameters.length > 1) {
                    itemParaList.add(parameters);
                }
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        int idx = rand.nextInt(itemParaList.size());
        try {
            Class<?> clazz = Class.forName(itemType);
            Constructor<?> cons = clazz.getConstructor(String[].class);
            item = (Item) cons.newInstance((Object) itemParaList.get(idx));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        //System.out.println(item.getName());
        return item;
    }
}
