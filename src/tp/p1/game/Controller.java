package tp.p1.game;

import java.util.Scanner;

import tp.p1.command.*;

public class Controller {
	// Atributos
		private Game game;
		private Scanner in;
		
		private static final String PROMPT = "Command > ";	
		private static final String unknownCommandMsg = "Comando desconocido, vuelva a intentarlo.";
	
	// Metodos	
		
	// Constructor	
		public Controller(Game game){
			this.game = game;
			this.in = new Scanner(System.in);
		}

	// Logica
		public void run() {
			this.game.draw();
			while (!this.game.isFinished()){
				System.out.println(PROMPT);
				String[] words = this.in.nextLine().toLowerCase().trim().split ("\\s+");
				Command command = CommandGenerator.parseCommand(words);
				if (command != null) {
					if (command.execute(this._game)){
						this._game.update();
						this._game.draw();
					}
				}
				else System.out.format(unknownCommandMsg);
				if (this._game.playerDefeated()) this._game.printGameOver();
				else this._game.printWin();
			}
		}
}
/*
 *	
*/