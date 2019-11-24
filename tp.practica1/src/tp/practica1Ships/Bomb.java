package tp.practica1Ships;

import tp.practica1Game.Coordinates;
import tp.practica1Game.Game;

public class Bomb extends Weapon {
	
	private final static int DAMAGE = 1;

	public Bomb(Game game, Coordinates coord) {
		super(game, coord, 0, DAMAGE); // TODO preguntar si la vida de las armas es 0 o 1
	}

	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
