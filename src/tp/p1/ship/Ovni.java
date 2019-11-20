package tp.p1.ship;

import tp.p1.game.Game;

public class Ovni {
	// Atributos
	private int _x;
	private int _y;
	private int _vida;
	private int _puntos;
	
	private Game _game;
	
	// Metodos

	// Constructor
		public Ovni(Game game) {
			this._vida = 1;
			this._puntos =25;
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
		
	// Logica
		public boolean isOvniIn(int i, int j) {
			return this._x == j && this._y == i;
		}
		public String toString() {
			return "O["+ this._vida +"]";
		}
		public boolean impactLaser(int x, int y) {
			if(this._x == x && this._y == y) {
				this._game.updatePuntuation(this._puntos);
				return true;
			}
			else return false;
		}
		public void update() {
			this._x--;
		}

		

		
}
