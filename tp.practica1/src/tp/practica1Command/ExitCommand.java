package tp.practica1Command;

import tp.practica1Game.Game;

public class ExitCommand extends Command {

	public ExitCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		/* TODO pasarle los comandos en formato
		 *  String[]  words = this.in.nextLine().toLowerCase().trim().split("\\s+");
		 *  Desde el método que hace la llamada del commandGenerator
		 */
		
		if (commandWords[0].equals(this.name) ||commandWords[0].equals(this.shortcut)){
			return this;
		}
		return null; //añadir la excepcion para parte 3
	}

}
