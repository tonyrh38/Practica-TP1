package tp.p1.shipList;

import tp.p1.game.Game;
import tp.p1.game.Level;
import tp.p1.ship.Bomb;
import tp.p1.ship.DestroyerShip;

public class DestroyerShipList {
	
	private DestroyerShip list[];
	private int tam;
	
	public DestroyerShipList(Level level) {
		this.tam = level.getDestroyerShip();
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
	
	public DestroyerShip getPos(int pos) {
		return this.list[pos];
	}
	public void dropBomb(Game game) {
		int n = game.getDestroyerShipList().getTam();
		for(int i = 0; i < n; i++ ) {
			if(game.getBombList().getPos(i) == null) {
				if(game.getRand() <= game.getLevel().getFreq() * 10) {
					Bomb bomb = new Bomb(game.getDestroyerShipList().getPos(i).getX(),game.getDestroyerShipList().getPos(i).getY());
					game.getBombList().insertIn(i, bomb);
				}
			}
		}
	}
}
