package tp.p1.command;

import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;
import tp.p1.game.Game;

public class ShootCommand extends Command {

	private boolean supermisil;

	public ShootCommand() {
		super("shoot","s","shoot [supermisil]","UCM-Ship launches a laser (or a supermisil).");
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		if(this.supermisil) game.shootSupermisile();
		else game.shootLaser();	
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(this.matchCommandName(commandWords[0])) {
			if(commandWords.length == 2) {
				if(commandWords[1].equals("supermisil")) {
					this.supermisil = true;
					return this;
				}
				else throw new CommandParseException(incorrectArgsMsg);
			}
			else {
				this.supermisil = false;
				return this;
			}
		}
		else return null;
	}

}
