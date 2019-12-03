package tp.p1.printer;

import tp.p1.game.Game;

public enum PrinterTypes {
	
	BOARDPRINTER("boardprinter",
				"prints the game formatted as a board of dimension: ",
				new BoardPrinter(Game.DIM_X, Game.DIM_Y)),
	STRINGIFIER("stringifier",
				"prints the game as plain text",
				new Stringifier());
	
	private String name;
	private String text;
	
	private GamePrinter printerObject;
	
	private PrinterTypes(String name, String text, GamePrinter printer) {
		this.name = name;
		this.text = text;
		this.printerObject = printer;
	}
	
	public static String printerHelp(Game game) {
		return BOARDPRINTER.name + ": " + BOARDPRINTER.text + Game.DIM_X + "x" + Game.DIM_Y + "\n"
			+ STRINGIFIER.name+ ": " + STRINGIFIER.text;
	}
	
	public GamePrinter getObject(Game game) {
		this.printerObject.setGame(game);
		return this.printerObject;
	}
}