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
			list[i] = new RegularShip(1,3 + i);
		}
		if(level.name() != "EASY") {
			for(int i = 0; i < 4; i++) {
				list[i] = new RegularShip(2,3 + i);
			}
		}	
	}
	
	public int getTam() {
		return this.tam;
	}
}
