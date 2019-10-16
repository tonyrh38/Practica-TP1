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
	public int getVidaUCMShip() {
		return this.ucmShip.getVida();
	}
	public UCMShip getUCMShip() {
		return this.ucmShip;
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
	
	public void setCycleCounter(int cycleCounter) {
		this.cycleCounter = cycleCounter;
	}
	public void setPuntuation(int puntuation) {
		this.puntuation = puntuation;
	}
	public void calculatePuntuation() {
		int points = this.destroyerShipList.calculatePuntuation();
		points += this.regularShipList.calculatePuntuation();
		if(this.isOvni() && this.getVidaOvni() == 0) {
			points += this.getPuntOvni();
			this.setShockwaveUCMShip(true);
		}
		this.setPuntuation(points);
	}
	public void setLevel(Level level) {
		this.level = level;
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
	public void setOvni(Ovni ovni) {
		 this.ovni = ovni;
	}
	public void setXOvni(int x) {
		this.ovni.setX(x);
	}
	public void damageOvni() {
		this.ovni.damage();
	}
	public void setPlayerDefeated() {
		setVidaUCMShip(0);
	}
	public void setMovement(boolean movement) {
		this.movement = movement;
	}
	
	public int getRemaining() {
		return this.destroyerShipList.getDestroyerRemaining() + this.regularShipList.getRegularRemaining();
	}
	
	public void createOvni() {
		if(!this.isOvni() && this.getRand().nextInt(10) <= this.getFreqOvni() * 10) {
			Ovni ovni = new Ovni();
			this.setOvni(ovni);
		}
	}
	public boolean isOvni() {
		if(this.ovni != null) return true;
		else return false;
	}
	
	public void moveBombs() {
		/* Avanzar misil del jugador
		 * 	� Si choca con una nave o con un proyectil, actualizar vida
		 * Avanzar los proyectiles enemigos
		 * 	� Si impacta contra el jugador, actualizar vida
		 * Avanzar naves enemigas
		 * Avanzar ovni*/
		this.getBombList().movePlayerBomb();
		Bomb playerBomb = getBombList().getPos(getBombList().getTam() - 1);
		if(playerBomb != null) {
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
			for(int i = 0; i < this.destroyerShipList.getTam() && !found; i++) {
				if(this.destroyerShipList.getPos(i).getX() == playerBomb.getX() && this.destroyerShipList.getPos(i).getY() == playerBomb.getY() - 1) {
					this.destroyerShipList.getPos(i).damage();
					getBombList().destroyBomb(getBombList().getTam() - 1);
					found = true;
				}
			}
			for(int i = 0; i < this.regularShipList.getTam() && !found; i++) {
				if(this.regularShipList.getPos(i).getX() == playerBomb.getX() && this.regularShipList.getPos(i).getY() == playerBomb.getY() - 1) {
					this.regularShipList.getPos(i).damage();
					getBombList().destroyBomb(getBombList().getTam() - 1);
					found = true;
				}
			}
//	REDUNDANTE		if(!found) {
//				getBombList().getPos(getBombList().getTam() - 1).setY(playerBomb.getY() - 1);
//				if(getBombList().getPos(getBombList().getTam() - 1).getY() < 0) {
//					getBombList().destroyBomb(getBombList().getTam() - 1);
//				}
//			}
		}
		
		for( int i = 0; i < getBombList().getTam() - 1; i++) {
			if(getBombList().getPos(i) != null) {
				if(getBombList().getPos(i).getY() == 7 ) {
					if(getBombList().getPos(i).getX() == getXUCMShip()) this.ucmShip.damage();
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
		int n = this.regularShipList.getTam();
		int m = this.destroyerShipList.getTam();
		for(int i = 0; i < n && !found; i++) {
			if(this.regularShipList.getPos(i).getX() == 8 || this.regularShipList.getPos(i).getX() == 0) {
				found = true;
			}
			if(i < m) {
				if(this.destroyerShipList.getPos(i).getX() == 8 || this.destroyerShipList.getPos(i).getX() == 0) {
					found = true;
				}
			}
		}
		if(getCycleCounter() % getLevel().getVel() == 0) {
			if(!found || abajo) {
				if(getMovement()) {
					for(int i = 0; i < n; i++) {
						this.regularShipList.getPos(i).setX(this.regularShipList.getPos(i).getX() + 1);
						if(i < m) {
							this.destroyerShipList.getPos(i).setX(this.destroyerShipList.getPos(i).getX() + 1);
						}
					}
				}
				else {
					for(int i = 0; i < n; i++) {
						this.regularShipList.getPos(i).setX(this.regularShipList.getPos(i).getX() - 1);
						if(i < m) {
							this.destroyerShipList.getPos(i).setX(this.destroyerShipList.getPos(i).getX() - 1);
						}
					}
				}
				if(abajo) abajo = false;
			}
			else {
				for(int i = 0; i < n; i++) {
					this.regularShipList.getPos(i).setY(this.regularShipList.getPos(i).getY() + 1);
					if(i < m) {
						this.destroyerShipList.getPos(i).setY(this.destroyerShipList.getPos(i).getY() + 1);
					}
				}
				if(!abajo)setMovement(!getMovement());
				abajo = true;
			}
		}
	}
	public void moveOvni() {
		if(isOvni()) {
			if(getXOvni() > 0) {
				setXOvni(getXOvni() - 1);
			}
			else setOvni(null);
		}
	}
	public boolean playerDefeated() {
		if(this.getVidaUCMShip() == 0 || this.destroyerShipList.DestroyershipWins() || this.regularShipList.RegularshipWins()) return true;
		else return false;
	}
	public boolean enemyDefeated() {
		if(this.regularShipList.getRegularRemaining() == 0 && this.destroyerShipList.getDestroyerRemaining() == 0) return true;
		else return false;
	}
}
