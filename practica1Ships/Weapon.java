package tp.practica1Ships;

import tp.practica1Game.Game;
import tp.practica1GameObjects.GameObject;

public abstract class Weapon extends GameObject {
	
	protected int damage;

	public Weapon(Game game, int x, int y, int life,int damage) {
		super(game, x, y, life);
		this.damage = damage;
	}
}
