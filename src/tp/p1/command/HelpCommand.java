package tp.p1.command;

import tp.p1.game.Game;

public class HelpCommand extends Command {

	public HelpCommand() {
		super("help","h","help","Prints this help message.");
	}
	
	@Override
	public boolean execute(Game game) {
		System.out.println(CommandGenerator.commandHelp());
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(this.matchCommandName(commandWords[0])) return this;
		else return null;
	}

}
