package tp.practica1Game;

import java.util.Random;

import tp.practica1Controller.IPlayerController;
import tp.practica1GameObjects.GameObject;
import tp.practica1GameObjects.GameObjectBoard;
import tp.practica1Ships.AlienShip;
import tp.practica1Ships.UCMShip;


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
	
	public String positionToString( Coordinates coord ) {
		return board.toString( coord );
	}

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
	
	public boolean isOnBoard(Coordinates coord ) {
		//Comprobamos que está dentro de nuestro "tablero"
		if ((coord.getX()<DIM_X && coord.getX()>= 0) && (coord.getY()<DIM_Y && coord.getY()>=0)){
			return true;
		}
		else
			return false;
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