package tp.p1.shipList;

import tp.p1.game.Level;
import tp.p1.ship.*;

public class BombList {
	
	private Bomb list[];
	private int tam;
	private int posPlayer;

	public BombList(Level level) {
		this.posPlayer = level.getDestroyerShip();
		this.tam = level.getDestroyerShip() + 1;
		list = new Bomb[this.tam];
	}

	public int getTam() {
		return this.tam;
	}
	public int getPosPlayer() {
		return this.posPlayer;
	}

	public int getXPos(int pos) {
		return this.list[pos].getX();
	}
	public int getYPos(int pos) {
		return this.list[pos].getY();
	}
	
	public int getXBombPlayer() {
		return this.list[this.getPosPlayer()].getX();
	}
	public int getYBombPlayer() {
		return this.list[this.getPosPlayer()].getY();
	}
	
	public void setYPos(int pos, int y) {
		this.list[pos].setY(y);
	}
	
	public boolean isPosNull(int pos) {
		return this.list[pos] == null;
	}
	
	public boolean isPlayerBombNull() {
		return this.list[this.getPosPlayer()] == null;
	}
	
	public void insertIn(int pos, Bomb bomb) {
		this.list[pos] = bomb;
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
