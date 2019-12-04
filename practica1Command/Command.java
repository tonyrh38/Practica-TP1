package tp.practica1Command;

import tp.practica1Exception.*;
import tp.practica1Game.Game;

public abstract class Command {
	// Atributos
		protected final String name;
		protected final String shortcut;
		
		private final String _details ;
		private final String _help;
		
		protected static final String incorrectNumArgsMsg = "Incorrect number of arguments";
		protected static final String incorrectArgsMsg = "Incorrect argument format";
	
	// Metodos
		
	// Constructor
		public Command(String name, String shortcut, String details, String help){
			this.name = name;
			this.shortcut = shortcut;
			this._details = details;
			this._help = help;
		}
		
	// Logica
		public abstract boolean execute(Game game) throws CommandExecuteException;
		public abstract Command parse(String[] commandWords)throws CommandParseException;
		
		protected boolean matchCommandName(String name) {
			return this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name);
		}
		public String helpText(){
			return this._details + " : " + this._help + "\n";
		}
}
