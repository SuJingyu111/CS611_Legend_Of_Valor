//Class for FireSpells, inherits Spell

public class FireSpells extends Spell {

    public FireSpells(String[] args) {
        super(args);
    }

    @Override
    public void castInfluence(Figure fig) {
        fig.setBaseDefense((int)(fig.getBaseDefense() * (1 - getDETEROIATE_RATE())));
    }

    @Override
    public void printInfluenceMessage() {
        System.out.println("Enemy defense is lower!");
    }
}
