package invaders.commands;

import invaders.exceptions.CommandExecuteException;
import invaders.exceptions.CommandParseException;
import invaders.game.Game;

public class ShootCommand extends Command {


	private boolean _superlaser;

	public ShootCommand() {
		super("shoot","s","shoot [supermisil]","UCM-Ship launches a laser (or a supermisil).");
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		if(_superlaser) game.shootSuperLaser();
		else game.shootLaser();	
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(this.matchCommandName(commandWords[0])) {
			if(commandWords.length == 2) {
				if(commandWords[1].equals("supermisil")) {
					_superlaser = true;
					return this;
				}
				else throw new CommandParseException(incorrectArgsMsg);
			}
			else {
				_superlaser = false;
				return this;
			}
		}
		else return null;
	}

}
