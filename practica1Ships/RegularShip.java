package tp.practica1Ships;

import tp.practica1Game.Game;
import tp.practica1cosasRandom.IExecuteRandomActions;

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
	
	@Override
	public String toSerialize() {
		if(this.explode) {
			if(this.game.getMovement())	return "E; "+ this.x +";"+ this.y +";"+ this.life +";"+
					(this.game.getLevel().getNumCyclesToMoveOneCell() -  this.game.getCurrentCycle()) % this.game.getLevel().getNumCyclesToMoveOneCell()+";right";
			else  return "E; "+ this.x +";"+ this.y +";"+ this.life +";"+
			(this.game.getLevel().getNumCyclesToMoveOneCell() -  this.game.getCurrentCycle()) % this.game.getLevel().getNumCyclesToMoveOneCell()+";left";
		}
		else if(this.game.getMovement())	return "R; "+ this.x +";"+ this.y +";"+ this.life +";"+
			(this.game.getLevel().getNumCyclesToMoveOneCell() -  this.game.getCurrentCycle()) % this.game.getLevel().getNumCyclesToMoveOneCell()+";right";
			else  return "R; "+ this.x +";"+ this.y +";"+ this.life +";"+
			(this.game.getLevel().getNumCyclesToMoveOneCell() -  this.game.getCurrentCycle()) % this.game.getLevel().getNumCyclesToMoveOneCell()+";left";
		}
}
