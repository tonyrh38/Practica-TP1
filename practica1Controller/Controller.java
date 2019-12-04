package tp.practica1Controller;

import java.util.Scanner;

import tp.practica1Command.*;
import tp.practica1Exception.CommandExecuteException;
import tp.practica1Exception.CommandParseException;
import tp.practica1Game.Game;
import tp.practica1Printer.PrinterTypes;

public class Controller {
	// Atributos
		private Game game;
		private Scanner in;
		
		private static final String PROMPT = "Command > ";	
		private static final String unknownCommandMsg = "Comando desconocido, vuelva a intentarlo.\n";
	
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
					if (command != null) {
						if (command.execute(this.game)){
							this.game.update();
							this.game.infoToString();
							System.out.println(PrinterTypes.BOARDPRINTER.getObject(this.game).toString());
						}
					}
					else System.out.format(unknownCommandMsg);
				} catch (CommandParseException | CommandExecuteException ex)
				{ System.out.format(ex.getMessage()+ "%n%n"); }
			}
			System.out.println(this.game.getWinnerMessage());
		}
}