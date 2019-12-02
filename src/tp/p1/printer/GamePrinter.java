package tp.p1.printer;

import tp.p1.game.Game;

public abstract class GamePrinter {

	public GamePrinter() {
		// TODO Auto-generated constructor stub
	}

	abstract String toString(Game game);
	abstract GamePrinter parse(String name);
	abstract String helpText();
}
