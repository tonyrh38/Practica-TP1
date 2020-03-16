package invaders.game;

import java.util.*;

import invaders.exceptions.CommandExecuteException;
import invaders.interfaces.IPlayerController;
import invaders.model.*;

public class Game implements IPlayerController{
	
	public final static int DIM_X = 9;
	public final static int DIM_Y = 8;

	private int currentCycle;
	private Random rand;
	private Level level;

	GameObjectBoard board;
	private UCMShip player;
	
	private boolean movement;
	private boolean down;
	
	private boolean doExit;
	private BoardInitializer initializer;
	
	public Game (Level level, Random random){
		this.rand = random;
		this.level = level;
		this.initializer = new BoardInitializer();
		initGame();
		this.movement = true;
		this.down = false;
	}
	
	public void initGame (){
		this.currentCycle = 0;
		this.board = this.initializer.initialize(this, level);
		this.player = new UCMShip(this, DIM_X / 2, DIM_Y - 1);
		this.board.add(player);
	}

	public Random getRandom() {
		return rand;
	}
	
	public int getCurrentCycle() {
		return this.currentCycle;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public boolean getMovement() {
		return this.movement;
	}
	
	public void reset() {
		initGame();
	}
	
	public void addObject(GameObject object) {
		board.add(object);
	}
	
	public String positionToString(int x, int y) {
		return board.toString(x,y);
	}

	public boolean isFinished() {
		return playerWin() || aliensWin() || doExit;
	}
	
	public boolean canMove() {
		return this.currentCycle % this.level.getVel() == 0;
	}
	
	public boolean aliensWin() {
		return !player.isAlive() || this.board.aliensLanded();
	}
	
	private boolean playerWin() {
		return this.board.aliensDead();
	}
	
	private void checkShipMovement() {
		if(this.board.isOnWall()) {
			if(this.down) {
				this.down = false;
				this.movement = !this.movement;
			}
			else {
				this.down = true;
			}
		}
	}
	public void update() {
		board.computerAction();
		this.checkShipMovement();
		board.update(this.down,this.movement);
		currentCycle += 1;
	}
	
	public boolean isOnBoard(int x, int y) {
		return x >= 0 && x < DIM_X && y >= 0 && y < DIM_Y;
	}
	
	public void exit() {
		doExit = true;
	}
	
	public String characterAtToString(int x, int y) {
		if(this.player.isOnPosition(x, y)) return this.player.toString();
		else return this.board.objectAtToString(x,y);
	}
	public String characterAtToSerialize(int x, int y) {
		if(this.player.isOnPosition(x, y)) return this.player.toSerialize();
		else return this.board.objectAtToSerialize(x,y);
	}
	
	public void infoToString() {
		System.out.println("Life: " + this.player.getLive());
		System.out.println("Number of cycles: " + this.currentCycle);
		System.out.println("Points:" + this.player.getTotalPuntuation());
		System.out.println("Remaining aliens: " + this.board.aliveAliens());
		System.out.println(this.player.shockwaveToString());
		System.out.println("Supermisiles: " + this.player.getNumSupermisile());
	}
	
	public String getWinnerMessage() {
		if (playerWin()) return "Player win!";
		else if (aliensWin()) return "Aliens win!";
		else if (doExit) return "Player exits the game";
		else return "This should not happen";
	}
	
	@Override
	public void move(int numCells) throws CommandExecuteException{
		this.player.move(numCells);
	}

	@Override
	public void shootLaser() throws CommandExecuteException{
		this.player.shoot();
	}

	public void shootSupermisile() throws CommandExecuteException{
		this.player.shootSupermisile();
	}
	
	public void shockwaveAttack(int damage) {
		this.board.shockwaveAttack(damage);
	}
	
	@Override
	public void shockWave() throws CommandExecuteException{
		this.player.shockwaveAttack();
	}
	
	@Override
	public void buy() throws CommandExecuteException {
		if(this.player.getTotalPuntuation() >= 20) {
			this.receivePoints(-20);
			this.player.addSupermisile();
		}
		else throw new CommandExecuteException("No tienes suficientes puntos");
	}

	@Override
	public void receivePoints(int points) {
		this.player.updatePoints(points);	
	}

	@Override
	public void enableShockWave() {
		this.player.enableShockwave();
	}

	@Override
	public void enableLaser() {
		this.player.enableLaser();
	}

	public void ShipExplodesIn(int x, int y) {
		this.board.damageAround(x,y);
	}
}