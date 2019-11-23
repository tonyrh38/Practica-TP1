package tp.p1.command;

import tp.p1.game.Game;

public class ListCommand extends Command {

	public ListCommand() {
		super("list","l","list","Prints the list of available ships.");
	}
	
	@Override
	public boolean execute(Game game) {
		System.out.println("[R]egular ship: Points: 5 - Harm: 0 - Shield: 2");
		System.out.println("[D]estroyer ship: Points: 10 - Harm: 1 - Shield: 1");
		System.out.println("[O]vni: Points: 25 - Harm: 0 - Shield: 1");
		System.out.println("^__^: Harm: 1 - Shield: 3");
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(this.matchCommandName(commandWords[0])) return new ListCommand();
		else return null;
	}

}
