//Class for the world the game is played in.

import java.util.Random;

public class World {

    private Lane[] map;
    
    private int dimension;
    
	private LaneFactory laneFactory;

	private int INACCESSIBLE_LANE = 0, ACCESSIBLE_LANE = 1;

	private HeroFactory heroFactory;

	private MonsterFactory monsterFactory;

    public World(int dimension) {
        this.dimension = dimension;
        map = new Lane[2*dimension+1];
		laneFactory = new LaneFactory();
		for (int i=0; i<dimension; i++){
			map[2*i]=laneFactory.getLane(INACCESSIBLE_LANE);
			map[2*i+1]=laneFactory.getLane(ACCESSIBLE_LANE);
		}
		map[2*dimension]=laneFactory.getLane(INACCESSIBLE_LANE);
		heroFactory = HeroFactory.getInstance();
		monsterFactory = MonsterFactory.getInstance();
    }

	public void placeHeroInit(HeroTeam heroTeam) {
		for (int i=0; i<dimension;i++){
			System.out.println("Hero types: " + heroFactory.getHeroTypeSet());
			System.out.println("Select the type of hero on Lane " + i + ": ");
			Hero hero= heroFactory.produce();
			setFigurePos(hero, i);
			heroTeam.addHero(hero);
			map[2*i+1].putHeroAt(hero.getPosI(), hero.getPosJ(), hero);
		}
	}

	public void generateMonsters(MonsterTeam monsterTeam) {
		for (int i=0; i<dimension;i++){
			Monster monster = monsterFactory.produce();
			setFigurePos(monster, i);
			monsterTeam.addMonster(monster);
			map[2*i+1].putMonsterAt(monster.getPosI(), monster.getPosJ(), monster);
		}
	}

	private void setFigurePos(Figure figure, int i) {
		Random rand = new Random();
		figure.setPosL(i+1);
		figure.setPosI(map[i].getROW() - 1);
		figure.setPosJ(rand.nextInt(2));
	}

    public void display(){
		for (int i = 0; i < map[0].getROW(); i++) {
			int length = 0;
			for (int j = 0; j < Cell.DISPLAY_ROW_NUM; j++) {
				StringBuilder sb = new StringBuilder();
				for (int k = 1; k < map.length - 1; k++) {
					sb.append(map[k].toStringArrayByRow(i)[j]);
					sb.append("  ");
				}
				System.out.println(sb);
				length = sb.length();
			}
			System.out.println(" ".repeat(length));
		}
	}

	public boolean monsterWon() {
		for (int i = 0; i < dimension; i++) {
			if (map[2 * i + 1].containMonsterInRow(map[0].ROW - 1)) {
				return true;
			}
		}
		return false;
	}

	public boolean heroWon() {
		for (int i = 0; i < dimension; i++) {
			if (map[2 * i + 1].containHeroInRow(0)) {
				return true;
			}
		}
		return false;
	}
}
