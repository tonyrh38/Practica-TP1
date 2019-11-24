package tp.practica1Ships;

import tp.practica1Game.Coordinates;
import tp.practica1Game.Game;
import tp.practica1GameObjects.GameObject;

public abstract class Ship extends GameObject {

	public Ship(Game game,Coordinates coord, int live) {
		super(game, coord, live);
	}
}
