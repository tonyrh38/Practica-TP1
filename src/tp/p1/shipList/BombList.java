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
	public void update() {
		// Completar
	}
	
	public void destroyBomb(int pos) {
		this.list[pos] = null;
	}

	public void movePlayerBomb() {
		int posPlayer = this.getPosPlayer();
		if(this.getYBombPlayer() > 0) this.setYPos(posPlayer, this.getYBombPlayer() - 1);
		else this.destroyBomb(posPlayer);
	}
	
	public boolean impactBomb() {
		boolean impacted = false;
		for(int i = 0; i < this.getTam() - 1 && !impacted; i++) {
			if(!this.isPosNull(i) && this.getXPos(i) == this.getXBombPlayer() && this.getYPos(i) == this.getYBombPlayer()) {
				this.destroyBomb(i);
				impacted = true;
			}
		}
		
		return impacted;
	}
	
	public void moveShipBombs() {
		for(int i = 0; i < this.getTam() - 1; i++) {
			if(!this.isPosNull(i) && this.getYPos(i) < 7) this.setYPos(i, this.getYPos(i) + 1);
			else this.destroyBomb(i);
		}	
	}

	public boolean impactBombPlayer(int x, int y) {
		boolean impacted = false;
		for(int i = 0; i < this.getTam() - 1; i++) {
			if(!this.isPosNull(i) && this.getXPos(i) == x && this.getYPos(i) == y) {
				this.destroyBomb(i);
				impacted = true;
			}
		}
		return impacted;
	}

	
	
	
}
