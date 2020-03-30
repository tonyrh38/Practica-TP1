package invaders.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import invaders.exceptions.CommandExecuteException;
import invaders.exceptions.CommandParseException;
import invaders.game.Game;
import invaders.printer.PrinterTypes;

public class SaveCommand extends Command {

	private String _filename;
	
	
	public SaveCommand() {
		super("save", "g", "save <filename>","Saves the status of the game in a file.");
	}

	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try (BufferedWriter w = new BufferedWriter(new FileWriter(_filename))){
			w.write(PrinterTypes.STRINGIFIER.getObject().toString(game));
			System.out.println("Game successflly saved in file " + _filename + ". Use the load command to reload it.");
		} catch (IOException e) {
			throw new CommandExecuteException("Cannot open " + e.getMessage());
		} 
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(this.matchCommandName(commandWords[0])) {
			if (commandWords.length != 2) throw new CommandParseException(incorrectNumArgsMsg);
			else{
				_filename = commandWords[1];
				if (!_filename.endsWith(".dat")) _filename += ".dat";
				return this;
			}
		}
		else return null;
	}

}
