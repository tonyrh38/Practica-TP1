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
	public void update() {
		//Completar
	}
	
	public boolean RegularshipWins() {
		boolean win = false;
			for(int i = 0; i < this.getTam(); i++) {
				if(this.getVidaPos(i) > 0 && this.getYPos(i) == 7) win = true;
			}
		return win;
	}
	
	public void damagePos(int i) {
		this.list[i].damage();
	}
	
	public void damageAll() {
		for(int i = 0; i < this.getTam(); i++) {
			this.damagePos(i);
		}
	}
	
	public int calculatePuntuation() {
		int points = 0;
		for(int i = 0; i < this.getTam(); i++) {
			if(this.getVidaPos(i) == 0) {
				points += this.getPuntPos(i);
			}
		}
		return points;
	}

	public boolean impactBomb(int xBombPlayer, int yBombPlayer) {
		boolean impacted = false;
		for(int i = 0; i < this.getTam() && !impacted; i++) {
			if(this.getVidaPos(i) > 0 && this.getXPos(i) == xBombPlayer && this.getYPos(i) == yBombPlayer) {
				this.damagePos(i);
				impacted = true;
			}
		}
		return impacted;
	}
	
	public boolean isWall() {
		boolean wall = false;
		for(int i = 0; i < this.getTam() && !wall; i++) {
			if(this.getVidaPos(i) > 0 && (this.getXPos(i) == 0 || this.getXPos(i) == 8)) wall = true;
		}
		return wall;
	}
	
	public void moveLateralShips(boolean direction) {
		int dir = (direction)? 1 : -1;
		for(int i = 0; i < this.getTam(); i++) {
			this.setXPos(i, this.getXPos(i) + dir);
		}
	}
	
	public void moveDownShips() {
		for(int i = 0; i < this.getTam(); i++) {
			this.setYPos(i, this.getYPos(i) + 1);
		}
	}
}
