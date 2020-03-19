package invaders.game;

import java.util.*;

import invaders.exceptions.CommandExecuteException;
import invaders.lists.*;
import invaders.model.*;

public class Game {
	
	public final static int _X = 9;
	public final static int _Y = 8;

	private Random _rand;
	private Level _level;

	private UCMShip _player;
	private RegularShipList _regularList;
	private DestroyerShipList _destroyerList;
	private BombList _bombList;
	private Ovni _ovni;
	
	private int _cycle;
	private int _score;
	
	private boolean _down;
	private boolean _movement;
	
	private boolean _exit;
	
	
	public Game (Level level, Random random){
		_rand = random;
		_level = level;
		_player = new UCMShip(this);
		reset();
	}
	
	
	private void initializeRegularList() {
		for(int i = 0; i < _level.getRegularShip(); i++) {
			RegularShip ship = new RegularShip(3 + (i % 4), 1 + (i / 4), this);
			_regularList.add(ship);
		}
	}

	private void initializeDestroyerlist() {
		if(_level.toString() == "EASY") {
			for(int i = 0; i < 2; i++) {
				DestroyerShip ship = new DestroyerShip(4 + i, 2, this);
				_destroyerList.add(ship);
			}
		}
		if(_level.toString() == "HARD") {
			for(int i = 0; i < 2; i++) {
				DestroyerShip ship = new DestroyerShip(4 + i, 3, this);
				_destroyerList.add(ship);
			}
		}
		if(_level.toString() == "INSANE") {
			for(int i = 0; i < 4; i++) {
				DestroyerShip ship = new DestroyerShip(3 + i, 3, this);
				_destroyerList.add(ship);
			}
		}
	}
	
	private int remainingAliens() {
		return _regularList.remainingAliens() + _destroyerList.remainingAliens();
	}
	
	private boolean playerWin() {
		return remainingAliens() == 0;
	}
	
	private boolean aliensWin() {
		return !_player.isAlive() || _regularList.hasLanded() || _destroyerList.hasLanded();
	}
	
	private void cleanDestroyed() {
		_player.cleanLaser();
		_regularList.cleanDestroyed();
		_destroyerList.cleanDestroyed();
		_bombList.cleanDestroyed();
		if(_ovni != null && !_ovni.isAlive()) {
			_ovni.onDelete();
			_ovni = null;
		}
	}
	
	private void computerAction() {
		_destroyerList.computerAction(_rand);
		if(_ovni == null && _rand.nextDouble() < _level.getOvni()) _ovni = new Ovni(this);
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
			_ovni.advance();
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
	
	private boolean shipInWall() {
		if(_regularList.shipInWall()) return true;
		else if(_destroyerList.shipInWall()) return true;
		else return false;
	}


	public Level getLevel() {
		return _level;
	}
	
	public void update() {
		cleanDestroyed();
		_cycle++;
		computerAction();
		advance();
	}
	
	public void dropBomb(Bomb bomb) {
		_bombList.add(bomb);
	}
	
	public void addPoints(int points) {
		_score += points;
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
	
	public void move(int numCells) throws CommandExecuteException {
		_player.move(numCells);		
	}
	
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
		_regularList = new RegularShipList(this);
		initializeRegularList();
		_destroyerList = new DestroyerShipList(this);
		initializeDestroyerlist();
		_bombList = new BombList(this);
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
		System.out.println("Remaining aliens: " + remainingAliens());
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

}