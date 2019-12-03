package tp.p1.object;

import tp.p1.game.Game;

public class Laser extends Weapon {
	
	public Laser(Game game, int x, int y) {
		super(game,x,y,1,1);
	}

	@Override
	public void computerAction() {}

	@Override
	public void onDelete() {
		this.game.enableLaser();
	}

	@Override
	public void move(boolean down,boolean movement) {
		this.y--;
		if(!this.game.isOnBoard(this.x,this.y)) this.onDelete();
	}

	@Override
	public String toString() {
		return "oo";
	}
	
	@Override
	public String toSerialize() {
		return "L; "+ this.x +";"+ this.y;
	}
	
	@Override
	public boolean performAttack(GameObject other){
		other.receiveLaserAttack(this.damage);
		this.onDelete();
		return true;
	}
	
	@Override
	public boolean receiveBombAttack(int damage){
		this.getDamage(damage);
		return true;
	}
}
