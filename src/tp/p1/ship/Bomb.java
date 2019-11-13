package tp.p1.ship;

import tp.p1.game.Game;

public class Bomb {
	
	// Atributos
		private int _x;
		private int _y;
		private int _damage;
		
		private Game _game;
	
	// Metodos
	
	// Constructor	
		public Bomb(int x, int y, Game game) {
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
	public String toString() {
		return ".";
	}
	public boolean update() {
		this._y++;
		if(this._y < this._game.getX_SIZE()) {
			return !this._game.impactBomb(this._x, this._y);
		}
		else return false;
	}
}
