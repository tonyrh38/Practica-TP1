package tp.p1.ship;

import tp.p1.game.Game;

public class RegularShip {
	
	//Atributos
		private int _x;
		private int _y;
		private int _vida;
		private int _puntos;
		
	//Metodos
	
	//Constructor
		public RegularShip(int x, int y) {
			this.setX(x);
			this.setY(y);
			this.setVida(2);
			this.setPuntos(5);
		}
		
	//Getters
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
		
	//Setters
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
		
		public void damage(Game game) {
			this.setVida(this.getVida() - 1);
			if(this.getVida() == 0) {
				game.setPuntuation(game.getPuntuation() + this.getPuntos());
			}
		}
}
