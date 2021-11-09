//Class for general cells in the map

public class Cell {

    public enum CellType {INACCESSIBLE, MARKET, NORMAL}

    private CellType type;

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

}
