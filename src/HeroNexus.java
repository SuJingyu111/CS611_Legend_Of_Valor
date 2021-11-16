public class HeroNexus extends Market implements FigrueGenerator{

    private static HeroFactory heroFactory;

    public HeroNexus() {
        super('N');
        heroFactory = HeroFactory.getInstance();
    }

    @Override
    public Figure generate() {
        Hero hero = heroFactory.produce();
        setHero(hero);
        return hero;
    }
}
