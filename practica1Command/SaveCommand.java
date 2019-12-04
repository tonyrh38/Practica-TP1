package tp.practica1Command;

import tp.practica1Exception.CommandParseException;
import tp.practica1Game.Game;

public class SaveCommand extends Command {

	public SaveCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		// TODO Auto-generated method stub
		return null;
	}

}
