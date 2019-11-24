package tp.p1.object;

import tp.p1.game.Game;
import tp.p1.interfaces.IExecuteRandomActions;

public class Ovni extends EnemyShip implements IExecuteRandomActions{
	
	private boolean enable;

	public Ovni(Game game) {
		super(game,8,0,1,25);
		this.enable = false;
	}

	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "O["+ this.life +"]";
	}
	
/*// Logica
	public boolean isOvniIn(int i, int j) {
		return this._x == j && this._y == i;
	}
	public String toString() {
		return "O["+ this._vida +"]";
	}
	public boolean impactLaser(int x, int y, int damage) {
		if(this._x == x && this._y == y) {
			this._vida -= damage;
			return true;
		}
		else return false;
	}
	public boolean isDestroyed() {
		if(this._vida <= 0) {
			this._game.updatePuntuation(this._puntos);
			return true;
		}
		else return false;
	}
	public void update() {
		this._x--;
	}

	public void shockwaveDamage() {
		this._vida--;
	}

	*/	
}
