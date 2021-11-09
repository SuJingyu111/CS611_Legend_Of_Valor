//Inventory class for both hero and market, implements Iterable<String>

import java.util.*;

public class Inventory implements Iterable<String>{

    Map<String, List<Item>> inventory;

    public Inventory() {
        inventory = new HashMap<>();
    }

    public void add(Item item) {
        List<Item> itemList = inventory.getOrDefault(item.getName(), new ArrayList<>());
        itemList.add(item);
        inventory.put(item.getName(), itemList);
    }

    public Item remove(String name) {
        if (inventory.containsKey(name)) {
            List<Item> itemList = inventory.get(name);
            Item item = itemList.remove(0);
            if (itemList.size() == 0) {
                inventory.remove(name);
            }
            return item;
        }
        return null;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (String name : inventory.keySet()) {
            Item item = inventory.get(name).get(0);
            res.append(item.toString());
            res.append("   X ").append(inventory.get(name).size());
            res.append("\n");
        }
        return res.toString();
    }

    public boolean ifContains(String name) {
        return inventory.containsKey(name);
    }

    public Item getItemByName(String name) {
        Item returnItem = null;
        if (inventory.containsKey(name)) {
            returnItem = inventory.get(name).get(0);
        }
        return returnItem;
    }

    @Override
    public Iterator<String> iterator() {
        return inventory.keySet().iterator();
    }
}
