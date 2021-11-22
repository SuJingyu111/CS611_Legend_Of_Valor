//A factory that generates difference kinds of cells

import java.util.Random;

public class CellFactory {

    public Cell getRandomAccessibleCell() {
        Random rand = new Random();
        int randInt = rand.nextInt(10);
        if (randInt < 2) {
            return new Bush();
        }
        else if (randInt < 4) {
            return new Cave();
        }
        else if (randInt < 6){
            return new Koulou();
        }
        else {
            return new PlainCell();
        }
    }

    public InaccessibleCell getInaccessibleCell() {
        return new InaccessibleCell();
    }

    public HeroNexus getHeroNexus() {
        return new HeroNexus();
    }

    public MonsterNexus getMonsterNexus() {
        return new MonsterNexus();
    }
}
