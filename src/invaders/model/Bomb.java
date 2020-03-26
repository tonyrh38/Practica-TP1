package invaders.model;

import invaders.game.Game;

public class Bomb extends Weapon{
	
	private DestroyerShip _father;
	
	
	public Bomb(int x, int y, DestroyerShip father, Game game) {
		super(x, y, game);
		_life = 1;
		_damage = 1;
		_father = father;
	}

	
	// IAttack Interface Methods
	@Override
	public boolean performAttack(GameObject other) {
		if(other.isIn(_y, _x)) return other.receiveBombAttack(_damage);
		return false;
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
		_father.resetBomb();
	}

	@Override
	public void move() {
		_y++;
	}
	
	@Override
	public String toString() {
		return ".";
	}
	
}
