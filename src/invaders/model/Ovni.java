package invaders.model;

import invaders.game.Game;
import invaders.interfaces.IExecuteRandomActions;

public class Ovni extends EnemyShip implements IExecuteRandomActions{
	
	private boolean enable;

	public Ovni(Game game) {
		super(game,8,0,1,25);
		this.enable = false;
	}

	@Override
	public void computerAction() {
		if(!this.enable && IExecuteRandomActions.canGenerateRandomOvni(this.game)) {
			this.enable = true;
		}
	}
	
	@Override
	public void onDelete(){
		super.onDelete();
		this.game.enableShockWave();
		this.enable = false;
	}
	
	@Override
	public void move(boolean down,boolean movement) {
		this.x--;
		if(!this.game.isOnBoard(this.x,this.y)) this.enable = false;
	}

	@Override
	public String toString() {
		if(this.enable) return "O["+ this.life +"]";
		else return "";
	}
	
	@Override
	public String toSerialize() {
		return "O; "+ this.x +";"+ this.y +";"+ this.life;
	}
}
