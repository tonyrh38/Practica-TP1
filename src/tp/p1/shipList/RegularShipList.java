package tp.p1.shipList;

import tp.p1.game.Level;
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
	public int getXPos(int pos) {
		return this.list[pos].getX();
	}
	public int getYPos(int pos) {
		return this.list[pos].getY();
	}
	public int getPuntPos(int pos) {
		return this.list[pos].getPuntos();
	}
	
	public void setXPos(int pos, int x) {
		this.list[pos].setX(x);
	}
	public void setYPos(int pos, int y) {
		this.list[pos].setY(y);
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
