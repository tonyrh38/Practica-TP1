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

	public int getXPos(int i) {
		return this.list[i].getX();
	}
	public int getYPos(int i) {
		return this.list[i].getY();
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
}
