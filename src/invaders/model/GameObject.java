package invaders.model;

import extra.utils.FileContentsVerifier;
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
	
	public boolean hasLanded() {return false;}

	// IAttack Interface Method
	@Override
	public boolean receiveExplosionIn(int x, int y) {
		boolean hit = false;
		for(int i = y - 1; i <= y + 1 && !hit; i++) {
			for(int j = x - 1; j <= x + 1 && !hit; j++) {
				if(isIn(i, j)) {
					_life--;
					hit = true;
				}
			}
		}
		return hit;
	}
	
	public abstract void computerAction();
	public abstract void onDelete();
	public abstract void move();
	public abstract String toString();
	public abstract String toSerialize();
	public abstract GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier);
	
}
