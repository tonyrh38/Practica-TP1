package tp.p1.shipList;

import tp.p1.game.Level;
import tp.p1.ship.Bomb;


public class BombList {
	
	// Atributos
		private Bomb _list[];
		private int _tam;
		
	// Metodos
		
	//Constructor
		public BombList(Level level) {
			this._tam = level.getDestroyerShip();
			this._list = new Bomb[this._tam];
		}
		
	// Logica	
	public boolean isBombIn(int i, int j) {
		int idx = 0;
		boolean found = false;
		while(idx < this._tam && !found) {
			found = this._list[idx] != null && this._list[idx].getX() == j && this._list[idx].getY() == i;
			idx++;
		}
		return found;
	}
	public String bombInToString(int i, int j) {
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
	public void insert(Bomb bomb) {
		int i = 0;
		boolean inserted = false;
		while(i < this._tam && !inserted) {
			if(this._list[i] == null) {
				this._list[i] = bomb;
				inserted = true;
			}
			i++;
		}
	}
	public boolean impactLaser(int x, int y) {
		boolean impacted = false;
		for(int i = 0; i < this._tam && !impacted; i++) {
			if(this._list[i] != null && this._list[i].getX() == x && this._list[i].getY() == y) {
				this._list[i] = null;
				impacted = true;
			}
		}
		return impacted;
	}
	public void update() {
		for(int i = 0; i < this._tam; i++) {
			if(this._list[i] != null)	{
				if(!this._list[i].update()) {
					// En caso de que la bomba ya no se deba mostrar por pantalla, se elimina
					this._list[i] = null;
				}
			}
		}
	}	
}
