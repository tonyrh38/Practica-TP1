package tp.p1.ship;

public class DestroyerShip {
	//Atributos
			private int _x;
			private int _y;
			private int _vida;
			private int _daño;
			private int _puntos;
			
	//Metodos
	
	//Constructor
		public DestroyerShip(int x, int y) {
			this.setX(x);
			this.setY(y);
			this.setVida(1);
			this.setDaño(1);
			this.setPuntos(10);
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
		public int getDaño() {
			return _daño;
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
		public void setDaño(int daño) {
			this._daño = daño;
		}
		public void setPuntos(int puntos) {
			this._puntos = puntos;
		}
		
}
