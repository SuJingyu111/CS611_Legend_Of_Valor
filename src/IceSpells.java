//Class for icespells

public class IceSpells extends Spell {

    private int baseDamageDeduction;

    public IceSpells(String[] args) {
        super(args);
    }

    @Override
    public void castInfluence(Figure fig) {
        fig.setBaseDamage((int)(fig.getBaseDamage() * (1 - getDETEROIATE_RATE())));
    }

    @Override
    public void printInfluenceMessage() {
        System.out.println("Enemy damage is lower!");
    }
}
