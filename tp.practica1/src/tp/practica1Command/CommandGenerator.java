package tp.practica1Command;

public class CommandGenerator {

	private static Command[] availableCommands = {
			new ListCommands(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new ListCommand(),
			new UpdateCommand(),
			new MoveCommand(),
			new ShockwaveCommand()
	};


	
	public static Command parseCommand (String[] commandWords) {
		 //TODO Ver qué le devolvemos bien capturamos la excepcion aqui?
		
		for (Command command : availableCommands){
			if (command.parse(commandWords) != null) {
				return command;
			}
		}
		
	}
	
	public static String commandHelp() {
		return null;
		
	}

}


