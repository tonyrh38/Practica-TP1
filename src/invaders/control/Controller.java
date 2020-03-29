package invaders.control;

import java.util.Scanner;

import invaders.commands.*;
import invaders.exceptions.*;
import invaders.game.Game;
import invaders.printer.PrinterTypes;

public class Controller {

	private Game _game;
	private Scanner _in;
	
	private static final String PROMPT = "Command > ";	


	public Controller(Game game){
		_game = game;
		_in = new Scanner(System.in);
	}


	public void run() {
		_game.info();
		System.out.println(PrinterTypes.BOARDPRINTER.getObject().toString(_game));
		while (!_game.isFinished()){
			System.out.println(PROMPT);
			String[] words = _in.nextLine().toLowerCase().trim().split ("\\s+");
			try {
				Command command = CommandGenerator.parseCommand(words);
				if (command.execute(_game)){
					_game.update();
					_game.info();
					System.out.println(PrinterTypes.BOARDPRINTER.getObject().toString(_game));
				}
			}
			catch(CommandParseException | CommandExecuteException ex) {
				System.out.format(ex.getMessage() + " %n %n");
			}
		}
		System.out.println(_game.getWinnerMessage());
	}
	
}