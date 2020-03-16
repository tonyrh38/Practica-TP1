package invaders.model;

import invaders.game.Game;

public class RegularShip {
	
	private int _x;
	private int _y;
	private int _life;
	private int _points;
	
	private Game _game;
	
		
	public RegularShip(int x, int y, Game game) {
		_x = x;
		_y = y;
		_life = 2;
		_points = 5;
		_game = game;
	}

	
	public boolean hasLanded() {
		return _y >= Game._Y;
	}

	public boolean isIn(int row, int col) {
		return _x == col && _y == row;
	}

	public void damage(int damage) {
		_life -= damage;
	}
	
	public String toString() {
		return "C[" + _life + "]";
	}
	
}
