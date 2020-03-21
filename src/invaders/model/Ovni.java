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
	
	public boolean isEnable() {
		return _enable;
	}
	
	// GameObject Abstract Methods
	@Override
	public void computerAction() {
		if(IExecuteRandomActions.canGenerateRandomOvni(_game)) reset();
	}

	public void onDelete() {
		super.onDelete();
		_game.enableShockWave();
		_enable = false;
	}

	@Override
	public void move() {
		_x--;
	}

	@Override
	public String toString() {
		return "O[" + _life + "]";
	}
	
}
