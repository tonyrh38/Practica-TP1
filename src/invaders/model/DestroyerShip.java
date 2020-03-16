package invaders.model;

import invaders.game.Game;

public class DestroyerShip {
	
	private int _x;
	private int _y;
	private int _life;
	private int _points;
	
	private Game _game;
	
	private Bomb bomb;


	public DestroyerShip(int x, int y, Game game) {
		_x = x;
		_y = y;
		_life = 1;
		_points = 10;
		_game = game;
	}


	public boolean isIn(int row, int col) {
		return _x == col && _y == row;
	}
	
	public boolean hasLanded() {
		return _y >= Game._Y;
	}
	
	public void damage(int damage) {
		_life -= damage;
	}
	
	public String toString() {
		return "D[" + _life + "]";
	}

}
