package invaders.model;

import invaders.game.Game;

public abstract class EnemyShip extends Ship {

	public EnemyShip(Game game, int x, int y, int life,int points) {
		super(game, x, y, life, points);
	}

	@Override
	public void onDelete() {
		this.game.receivePoints(this.points);
	}
	
	@Override
	public boolean receiveLaserAttack(int damage) {
		this.getDamage(damage);
		return true;
	 }
	
	@Override
	public boolean receiveShockWaveAttack(int damage) {
		this.getDamage(damage);
		return true;
	}
}
