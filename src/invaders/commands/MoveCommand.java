package invaders.commands;

import invaders.exceptions.CommandExecuteException;
import invaders.exceptions.CommandParseException;
import invaders.game.Game;

public class MoveCommand extends Command {

	private int _numCells;
	

	public MoveCommand() {
		super("move","m","move <left|right><1|2>","Moves UCM-Ship to the indicated direction.");
	}
	
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		game.move(_numCells);
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(this.matchCommandName(commandWords[0])) {
			if(commandWords.length == 3) {
				if(commandWords[1].equals("left") || commandWords[1].equals("right")){
					if (commandWords[2].equals("1") || commandWords[2].equals("2")) {
						try {
							_numCells = Integer.parseUnsignedInt(commandWords[2]);
						} catch (NumberFormatException e) {
							throw new CommandParseException(incorrectArgsMsg);
						}
						_numCells *= (commandWords[1].equals("left"))? -1 : 1;
						
						return this;
					}
					else throw new CommandParseException(incorrectArgsMsg);
				}
				else throw new CommandParseException(incorrectArgsMsg);
			}
			else throw new CommandParseException(incorrectNumArgsMsg);
		}
		else return null;
	}

}
