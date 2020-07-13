package invaders.model;

import extra.utils.FileContentsVerifier;
import invaders.game.Game;

public class SuperLaser extends Weapon {

	
	public SuperLaser() {}
	
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

	@Override
	public String toSerialize() {
		return "A;"+ _x +","+ _y;
	}

	// GameObjectGenerator Method
	@Override
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
		if(!verifier.verifyWeaponString(stringFromFile, game)) return null;
		else {
			String [] words = stringFromFile.split(";");
			if(!words[0].equals("A")) return null;
			else {
				String [] coords = words[1].split(",");
				
				return new SuperLaser(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game);
			}
		}
	}
	
}
