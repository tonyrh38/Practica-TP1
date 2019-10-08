package tp.p1.game;

import java.util.Scanner;

public class Controller {
	
	private Game game;
	private GamePrinter gamePrinter;
	private Scanner in;
	private static final String PROMPT = "> ";
	
	Controller(Game game){
		this.game = game;
		this.gamePrinter= new GamePrinter(game, 8, 9);
		this.in = new Scanner(System.in);
	}

	public void run() {
		this.gamePrinter.encodeGame(game);	
		
		while(!this.game.jugadorDerrotado() && !this.game.zombiesDerrotados() ) {
			
			System.out.print(Controller.PROMPT);
			String[]  words = this.in.nextLine().toLowerCase().trim().split("\\s+");
			
			try {
				Command command = CommandParser.parseCommand(words);
				// Note: Command can't be null because parseCommand can throw an UnknownCommandException
				if (command.execute(game))
					this.game.print();
			} catch (CommandExecuteException | CommandParseException e) {
				System.out.println(e.getMessage());
			}
		}
		
		if (this.game.jugadorDerrotado() ) {
			this.game.printGameOver();
		} else {
			this.game.printWin();
		}
	}
	
	/** Imprime una línea en la terminal */
	public void println(String line) {
		System.out.println(line);
	}
	
	/** Imprime en la terminal sin salto de línea */
	public void print(String line) {
		System.out.print(line);
	}

}
