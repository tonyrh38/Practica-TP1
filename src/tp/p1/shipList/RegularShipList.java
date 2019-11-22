package tp.p1.shipList;

import tp.p1.game.Game;
import tp.p1.game.Level;
import tp.p1.ship.RegularShip;

public class RegularShipList {
	
	// Atributos	
		private RegularShip _list[];
		private int _tam;
	
	// Metodos
		
	// Constructor
		public RegularShipList(Level level, Game game) {
			this._tam = level.getRegularShip();
			this._list = new RegularShip[this._tam];
			this._initArray(level, game);
		}
		
	// Logica
		private void _initArray(Level level, Game game) {
			
			for(int i = 0; i < 4; i++) {
				this._list[i] = new RegularShip(3 + i,1, game);
			}
			if(level.name() != "EASY") {
				for(int i = 0; i < 4; i++) {
					this._list[4 + i] = new RegularShip(3 + i,2, game);
				}
			}	
		}
		
		public boolean isShipIn(int i, int j) {
			int idx = 0;
			boolean found = false;
			while(idx < this._tam && !found) {
				found = this._list[idx] != null && this._list[idx].getX() == j && this._list[idx].getY() == i;
				idx++;
			}
			return found;
		}
		public String shipInToString(int i, int j) {
			int idx = 0, position = 0;
			boolean found = false;
			while(idx < this._tam && !found) {
				if(this._list[idx] != null && this._list[idx].getX() == j && this._list[idx].getY() == i) {
					position = idx;
					found = true;
				}
				idx++;
			}
			return this._list[position].toString();
		}
		public int getRegularRemaining() {
			int remaining = 0;
			for(int i = 0; i < this._tam;i++) {
				if(this._list[i] != null) remaining++;
			}
			return remaining;
		}
		public void shockwaveDamage() {
			for(int i = 0; i < this._tam; i++) {
				if(this._list[i] != null) {
					this._list[i].damage(1);
					if(this._list[i].isDestroyed()) this._list[i] = null;
				}
			}
		}	
		public boolean impactLaser(int x, int y, int damage) {
			boolean impacted = false;
			for(int i = 0; i < this._tam && !impacted; i++) {
				if(this._list[i] != null && this._list[i].getX() == x && this._list[i].getY() == y) {
					this._list[i].damage(damage);
					if(this._list[i].isDestroyed()) this._list[i] = null;
					impacted = true;
				}
			}
			return impacted;
		}
		public boolean isOnWall() {
			boolean inWall = false;
			for(int i = 0; i < this._tam && !inWall; i++) {
				if(this._list[i] != null) inWall = this._list[i].isOnWall();
			}
			return inWall;
		}	
		public void update(boolean movement, boolean down) {
			for(int i = 0; i < this._tam; i++) {
				if(this._list[i] != null) this._list[i].update(movement, down);
			}
		}	
		public boolean win() {
			boolean win = false;
				for(int i = 0; i < this._tam && !win; i++) {
					if(this._list[i]!= null) win = this._list[i].win();
				}
			return win;
		}	
		public boolean lose() {
			boolean lose = true;
			for(int i = 0; i < this._tam && lose; i++) {
				if(this._list[i] != null) lose = false;
			}
			return lose;
		}		
}
