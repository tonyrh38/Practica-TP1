package tp.p1.ship;

public class UCMShip {
	
	//Atributos
		private int _x;
		private int _y;
		private int _vida;
		private int _da�o;
		private boolean _shockwave;
	
	//Metodos
	
	//Constructor
		public UCMShip() {
			this.setX(7);
			this.setY(4);
			this.setVida(3);
			this.setDa�o(1);
			this.setShockwave(false);
		}
	
	//Getters 
		public int getX() {
			return this._x;
		}
		public int getY() {
			return _y;
		}
		public int getVida() {
			return _vida;
		}
		public int getDa�o() {
			return _da�o;
		}
		public boolean getShockwave() {
			return _shockwave;
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
		public void setDa�o(int da�o) {
			this._da�o = da�o;
		}
		public void setShockwave(boolean shockwave) {
			this._shockwave = shockwave;
		}	
}
