public class MonsterNexus extends Cell implements FigrueGenerator{

    private static MonsterFactory monsterFactory;

    public MonsterNexus() {
        super('N');
        monsterFactory = MonsterFactory.getInstance();
    }

    @Override
    public Figure generate() {
        Monster monster = monsterFactory.produce();
        setMonster(monster);
        return monster;
    }
}
