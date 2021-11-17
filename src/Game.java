public abstract class Game {
    //TODO
    public void play(){
        do {
            runGame();
        }
        while (ifPlayAgain());
        printEndingMsg();
    }

    protected abstract void runGame();

    protected abstract boolean ifPlayAgain();

    protected abstract void printEndingMsg();
}
