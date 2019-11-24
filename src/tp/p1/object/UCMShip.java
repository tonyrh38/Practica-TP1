package tp.p1.object;

import tp.p1.game.Game;

public class UCMShip extends Ship{
	
		private Shockwave shockwave;
		private Laser laser;
		
		private Game game;

		public UCMShip(Game game,int x,int y) {
			super(game,x,y,3,0);
		}

		@Override
		public void computerAction() {
			
		}

		@Override
		public void onDelete() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void move() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String toString() {
			if(this.isAlive()) return "^__^";
			else return "!xx!";
		}
	
	/*// Getters 
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
		public void setDa�o(int damage) {
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
			if(this._laser != null) return this._laser.isLaserIn(i,j);
			else return false;
		}
		public String laserToString() {
			return this._laser.toString();
		}
		public void move(int move, int num) {
			this._x += move * num;
			if(this._x < 0) this._x = 0;
			else if(this._x >= this._game.getX_SIZE()) this._x = this._game.getX_SIZE() - 1;
		}
		public void shoot() {
			if(this._laser == null)	this._laser = new Laser(this._x, this._y, this._game);
		}
		public void shockwave() {
			if(this._shockwave) {
				this._game.damageAll();
				this._shockwave = false;
			}
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
		public boolean isDefeated() {
			return this._vida <= 0;
		}
		public void setDefeat() {
			this._vida = 0;
		}*/
}