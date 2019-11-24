package tp.p1.object;

import tp.p1.game.Game;

public abstract class EnemyShip extends Ship {

	public EnemyShip(Game game, int x, int y, int life,int points) {
		super(game, x, y, life, points);
	}

	@Override
	public void onDelete() {
		this.game.receivePoints(this.points);
	}
}
