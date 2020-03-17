package invaders.model;

import invaders.game.Game;

public class Ovni {
	
	private int _x;
	private int _y;
	private int _life;
	private int _points;
	
	private Game _game;
	

	public Ovni(Game game) {
		_x = 8;
		_y = 0;
		_life = 1;
		_points = 25;
		_game = game;
	}


	public boolean isIn(int row, int col) {
		return _x == col && _y == row;
	}
	
	public boolean isAlive() {
		return _life > 0;
	}
	
	public void damage(int damage) {
		_life -= damage;
	}

	public void onDelete() {
		_game.addPoints(_points);
		_game.addShockwave();
	}
	
	public String toString() {
		return "O[" + _life + "]";
	}

}
