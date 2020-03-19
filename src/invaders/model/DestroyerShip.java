package invaders.model;

import java.util.Random;

import invaders.game.Game;

public class DestroyerShip {
	
	private int _x;
	private int _y;
	private int _life;
	private int _points;
	
	private Game _game;
	
	private Bomb _bomb;


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
	
	public void computerAction(Random rand) {
		if(_bomb == null && rand.nextDouble() < _game.getLevel().getFreq()) {
			_bomb = new Bomb(_x, _y, this, _game);
			_game.dropBomb(_bomb);
		}
	}
	
	public void cleanBomb() {
		_bomb = null;
	}
	
	public void onDelete() {
		_game.addPoints(_points);
	}
	
	public String toString() {
		return "D[" + _life + "]";
	}	

}
