package invaders.commands;

import invaders.exceptions.CommandExecuteException;
import invaders.game.Game;
import invaders.printer.PrinterTypes;

public class StringifyCommand extends Command {

	
	public StringifyCommand() {
		super("stringify","f","stringify","Shows the game state serialized.");
	}

	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		System.out.println(PrinterTypes.STRINGIFIER.getObject().toString(game));
		return false;
	}
	
	@Override
	public Command parse(String[] commandWords) {
		if(this.matchCommandName(commandWords[0])) return this;
		else return null;
	}

}
