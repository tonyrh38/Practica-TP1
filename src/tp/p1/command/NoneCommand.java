package tp.p1.command;

import tp.p1.game.Game;

public class NoneCommand extends Command {

	public NoneCommand() {
		super("none","n","[none]","Skips one cycle.");
	}
	
	@Override
	public boolean execute(Game game) {
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(this.matchCommandName(commandWords[0])) return this;
		else return null;
	}

}
