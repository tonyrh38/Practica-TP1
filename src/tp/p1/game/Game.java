package tp.p1.game;

import java.util.*;
import tp.p1.shipList.*;

public class Game {
	
	private int cycleCounter;
	private int puntuation;
	private Level level;
	private long seed;

	
	private RegularShipList regularShipList;
	private DestroyerShipList destroyerShipList;
	private BombList bombList;
	
	private Random rand;
	
	public Game(Level level) {
		this(level, System.currentTimeMillis());
	}
	public Game(Level level, long seed) {
		this.level = level;
		this.seed = seed;
		this.reset();
	}
	public void reset() {
		this.cycleCounter = 0;
		this.puntuation = 0;
		this.rand = new Random(this.seed);
		
		this.regularShipList = new RegularShipList();
		this.destroyerShipList = new DestroyerShipList();
		this.bombList = new BombList();
	}
	
	/*public void reset(int width, int height) {
		this.sunManager = new SunManager(this, this.rand, 50);
		this.zombieManager = new ZombieManager(this.level, this.rand);
		this.board = new Board(width, height, this.level.getMaxZombies());
	}*/

	
}
