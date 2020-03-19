package invaders.model;

import invaders.game.Game;

public class Bomb {
	
	private int _x;
	private int _y;
	private int _life;
	private int _damage;
	private DestroyerShip _father;
	
	private Game _game;
	
	
	public Bomb(int x, int y, DestroyerShip father, Game game) {
		_x = x;
		_y = y;
		_life = 1;
		_damage = 1;
		_father = father;
		_game  =game;
	}


	public boolean isIn(int row, int col) {
		return _x == col && _y == row;
	}
	
	public boolean isAlive() {
		return _life > 0 && _y < Game._Y;
	}
	
	public void damage(int damage) {
		_life -= damage;
	}
	
	public void advance() {
		_y++;
		if(_game.damagePlayer(_x, _y, _damage)) _life--;
	}
	
	public void onDelete() {
		_father.cleanBomb();
	}
	
	public String toString() {
		return ".";
	}

}
