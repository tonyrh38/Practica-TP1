package tp.p1.game;

import java.util.*;
import tp.p1.ship.*;
import tp.p1.shipList.*;

public class Game {
	
	private int cycleCounter;
	private int puntuation;
	private Level level;
	private long seed;

	private UCMShip ucmShip;
	private RegularShipList regularShipList;
	private DestroyerShipList destroyerShipList;
	private BombList bombList;
	private Ovni ovni;
	
	private Random rand;
	
	public Game(Level level) {
		this(level, System.currentTimeMillis());
	}
	public Game(Level level, long seed) {
		this.setLevel(level);
		this.seed = seed;
		this.reset();
	}
	public void reset() {
		this.setCycleCounter(0);
		this.setPuntuation(0);
		this.rand = new Random(this.seed);
		
		this.ucmShip = new UCMShip();
		this.regularShipList = new RegularShipList(this.getLevel());
		this.destroyerShipList = new DestroyerShipList(this.getLevel());
		this.bombList = new BombList();
	}
	
	public int getCycleCounter() {
		return cycleCounter;
	}
	public int getPuntuation() {
		return puntuation;
	}
	public Level getLevel() {
		return level;
	}
	public int getVidaUCM() {
		return this.ucmShip.getVida();
	}
	public int getDañoUCM() {
		return this.ucmShip.getDaño();
	}
	public boolean getShockwaveUCM() {
		return this.ucmShip.getShockwave();
	}
	
	public void setCycleCounter(int cycleCounter) {
		this.cycleCounter = cycleCounter;
	}
	public void setPuntuation(int puntuation) {
		this.puntuation = puntuation;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public void setVidaUCM(int vida) {
		this.ucmShip.setVida(vida);
	}
	public void setShockwaveUCM(boolean sw) {
		this.ucmShip.setShockwave(sw);
	}
	
	public boolean playerDefeated() {
		if(this.getVidaUCM() == 0) return true;
		else return false;
	}
	public boolean enemyDefeated() {
		if(this.regularShipList.getTam() == 0 && this.destroyerShipList.getTam() == 0) return true;
		else return false;
	}
	
	public String characterAtToString(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*public void reset(int width, int height) {
		this.sunManager = new SunManager(this, this.rand, 50);
		this.zombieManager = new ZombieManager(this.level, this.rand);
		this.board = new Board(width, height, this.level.getMaxZombies());
	}*/

	
}
