package invaders.model;

import invaders.game.Game;

abstract public class AlienShip extends EnemyShip {

	private static int _remaining = 0;
	
	// TODO: Arreglar el movimiento de las naves enemigas
	private static boolean _down;
	private static boolean _movement;
	
	public AlienShip(int x, int y, Game game) {
		super(x, y, game);
		_remaining++;
		_down = false;
		_movement = false;
	}

	public static int getRemaining() {
		return _remaining;
	}
	
	public boolean isInWall() {
		return _x <= 0 || _x >= Game._X - 1;
	}
	
	public boolean hasLanded() {
		return _y >= Game._Y - 1;
	}
	
	// GameObject Abstract Methods	
	@Override
	public void move() {
		if(_down) _y++;
		else if(_movement) _x++;
		else _x--;
	}

}
