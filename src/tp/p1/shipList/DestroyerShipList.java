package tp.p1.shipList;

import java.util.Random;

import tp.p1.game.Level;
import tp.p1.ship.Bomb;
import tp.p1.ship.DestroyerShip;

public class DestroyerShipList {
	
	private DestroyerShip list[];
	private int tam;
	private double freq;
	
	public DestroyerShipList(Level level) {
		this.tam = level.getDestroyerShip();
		this.freq = level.getFreq();
		list = new DestroyerShip[this.tam];
		this.initArray(level);
	}
	private void initArray(Level level) {
		
		if(level.name() == "EASY") {
			for(int i = 0; i < 2; i++) {
				list[i] = new DestroyerShip(4 + i,2);
			}
		}
		if(level.name() == "HARD") {
			for(int i = 0; i < 2; i++) {
				list[i] = new DestroyerShip(4 + i,3);
			}
		}
		if(level.name() == "INSANE") {
			for(int i = 0; i < 4; i++) {
				list[i] = new DestroyerShip(3 + i,3);
			}
		}
	}
	
	public int getTam() {
		return this.tam;
	}
	private double getFreq() {
		return this.freq;
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
