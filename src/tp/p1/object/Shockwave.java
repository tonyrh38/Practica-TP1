package tp.p1.object;

import tp.p1.game.Game;

public class Shockwave extends Weapon {

	private boolean enable;
	
	public Shockwave(Game game, int x, int y) {
		super(game, x, y, 1, 1);
		this.enable = false;
	}

	@Override
	public void computerAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "ShockWave: " + this.enable;
	}

}
