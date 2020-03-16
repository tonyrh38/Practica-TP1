package invaders.control;

import java.util.Scanner;

import invaders.commands.*;
import invaders.exceptions.*;
import invaders.game.Game;
import invaders.printer.GamePrinter;

public class Controller {

	private Game _game;
	private Scanner _in;
	
	private static final String PROMPT = "Command > ";	


	public Controller(Game game){
		_game = game;
		_in = new Scanner(System.in);
	}


	public void run() {
		GamePrinter gp;
		_game.info();
		gp = new GamePrinter(_game, Game._Y, Game._X);
		gp.toString();
		while (!_game.isFinished()){
			System.out.println(PROMPT);
			String[] words = _in.nextLine().toLowerCase().trim().split ("\\s+");
			try {
				Command command = CommandGenerator.parseCommand(words);
				if (command.execute(_game)){
					_game.update();
					_game.info();
					gp = new GamePrinter(_game, Game._Y, Game._X);
					gp.toString();
				}
			}
			catch(CommandParseException | CommandExecuteException ex) {
				System.out.format(ex.getMessage() + " %n %n");
			}
		}
		System.out.println(_game.getWinnerMessage());
	}
	
}