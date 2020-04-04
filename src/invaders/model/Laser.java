package invaders.model;

import extra.utils.FileContentsVerifier;
import invaders.game.Game;

public class Laser extends Weapon{
	
	
	public Laser() {}
	
	public Laser(int x, int y, Game game) {
		super(x, y, game);
		_life = 1;
		_damage = 1;
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
	
	@Override
	public String toSerialize() {
		return "L;"+ _x +","+ _y;
	}

	// GameObjectGenerator Method
	@Override
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
