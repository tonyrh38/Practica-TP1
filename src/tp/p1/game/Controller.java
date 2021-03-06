package tp.p1.game;

import java.util.Scanner;

import tp.p1.command.*;
import tp.p1.exception.*;
import tp.p1.printer.PrinterTypes;

public class Controller {
	// Atributos
		private Game game;
		private Scanner in;
		
		private static final String PROMPT = "Command > ";	
	
	// Metodos	
		
	// Constructor	
		public Controller(Game game){
			this.game = game;
			this.in = new Scanner(System.in);
		}

	// Logica
		public void run() {
			this.game.infoToString();
			System.out.println(PrinterTypes.BOARDPRINTER.getObject(this.game).toString());
			while (!this.game.isFinished()){
				System.out.println(PROMPT);
				String[] words = this.in.nextLine().toLowerCase().trim().split ("\\s+");
				try {
					Command command = CommandGenerator.parseCommand(words);
					if (command.execute(this.game)){
						this.game.update();
						this.game.infoToString();
						System.out.println(PrinterTypes.BOARDPRINTER.getObject(this.game).toString());
					}
				}
				catch(CommandParseException | CommandExecuteException ex) {
					System.out.format(ex.getMessage() + " %n %n");
				}
			}
			System.out.println(this.game.getWinnerMessage());
		}
}
/*
 *	
*/