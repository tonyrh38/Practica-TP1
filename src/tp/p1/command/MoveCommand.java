package tp.p1.command;

import tp.p1.game.Game;

public class MoveCommand extends Command {

	// Atributos
	private int _move;
	private int _num;
	
	// Metodos
	public MoveCommand() {
		super("move","m","move <left|right><1|2>","Moves UCM-Ship to the indicated direction.");
	}
	
	@Override
	public boolean execute(Game game) {
		game.move(this._move, this._num);
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords.length == 3 && this.matchCommandName(commandWords[0])) {
			if((commandWords[1] == "left" || commandWords[1] == "right") && (commandWords[2] == "1" || commandWords[2] == "2")) {
				this._move = (commandWords[1].equals("left"))? -1 : 1;
				this._num = (commandWords[2].equals("1"))? 1 : 2;
				return this;
			}
			else return null;
		}
		else return null;
	}

}
