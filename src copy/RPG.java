public abstract class RPG extends Game{
    //TODO

    private World world;

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
}
