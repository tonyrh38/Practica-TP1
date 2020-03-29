package invaders.commands;

import invaders.exceptions.CommandParseException;

public class CommandGenerator {

		private static Command[] availableCommands = {
			new ListCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new ListCommand(),
			new NoneCommand(),
			new MoveCommand(),
			new ShootCommand(),
			new ShockwaveCommand(),
			new BuyCommand(),
			new StringifyCommand()
		};
		
		private static final String unknownCommandMsg = "Comando desconocido, vuelva a intentarlo.\n";
	
		
		public static Command parseCommand(String[ ] commandWords) throws CommandParseException{
				for (Command command : availableCommands) {
					if (command.parse(commandWords) != null) return command;
				}
				throw new CommandParseException(unknownCommandMsg);
		}
		
		public static String commandHelp() {
			String str = "";
			for (Command command : availableCommands) {
				str += command.helpText() + "\n";
			}
			return str;
		}
		
}
