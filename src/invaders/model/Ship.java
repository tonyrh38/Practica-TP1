package invaders.model;

import invaders.game.Game;

abstract public class Ship extends GameObject {

	
	public Ship() {}
	
	public Ship(int x, int y, Game game) {
		super(x, y, game);
	}
	
}
