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
	
	private boolean _exit;
	
	
	public Game (Level level, Random random){
		_rand = random;
		_level = level;
		_player = new UCMShip(this);
		// TODO: Llenar las listas con las naves correspondientes.
		_regularList = new RegularShipList(this);
		_destroyerList = new DestroyerShipList(this);
		_bombList = new BombList(this);
		_ovni = new Ovni(this);
		_cycle = 0;
		_score = 0;
		_exit = false;
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
	
	public Level getLevel() {
		return _level;
	}
	
	public void update() {
		// ComputerAction se incluye aqui
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
		//TODO: Completar metodo.
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