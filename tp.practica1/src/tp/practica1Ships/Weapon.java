package tp.practica1Ships;

import tp.practica1Game.Coordinates;
import tp.practica1Game.Game;
import tp.practica1GameObjects.GameObject;

public abstract class Weapon extends GameObject {
	protected int damage;

	public Weapon(Game game, Coordinates coord, int live, int damage) {
		
		super(game, coord, live);
		this.damage = damage;
	}

	

}
