package tp.p1.ship;

import tp.p1.game.Game;

public class Laser {
	
	// Atributos
		private int _x;
		private int _y;
		private int _damage;
		
		private Game _game;
	
	// Metodos
	
	// Constructor	
		public Laser(int x, int y, Game game) {
			this._x = x;
			this._y = y;
			this._damage = 1;
			this._game = game;
		}
	
	// Getters
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
	}	
}
