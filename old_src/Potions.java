//Class for potions, inherits Item

public class Potions extends Item{

    private int attributeIncrease;

    String[] influenceAttributes;

    public Potions(String[] args) {
        setName(args[0]);
        setPrice(Integer.parseInt(args[1]));
        setMinHeroLevel(Integer.parseInt(args[2]));
        attributeIncrease = Integer.parseInt(args[3]);
        influenceAttributes = args[4].split("/");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(getName() + "  Minimum level: " + getMinHeroLevel() + "  price: " + getPrice() + "  increase: " + attributeIncrease + " on: ");
        for (String attribute : influenceAttributes) {
            sb.append(attribute).append("/");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    public void cure(Hero hero) {
        for (String attibute : influenceAttributes) {
            if (attibute.equals("Health")) {
                hero.setCurHp(hero.getCurHp() + attributeIncrease);
                System.out.println("Health increase by " + attributeIncrease);
            }
            else if (attibute.equals("Strength")) {
                hero.setStrength(hero.getStrength() + attributeIncrease);
                System.out.println("Strength increase by " + attributeIncrease);
            }
            else if (attibute.equals("Mana")) {
                hero.setCurMana(hero.getCurMana() + attributeIncrease);
                System.out.println("Health increase by " + attributeIncrease);
            }
            else if (attibute.equals("Agility")) {
                hero.setAgility(hero.getAgility() + attributeIncrease);
                System.out.println("Mana increase by " + attributeIncrease);
            }
            else if (attibute.equals("All")) {
                hero.setCurHp(hero.getCurHp() + attributeIncrease);
                System.out.println("Health increase by " + attributeIncrease);
                hero.setStrength(hero.getStrength() + attributeIncrease);
                System.out.println("Strength increase by " + attributeIncrease);
                hero.setCurMana(hero.getCurMana() + attributeIncrease);
                System.out.println("Mana increase by " + attributeIncrease);
                hero.setAgility(hero.getAgility() + attributeIncrease);
                System.out.println("Agility increase by " + attributeIncrease);
                hero.setDexterity(hero.getDexterity() + attributeIncrease);
                System.out.println("Dexterity increase by " + attributeIncrease);
                hero.setBaseDefense(hero.getBaseDefense() + attributeIncrease);
                System.out.println("Defense increase by " + attributeIncrease);
            }
        }
    }
}
