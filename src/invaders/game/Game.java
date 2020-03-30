package invaders.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import extra.utils.FileContentsVerifier;
import invaders.exceptions.CommandExecuteException;
import invaders.exceptions.FileContentsException;
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
		return AlienShip.getRemaining() <= 0;
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
	
	public int getScore() {
		return _score;
	}
	
	public void shockwaveAttack(int damage) {
		_board.shockwaveAttack(damage);
	}
	
	public void explosionIn(int x, int y) {
		_board.explosionIn(x, y);
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
	
	public void buy(String weapon) throws CommandExecuteException {
		switch(weapon) {
		case "supermisil":
			if(_score < 20) throw new CommandExecuteException("No dispone de suficientes puntos.");
			else {
				_score -= 20;
				_player.addSuperLaser();
			}
			break;
		default: throw new CommandExecuteException("El arma que desea no puede comprarse.");
		}
	}

	public void load(BufferedReader r) throws IOException, FileContentsException {
		FileContentsVerifier verifier = null;
		// TODO: Rehacer con el Verifier
		String cycles =  r.readLine();
		if(!cycles.contains("G;")) throw new FileContentsException("El archivo no tiene el formato correcto.");
		else _cycle = ((int)cycles.charAt(2));
		String level = r.readLine();
		if(!level.contains("L;")) throw new FileContentsException("El archivo no tiene el formato correcto.");
		else _level = Level.valueOf(level.substring(2));
		
		boolean loading = false;
		String line = r.readLine().trim();
		while(line != null && !line.isEmpty()) {
			GameObject gameObject = GameObjectGenerator.parse(line, this, verifier);
			if (gameObject == null) throw new FileContentsException("invalid file, unrecognised line prefix");
			else {
				_board.add(gameObject);
				line = r.readLine().trim();
			}	
		}
	}
	
	// Controller Methods
	public void info() {
		System.out.println("Life: " + _player.getLife());
		System.out.println("Number of cycles: " + _cycle);
		System.out.println("Points: " + _score);
		System.out.println("Remaining aliens: " + AlienShip.getRemaining());
		System.out.println("ShockWave: " + (_player.hasShockwave()?"YES":"NO"));
		System.out.println("Supermisiles: " + _player.getSuperLaser());
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

	public String characterAtToSerialize(int row, int col) {
		return _board.toSerialize(row, col);
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
	public void shootSuperLaser() throws CommandExecuteException {
		_player.shootSuperLaser();
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