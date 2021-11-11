//Class for lightning spells, inherits Spell

public class LightningSpells extends Spell {

    private int baseDodgeChanceDeduction;

    public LightningSpells(String[] args) {
        super(args);
    }

    @Override
    public void castInfluence(Figure fig) {
        if (fig instanceof Monster) {
            ((Monster) fig).setDodgeChance((int)(((Monster) fig).getDodgeChance() * (1 - getDETEROIATE_RATE())));
        }
    }

    @Override
    public void printInfluenceMessage() {
        System.out.println("Enemy dodge chance is lower!");
    }
}
