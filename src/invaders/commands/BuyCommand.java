package invaders.commands;

import invaders.exceptions.CommandExecuteException;
import invaders.exceptions.CommandParseException;
import invaders.game.Game;

public class BuyCommand extends Command {

	private String _weapon;
	
	
	public BuyCommand() {
		super("buy", "b", "buy", "Allow to spend points in better weapons.");
	}

	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
			game.buy(_weapon);
			return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(this.matchCommandName(commandWords[0])) {
			if(commandWords.length == 2) {
				_weapon = commandWords[0];
				return this;
			}
			else throw new CommandParseException(incorrectArgsMsg);
		}
		else return null;
	}

}