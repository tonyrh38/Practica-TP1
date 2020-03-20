package invaders.model;

import invaders.game.Game;

abstract public class Ship extends GameObject {

	public Ship(int x, int y, int life, Game game) {
		super(x, y, game);
	}
	
}
