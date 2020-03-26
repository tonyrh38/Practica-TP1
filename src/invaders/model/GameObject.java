package invaders.model;

import invaders.game.Game;
import invaders.interfaces.IAttack;

public abstract class GameObject implements IAttack {

	protected int _x;
	protected int _y;
	protected int _life;
	
	protected Game _game;
	
	
	public GameObject(int x, int y, Game game) {
		_x = x;
		_y = y;
		_game = game;
	}
	

	public int getLife() {
		return _life;
	}
	
	public boolean isAlive() {
		return _life > 0;
	}
	
	public boolean isIn(int row, int col) {
		return _x == col && _y == row;
	}

	public boolean isOut() {
		return _x >= Game._X || _x < 0 || _y < 0 || _y >= Game._Y;
	}
	
	public boolean hasLanded() {
		return false;
	}

	public abstract void computerAction();
	public abstract void onDelete();
	public abstract void move();
	public abstract String toString();
	
}
