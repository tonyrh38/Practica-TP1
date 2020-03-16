package invaders.lists;

import invaders.game.Game;
import invaders.model.DestroyerShip;
import invaders.model.RegularShip;

public class DestroyerShipList {
	
	private DestroyerShip[] _list;
	private int _size;
	

	public DestroyerShipList(Game game) {
		_size = game.getLevel().getDestroyerShip();
		_list = new DestroyerShip[_size];
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
	
	public void damageAll(int damage) {
		for(int i = 0; i < _size; i++) {
			if(_list[i] != null) _list[i].damage(damage);
		}
	}
	
}
