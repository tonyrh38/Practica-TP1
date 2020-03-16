package invaders.commands;

import invaders.exceptions.CommandExecuteException;
import invaders.exceptions.CommandParseException;
import invaders.game.Game;

public class ShootCommand extends Command {


	public ShootCommand() {
		super("shoot","s","shoot [supermisil]","UCM-Ship launches a laser (or a supermisil).");
	}
	
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		game.shootLaser();	
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(this.matchCommandName(commandWords[0])) return this;
		else return null;
	}

}
