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
			this._tam = level.getDestroyerShip() + 1;
			this._list = new Bomb[this._tam];
		}
		
	// Logica
	private boolean _isPosNull(int pos) {
		return this._list[pos] == null;
	}
	public void update() {
		// Completar
	}
	public void insert(Bomb bomb) {
		// Completar
		this._list[0] = bomb;
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
