//Class for the world the game is played in.

import java.util.Random;

public class World {

    private Cell[][] map;

    private int heroPosI, heroPosJ;

    private int dimension;

    private CellFactory cellFactory;

    public World(int dimension) {
        this.dimension = dimension;
        map = new Cell[dimension][dimension];
        cellFactory = new CellFactory();
    }

    public void placeHeroTeamInit() {
        Random rand = new Random();
        heroPosI = rand.nextInt(dimension);
        heroPosJ = rand.nextInt(dimension);
        Cell cell = cellFactory.getRandomAccessibleCell();
        while (cell instanceof InaccessibleCell) {
            cell = cellFactory.getRandomAccessibleCell();
        }
        map[heroPosI][heroPosJ] = cell;
        initSurrounding();
    }

    public boolean moveHeroTo(int i, int j) {
        if (heroPosI + i < 0 || heroPosI + i >= dimension || heroPosJ + j < 0 || heroPosJ + j >= dimension || map[heroPosI + i][heroPosJ + j] instanceof InaccessibleCell) {
            return false;
        }
        heroPosI += i;
        heroPosJ += j;
        initSurrounding();
        return true;
    }

    public void display() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            res.append("+--".repeat(dimension)).append("+\n");
            for (int j = 0; j < dimension; j++) {
                res.append('|');
                if (map[i][j] == null) {
                    res.append("  ");
                }
                else if (map[i][j] instanceof InaccessibleCell) {
                    res.append("I ");
                }
                else if (map[i][j] instanceof Market) {
                    res.append("M");
                    if (i == heroPosI && j == heroPosJ) {
                        res.append("H");
                    }
                    else {
                        res.append(" ");
                    }
                }
                else {
                    res.append("C");
                    if (i == heroPosI && j == heroPosJ) {
                        res.append("H");
                    }
                    else {
                        res.append(" ");
                    }
                }
            }
            res.append("|\n");
        }
        res.append("+--".repeat(dimension)).append("+\n");
        System.out.println(res);
    }
    public Cell getCurCell() {
        return map[heroPosI][heroPosJ];
    }

    private void initSurrounding() {
        boolean blocked = true;
        int up = Math.max(0, heroPosI - 1), down = Math.min(heroPosI + 1, dimension - 1);
        int left = Math.max(0, heroPosJ - 1), right = Math.min(heroPosJ + 1, dimension - 1);
        for (int i = up; i <= down; i++) {
            for (int j = left; j <= right; j++) {
                if (!(i == heroPosI && j == heroPosJ) && map[i][j] == null) {
                    Cell cell = cellFactory.getRandomAccessibleCell();
                    map[i][j] = cell;
                    if (! (cell instanceof InaccessibleCell)) {
                        blocked = false;
                    }
                }
            }
        }
        if (blocked) {
            Random rand = new Random();
            int unBlockI = rand.nextInt(down - up + 1) + up, unBlockJ = rand.nextInt(right - left + 1) + left;
            while (unBlockI == heroPosI && unBlockJ == heroPosJ) {
                unBlockI = rand.nextInt(down - up + 1) + up;
                unBlockJ = rand.nextInt(right - left + 1) + left;
            }
            Cell cell = cellFactory.getRandomAccessibleCell();
            if (cell instanceof InaccessibleCell) {
                cell = cellFactory.getRandomAccessibleCell();
            }
            map[unBlockI][unBlockJ] = cell;
        }
    }
}
