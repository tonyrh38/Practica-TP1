package invaders.model;

import java.util.Random;

import invaders.game.Game;

public class DestroyerShip extends AlienShip{
	
	private Bomb _bomb;


	public DestroyerShip(int x, int y, Game game) {
		super(x, y, game);
		_life = 1;
		_points = 10;
	}
	
	// TODO: comprobar el computerAction
	public void computerAction(Random rand) {
		if(_bomb == null && rand.nextDouble() < _game.getLevel().getFreq()) {
			_bomb = new Bomb(_x, _y, this, _game);
			_game.dropBomb(_bomb);
		}
	}
	
	public void resetBomb() {
		_bomb = null;
	}
	
	// GameObject Abstract Methods
	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
	}	
	
	@Override
	public String toString() {
		return "D[" + _life + "]";
	}
	
}
