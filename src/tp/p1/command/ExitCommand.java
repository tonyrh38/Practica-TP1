package tp.p1.command;

import tp.p1.game.Game;

public class ExitCommand extends Command {

	public ExitCommand(){
		super("exit", "e", "exit", "Terminates the program.");
	}
	
	@Override
	public boolean execute(Game game) {
		game.setPlayerDefeated();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(this.matchCommandName(commandWords[0])) return new ExitCommand();
		else return null;
	}

}
