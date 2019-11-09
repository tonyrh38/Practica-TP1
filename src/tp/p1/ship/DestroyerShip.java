package tp.p1.ship;

import tp.p1.game.Game;

public class DestroyerShip {
	// Atributos
		private int _x;
		private int _y;
		private int _vida;
		private int _puntos;
		private Bomb _bomb;
		
		private Game _game;
			
	// Metodos
	
	// Constructor
		public DestroyerShip(int x, int y, Game game) {
			this.setX(x);
			this.setY(y);
			this.setVida(1);
			this.setPuntos(10);
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
		public void damage() {
			if(this.getVida() > 0) {
				this.setVida(this.getVida() - 1);
			}
		}
		public void update() {
			
		}
}
