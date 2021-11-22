//Class for Lanes

import java.util.Arrays;

public class Lane {

    protected int COL = 2;

    protected int ROW = 8;

    protected Cell[][] cells;

    protected CellFactory cellFactory;

    public Lane() {
        createNewCells(ROW, COL);
        cellFactory = new CellFactory();
        initialize();
    }

    protected void initialize() {
        for (int j = 0; j < COL; j++) {
                cells[0][j] = cellFactory.getMonsterNexus();
        }
        for (int i = 1; i < ROW - 1; i++) {
            for (int j = 0; j < COL; j++) {
                cells[i][j] = cellFactory.getRandomAccessibleCell();
            }
        }
        for (int j = 0; j < COL; j++) {
            cells[ROW - 1][j] = cellFactory.getHeroNexus();
        }
    }

    public boolean putHeroAt(int row, int col, Hero hero) {
       if (!Utils.checkInBound(row, ROW, col, COL)) {
           return false;
       }
       if (cells[row][col].isOccupiedByAHero()) {
           return false;
       }
       cells[row][col].setHero(hero);
       return true;
    }

    public boolean putMonsterAt(int row, int col, Monster monster) {
        if (!Utils.checkInBound(row, ROW, col, COL)) {
            return false;
        }
        if (cells[row][col].isOccupiedByAMonster()) {
            return false;
        }
        cells[row][col].setMonster(monster);
        return true;
    }

    public String[] toStringArrayByRow(int row) {
        //System.out.println(row);
        int displayRowNum = Cell.DISPLAY_ROW_NUM;
        String[] strArr = new String[displayRowNum];
        Arrays.fill(strArr, "");
        for (int j = 0; j < COL - 1; j++) {
            String[] cellStrArr = cells[row][j].toStringArrayByRow();
            for (int r = 0; r < displayRowNum; r++) {
                strArr[r] += cellStrArr[r];
                strArr[r] += "  ";
            }
        }
        if (cells[row][COL - 1] == null) {
            System.out.println("row " +  row + " " + (COL - 1) + "It is null");
        }
        String[] cellStrArr = cells[row][COL - 1].toStringArrayByRow();
        for (int r = 0; r < displayRowNum; r++) {
            strArr[r] += cellStrArr[r];
        }
        return strArr;
    }

    protected void createNewCells(int row, int col) {
        cells = new Cell[row][col];
    }

    public int getCOL() {
        return COL;
    }

    public void setCOL(int COL) {
        this.COL = COL;
    }

    public int getROW() {
        return ROW;
    }

    public void setROW(int ROW) {
        this.ROW = ROW;
    }
    
    public Cell[][] getCells() {
        return cells;
    }

    public boolean containMonsterInRow(int row) {
        for (int j = 0; j < COL; j++) {
            if (cells[row][j].getMonster() != null) {
                return true;
            }
        }
        return false;
    }

    public boolean containHeroInRow(int row) {
        for (int j = 0; j < COL; j++) {
            if (cells[row][j].getHero() != null) {
                return true;
            }
        }
        return false;
    }

    public Cell getCell(int r, int c) {
        return cells[r][c];
    }

    public boolean rowIsFullOfHeroes(int row) {
        for (int j = 0; j < COL; j++) {
            if (cells[row][j].getHero() == null) {
                return false;
            }
        }
        return true;
    }

    public boolean visitedRow(int row) {
        for (int j = 0; j < COL; j++) {
            if (cells[row][j].isVisited()) {
                return true;
            }
        }
        return false;
    }
}
