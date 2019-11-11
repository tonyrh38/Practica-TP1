package tp.p1.ship;

public class Bomb {
	
	// Atributos
		private int _x;
		private int _y;
		private int _damage;
	
	// Metodos
	
	// Constructor	
		public Bomb(int x, int y) {
			this.setX(x);
			this.setY(y);
			this._damage = 1;
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
	public void update() {
		
	}
}
