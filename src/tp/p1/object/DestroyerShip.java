package tp.p1.object;

import tp.p1.game.Game;

public class DestroyerShip extends AlienShip{
		
	private Bomb bomb;

	public DestroyerShip( Game game, int x, int y) {
		super(game,x,y,2,10);
	}

	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "D["+ this.life +"]";
	}
		
	/*// Logica
		
		public void dropBomb() {
			if(this._bomb != null) {
				Bomb bomb = new Bomb(this._x,this._y,this._game);
				this._game.insertBomb(bomb);
			}
		}
		public void damage(int damage) {
			this._vida -= damage;
		}
		public boolean isDestroyed() {
			if(this._vida <= 0) {
				this._game.updatePuntuation(this._puntos);
				return true;
			}
			else return false;
		}
		public boolean isBombIn(int x, int y) {
			return this._bomb.getX() == x && this._bomb.getY() == y;
		}
		public void destroyBomb() {
			this._bomb = null;
		}
		public boolean isOnWall() {
			return this._x == 0 || this._x == this._game.getX_SIZE() - 1;
		}
		public void update(boolean movement, boolean down) {
			if(down) this._y++;
			else if(movement) this._x++;
			else this._x--;
		}
		public boolean win() {
			return this._y == this._game.getY_SIZE() - 1;
		}*/
}
