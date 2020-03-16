package invaders.lists;

import invaders.game.Game;
import invaders.model.Bomb;
import invaders.model.DestroyerShip;

public class BombList {

	private Bomb[] _list;
	private int _size;
	
	
	public BombList(Game game) {
		_size = game.getLevel().getDestroyerShip();
		_list = new Bomb[_size];
	}


	public Bomb bombIn(int row, int col) {
		Bomb b = null;
		for(int i = 0; i < _size && b == null; i++) {
			b = (_list[i] != null && _list[i].isIn(row,col))? _list[i]:null;
		}
		return b;
	}

}
