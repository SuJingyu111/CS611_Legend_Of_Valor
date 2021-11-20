//Class for the world the game is played in.

import java.util.Random;

public class World {

    private Lane[] map;
    
    private int dimension;
    
	private LaneFactory laneFactory;

    public World(int dimension) {
        this.dimension = dimension;
        map = new Lane[2*dimension+1];
		laneFactory = new LaneFactory();
		for (int i=0; i<dimension; i++){
			map[2*i]=laneFactory.getLane(0);
			map[2*i+1]=laneFactory.getLane(1);
		}
		map[2*dimension]=laneFactory.getLane(0);
    }

	public void placeHeroInit() {
		Random rand = new Random();
		for (int i=0; i<dimension;i++){
			Hero hero= new Hero();
			hero.setHeroPosL(i+1);
			hero.setHeroPosI(8);
			hero.setHeroPosJ(rand.nextInt(2));
			map[2*i+1].putHeroAt(hero.getHeroPosI(), hero.getHeroPosJ(), hero);
		}
	}

    public void display(){
		StringBuilder str= new StringBuilder();
		for (int i=0; i<this.map[0].getCells().length; i++){
			for (int j=0; j<this.map.length; j++){
				str.append(this.map[j].toStringArrayByRow(i)+" ");
			}
			str.append("\n");
		}
		System.out.println(str);
	}
}
