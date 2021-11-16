//Class for cells that are not accessible, inherits Cell

public class InaccessibleCell extends Cell{

    public InaccessibleCell() {
        super('I');
    }

    @Override
    protected void fillContents(StringBuilder sb) {
        sb.append(" X X X ");
    }

    @Override
    public void setHero(Hero hero) {
        throw new UnsupportedOperationException("Do not set Hero in InaccessibleCell!");
    }

    @Override
    public void setMonster(Monster monster) {
        throw new UnsupportedOperationException("Do not set Monster in InaccessibleCell!");
    }
}
