package tp.p1.command;

import tp.p1.game.Game;
import tp.p1.printer.PrinterTypes;

public class ListPrintersCommand extends Command {

	public ListPrintersCommand() {
		super("listprinters", "p","listprinters", "Prints the list of available printers.");
	}

	@Override
	public boolean execute(Game game) {
		PrinterTypes.printerHelp(game);
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(this.matchCommandName(commandWords[0])) return this;
		else return null;
	}

}
