package invaders.model;

import invaders.game.Game;

public class SuperLaser extends Weapon {

	public SuperLaser(int x, int y, Game game) {
		super(x, y, game);
		_life = 1;
		_damage = 2;
	}

	// IAttack Interface Methods
	@Override
	public boolean performAttack(GameObject other) {
		if(isAlive() && other.isIn(_y, _x) && other.receiveLaserAttack(_damage)) {
			_life--;
			return true;
		}
		else return false;
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
	public void onDelete() {}

	@Override
	public void move() {
		_y--;
	}
	
	@Override
	public String toString() {
		return "A";
	}

}
