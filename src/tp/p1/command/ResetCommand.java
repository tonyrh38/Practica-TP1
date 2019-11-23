package tp.p1.command;

import tp.p1.game.Game;

public class ResetCommand extends Command {

	public ResetCommand() {
		super("reset","r","reset","Starts a new game.");
	}
	
	@Override
	public boolean execute(Game game) {
		game.reset();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(this.matchCommandName(commandWords[0])) return this;
		else return null;
	}

}
