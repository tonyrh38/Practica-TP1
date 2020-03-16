package invaders.commands;

import invaders.exceptions.CommandExecuteException;
import invaders.game.Game;

public class ShockwaveCommand extends Command {

	
	public ShockwaveCommand() {
		super("shockwave","w","shockwave","UCM-Ship releases a shock wave.");
	}
	
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		game.shockwave();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(this.matchCommandName(commandWords[0])) return this;
		else return null;
	}
}
