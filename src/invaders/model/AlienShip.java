package invaders.model;

import invaders.game.Game;

abstract public class AlienShip extends EnemyShip {

	private static int _remaining = 0;
	private static int _counter = 0;
	
	private static boolean _down;
	private static boolean _locked;
	protected static boolean _direction;
	
	
	public AlienShip() {}
	
	public AlienShip(int x, int y, Game game) {
		super(x, y, game);
		_remaining++;
		_down = false;
		_locked = false;
		_direction = false;
	}

	
	public static int getRemaining() {
		return _remaining;
	}
	
	public boolean isInWall() {
		return _x <= 0 || _x >= Game._X - 1;
	}
	
	@Override
	public boolean hasLanded() {
		return _y >= Game._Y - 1;
	}	
		
	// IAttack Interface Method
	@Override
	public boolean receiveShockWaveAttack(int damage) {
		_life -= damage;
		return true;
	}
	
	// GameObject Abstract Methods	
	@Override
	public void onDelete() {
		super.onDelete();
		_remaining--;
		if(_remaining < 0) _remaining = 0;
	}
	
	@Override
	public void move() {
		if(_counter == 0 && _down) _locked = true;
		if(isAlive()) {
			if(_down && _locked) _y++;
			else if(_game.getCycle() % _game.getLevel().getVel() == 0) {
				if(_direction) _x++;
				else _x--;
				if(isInWall()) _down = true;
			}
		}
		_counter++;
		if(_counter == _remaining) {
			if(_down && _locked) {
				_down = false;
				_locked = false;
				_direction = !_direction;
			}
			_counter = 0;
		}
	}

}
