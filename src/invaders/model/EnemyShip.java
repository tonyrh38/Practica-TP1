package invaders.model;

import invaders.game.Game;

public abstract class EnemyShip extends GameObject {

	protected int _points;
	
	public EnemyShip(int x, int y, Game game) {
		super(x, y, game);
	}

	
	// IAttack Interface Method
	@Override
	public boolean receiveLaserAttack(int damage) {
		_life -= damage;
		return true;
	}
	
	// GameObject Abstract Method
	@Override
	public void onDelete() {
		_game.receivePoints(_points);
	}
	
}
