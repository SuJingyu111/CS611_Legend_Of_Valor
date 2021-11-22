//Class for cell type Koulou

public class Koulou extends Cell implements HeroBoostable{

    private int origStrength;

    private int ORIG_STRENGTH_INIT = -1;

    private double BOOST_RATE = 0.1;

    public Koulou() {
        super('C');
        origStrength = ORIG_STRENGTH_INIT;
    }

    @Override
    public void boostUponEnter(Hero hero) {
        setHero(hero);
        origStrength = hero.getStrength();
        hero.setStrength(origStrength + (int)(origStrength * BOOST_RATE));
    }

    @Override
    public void undoBoostWhenLeave() {
        getHero().setStrength(origStrength);
        setHero(null);
        origStrength = ORIG_STRENGTH_INIT;
    }

}
