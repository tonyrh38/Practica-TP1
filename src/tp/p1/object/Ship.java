package tp.p1.object;

import tp.p1.game.Game;

public abstract class Ship extends GameObject {
	
	protected int points;
	
	public Ship(Game game, int x, int y, int life, int points) {
		super(game, x, y, life);
		this.points = points;
	}
}
