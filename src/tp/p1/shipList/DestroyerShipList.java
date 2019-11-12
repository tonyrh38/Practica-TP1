package tp.p1.shipList;

import java.util.Random;
import tp.p1.game.Game;
import tp.p1.game.Level;
import tp.p1.ship.Bomb;
import tp.p1.ship.DestroyerShip;

public class DestroyerShipList {
	
	// Atributos
		private DestroyerShip _list[];
		private int _tam;
		private double _freq;
	
	// Metodos	
	
	// Constructor
		public DestroyerShipList(Level level,  Game game) {
			this._tam = level.getDestroyerShip();
			this._freq = level.getFreq();
			this._list = new DestroyerShip[this._tam];
			this._initArray(level, game);
		}
	
	// Logica
		private void _initArray(Level level, Game game) {
			
			if(level.name() == "EASY") {
				for(int i = 0; i < 2; i++) {
					this._list[i] = new DestroyerShip(4 + i,2, game);
				}
			}
			if(level.name() == "HARD") {
				for(int i = 0; i < 2; i++) {
					this._list[i] = new DestroyerShip(4 + i,3, game);
				}
			}
			if(level.name() == "INSANE") {
				for(int i = 0; i < 4; i++) {
					this._list[i] = new DestroyerShip(3 + i,3, game);
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
		public int getDestroyerRemaining() {
			int remaining = 0;
			for(int i = 0; i < this._tam;i++) {
				if(this._list[i] != null) remaining++;
			}
			return remaining;
		}
		public void dropBombs(Random rand) {
			for(int i = 0; i < this._tam; i++ ) {
				if(rand.nextInt(10) <= this._freq * 10) {
					this._list[i].dropBomb();
				}
			}
		}
		public boolean impactLaser(int x, int y) {
			boolean impacted = false;
			for(int i = 0; i < this._tam && !impacted; i++) {
				if(this._list[i] != null && this._list[i].getX() == x && this._list[i].getY() == y) {
					this._list[i].damage();
					if(this._list[i].isDestroyed()) this._list[i] = null;
					impacted = true;
				}
			}
			return impacted;
		}
		public void update() {
			// Completar
		}
	public boolean DestroyershipWins() {
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
