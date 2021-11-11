//A factory that generates difference kinds of cells

import java.util.Random;

public class CellFactory {

    public Cell getCell() {
        Random rand = new Random();
        int randInt = rand.nextInt(10);
        if (randInt < 2) {
            return new InaccessibleCell();
        }
        else if (randInt < 5) {
            return new Market();
        }
        else {
            return new CommonCell();
        }
    }
}
