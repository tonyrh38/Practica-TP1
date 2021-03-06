package tp.p1.command;

import java.io.*;
import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;
import tp.p1.game.Game;
import tp.p1.printer.PrinterTypes;

public class SaveCommand extends Command {
	
	private String filename;

	public SaveCommand() {
		super("save", "g", "save <filename>","Saves the status of the game in a file.");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try ( BufferedWriter f = new BufferedWriter(new FileWriter(this.filename)) ){
			f.write(PrinterTypes.STRINGIFIER.getObject(game).toString());
			System.out.println("Game successflly saved in file " + filename + ". Use the load command to reload it.");
		} catch (IOException e) {
			throw new CommandExecuteException("Cannot open " + e.getMessage());
		}
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(this.matchCommandName(commandWords[0])) {
			if (commandWords.length != 2) throw new CommandParseException(incorrectNumArgsMsg);
			this.filename = commandWords[1];
			if (!this.filename.endsWith(".dat")) this.filename += ".dat";
			return this;
		}
		else return null;
	}

}
