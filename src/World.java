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
		Random rand = new Random();
		for (int i=0; i<dimension;i++){
			System.out.println("Hero types: " + heroFactory.getHeroTypeSet());
			System.out.println("Select the type of hero on Lane " + i + ": ");
			Hero hero= heroFactory.produce();
			setFigurePos(hero, 2 * i + 1, map[i].getROW() - 1, rand.nextInt(2));
			heroTeam.add(hero);
			map[2*i+1].putHeroAt(hero.getPosI(), hero.getPosJ(), hero);
		}
	}

	public void generateMonsters(MonsterTeam monsterTeam) {
		//System.out.println("generating monsters");
		Random rand = new Random();
		for (int i=0; i<dimension;i++){
			Monster monster = monsterFactory.produce();
			setFigurePos(monster, 2 * i + 1, 0, rand.nextInt(2));
			monsterTeam.add(monster);
			map[2*i+1].putMonsterAt(monster.getPosI(), monster.getPosJ(), monster);
		}
	}

	private void setFigurePos(Figure figure, int lane, int i, int j) {
		figure.setPosL(lane);
		figure.setPosI(i);
		figure.setPosJ(j);
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

	public Cell getCell(int lane, int r, int c) {
		return map[lane].getCell(r, c);
	}

	public int getROW() {
		return map[0].getROW();
	}

	public int getCOL() {
		return map[1].getCOL();
	}

	public boolean moveHeroBy(int di, int dj, Hero hero) {
		int lane = hero.getPosL(), i = hero.getPosI(), j = hero.getPosJ();
		//System.out.println(i + " " + j + " " + lane);
		int nextI = i + di, nextJ = j + dj;
		//System.out.println(nextI + " " + nextJ);
		if (nextJ < 0 || nextJ >= map[lane].COL || nextI < 0 || nextI >= map[lane].ROW) {
			System.out.println("Inaccessible!");
			return true;
		}
		if (map[lane].containMonsterInRow(i)) {
			System.out.println("Cannot sneak pass a monster!");
			return true;
		}
		boolean canMove = putHeroAt(lane, nextI, nextJ, hero);
		return canMove;
	}

	public boolean putHeroAt(int lane, int i, int j, Hero hero) {
		Cell nextCell = map[lane].getCell(i, j);
		if (nextCell.isOccupiedByAHero()) {
			System.out.println("Destination occupied by a hero!");
			return true;
		}
		nextCell.setHero(hero);
		if (nextCell instanceof HeroBoostable) {
			((HeroBoostable)nextCell).boostUponEnter(hero);
		}
		leaveCell(hero.getPosL(), hero.getPosI(), hero.getPosJ(), true);
		setFigurePos(hero, lane, i, j);
		return false;
	}

	public boolean putMonsterAt(int lane, int i, int j, Monster monster) {
		Cell nextCell = map[lane].getCell(i, j);
		if (nextCell.isOccupiedByAMonster()) {
			return true;
		}
		nextCell.setMonster(monster);
		leaveCell(monster.getPosL(), monster.getPosI(), monster.getPosJ(), false);
		setFigurePos(monster, lane, i, j);
		return false;
	}

	private void leaveCell(int lane, int i, int j, boolean isHero) {
		Cell curCell = map[lane].getCell(i, j);
		if (isHero) {
			if (curCell instanceof HeroBoostable) {
				((HeroBoostable)curCell).undoBoostWhenLeave();
			}
		}
		curCell.leave(isHero);
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public boolean behindMonster(int lane, int row) {
		Lane l = map[lane];
		for (int i = 0; i < row; i++) {
			if (l.containMonsterInRow(i)) {
				return false;
			}
		}
		return true;
	}

	public boolean rowIsFullOfHeroes(int lane, int row) {
		return map[lane].rowIsFullOfHeroes(row);
	}

	public boolean containsHeroInCell(int lane, int row, int col) {
		return getCell(lane, row, col).getHero() != null;
	}

	public boolean visitedCell(int lane, int row, int col) {
		return getCell(lane, row, col).isVisited();
	}

	public boolean visitedRow(int lane, int row) {
		return map[lane].visitedRow(row);
	}

	public void removeMonster(int lane, int i, int j) {
		map[lane].getCell(i, j).setMonster(null);
	}
}
