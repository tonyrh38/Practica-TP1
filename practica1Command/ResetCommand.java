package tp.practica1Command;

import tp.practica1Game.Game;

public class ResetCommand extends Command {

	public ResetCommand() {
		super("reset","r","reset","Starts a new game.");
	}
	
	@Override
	public boolean execute(Game game) {
		game.reset();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(this.matchCommandName(commandWords[0])) return this;
		else return null;
	}

}
