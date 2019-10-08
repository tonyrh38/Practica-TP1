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
		this(8, 4, level, seed);
	}
	
	public Game(int width, int height, Level level) {	
		this(width, height, level, System.currentTimeMillis());
	}
	
	public Game(int width, int height, Level level, long seed) {
		this.seed = seed;
		this.level = level;
		
		this.reset(width, height);
		
		this.gamePrinter = new ReleasePrinter(this);
	}
	
	public void reset() {
		reset(this.getWidth(), this.getHeight());
	}
	
	public void reset(int width, int height) {
		this.cycleCount = 0;
		
		this.rand = new Random(this.seed);		
		this.sunManager = new SunManager(this, this.rand, 50);
		this.zombieManager = new ZombieManager(this.level, this.rand);
		this.board = new Board(width, height, this.level.getMaxZombies());
	}

	
}
