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


	public boolean isIn(int row, int col) {
		return _x == col && _y == row;
	}

	public boolean isInWall() {
		return _x <= 0 || _x >= Game._X;
	}
	
	public boolean hasLanded() {
		return _y >= Game._Y;
	}
	
	public boolean isAlive() {
		return _life > 0;
	}
	
	public void damage(int damage) {
		_life -= damage;
	}
	
	public void advance(boolean down, boolean movement) {
		if(down) _y++;
		else if(movement) _x++;
		else _x--;
	}
	
	public void onDelete() {
		_game.addPoints(_points);
	}
	
	public String toString() {
		return "C[" + _life + "]";
	}
	
}
