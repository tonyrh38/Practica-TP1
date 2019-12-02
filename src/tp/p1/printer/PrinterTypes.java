package tp.p1.printer;

import tp.p1.game.Game;

public enum PrinterTypes {
	
	BOARDPRINTER("boardprinter",
				"prints the game formatted as a board of dimension: ",
				new BoardPrinter(ordinal, ordinal)),
	STRINGIFIER("stringifier",
				"prints the game as plain text",
				new Stringifier());
	
	private GamePrinter printerObject;
	
	private PrinterTypes(String name, String text, GamePrinter printer) {
	
	}
	public static String printerHelp(Game game) {
	
	}
	public GamePrinter getObject(Game game) {
		printerObject.setGame(game);
		return printerObject;
	}
}