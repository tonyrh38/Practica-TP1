package invaders.model;

import invaders.game.Game;

abstract public class Weapon extends GameObject {

	protected int _damage;
	
	
	public Weapon() {}
	
	public Weapon(int x, int y, Game game) {
		super(x, y, game);
	}

}
