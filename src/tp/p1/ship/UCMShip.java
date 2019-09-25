package tp.p1.ship;

public class UCMShip {
	
	//Atributos
		private int _x;
		private int _y;
		private int _daño;
	
	//Metodos
	
	//Constructor
		public UCMShip() {
			this.setX(7);
			this.setY(4);
			this.setDaño(1);
		}
	
	//Getters 
		public int getX() {
			return this._x;
		}
		public int getY() {
			return _y;
		}
		public int getDaño() {
			return _daño;
		}
	
	//Setters
		public void setX(int x) {
			this._x = x;
		}
		public void setY(int y) {
			this._y = y;
		}
		public void setDaño(int daño) {
			this._daño = daño;
		}
}
