package invaders.model;

import invaders.game.Game;

public class RegularShip extends AlienShip{
	
		
	public RegularShip(int x, int y, Game game) {
		super(x, y, game);
		_life = 2;
		_points = 5;
	}
	
	
	// IAttack Interface Methods
	@Override
	public void computerAction() {}
	
	@Override
	public String toString() {
		return "C[" + _life + "]";
	}
	
}
