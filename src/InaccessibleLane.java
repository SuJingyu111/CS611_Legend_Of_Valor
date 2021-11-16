public class InaccessibleLane extends Lane {

    private int INACCESSIBLE_COL = 1;

    public InaccessibleLane() {
        setCOL(INACCESSIBLE_COL);
        createNewCells(ROW, COL);
        cellFactory = new CellFactory();
        initialize();
    }

    @Override
    protected void initialize() {
        for (int i = 1; i < ROW ; i++) {
            for (int j = 0; j < COL; j++) {
                cells[i][j] = cellFactory.getInaccessibleCell();
            }
        }
    }
}
