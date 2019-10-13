package tp.p1.shipList;

import tp.p1.game.Level;
import tp.p1.ship.*;

public class BombList {
	
	private Bomb list[];
	private int tam;

	public BombList(Level level) {
		this.tam = level.getDestroyerShip() + 1;
		list = new Bomb[this.tam];
	}

	public int getTam() {
		return this.tam;
	}

	public Bomb getPos(int i) {
		return this.list[i];
	}
	
	public void insertIn(int pos, Bomb bomb) {
		this.list[pos] = bomb;
	}
	public void destroyBomb(int pos) {
		this.list[pos] = null;
	}
}
