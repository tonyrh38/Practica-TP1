package tp.p1.printer;

import tp.p1.game.Game;

public abstract class GamePrinter {

	protected Game game;
	
	public GamePrinter() {}

	protected void setGame(Game game) {
		this.game = game;
	}
	public abstract String toString();
}
