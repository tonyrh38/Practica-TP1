package invaders.model;

import extra.utils.FileContentsVerifier;
import invaders.game.Game;

public class Bomb extends Weapon{
	
	private DestroyerShip _father;
	
	
	public Bomb() {}
	
	public Bomb(int x, int y, DestroyerShip father, Game game) {
		super(x, y, game);
		_life = 1;
		_damage = 1;
		_father = father;
	}

	
	// IAttack Interface Methods
	@Override
	public boolean performAttack(GameObject other) {
		if(isAlive() && other.isIn(_y, _x) && other.receiveBombAttack(_damage)) {
			_life--;
			return true;
		}
		else return false;
	}
	
	@Override
	public boolean receiveLaserAttack(int damage) {
		_life -= damage;
		return true;
	}
	
	// GameObject Abstract Methods
	@Override
	public void computerAction() {}

	@Override
	public void onDelete() {
		if(_father != null) _father.resetBomb();
	}

	@Override
	public void move() {
		_y++;
	}
	
	@Override
	public String toString() {
		return ".";
	}

	@Override
	public String toSerialize() {
		return "B;"+ _x +","+ _y +";"+ _father.getId();
	}

	// GameObjectGenerator Method
	@Override
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
		if(!verifier.verifyWeaponString(stringFromFile, game)) return null;
		else {
			String words [] = stringFromFile.split(";");
			if(!words[0].equals("B")) return null;
			else {
				String [] coords = words[1].split(",");
				DestroyerShip father = game.findFather(Integer.parseInt(words[2]));
				return new Bomb(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), father, game);
			}
		}
	}
	
}
