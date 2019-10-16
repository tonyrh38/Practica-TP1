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

	public Bomb getPos(int pos) {
		return this.list[pos];
	}
	
	public int getXPos(int pos) {
		return this.list[pos].getX();
	}
	public int getYPos(int pos) {
		return this.list[pos].getY();
	}
	
	public void setYPos(int pos, int y) {
		this.list[pos].setY(y);
	}
	
	public boolean isPosNull(int pos) {
		return this.list[pos] == null;
	}
	
	public void insertIn(int pos, Bomb bomb) {
		this.list[pos] = bomb;
	}
	public void destroyBomb(int pos) {
		this.list[pos] = null;
	}

	public void movePlayerBomb() {
		
		if(this.posPlayer > 0 && this.list[this.posPlayer] != null) { 
			this.setYPos(this.posPlayer, this.getYPos(this.posPlayer) - 1);
		}
		else {
			this.destroyBomb(this.posPlayer);
		}
	}
}
