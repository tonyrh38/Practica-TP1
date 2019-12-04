trpackage tp.practica1Command;

import tp.practica1Game.Game;
import tp.practica1Printer.PrinterTypes;

public class StringifyCommand extends Command {

	public StringifyCommand() {
		super("stringify","f","stringify","Shows the game state serialized.");
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(PrinterTypes.STRINGIFIER.getObject(game).toString());
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(this.matchCommandName(commandWords[0])) return this;
		else return null;
	}

}
