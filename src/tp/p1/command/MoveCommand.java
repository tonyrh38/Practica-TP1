package tp.p1.command;

import tp.p1.game.Game;

public class MoveCommand extends Command {

	// Atributo
	private int numCells;
	
	// Metodos
	public MoveCommand() {
		super("move","m","move <left|right><1|2>","Moves UCM-Ship to the indicated direction.");
	}
	
	@Override
	public boolean execute(Game game) {
		game.move(this.numCells);
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords.length == 3 && this.matchCommandName(commandWords[0])) {
			if(commandWords[1].equals("left") || commandWords[1].equals("right")){
				if (commandWords[2].equals("1") || commandWords[2].equals("2")) {
					this.numCells = (commandWords[2].equals("1"))? 1 : 2;
					this.numCells *= (commandWords[1].equals("left"))? -1 : 1;
					return this;
				}
				else return null;
			}
			else return null;
		}
		else return null;
	}

}
