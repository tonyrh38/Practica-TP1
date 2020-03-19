package invaders.lists;

import java.util.Random;

import invaders.game.Game;
import invaders.model.DestroyerShip;

public class DestroyerShipList {
	
	private DestroyerShip[] _list;
	private int _size;
	

	public DestroyerShipList(Game game) {
		_size = game.getLevel().getDestroyerShip();
		_list = new DestroyerShip[_size];
	}
	

	public boolean add(DestroyerShip ship) {
		boolean added = false;
		for(int i = 0; i < _size && !added; i++) {
			if(_list[i] == null) {
				_list[i] = ship;
				added = true;
			}
		}
		return added;
	}
	
	public int remainingAliens() {
		int remaining = 0;
		for(int i = 0; i < _size; i++) {
			if(_list[i] != null) remaining++;
		}
		return remaining;
	}

	public boolean hasLanded() {
		boolean landed = false;
		for(int i = 0; i < _size && !landed; i++) {
			landed = (_list[i] != null && _list[i].hasLanded())? true:false;
		}
		return landed;
	}

	public DestroyerShip shipIn(int row, int col) {
		DestroyerShip ds = null;
		for(int i = 0; i < _size && ds == null; i++) {
			ds = (_list[i] != null && _list[i].isIn(row,col))? _list[i]:null;
		}
		return ds;
	}
	
	public boolean shipInWall() {
		boolean inWall = false;
		for(int i = 0; i < _size && !inWall; i++) {
			if(_list[i] != null && _list[i].isInWall()) inWall = true;
		}
		return inWall;
	}
	
	public void advance(boolean down, boolean movement) {
		for(int i = 0; i < _size; i++) {
			if(_list[i] != null) _list[i].advance(down, movement);
		}
	}
	
	public void computerAction(Random rand) {
		for(int i = 0; i < _size; i++) {
			if(_list[i] != null) _list[i].computerAction(rand);
		}
	}
	
	public void damageAll(int damage) {
		for(int i = 0; i < _size; i++) {
			if(_list[i] != null) _list[i].damage(damage);
		}
	}
	
	public boolean damageIn(int x, int y, int damage) {
		boolean damaged = false;
		for(int i = 0; i < _size && !damaged; i++) {
			if(_list[i] != null && _list[i].isIn(y, x)) {
				_list[i].damage(damage);
				damaged = true;
			}
		}
		return damaged;
	}
	
	public void cleanDestroyed() {
		for(int i = 0; i < _size; i++) {
			if(_list[i] != null && !_list[i].isAlive()) {
				_list[i].onDelete();
				_list[i] = null;
			}
		}
	}
	
}
