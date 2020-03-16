package invaders.commands;

import invaders.game.Game;

public class ExitCommand extends Command {

	public ExitCommand(){
		super("exit", "e", "exit", "Terminates the program.");
	}
	
	@Override
	public boolean execute(Game game) {
		game.exit();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(this.matchCommandName(commandWords[0])) return this;
		else return null;
	}

}
