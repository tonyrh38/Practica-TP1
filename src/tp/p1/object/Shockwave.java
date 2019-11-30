package tp.p1.object;

import tp.p1.game.Game;

public class Shockwave extends Weapon {

	private boolean enable;
	
	public Shockwave(Game game, int x, int y) {
		super(game, x, y, 1, 1);
		this.enable = false;
	}

	@Override
	public void computerAction() {}

	@Override
	public void onDelete(){}

	@Override
	public void move() {}

	@Override
	public String toString() {
		return "ShockWave: " + this.enable;
	}

	public boolean attack() {
		if(this.enable) {
			
			this.enable = false;
			return true;
		}
		return false;
	}

	public void enable() {
		this.enable = true;
	}

}
