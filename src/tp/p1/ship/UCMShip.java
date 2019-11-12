package tp.p1.ship;

import tp.p1.game.Game;

public class UCMShip {
	
	// Atributos
		private int _x;
		private int _y;
		private int _vida;
		private int _damage;
		private boolean _shockwave;
		private Laser _laser;
		
		private Game _game;
	
	// Metodos
	
	// Constructor
		public UCMShip(Game game) {
			this.setX(4);
			this.setY(7);
			this.setVida(3);
			this.setDaño(1);
			this.setShockwave(false);
			this._game = game;
		}
	
	// Getters 
		public int getX() {
			return this._x;
		}
		public int getY() {
			return _y;
		}
		public int getVida() {
			return _vida;
		}
		public int getDamage() {
			return _damage;
		}
		public boolean getShockwave() {
			return _shockwave;
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
		public void setDaño(int damage) {
			this._damage = damage;
		}
		public void setShockwave(boolean shockwave) {
			this._shockwave = shockwave;
		}
		
		public void damage() {
			if(this.getVida() > 0) this.setVida(this.getVida() - 1);
		}
		
	// Logica
		public boolean isPlayerIn(int i, int j) {
			return this._x == j && this._y == i;
		}
		public String toString() {
			return "^__^";
		}
		public boolean isLaserIn(int i, int j) {
			return this._laser.isLaserIn(i,j);
		}
		public String laserToString() {
			return this._laser.toString();
		}
		public void updateLaser() {
			if(this._laser != null)	this._laser.update();
		}
		public void update() {
			
		}	
}
