public abstract class RPG extends Game{
    //TODO

    private World world;

    private HeroTeam heroTeam;

    private MonsterTeam monsterTeam;

    protected abstract void runGame();

    protected abstract boolean ifPlayAgain();

    protected abstract void printEndingMsg();

    protected abstract void generateWorld();

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public HeroTeam getHeroTeam() {
        return heroTeam;
    }

    public void setHeroTeam(HeroTeam heroTeam) {
        this.heroTeam = heroTeam;
    }

    public MonsterTeam getMonsterTeam() {
        return monsterTeam;
    }

    public void setMonsterTeam(MonsterTeam monsterTeam) {
        this.monsterTeam = monsterTeam;
    }
}
