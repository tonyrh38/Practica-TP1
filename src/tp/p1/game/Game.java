package tp.p1.game;

import java.util.*;
import tp.p1.ship.*;
import tp.p1.shipList.*;

public class Game {
	
	// Atributos
		static final int X_SIZE = 9;
		static final int Y_SIZE = 8;
	
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
		
	// Getters
		public int getX_SIZE() {
			return X_SIZE;
		}
		public int getY_SIZE() {
			return Y_SIZE;
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
		private int _getRemaining() {
			return this._destroyerShipList.getDestroyerRemaining() + this._regularShipList.getRegularRemaining();
		}
		private void _createOvni() {
			if(this._ovni == null && this._rand.nextInt(10) <= this._level.getOvni() * 10) {
				this._ovni = new Ovni(this);
			}
		}
	
		public String characterAtToString(int i, int j) {
			if(this._ucmShip.isPlayerIn(i,j)) {return this._ucmShip.toString();}
			else if(this._regularShipList.isShipIn(i,j)) {return this._regularShipList.shipInToString(i, j);}
			else if(this._destroyerShipList.isShipIn(i,j)) {return this._destroyerShipList.shipInToString(i,j);}
			else if(this._ovni != null && this._ovni.isOvniIn(i,j)) {return this._ovni.toString();}
			else if(this._ucmShip.isLaserIn(i,j)) {return this._ucmShip.laserToString();}
			else if(this._bombList.isBombIn(i,j)) {return this._bombList.bombInToString(i,j);}
			else return "";
		}
		public void draw() {
			GamePrinter gp = new GamePrinter(this, Y_SIZE, X_SIZE);
			
			System.out.print("Life: " + this._ucmShip.getVida());
			System.out.print("Number of cycles: " + this._cycleCounter);
			System.out.print("Points:" + this._puntuation);
			System.out.print("Remaining aliens: " + this._getRemaining());
			System.out.print("ShockWave: " + this._ucmShip.getShockwave());
			System.out.print(gp.toString());
		}
		public void insertBomb(Bomb bomb) {
			this._bombList.insert(bomb);
		}
		public void computerAction() {
			this._destroyerShipList.dropBombs(this._rand);
			this._createOvni();
		}
		public void updatePuntuation(int points) {
			this._puntuation+= points;
		}
		public boolean impactLaser(int x, int y) {
			if(this._regularShipList.impactLaser(x,y)) return true;
			else if(this._destroyerShipList.impactLaser(x,y)) return true;
			else if(this._bombList.impactLaser(x,y)) {
				this._destroyerShipList.destroyBombIn(x,y);
				return true;
			}
			else if(this._ovni.impactLaser(x,y)) {
				this._ovni = null;
				return true;
			}
			else return false;
		}
		public boolean impactBomb(int _x, int _y) {
			// TODO Auto-generated method stub
			return false;
		}
		public void update() {
			this._ucmShip.updateLaser();
			this._bombList.update();
			
		}
		public void printWin() {
			// TODO Auto-generated method stub
			
		}
		public void printGameOver() {
			// TODO Auto-generated method stub
			
		}
	public void setPlayerDefeated() {
		setVidaUCMShip(0);
	}
	public void setMovement(boolean movement) {
		this.movement = movement;
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
