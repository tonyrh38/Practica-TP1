package tp.p1.object;

import tp.p1.game.Game;

public class Supermisile extends Weapon {
	
	public Supermisile(Game game, int x, int y) {
		super(game,x,y,1,2);
	}

	@Override
	public void computerAction() {}

	@Override
	public void onDelete() {}

	@Override
	public void move(boolean down,boolean movement) {
		this.y--;
		if(!this.game.isOnBoard(this.x,this.y)) this.onDelete();
	}

	@Override
	public String toString() {
		return "A";
	}
	
	@Override
	public String toSerialize() {
		return "X; "+ this.x +";"+ this.y;
	}
	
	@Override
	public boolean performAttack(GameObject other){
		other.receiveLaserAttack(this.damage);
		this.onDelete();
		return true;
	}
}
