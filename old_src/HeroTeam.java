//Team of heroes, implements Iterable<String>

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HeroTeam implements Iterable<Hero>{

    private List<Hero> heroes;

    private double RECOVER_RATE = 0.1;

    public HeroTeam() {
        heroes = new ArrayList<>();
    }

    @Override
    public Iterator<Hero> iterator() {
        return heroes.iterator();
    }

    public void addHero(Hero hero) {
        heroes.add(hero);
    }

    public boolean isSober() {
        int faintedCnt = 0;
        for (Hero hero : heroes) {
            if (hero.getCurHp() == 0) {
                faintedCnt++;
            }
        }
        return faintedCnt != heroes.size();
    }

    public void showTeam() {
        System.out.println("Members of the team: ");
        System.out.println("Name/hp/mana/strength/agility/dexterity/money/experience/hp");
        int idx = 1;
        for (Hero hero : heroes) {
            System.out.println(idx++ + " " + hero);
        }
    }

    public int size() {
        return heroes.size();
    }

    public Hero getHeroByIdx(int idx) {
        return heroes.get(idx);
    }

    public void recoverAll() {
        for (Hero hero : heroes) {
            hero.setCurHp(Math.min(hero.getHp(), (int)((1 + RECOVER_RATE) * hero.getCurHp())));
            hero.setCurMana(Math.min(hero.getMana(), (int)((1 + RECOVER_RATE) * hero.getCurMana())));
        }
    }

    public void lostPenalty() {
        for (Hero hero : heroes) {
            hero.setMoney(hero.getMoney() / 2);
        }
    }
}
