package tp.p1.object;

import tp.p1.game.Game;
import tp.p1.interfaces.IExecuteRandomActions;

public class DestroyerShip extends AlienShip implements IExecuteRandomActions {
		
	private Bomb bomb;

	public DestroyerShip( Game game, int x, int y) {
		super(game,x,y,1,10);
	}

	@Override
	public void computerAction() {
		if(this.bomb == null && IExecuteRandomActions.canGenerateRandomBomb(this.game)) {
			this.bomb = new Bomb(this.game,this.x,this.y,this);
			this.game.addObject(this.bomb);
		}
	}

	@Override
	public String toString() {
		return "D["+ this.life +"]";
	}
	
	@Override
	public String toSerialize() {
		if(this.game.getMovement())	return "E; "+ this.x +";"+ this.y +";"+ this.life +";"+
				(this.game.getLevel().getVel() -  this.game.getCurrentCycle() % this.game.getLevel().getVel())+";right";
		else  return "E; "+ this.x +";"+ this.y +";"+ this.life +";"+
		(this.game.getLevel().getVel() -  this.game.getCurrentCycle() % this.game.getLevel().getVel())+";left";
	}

	public void deleteBomb() {
		this.bomb = null;
	}
}
