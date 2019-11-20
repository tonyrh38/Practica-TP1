package tp.p1.ship;

import tp.p1.game.Game;

public class RegularShip {
	
	// Atributos
		private int _x;
		private int _y;
		private int _vida;
		private int _puntos;
		
		private Game _game;
		
	// Metodos
	
	// Constructor
		public RegularShip(int x, int y, Game game) {
			this.setX(x);
			this.setY(y);
			this.setVida(2);
			this.setPuntos(5);
			this._game = game;
		}
		
	// Getters
		public int getX() {
			return _x;
		}
		public int getY() {
			return _y;
		}
		public int getVida() {
			return _vida;
		}
		public int getPuntos() {
			return _puntos;
		}
		
	// Setters
		public void setX(int x) {
			this._x = x;
		}
		public void setY(int y) {
			this._y = y;
		}
		public void setVida(int vida) {
			this._vida = vida;
		}
		public void setPuntos(int puntos) {
			this._puntos = puntos;
		}
		
	// Logica
		public String toString() {
			return "C["+ this._vida +"]";
		}
		public void damage() {
			this.setVida(this.getVida() - 1);
		}
		public boolean isDestroyed() {
			if(this._vida <= 0) {
				this._game.updatePuntuation(this._puntos);
				return true;
			}
			else return false;
		}
		public boolean isOnWall() {
			return this._x == 0 || this._x == this._game.getX_SIZE() - 1;
		}
		public void update(boolean movement, boolean down) {
			if(down) this._y++;
			else if(movement) this._x++;
			else this._x--;
		}
		public boolean win() {
			return this._y == this._game.getY_SIZE() - 1;
		}
}
