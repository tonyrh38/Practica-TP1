package tp.p1.game;

import java.util.Scanner;

import tp.p1.command.*;
import tp.p1.printer.BoardPrinter;
import tp.p1.printer.GamePrinter;

public class Controller {
	// Atributos
		private Game game;
		private Scanner in;
		private GamePrinter printer;
		
		private static final String PROMPT = "Command > ";	
		private static final String unknownCommandMsg = "Comando desconocido, vuelva a intentarlo.";
	
	// Metodos	
		
	// Constructor	
		public Controller(Game game){
			this.game = game;
			this.in = new Scanner(System.in);
			this.printer = new BoardPrinter();
		}

	// Logica
		public void run() {
			System.out.println(this.printer);
			while (!this.game.isFinished()){
				System.out.println(PROMPT);
				String[] words = this.in.nextLine().toLowerCase().trim().split ("\\s+");
				Command command = CommandGenerator.parseCommand(words);
				if (command != null) {
					if (command.execute(this.game)){
						this.game.update();
						System.out.println(this.printer);
					}
				}
				else System.out.format(unknownCommandMsg);
			}
			System.out.println(this.game.getWinnerMessage());
		}
}
/*
 *	
*/