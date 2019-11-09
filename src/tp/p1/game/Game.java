package tp.p1.game;

import java.util.*;
import tp.p1.ship.*;
import tp.p1.shipList.*;

public class Game {
	
	private int cycleCounter;
	private int puntuation;
	private int ovnisDestroyed;
	private Level level;
	private long seed;
	private boolean movement;
	private boolean down;

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
		this.setOvnisDestroyed(0);
		this.rand = new Random(this.seed);
		this.setMovement(true);
		this.down = false;
		
		this.ucmShip = new UCMShip();
		this.regularShipList = new RegularShipList(this.getLevel());
		this.destroyerShipList = new DestroyerShipList(this.getLevel());
		this.bombList = new BombList(this.getLevel());
		this.ovni = new Ovni();
	}
	
	public DestroyerShipList getDestroyerShipList() {
		return destroyerShipList;
	}
	public RegularShipList getRegularShipList() {
		return regularShipList;
	}
	public BombList getBombList() {
		return bombList;
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
	public int getOvnisDestroyed() {
		return this.ovnisDestroyed;
	}
	public int getVidaUCMShip() {
		return this.ucmShip.getVida();
	}
	public int getXUCMShip() {
		return this.ucmShip.getX();
	}
	public int getYUCMShip() {
		return this.ucmShip.getY();
	}
	public boolean getShockwaveUCMShip() {
		return this.ucmShip.getShockwave();
	}
	public int getVidaOvni() {
		return this.ovni.getVida();
	}
	public int getXOvni() {
		return this.ovni.getX();
	}
	public int getYOvni() {
		return this.ovni.getY();
	}
	public int getPuntOvni() {
		return this.ovni.getPuntos();
	}
	public double getFreqOvni() {
		return this.getLevel().getOvni();
	}
	public Random getRand() {
		return this.rand;
	}
	public boolean getMovement() {
		return movement;
	}
	public boolean getDown() {
		return this.down;
	}
	
	public void setCycleCounter(int cycleCounter) {
		this.cycleCounter = cycleCounter;
	}
	public void setPuntuation(int puntuation) {
		this.puntuation = puntuation;
	}
	public void calculatePuntuation() {
		int points = this.getDestroyerShipList().calculatePuntuation();
		points += this.getRegularShipList().calculatePuntuation();
		points += this.getPuntOvni() * this.getOvnisDestroyed();
		this.setPuntuation(points);
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public void setOvnisDestroyed(int num) {
		this.ovnisDestroyed = num;
	}
	public void setVidaUCMShip(int vida) {
		this.ucmShip.setVida(vida);
	}
	public void setXUCMShip(int x) {
		this.ucmShip.setX(x);
	}
	public void setShockwaveUCMShip(boolean sw) {
		this.ucmShip.setShockwave(sw);
	}
	public boolean getEnableOvni() {
		 return this.ovni.getEnable();
	}
	public void setEnableOvni(boolean set) {
		this.ovni.setEnable(set);
	}
	public void setXOvni(int x) {
		this.ovni.setX(x);
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public void damageOvni() {
		this.setEnableOvni(false);
		this.setOvnisDestroyed(this.getOvnisDestroyed() + 1);
		this.setShockwaveUCMShip(true);
	}
	public void damagePlayer() {
		this.ucmShip.damage();
	}
	public void setPlayerDefeated() {
		setVidaUCMShip(0);
	}
	public void setMovement(boolean movement) {
		this.movement = movement;
	}
	
	public int getRemaining() {
		return this.getDestroyerShipList().getDestroyerRemaining() + this.getRegularShipList().getRegularRemaining();
	}
	
	public void createOvni() {
		if(!getEnableOvni() && this.getRand().nextInt(10) <= this.getFreqOvni() * 10) {
			this.setEnableOvni(true);
		}
	}
	public boolean impactBombOvni(int x, int y) {
		if(getEnableOvni() && this.getXOvni() == x && this.getYOvni() == y) {
			this.damageOvni();
			return true;
		}
		else return false;
	}
	public void moveBombs() {		
		//Avanzar proyectil del jugador
		if (!this.getBombList().isPlayerBombNull()) {
			this.getBombList().movePlayerBomb();
			int x = this.getBombList().getXBombPlayer();
			int y = this.getBombList().getYBombPlayer();
			if(this.getDestroyerShipList().impactBomb(x, y) || this.getRegularShipList().impactBomb(x, y) ||
			this.impactBombOvni(x, y) || this.getBombList().impactBomb()){
				this.getBombList().destroyBomb(this.getBombList().getPosPlayer());
			}
		}
		//Avanzar bombas enemigas
		this.getBombList().moveShipBombs();
		int x = this.getXUCMShip();
		int y = this.getYUCMShip();
		if(this.getBombList().impactBombPlayer(x,y)) {
			this.damagePlayer();
		}
	}

	
	public void moveShips() {
		
		boolean found = this.getRegularShipList().isWall() || this.getDestroyerShipList().isWall();
		
		if(this.getCycleCounter() % this.getLevel().getVel() == 0) {
			if(found) {
				if(this.getDown()) {
					this.getRegularShipList().moveLateralShips(this.getMovement());
					this.getDestroyerShipList().moveLateralShips(this.getMovement());
					this.setDown(false);
				}
				else {
					this.getRegularShipList().moveDownShips();
					this.getDestroyerShipList().moveDownShips();
					this.setDown(true);
					this.setMovement(!this.getMovement());
				}
			}
			else {
				this.getRegularShipList().moveLateralShips(this.getMovement());
				this.getDestroyerShipList().moveLateralShips(this.getMovement());
			}
		}
	}
	public void moveOvni() {
		if(this.getEnableOvni()) {
			if(getXOvni() > 0) {
				setXOvni(getXOvni() - 1);
			}
			else setEnableOvni(false);
		}
	}
	public boolean playerDefeated() {
		if(this.getVidaUCMShip() <= 0 || this.getDestroyerShipList().DestroyershipWins() || this.getRegularShipList().RegularshipWins()) return true;
		else return false;
	}
	public boolean enemyDefeated() {
		if(this.getRegularShipList().getRegularRemaining() == 0 && this.getDestroyerShipList().getDestroyerRemaining() == 0) return true;
		else return false;
	}
	public String characterAtToString(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}
	public void update() {
		// TODO Auto-generated method stub
		
	}
	public void printGameOver() {
		// TODO Auto-generated method stub
		
	}
	public void printWin() {
		// TODO Auto-generated method stub
		
	}
}
