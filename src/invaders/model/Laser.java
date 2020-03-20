package invaders.model;

import invaders.game.Game;

public class Laser extends Weapon{

	
	public Laser(int x, int y, Game game) {
		super(x, y, game);
		_life = 1;
		_damage = 1;
	}


	// IAttacks Interface Methods
	@Override
	public void computerAction() {}

	@Override
	public void onDelete() {
		// TODO: Se puede crear un father y llamar en este metodo a father.resetLaser()
	}

	@Override
	public void move() {
		_y--;
		if(_game.damageIn(_x, _y, _damage)) _life--;
	}
	
	@Override
	public String toString() {
		return "oo";
	}
	
}
