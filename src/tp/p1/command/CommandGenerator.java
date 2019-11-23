package tp.p1.command;

public class CommandGenerator {
	// Atributos
		private static Command[] _availableCommands = {
			new ListCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new ListCommand(),
			new NoneCommand(),
			new MoveCommand(),
			new ShootCommand(),
			new ShockwaveCommand()
		};
	
	// Metodos
		public static Command parseCommand(String[ ] commandWords) {
			for (Command command : _availableCommands) {
				if (command.parse(commandWords) != null) return command;
			}
			return null;
		}
		public static String commandHelp() {
			String str = "";
			for (Command command : _availableCommands) {
				str += command.helpText() + "\n";
			}
			return str;
		}
}
