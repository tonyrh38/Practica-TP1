package tp.p1.command;

import tp.p1.game.Game;

public class BuyCommand extends Command {

	public BuyCommand() {
		super("buy", "b", "buy", "Allow to spend points in better weapons.");
	}

	@Override
	public boolean execute(Game game) {
		game.buy();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords.length == 2 && this.matchCommandName(commandWords[0])) {
			if((commandWords[1] == "supermisil")) return this;
			else return null;
		}
		else return null;
	}

}
