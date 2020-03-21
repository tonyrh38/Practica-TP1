package invaders.model;

import invaders.game.Game;

public class Shockwave extends Weapon{

	
	public Shockwave(Game game) {
		super(Game._X, Game._Y, game);
		_damage = 1;
	}


	public void impacts() {
		_game.shockwaveImpacts(_damage);
	}

	// GameObject Abstract Methods
	@Override
	public void computerAction() {}

	@Override
	public void onDelete() {
		// TODO Lo mismo que en el laser.	
	}

	@Override
	public void move() {}

	@Override
	public String toString() {
		return "";
	}

}
