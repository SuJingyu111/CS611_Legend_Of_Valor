//Interface for cells that can boost heroes

public interface HeroBoostable {

    public void boostUponEnter(Hero hero);

    public void undoBoostWhenLeave();
}
