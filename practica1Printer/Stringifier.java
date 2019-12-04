package tp.practica1Printer;

import tp.practica1Game.Game;

public class Stringifier extends GamePrinter {

	public Stringifier() {}

	private static final String title = "� Space Invaders v2.0 �";	
	
	@Override
	public
	String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(title + "\n");
		str.append("\n");
		str.append("G;" + this.game.getCurrentCycle() + "\n");
		str.append("L;" + this.game.getLevel().name() + "\n");
		
		for(int i = 0; i < Game.DIM_Y; i++) {
			for(int j = 0; j < Game.DIM_X; j++) {
				String aux = this.game.characterAtToSerialize(j, i);
				if(aux != "") str.append(aux +"\n");
			}
		}
		
		return str.toString();
	}
}
