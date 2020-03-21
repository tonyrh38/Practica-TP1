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
		if(_game.damagePlayer(_x, _y, _damage)) _life--;
	}
	
	@Override
	public String toString() {
		return ".";
	}
	
}
