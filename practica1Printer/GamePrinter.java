package tp.practica1Printer;

import tp.practica1Game.Game;

public abstract class GamePrinter {

	protected Game game;
	
	public GamePrinter() {}

	protected void setGame(Game game) {
		this.game = game;
	}
	public abstract String toString();
}