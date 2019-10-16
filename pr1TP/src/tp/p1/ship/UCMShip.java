package tp.p1.ship;

public class UCMShip {
	
	//Atributos
		private int _x;
		private int _y;
		private int _vida;
		private int _damage;
		private boolean _shockwave;
	
	//Metodos
	
	//Constructor
		public UCMShip() {
			this.setX(4);
			this.setY(7);
			this.setVida(3);
			this.setDamage(1);
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
		public int getdamage() {
			return _damage;
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
		public void setDamage(int damage) {
			this._damage = damage;
		}
		public void setShockwave(boolean shockwave) {
			this._shockwave = shockwave;
		}
		
		public void damage() {
			this.setVida(this.getVida() - 1);
		}
}
