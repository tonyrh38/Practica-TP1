package tp.p1.object;

import tp.p1.game.Game;

public abstract class Weapon extends GameObject {
	
	protected int damage;

	public Weapon(Game game, int x, int y, int life,int damage) {
		super(game, x, y, life);
		this.damage = damage;
	}
}
