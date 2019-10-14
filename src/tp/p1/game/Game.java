package tp.p1.game;

import java.util.*;
import tp.p1.ship.*;
import tp.p1.shipList.*;

public class Game {
	
	private int cycleCounter;
	private int puntuation;
	private Level level;
	private long seed;
	private boolean movement;
	private boolean abajo;

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
		this.setMovement(true);
		this.abajo = false;
		
		this.ucmShip = new UCMShip();
		this.regularShipList = new RegularShipList(this.getLevel());
		this.destroyerShipList = new DestroyerShipList(this.getLevel());
		this.bombList = new BombList(this.getLevel());
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
	public UCMShip getUCMShip() {
		return this.ucmShip;
	}
	public boolean getShockwaveUCM() {
		return this.ucmShip.getShockwave();
	}
	public Ovni getOvni() {
		return this.ovni;
	}
	public int getRand() {
		int num = rand.nextInt(10);
		return num;
	}
	public boolean getMovement() {
		return movement;
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
	public void setOvni(Ovni ovni) {
		 this.ovni = ovni;
	}
	public void setPlayerDefeated() {
		this.getUCMShip().setVida(0);
	}
	public void setMovement(boolean movement) {
		this.movement = movement;
	}
	
	public int getRemaining() {
		return this.getDestroyerShipList().getTam() + this.regularShipList.getTam();
	}
	
	public void createOvni() {
		if(!isOvni() && getRand() <= level.getOvni() * 10) {
			Ovni ovni = new Ovni();
			setOvni(ovni);
		}
	}
	public boolean isOvni() {
		if(this.ovni != null) return true;
		else return false;
	}
	
	public void moveBombs() {

		Bomb playerBomb = getBombList().getPos(getBombList().getTam() - 1);
		if( playerBomb != null) {
			boolean found = false;
			for(int i = 0; i < getBombList().getTam() - 1 && !found;i++) {
				if(getBombList().getPos(i) != null) {
					if(getBombList().getPos(i).getX() == playerBomb.getX() && getBombList().getPos(i).getY() == playerBomb.getY() - 1) {
						getBombList().destroyBomb(i);
						getBombList().destroyBomb(getBombList().getTam() - 1);
						found = true;
					}
				}
			}
			for(int i = 0; i < getDestroyerShipList().getTam() && !found; i++) {
				if(getDestroyerShipList().getPos(i).getX() == playerBomb.getX() && getDestroyerShipList().getPos(i).getY() == playerBomb.getY() - 1) {
					getDestroyerShipList().getPos(i).damage(this);
					getBombList().destroyBomb(getBombList().getTam() - 1);
					found = true;
				}
			}
			for(int i = 0; i < getRegularShipList().getTam() && !found; i++) {
				if(getRegularShipList().getPos(i).getX() == playerBomb.getX() && getRegularShipList().getPos(i).getY() == playerBomb.getY() - 1) {
					getRegularShipList().getPos(i).damage(this);
					getBombList().destroyBomb(getBombList().getTam() - 1);
					found = true;
				}
			}
			if(!found) {
				getBombList().getPos(getBombList().getTam() - 1).setY(playerBomb.getY() - 1);
				if(getBombList().getPos(getBombList().getTam() - 1).getY() < 0) {
					getBombList().insertIn(getBombList().getTam() - 1, null);
				}
			}
		}
		
		for( int i = 0; i < getBombList().getTam() - 1; i++) {
			if(getBombList().getPos(i) != null) {
				if(getBombList().getPos(i).getY() == 7 ) {
					if(getBombList().getPos(i).getX() == getUCMShip().getX()) getUCMShip().damage();
					getBombList().destroyBomb(i);
				}
				else {
					getBombList().getPos(i).setY(getBombList().getPos(i).getY() + 1);
				}
			}
		}
	}

	public void moveShips() {
		boolean found = false;
		int n = getRegularShipList().getTam();
		int m = getDestroyerShipList().getTam();
		for(int i = 0; i < n && !found; i++) {
			if(getRegularShipList().getPos(i).getX() == 8 || getRegularShipList().getPos(i).getX() == 0) {
				found = true;
			}
			if(i < m) {
				if(getDestroyerShipList().getPos(i).getX() == 8 || getDestroyerShipList().getPos(i).getX() == 0) {
					found = true;
				}
			}
		}
		if(getCycleCounter() % getLevel().getVel() == 0) {
			if(!found || abajo) {
				if(getMovement()) {
					for(int i = 0; i < n; i++) {
						getRegularShipList().getPos(i).setX(getRegularShipList().getPos(i).getX() + 1);
						if(i < m) {
							getDestroyerShipList().getPos(i).setX(getDestroyerShipList().getPos(i).getX() + 1);
						}
					}
				}
				else {
					for(int i = 0; i < n; i++) {
						getRegularShipList().getPos(i).setX(getRegularShipList().getPos(i).getX() - 1);
						if(i < m) {
							getDestroyerShipList().getPos(i).setX(getDestroyerShipList().getPos(i).getX() - 1);
						}
					}
				}
				if(abajo) abajo = false;
			}
			else {
				for(int i = 0; i < n; i++) {
					getRegularShipList().getPos(i).setY(getRegularShipList().getPos(i).getY() + 1);
					if(i < m) {
						getDestroyerShipList().getPos(i).setY(getDestroyerShipList().getPos(i).getY() + 1);
					}
				}
				if(!abajo)setMovement(!getMovement());
				abajo = true;
			}
		}
	}
	public void moveOvni() {
		if(isOvni()) {
			if(getOvni().getX() > 0) {
				getOvni().setX(getOvni().getX() - 1);
			}
			else setOvni(null);
		}
	}
	public boolean playerDefeated() {
		boolean found = false;
		int n = getRegularShipList().getTam();
		int m = getDestroyerShipList().getTam();
		for(int i = 0; i < n && !found; i++) {
			if(getRegularShipList().getPos(i).getY() == 7) found = true;
			if(i < m) {
				if(getDestroyerShipList().getPos(i).getY() == 7) found = true;
			}
		}
		if(this.getUCMShip().getVida() == 0 || found) return true;
		else return false;
	}
	public boolean enemyDefeated() {
		if(this.regularShipList.getTam() == 0 && this.getDestroyerShipList().getTam() == 0) return true;
		else return false;
	}
	public void printGameOver() {
		System.out.println("Game Over.");
	}
	public void printWin() {
		System.out.println("Win.");
	}
}
