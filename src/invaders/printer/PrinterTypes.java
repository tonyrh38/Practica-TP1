package invaders.printer;

import invaders.game.Game;

public enum PrinterTypes {
	
	BOARDPRINTER(	"boardprinter",
					"prints the game formatted as a board of dimension: ",
					new BoardPrinter(Game._Y, Game._X)
				),
	STRINGIFIER(	"stringifier",
					"prints the game as plain text",
					new Stringifier()
				);
	
	private String _printerName;
	private String _helpText;
	private GamePrinter _printerObject;
	
	
	private PrinterTypes(String name, String text, GamePrinter printer) {
		_printerName = name;
		_helpText = text;
		_printerObject = printer;
	}
	
	
	public static String printerHelp() {
		String helpString = "";
		for (PrinterTypes printer : PrinterTypes.values())
			helpString += String.format("%s : %s%s%n", printer._printerName, printer._helpText,
				(printer == BOARDPRINTER ? Game._X + " x " + Game._Y : ""));
		return helpString;
	}
	
	// Assumes a max of one object of each printer type is needed (otherwise return copy)
	public GamePrinter getObject() {
		return _printerObject;
	}

}
