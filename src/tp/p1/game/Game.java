package tp.p1.game;

import java.util.*;

import tp.p1.interfaces.IPlayerController;
import tp.p1.object.*;
import tp.p1.shipList.*;

public class Game implements IPlayerController{
	public final static int DIM_X = 9;
	public final static int DIM_Y = 8;

	private int currentCycle;
	private Random rand;
	private Level level;

	GameObjectBoard board;

	private UCMShip player;
	
	private boolean doExit;
	private BoardInitializer initializer;
	
	public Game (Level level, Random random){
		this.rand = random;
		this.level = level;
		initializer = new BoardInitializer();
		initGame();
	}
	
	public void initGame () {
		currentCycle = 0;
		board = initializer.initialize(this, level);
		player = new UCMShip(this, DIM_X / 2, DIM_Y - 1);
		board.add(player);
	}

	public Random getRandom() {
		return rand;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void reset() {
		initGame();
	}
	
	public void addObject(GameObject object) {
		board.add(object);
	}
	
	public String positionToString( /* coordinadas */ ) {
		return board.toString( /* coordinadas */ );

	public boolean isFinished() {
		return playerWin() || aliensWin() || doExit;
	}
	
	public boolean aliensWin() {
		return !player.isAlive() || AlienShip.haveLanded();
	}
	
	private boolean playerWin () {
		return AlienShip.allDead();
	}
	
	public void update() {
		board.computerAction();
		board.update();
		currentCycle += 1;
	}
	
	public boolean isOnBoard(int x, int y) {

		return /* condición de rango sobre las coordenadas */ ;
	}
	
	public void exit() {
		doExit = true;
	}
	
	public String infoToString() {
		return /* cadena estado-juego para imprimir junto con el tablero */; 
	}
	
	public String getWinnerMessage () {
		if (playerWin()) return "Player win!";
		else if (aliensWin()) return "Aliens win!";
		else if (doExit) return "Player exits the game";
		else return "This should not happen";
	}

	// TODO implementar los métodos del interfaz IPlayerController
	
	@Override
	public boolean move(int numCells) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shootLaser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shockWave() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void receivePoints(int points) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableShockWave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableMissile() {
		// TODO Auto-generated method stub
		
	}
}
/*public class Game {
	
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
			this.reset();
		}
		
	// Getters
		public int getX_SIZE() {
			return X_SIZE;
		}
		public int getY_SIZE() {
			return Y_SIZE;
		}
		
	// Logica
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
	
		public void reset() {
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
			else if(this._bombList.isBombIn(i,j)) {return this._bombList.bombInToString(i,j);}
			else return "";
		}
		public void draw() {
			GamePrinter gp = new GamePrinter(this, Y_SIZE, X_SIZE);
			
			System.out.println("Life: " + this._ucmShip.getVida());
			System.out.println("Number of cycles: " + this._cycleCounter);
			System.out.println("Points:" + this._puntuation);
			System.out.println("Remaining aliens: " + this._getRemaining());
			System.out.println("ShockWave: " + this._ucmShip.getShockwave());
			System.out.println(gp.toString());
		}
		public void move(int move, int num) {
			this._ucmShip.move(move,num);
		}
		public void shoot() {
			this._ucmShip.shoot();
		}
		public void damageAll() {
			this._regularShipList.shockwaveDamage();
			this._destroyerShipList.shockwaveDamage();
			this._ovni.shockwaveDamage();
			if(this._ovni.isDestroyed()) this._ovni = null;
			
		}
		public void shockwave() {
			this._ucmShip.shockwave();
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
		public boolean impactLaser(int x, int y, int damage) {
			if(this._regularShipList.impactLaser(x,y,damage)) return true;
			else if(this._destroyerShipList.impactLaser(x,y,damage)) return true;
			else if(this._bombList.impactLaser(x,y)) {
				this._destroyerShipList.destroyBombIn(x,y);
				return true;
			}
			else if(this._ovni != null && this._ovni.impactLaser(x,y,damage)) {
				if(this._ovni.isDestroyed()) this._ovni = null;
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
			this._ucmShip.setDefeat();
		}
		public boolean playerDefeated() {
			return this._ucmShip.isDefeated() || this._regularShipList.win() || this._destroyerShipList.win();
		}
		public boolean enemyDefeated() {
			return this._regularShipList.lose() && this._destroyerShipList.lose();
		}
}*/
