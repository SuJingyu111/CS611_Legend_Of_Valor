//Class for lanes composed of inaccessible cells.

public class InaccessibleLane extends Lane {

    private int INACCESSIBLE_COL = 1;

    public InaccessibleLane() {
        super();
        setCOL(INACCESSIBLE_COL);
    }

    @Override
    protected void initialize() {
        for (int i = 0; i < ROW ; i++) {
            for (int j = 0; j < COL; j++) {
                cells[i][j] = cellFactory.getInaccessibleCell();
            }
        }
    }
}
