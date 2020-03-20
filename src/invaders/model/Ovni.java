package invaders.model;

import invaders.game.Game;

public class Ovni extends EnemyShip{
	
	
	public Ovni(Game game) {
		super(Game._X - 1, 0, game);
		_life = 1;
		_points = 25;
		_game = game;
	}
	
	
	// IAttacks Interface Methods
	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	public void onDelete() {
		_game.addPoints(_points);
		_game.addShockwave();
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
