package tp.p1.command;

import tp.p1.game.Game;

public class ShootCommand extends Command {

	public ShootCommand() {
		super("shoot","s","shoot","UCM-Ship launches a missile.");
	}
	
	@Override
	public boolean execute(Game game) {
		game.shoot();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(this.matchCommandName(commandWords[0])) return this;
		else return null;
	}

}
