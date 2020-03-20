package invaders.model;

import invaders.game.Game;

public abstract class EnemyShip extends GameObject {

	protected int _points;
	
	public EnemyShip(int x, int y, Game game) {
		super(x, y, game);
	}

}
