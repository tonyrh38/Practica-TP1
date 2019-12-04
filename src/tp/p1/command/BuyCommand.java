package tp.p1.command;

import tp.p1.game.Game;
import tp.p1.exception.*;

public class BuyCommand extends Command {

	public BuyCommand() {
		super("buy", "b", "buy", "Allow to spend points in better weapons.");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
			game.buy();
			return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords.length == 2 && this.matchCommandName(commandWords[0])) {
			if(commandWords[1].equals( "supermisil")) return this;
			else throw new CommandParseException(incorrectArgsMsg);
		}
		else return null;
	}

}
