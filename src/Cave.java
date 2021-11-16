public class Cave extends Cell implements HeroBoostable{

    private int origAgility;

    private int ORIG_AGILITY_INIT = -1;

    private double BOOST_RATE = 0.1;

    public Cave() {
        super('C');
        origAgility = ORIG_AGILITY_INIT;
    }

    @Override
    public void boostUponEnter(Hero hero) {
        setHero(hero);
        origAgility = hero.getAgility();
        hero.setAgility(origAgility + (int)(origAgility * BOOST_RATE));
    }

    @Override
    public void undoBoostWhenLeave() {
        getHero().setAgility(origAgility);
        setHero(null);
        origAgility = ORIG_AGILITY_INIT;
    }

}
