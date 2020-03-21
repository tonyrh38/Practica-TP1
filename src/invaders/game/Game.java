package invaders.game;

import java.util.*;

import invaders.exceptions.CommandExecuteException;
import invaders.interfaces.IPlayerController;
import invaders.lists.*;
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
	
	private boolean _down;
	private boolean _movement;
	
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
	
	private void advance() {
		//1)
		if(_player.getLaser() != null) _player.getLaser().advance();
		cleanDestroyed();
		//2)
		_bombList.advance();
		//3)
		checkShipMovement();
		if(_down || _cycle % _level.getVel() == 0) {
			_destroyerList.advance(_down, _movement);
			_regularList.advance(_down, _movement);
		}
		//4)
		if(_ovni != null) {
			_ovni.move();
			if(!_ovni.isAlive()) _ovni = null;
		}
	}
	
	private void checkShipMovement() {
		if(shipInWall()) {
			if(_down) {
				_down = false;
				_movement = !_movement;
			}
			else _down = true;
		}
	}

	public Random getRandom() {
		return _rand;
	}
	
	public Level getLevel() {
		return _level;
	}
	
	public void update() {
		_cycle++;
		_board.computerAction();
		_board.update();
	}
	
	public void dropBomb(Bomb bomb) {
		_bombList.add(bomb);
	}
	
	public void addShockwave() {
		if(!_player.hasShockwave()) _player.addShockwave();
	}
	
	public boolean damageIn(int x, int y, int damage) {
		if(_bombList.damageIn(x, y, damage)) return true;
		else if(_destroyerList.damageIn(x, y, damage)) return true;
		else if(_regularList.damageIn(x, y, damage)) return true;
		else if(_ovni.isIn(y, x)) {
			_ovni.damage(damage);
			return true;
		}
		else return false;
	}
	
	public boolean damagePlayer(int x, int y, int damage) {
		if(_player.isIn(y, x)) {
			_player.damage(damage);
			return true;
		}
		else return false;
	}
	
	// Command Methods
	
	public void shootLaser() throws CommandExecuteException {
		_player.shootLaser();
	}
	
	public void shockwave() throws CommandExecuteException {
		_player.shockwave();
	}
	
	public void shockwaveImpacts(int damage) {
		_regularList.damageAll(damage);
		_destroyerList.damageAll(damage);
		if(_ovni != null) _ovni.damage(damage);
	}
	
	public void exit() {
		_exit = true;
	}

	public void reset() {
		_board = new BoardInitializer().initialize(this, _level);
		_player = new UCMShip(this);
		_board.add(_player);
		_cycle = 0;
		_score = 0;
		_down = false;
		_movement = false;
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
		if(_player.isIn(row,col)) return _player.toString();
		else if(_player.getLaser() != null && _player.getLaser().isIn(row,col)) return _player.getLaser().toString();
		else {
			RegularShip rs = _regularList.shipIn(row,col);
			if(rs != null) return rs.toString();
			else {
				DestroyerShip ds = _destroyerList.shipIn(row,col);
				if(ds != null) return ds.toString();
				else {
					Bomb b = _bombList.bombIn(row,col);
					if(b != null) return b.toString();
					else if(_ovni != null && _ovni.isIn(row,col)) return _ovni.toString();
					else return "";
				}
			}
		}
	}

	// IPlayerController Methods
	

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
		_score += points;
	}

	@Override
	public void enableShockWave() {
		// TODO Auto-generated method stub
		// Lo llama Ovni
	}


	@Override
	public void move(int numCells) throws CommandExecuteException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void enableLaser() {
		// TODO Auto-generated method stub
		
	}

}