package tp.p1.shipList;

import tp.p1.game.Level;
import tp.p1.ship.DestroyerShip;
import tp.p1.ship.RegularShip;

public class RegularShipList {
	
	private RegularShip list[];
	private int tam;
	
	public RegularShipList(Level level) {
		this.tam = level.getRegularShip();
		this.list = new RegularShip[this.tam];
		this.initArray(level);
	}
	
	private void initArray(Level level) {
		
		for(int i = 0; i < 4; i++) {
			list[i] = new RegularShip(3 + i,1);
		}
		if(level.name() != "EASY") {
			for(int i = 0; i < 4; i++) {
				list[4 + i] = new RegularShip(3 + i,2);
			}
		}	
	}
	
	public int getTam() {
		return this.tam;
	}

	public int getVidaPos(int pos) {
		return this.list[pos].getVida();
	}
	
	public RegularShip getPos(int pos) {
		return this.list[pos];
	}
	
	public int getYPos(int pos) {
		return this.list[pos].getY();
	}
	public int getXPos(int pos) {
		return this.list[pos].getX();
	}
	public int getPuntPos(int pos) {
		return this.list[pos].getPuntos();
	}
	
	public int getRegularRemaining() {
		int remaining = 0;
		for(int i = 0; i < this.getTam();i++) {
			if(this.getVidaPos(i) > 0) remaining++;
		}
		return remaining;
	}
	public boolean RegularshipWins() {
		boolean win = false;
			for(int i = 0; i < this.getTam(); i++) {
				if(this.getYPos(i) == 7) win = true;
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
}
