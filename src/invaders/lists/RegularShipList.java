package invaders.lists;

import invaders.game.Game;
import invaders.model.RegularShip;

public class RegularShipList {

	private RegularShip[] _list;
	private int _size;
	
	
	public RegularShipList(Game game) {
		_size = game.getLevel().getRegularShip();
		_list = new RegularShip[_size];
	}


	public boolean add(RegularShip ship) {
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

	public RegularShip shipIn(int row, int col) {
		RegularShip rs = null;
		for(int i = 0; i < _size && rs == null; i++) {
			rs = (_list[i] != null && _list[i].isIn(row,col))? _list[i]:null;
		}
		return rs;
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
