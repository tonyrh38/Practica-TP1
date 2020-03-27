package invaders.game;

import java.util.*;

import invaders.exceptions.CommandExecuteException;
import invaders.interfaces.IPlayerController;
import invaders.model.*;

public class Game implements IPlayerController {
	
	public final static int _X = 9;
	public final static int _Y = 8;

	private Random _rand;
	private Level _level;

	private GameObjectBoard _board;
	private UCMShip _player;
	
	private int _cycle;
	private int _score;
	
	private boolean _exit;
	
	
	public Game (Level level, Random random){
		_rand = random;
		_level = level;
		reset();
	}
	
	
	private boolean playerWin() {
		return AlienShip.getRemaining() == 0;
	}
	
	private boolean aliensWin() {
		return !_player.isAlive() || _board.haveLanded();
	}
	
	public Random getRandom() {
		return _rand;
	}
	
	public Level getLevel() {
		return _level;
	}
	
	public int getCycle() {
		return _cycle;
	}
	
	public void shockwaveAttack(int damage) {
		_board.shockwaveAttack(damage);
	}
	
	public void add(GameObject go) {
		_board.add(go);
	}

	public void update() {
		_cycle++;
		_board.computerAction();
		_board.update();
	}
	
	// Command Methods
	public void exit() {
		_exit = true;
	}

	public void reset() {
		_board = new BoardInitializer().initialize(this, _level);
		_player = new UCMShip(this);
		_board.add(_player);
		_cycle = 0;
		_score = 0;
		_exit = false;
	}

	// Controller Methods
	public void info() {
		System.out.println("Life: " + _player.getLife());
		System.out.println("Number of cycles: " + _cycle);
		System.out.println("Points: " + _score);
		System.out.println("Remaining aliens: " + AlienShip.getRemaining());
		System.out.println("ShockWave: " + (_player.hasShockwave()?"YES":"NO"));
	}
	
	public boolean isFinished() {
		return playerWin() || aliensWin() || _exit;
	}

	public String getWinnerMessage() {
		if (playerWin()) return "Player win!";
		else if (aliensWin()) return "Aliens win!";
		else return "Game Over";
	}
	
	// GamePrinter Methods
	public String characterAtToString(int row, int col) {
		return _board.toString(row, col);
	}

	// IPlayerController Methods
	@Override
	public void move(int numCells) throws CommandExecuteException {
		_player.move(numCells);
	}
	
	@Override
	public void shootLaser() throws CommandExecuteException {
		_player.shootLaser();
	}

	@Override
	public void shockwave() throws CommandExecuteException {
		_player.shockwave();
	}

	@Override
	public void receivePoints(int points) {
		_score += points;
	}

	@Override
	public void enableShockWave() {
		_player.enableShockwave();
	}

	@Override
	public void enableLaser() {
		_player.enableLaser();
	}

}