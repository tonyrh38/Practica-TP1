package tp.practica1Command;

import tp.practica1Game.Game;

public class BuyCommand extends Command {

	public BuyCommand() {
		super("buy", "b", "buy", "Allow to spend points in better weapons.");
	}

	@Override
	public boolean execute(Game game) {
		game.buy();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords.length == 2 && this.matchCommandName(commandWords[0])) {
			if(commandWords[1].equals( "supermisil")) return this;
			else return null;
		}
		else return null;
	}

}
