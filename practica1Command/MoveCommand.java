package tp.practica1Command;

import tp.practica1Exception.CommandParseException;
import tp.practica1Game.Game;

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
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords.length > 0 && this.matchCommandName(commandWords[0])) {
			if (commandWords.length != 3) {
				throw new CommandParseException ("Wrong number for arguments for move command");
			}
			else {
				if(commandWords[1].equals("left") || commandWords[1].equals("right")){
					if (commandWords[2].equals("1") || commandWords[2].equals("2")) {
						this.numCells = (commandWords[2].equals("1"))? 1 : 2;
						this.numCells *= (commandWords[1].equals("left"))? -1 : 1;
						return this;
					}
					else
					{
						throw new CommandParseException ("Move command only accepts int 1 or 2 as second argument");
					}
				}
				else 
				{
					throw new CommandParseException ("Move command only accepts right or left as first argument");
				}
			}
		}
		else
		{
			return null;
		}
	}

}
