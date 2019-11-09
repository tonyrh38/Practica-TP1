package tp.p1.game;

import java.util.*;
import tp.p1.ship.*;
import tp.p1.shipList.*;

public class Game {
	
	// Atributos
		private static int X_SIZE = 9;
		private static int Y_SIZE = 8;
	
		private int _cycleCounter;
		private int _puntuation;
		private Level _level;
		private long _seed;
		private Random _rand;
		
		private boolean _movement;
		private boolean _down;
	
		private UCMShip _ucmShip;
		private RegularShipList _regularShipList;
		private DestroyerShipList _destroyerShipList;
		private BombList _bombList;
		private Ovni _ovni;
	
	// Metodos
	
	// Constructores
		public Game(Level level) {
			this(level, System.currentTimeMillis());
		}
		public Game(Level level, long seed) {
			this._level = level;
			this._seed = seed;
			this._reset();
		}
	// Logica
		private void _reset() {
			this._cycleCounter = 0;
			this._puntuation = 0;
			this._rand = new Random(this._seed);
			
			this._movement = true;
			this._down = false;
			
			this._ucmShip = new UCMShip(this);
			this._regularShipList = new RegularShipList(this._level, this);
			this._destroyerShipList = new DestroyerShipList(this._level, this);
			this._bombList = new BombList(this._level);
		}
		
		public String characterAtToString(int i, int j) {
			if(this._ucmShip.isPlayerIn(i,j)) {return this._ucmShip.toString();}
			else if(this._regularShipList.isShipIn(i,j)) {return this._regularShipList.shipInToString(i, j);}
			else if(this._destroyerShipList.isShipIn(i,j)) {return this._destroyerShipList.shipInToString(i,j);}
			else if(this._ovni != null && this._ovni.isOvniIn(i,j)) {return this._ovni.toString();}
			else if(this._ucmShip.isLaserIn(i,j)) {return this._ucmShip.laserToString();}
			else if(this._)
			else return "";
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
		public void printWin() {
			// TODO Auto-generated method stub
			
		}
		public void printGameOver() {
			// TODO Auto-generated method stub
			
		}
	public void setShockwaveUCMShip(boolean sw) {
		this._ucmShip.setShockwave(sw);
	}
	public boolean getEnableOvni() {
		 return this._ovni.getEnable();
	}
	public void setEnableOvni(boolean set) {
		this._ovni.setEnable(set);
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
}
