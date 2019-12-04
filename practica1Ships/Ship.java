package tp.practica1Ships;

import tp.practica1Game.Game;
import tp.practica1GameObjects.GameObject;

public abstract class Ship extends GameObject {
	
	protected int points;
	
	public Ship(Game game, int x, int y, int life, int points) {
		super(game, x, y, life);
		this.points = points;
	}
}
