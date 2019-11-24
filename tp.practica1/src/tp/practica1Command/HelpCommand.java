package tp.practica1Command;

import tp.practica1Exception.CommandParseExceptions;
import tp.practica1Game.Game;

public class HelpCommand extends Command {
	
	private static String nameCommand = "Help";

	@Override
	public boolean execute(Game game) {
		// TODO Muestra la lista de comandos y su texto de help
		// delega en el help de cada objeto llamando a su metodo de impresion por pantalla del help
		
		String textoHelpCommands = " ";
		return false;
	}

	@Override
	/*Esto está todo bien y precioso*/
	public Command parse(String[] commandWords) throws CommandParseExceptions{
		Command help = null;
		if(commandWords[0].equals("H") || commandWords[0].equals("HELP")) { 
			if (commandWords.length == 1) {help = new HelpCommand(); }
			else throw new CommandParseExceptions("Help command has no arguments");		
		}
		return help;
	}

}
