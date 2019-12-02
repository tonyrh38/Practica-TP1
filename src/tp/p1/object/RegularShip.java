package tp.p1.object;

import tp.p1.game.Game;
import tp.p1.interfaces.IExecuteRandomActions;

public class RegularShip extends AlienShip{
	
	private boolean explode;
		
	public RegularShip(Game game, int x, int y) {
		super(game,x,y,2,5);
		this.explode = false;
	}

	@Override
	public void computerAction() {
		if(!this.explode && IExecuteRandomActions.canBecomeExplosiveShip(this.game)) {
			this.explode = true;
		}
	}
	
	@Override
	public void onDelete() {
		super.onDelete();
		if(this.explode) {
			this.game.ShipExplodesIn(this.x,this.y);
		}
	}
	
	@Override
	public String toString() {
		if(this.explode) return "E["+ this.life +"]";
		else return "C["+ this.life +"]";
	}
}
