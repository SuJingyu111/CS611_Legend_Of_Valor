//Class for bush cells

public class Bush extends Cell implements HeroBoostable {

    private int origDexterity;

    private int ORIG_DEXTERITY_INIT = -1;

    private double BOOST_RATE = 0.1;

    public Bush() {
        super('B');
        origDexterity = ORIG_DEXTERITY_INIT;
    }

    @Override
    public void boostUponEnter(Hero hero) {
        setHero(hero);
        origDexterity = hero.getDexterity();
        hero.setDexterity(origDexterity + (int)(origDexterity * BOOST_RATE));
    }

    @Override
    public void undoBoostWhenLeave() {
        getHero().setDexterity(origDexterity);
        setHero(null);
        origDexterity = ORIG_DEXTERITY_INIT;
    }
}
