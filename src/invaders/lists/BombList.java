package invaders.lists;

import invaders.game.Game;
import invaders.model.Bomb;

public class BombList {

	private Bomb[] _list;
	private int _size;
	
	
	public BombList(Game game) {
		_size = game.getLevel().getDestroyerShip();
		_list = new Bomb[_size];
	}

	
	public boolean add(Bomb bomb) {
		boolean added = false;
		for(int i = 0; i < _size && !added; i++) {
			if(_list[i] == null) {
				_list[i] = bomb;
				added = true;
			}
		}
		return added;
	}
	
	public Bomb bombIn(int row, int col) {
		Bomb b = null;
		for(int i = 0; i < _size && b == null; i++) {
			b = (_list[i] != null && _list[i].isIn(row,col))? _list[i]:null;
		}
		return b;
	}

	public void cleanDestroyed() {
		for(int i = 0; i < _size; i++) {
			if(_list[i] != null && !_list[i].isAlive()) {
				_list[i].onDelete();
				_list[i] = null;
			}
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

	public void advance() {
		for(int i = 0; i < _size; i++) {
			if(_list[i] != null) _list[i].advance();
		}
		cleanDestroyed();
	}
	
}
