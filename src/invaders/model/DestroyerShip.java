package invaders.model;

import invaders.game.Game;
import invaders.interfaces.IExecuteRandomActions;

public class DestroyerShip extends AlienShip{
	
	private Bomb _bomb;


	public DestroyerShip(int x, int y, Game game) {
		super(x, y, game);
		_life = 1;
		_points = 10;
	}

	
	public void resetBomb() {
		_bomb = null;
	}
	
	// GameObject Abstract Methods
	@Override
	public void computerAction() {
		if(_bomb == null && IExecuteRandomActions.canGenerateRandomBomb(_game)) {
			_bomb = new Bomb(_x, _y, this, _game);
			_game.add(_bomb);
		}
	}	
	
	@Override
	public String toString() {
		return "D[" + _life + "]";
	}
	
}
