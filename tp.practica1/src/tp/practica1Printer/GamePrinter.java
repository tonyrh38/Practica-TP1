package tp.practica1Printer;

import tp.practica1Game.Game;

public interface GamePrinter {
	String toString(Game game);
	public GamePrinter parse(String name);
	public String helpText();
}
