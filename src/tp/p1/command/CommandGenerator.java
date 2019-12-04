package tp.p1.command;

public class CommandGenerator {
	// Atributos
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
			new StringifyCommand(),
			new ListPrintersCommand()
		};
	
	// Metodos
		public static Command parseCommand(String[ ] commandWords) {
			for (Command command : availableCommands) {
				if (command.parse(commandWords) != null) return command;
			}
			return null;
		}
		public static String commandHelp() {
			String str = "";
			for (Command command : availableCommands) {
				str += command.helpText() + "\n";
			}
			return str;
		}
}
