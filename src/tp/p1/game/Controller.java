package tp.p1.game;

import java.util.Scanner;

public class Controller {
	
	private Game game;
	private GamePrinter gamePrinter;
	private Scanner in;
	private static final String PROMPT = "> ";
	
	public Controller(Game game){
		this.game = game;
		this.gamePrinter= new GamePrinter(game, 8, 9);
		this.in = new Scanner(System.in);
	}

	public void run() {
		
		this.draw();
		
		while(!this.game.playerDefeated() && !this.game.enemyDefeated() ) {
			
			this.userCommand();
			this.computerAction();
			this.update();
			/*System.out.print(Controller.PROMPT);
			String[]  words = this.in.nextLine().toLowerCase().trim().split("\\s+");
			
				Command command = CommandParser.parseCommand(words);
				// Note: Command can't be null because parseCommand can throw an UnknownCommandException
				if (command.execute(game))
					this.game.print();
		}
		
		if (this.game.jugadorDerrotado() ) {
			this.game.printGameOver();
		} else {
			this.game.printWin();
		}*/
		}
	}
	
	private void draw() {
		System.out.println("Life: " + this.game.getVidaUCM());
		System.out.println("Number of cycles: " + this.game.getCycleCounter());
		System.out.println("Points: " + this.game.getPuntuation());
		System.out.println("Remaining aliens: " );
		System.out.println("Shockwave: " + this.game.getShockwaveUCM());
		
		System.out.println(this.gamePrinter.toString());
	}
	private void userCommand() {
		
	}
	private void computerAction() {
		
	}
	private void update() {
		
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
