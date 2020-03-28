package invaders.model;

import invaders.game.Game;
import invaders.interfaces.IExecuteRandomActions;

public class RegularShip extends AlienShip{
	
	private boolean _explosive;	
	
	
	public RegularShip(int x, int y, Game game) {
		super(x, y, game);
		_life = 2;
		_points = 5;
		_explosive = false;
	}
	
	
	// GameObject Abstract Methods
	@Override
	public void computerAction() {
		if(!_explosive && IExecuteRandomActions.canGenerateExplosiveShip(_game)) _explosive = true;
	}
	
	@Override
	public void onDelete() {
		super.onDelete();
		if(_explosive) _game.explosionIn(_x, _y);
	}
	
	@Override
	public String toString() {
		if(_explosive) return "E[" + _life + "]";
		else return "C[" + _life + "]";
	}
	
}
