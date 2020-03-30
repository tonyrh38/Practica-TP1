package invaders.printer;

import invaders.game.Game;

public class Stringifier extends GamePrinter {

	private static final String title = "- Space Invaders v2.0 -";	
	
	
	public Stringifier() {}

	
	// GamePrinter Abstract Methods
	@Override
	public
	String toString(Game game) {
		StringBuilder str = new StringBuilder();
		
		str.append(title + "\n");
		str.append("\n");
		str.append("G;" + game.getCycle() + "\n");
		str.append("L;" + game.getLevel().toString() + "\n");
		
		for(int i = 0; i < Game._Y; i++) {
			for(int j = 0; j < Game._X; j++) {
				String aux = game.characterAtToSerialize(i, j);
				if(aux != "") str.append(aux +"\n");
			}
		}
		
		return str.toString();
	}
	
}
