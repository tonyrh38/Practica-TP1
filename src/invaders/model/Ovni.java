package invaders.model;

import invaders.game.Game;
import invaders.interfaces.IExecuteRandomActions;

public class Ovni extends EnemyShip{
	
	private boolean _enable;
	
	
	public Ovni(Game game) {
		super(Game._X - 1, 0, game);
		_life = 1;
		_points = 25;
		_game = game;
		_enable = false;
	}
	
	
	private void reset() {
		_x = Game._X - 1;
		_life = 1;
		_enable = true;
	}
	
	// IAttack Interface Method
		@Override
		public boolean receiveLaserAttack(int damage) {
			if(_enable) {
				_life -= damage;
				if(!isAlive()) {
					reset();
					onDelete();					
				}
				return true;
			}
			else return false;
		}
	
	// GameObject Abstract Methods
	@Override
	public void computerAction() {
		if(!_enable && IExecuteRandomActions.canGenerateRandomOvni(_game)) reset();
	}
	
	@Override
	public void onDelete() {
		super.onDelete();
		_game.enableShockWave();
		_enable = false;
	}

	@Override
	public void move() {
		if(_enable) _x--;
	}

	@Override
	public String toString() {
		if(_enable) return "O[" + _life + "]";
		else return "";
	}
	
}
