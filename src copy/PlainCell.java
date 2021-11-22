public class PlainCell extends Cell {

    public PlainCell() {
        super('P');
    }

    protected void fillContents(StringBuilder sb) {
        sb.append("       ");
    }

}
