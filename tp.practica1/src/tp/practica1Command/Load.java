package tp.practica1Command;

import tp.practica1Exception.CommandParseExceptions;
import tp.practica1Game.Game;

public class Load extends Command {

	public Load(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseExceptions {
		// TODO Auto-generated method stub
		return null;
	}

}
