package invaders.model;

import invaders.game.Game;

public class Shockwave{
	
	private int _damage;
	
	private Game _game;
	
	
	public Shockwave(Game game) {
		_damage = 1;
		_game = game;
	}


	public void impacts() {
		_game.shockwaveImpacts(_damage);
	}

}
