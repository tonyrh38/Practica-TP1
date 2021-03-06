package tp.p1.object;

import tp.p1.exception.CommandExecuteException;
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
	public void move(boolean down,boolean movement) {}

	@Override
	public String toString() {
		return "ShockWave: " + this.enable;
	}
	@Override
	public String toSerialize() {
		return "";
	}

	public void attack() throws CommandExecuteException {
		if(this.enable) {
			this.game.shockwaveAttack(this.damage);
			this.enable = false;
		}
		else throw new CommandExecuteException("La shockwave no esta disponible");
	}

	public void enable() {
		this.enable = true;
	}
}
