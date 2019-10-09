package tp.p1.shipList;

import tp.p1.game.Level;
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
				list[i] = new DestroyerShip(2,4 + i);
			}
		}
		if(level.name() == "HARD") {
			for(int i = 0; i < 2; i++) {
				list[i] = new DestroyerShip(3,4 + i);
			}
		}
		if(level.name() == "INSANE") {
			for(int i = 0; i < 4; i++) {
				list[i] = new DestroyerShip(3,3 + i);
			}
		}
	}
	
	public int getTam() {
		return this.tam;
	}
}
