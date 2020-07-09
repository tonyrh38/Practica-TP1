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
	
	
	public boolean isOnBoard(int x, int y) {
		return x >= 0 && x < _X && y >= 0 && y < _Y ;
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
	
	public void setLevel(Level level) {
		_level = level;
	}
	
	public void setPlayer(UCMShip player) {
		_player = player;
	}
	
	public void setCycle(int cycle) {
		_cycle = cycle;
	}
	
	public void setScore(int score) {
		_score = score;
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
		FileContentsVerifier verifier = new FileContentsVerifier();
		
		// Copia de seguridad del juego
		Game game = null;
		try {
			game = (Game) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		try {
			String cycleString =  r.readLine().trim();
			if(!verifier.verifyCycleString(cycleString)) throw new FileContentsException("invalid file, unrecognised line prefix");
			else setCycle(((int)cycleString.charAt(2)));
			
			String levelString = r.readLine().trim();
			if(!verifier.verifyLevelString(levelString)) throw new FileContentsException("invalid file, unrecognised line prefix");
			else setLevel(Level.valueOf(levelString.substring(2)));
			
			_board.clear();
			String line = r.readLine().trim();
			while(line != null && !line.isEmpty()) {
				GameObject gameObject = GameObjectGenerator.parse(line, this, verifier);
				if (gameObject == null) throw new FileContentsException("invalid file, unrecognised line prefix");
				else {
					add(gameObject);
					line = r.readLine().trim();
				}	
			}
		} catch(FileContentsException e) {
			_level = game._level;
			_board = game._board;
			_player = game._player;
			_cycle = game._cycle;
			_score = game._score;
			throw new FileContentsException("invalid file, unrecognised line prefix");
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