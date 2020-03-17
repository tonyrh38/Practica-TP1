package invaders.model;

import invaders.game.Game;

public class Laser {
	
	private int _x;
	private int _y;
	private int _life;
	private int _damage;
	
	private Game _game;
	
	
	public Laser(int x, int y, Game game) {
		_x = x;
		_y = y;
		_life = 1;
		_damage = 1;
		_game = game;
	}


	public boolean isIn(int row, int col) {
		return _x == col && _y == row;
	}
	
	public boolean isAlive() {
		return _life > 0 && _y > 0;
	}
	
	public String toString() {
		return "oo";
	}

}
