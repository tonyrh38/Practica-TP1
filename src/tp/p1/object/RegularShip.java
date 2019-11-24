package tp.p1.object;

import tp.p1.game.Game;

public class RegularShip extends AlienShip{
		
	public RegularShip(Game game, int x, int y) {
		super(game,x,y,2,5);
	}

	@Override
	public void computerAction() {
		
	}
	
	@Override
	public String toString() {
		return "C["+ this.life +"]";
	}
	/*public boolean isOnWall() {
	return this.x == 0 || this.x == Game.DIM_X - 1;
	}
	public void update(boolean movement, boolean down) {
		if(down) this._y++;
		else if(movement) this._x++;
		else this._x--;
	}
	public boolean win() {
		return this._y == this._game.getY_SIZE() - 1;
	}
	*/
}
