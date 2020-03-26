package invaders.model;

import invaders.game.Game;

public class Laser extends Weapon{
	
	
	public Laser(int x, int y, Game game) {
		super(x, y, game);
		_life = 1;
		_damage = 1;
	}

	
	// IAttack Interface Methods
	@Override
	public boolean performAttack(GameObject other) {
		if(other.isIn(_y, _x)) return other.receiveLaserAttack(_damage);
		return false;
	}
	@Override
	public boolean receiveBombAttack(int damage) {
		_life -= damage;
		return true;
	}
	
	// GameObject Abstract Methods
	@Override
	public void computerAction() {}

	@Override
	public void onDelete() {
		_game.enableLaser();
	}

	@Override
	public void move() {
		_y--;
	}
	
	@Override
	public String toString() {
		return "oo";
	}
	
}
