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
		private void _checkShipMovement() {
			if((this._regularShipList.isOnWall() || this._destroyerShipList.isOnWall())) {
				if(this._down) {
					this._down = false;
					this._movement = !this._movement;
				}
				else {
					this._down = true;
				}
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
		public boolean impactBomb(int x, int y) {
			return this._ucmShip.impactBomb(x,y);
		}
		public void update() {
			this._ucmShip.updateLaser();
			this._bombList.update();
			if(this._cycleCounter % this._level.getVel() == 0) {
				this._checkShipMovement();
				this._regularShipList.update(this._movement, this._down);
				this._destroyerShipList.update(this._movement, this._down);
			}
			if (this._ovni != null) {
				this._ovni.update();
				if(this._ovni.isOvniIn(-1,0)) {
					this._ovni = null;
				}
			}
			this._cycleCounter++;
		}
		public void printWin() {
			System.out.println("Player wins.");
		}
		public void printGameOver() {
			System.out.println("Aliens win.");
		}
		public void setPlayerDefeated() {
			
		}
		public boolean playerDefeated() {
			return this._ucmShip.isDefeated() || this._regularShipList.win() || this._destroyerShipList.win();
		}
		public boolean enemyDefeated() {
			return this._regularShipList.lose() && this._destroyerShipList.lose();
		}
}
