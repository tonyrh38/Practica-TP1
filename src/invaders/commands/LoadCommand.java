package invaders.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import invaders.exceptions.CommandExecuteException;
import invaders.exceptions.CommandParseException;
import invaders.exceptions.FileContentsException;
import invaders.game.Game;

public class LoadCommand extends Command {

	private String _filename;
	
	
	public LoadCommand() {
		super("load", "o", "load <filename>", "Load a status of the game saved in a file.");
	}

	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try(BufferedReader r = new BufferedReader(new FileReader(_filename))) {
			if(r.readLine() != "- Space Invaders v2.0 -") throw new FileContentsException("El archivo no tiene el formato correcto.");
			else if(!r.readLine().isEmpty()) throw new FileContentsException("El archivo no tiene el formato correcto correcto.");
			else game.load(r);
		} catch(IOException | FileContentsException e) {
			throw new CommandExecuteException("Cannot load. " + e.getMessage());
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
