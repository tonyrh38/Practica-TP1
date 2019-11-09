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
		public DestroyerShipList( Game game, Level level) {
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
		public void update() {
			// Completar
		}
	
	public int getDestroyerRemaining() {
		int remaining = 0;
		for(int i = 0; i < this.getTam();i++) {
			if(this.getVidaPos(i) > 0) remaining++;
		}
		return remaining;
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
	
	public void dropBomb(Random rand, BombList bombList) {
		for(int i = 0; i < this.getTam(); i++ ) {
			if(bombList.isPosNull(i)) {
				if(rand.nextInt(10) <= this.getFreq() * 10) {
					Bomb bomb = new Bomb(this.getXPos(i),this.getYPos(i));
					bombList.insertIn(i, bomb);
				}
			}
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
