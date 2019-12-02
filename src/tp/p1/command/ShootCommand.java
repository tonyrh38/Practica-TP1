package tp.p1.command;

import tp.p1.game.Game;

public class ShootCommand extends Command {

	private boolean supermisil;

	public ShootCommand() {
		super("shoot","s","shoot [supermisil]","UCM-Ship launches a laser (or a supermisil).");
	}
	
	@Override
	public boolean execute(Game game) {
		if(this.supermisil) return game.shootSupermisile();
		else return game.shootLaser();	
	}

	@Override
	public Command parse(String[] commandWords) {
		if(this.matchCommandName(commandWords[0])) {
			if(commandWords.length == 2) {
				if(commandWords[0] == "supermisil") {
					this.supermisil = true;
					return this;
				}
				else return null;
			}
			else {
				this.supermisil = false;
				return this;
			}
		}
		else return null;
	}

}
