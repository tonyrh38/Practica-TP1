package tp.p1.object;

import tp.p1.game.Game;

public class Laser extends Weapon {
	
	public Laser(Game game, int x, int y) {
		super(game,x,y,1,1);
		this.game.addObject(this);
	}

	@Override
	public void computerAction() {}

	@Override
	public void onDelete() {}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "oo";
	}
	
	@Override
	public boolean performAttack(GameObject other){
		other.receiveLaserAttack(this.damage);
		return true;
	}
	
	/*// Getters
	public int getX() {
		return _x;
	}
	public int getY() {
		return _y;
	}
	public int getDamage() {
		return _damage;
	}
	
	// Setters
	public void setX(int x) {
		this._x = x;
	}
	public void setY(int y) {
		this._y = y;
	}
	
	// Logica
	public boolean isLaserIn(int i, int j) {
		return this._x == j && this._y == i;
	}
	public String toString() {
		return "oo";
	}
	public boolean impactBomb(int x, int y) {
		return this._x == x && this._y == y;
	}
	public boolean update() {
		this._y--;
		if(this._y > 0) {
			// Si el laser sigue vivo, se devuelve true
			return !this._game.impactLaser(this._x, this._y, this._damage);
		}
		else {
			// Si el laser se sale de rango, se devuelve false
			return false;
		}
	}	*/
}
