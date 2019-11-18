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
		
	// Logica
		public boolean isPlayerIn(int i, int j) {
			return this._x == j && this._y == i;
		}
		public String toString() {
			if(this._vida > 0) return "^__^";
			else return "!xx!";
		}
		public boolean isLaserIn(int i, int j) {
			return this._laser.isLaserIn(i,j);
		}
		public String laserToString() {
			return this._laser.toString();
		}
		public void updateLaser() {
			if(this._laser != null)	{
				if(!this._laser.update()) {
					// En caso de que el laser ya no se deba mostrar por pantalla, se elimina
					this._laser = null;
				}
			}
		}
		public boolean impactBomb(int x, int y) {
			if(this._laser != null && this._laser.impactBomb(x,y)) {
				this._laser = null;
				return true;
			}
			else if(this._x == x && this._y == y) {
				this._vida--;
				return true;
			}
			else return false;
		}
		public void update() {
			
		}	
		public void damage() {
			if(this.getVida() > 0) this.setVida(this.getVida() - 1);
		}
}
