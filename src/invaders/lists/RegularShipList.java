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

	public void damageAll(int damage) {
		for(int i = 0; i < _size; i++) {
			if(_list[i] != null) _list[i].damage(damage);
		}
	}
	
}
