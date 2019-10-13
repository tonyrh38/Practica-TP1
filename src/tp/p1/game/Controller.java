package tp.p1.game;

import java.util.Scanner;
import tp.p1.ship.Bomb;

public class Controller {
	
	private Game game;
	private GamePrinter gamePrinter;
	private Scanner in;
	private static final String PROMPT = "Command > ";
	
	public Controller(Game game){
		this.game = game;
		this.gamePrinter= new GamePrinter(game, 8, 9);
		this.in = new Scanner(System.in);
	}

	public void run() {
		
		this.gamePrinter.printGame(game);
		
		while(!this.game.playerDefeated() && !this.game.enemyDefeated() ) {
			
			this.userCommand();
			this.computerAction();
			this.update();	
			/*Command command = CommandParser.parseCommand(words);
				if (command.execute(game))
					this.game.print();*/
		}
		
		/*if (this.game.jugadorDerrotado() ) {
			this.game.printGameOver();
		} else {
			this.game.printWin();
		}*/
	}
	private void userCommand() {
		boolean command = true;
		do {
			System.out.print(Controller.PROMPT);
			String[]  words = this.in.nextLine().toLowerCase().trim().split("\\s+");
			if(words[0].equals("move") ||words[0].equals("m") && words.length == 3) {
				command = move(words[1],words[2]);
			}
			else if (words[0].equals("shoot") ||words[0].equals("s")) {
				shoot();
			}
			else if (words[0].equals("shockwave") ||words[0].equals("w")) {
				shockwave();
			}
			else if (words[0].equals("none") ||words[0].equals("n")) {
			}
			else if (words[0].equals("list") ||words[0].equals("l")) {
				list();
			}
			else if (words[0].equals("reset") ||words[0].equals("r")) {
				reset();
			}
			else if (words[0].equals("help") ||words[0].equals("h")) {
				help();
			}
			else if (words[0].equals("exit") ||words[0].equals("e")) {
				exit();
			}
			else {
				System.out.print("Comando erroneo o no reconocido.");
				command = false;
			}
		}while(!command);
		
	}
	private void computerAction() {
		int random = game.getRand();
		
	}
	private void update() {
		this.gamePrinter.printGame(game);
	}
	
	private boolean move(String dir, String pos) {
		if((dir.equals("left") || dir.equals("right")) && (pos.equals("1") || pos.equals("2"))) {
			int move = (dir.equals("left"))? -1 : 1;
			int num = (pos.equals("1"))? 1 : 2;
			int op = game.getUCMShip().getY() + move * num;
			if(op >= 0 && op <= 8) {
				game.getUCMShip().setY(op);
			}
			else if(op - 1 == 8) {
				game.getUCMShip().setY(op - 1);
			}
			else if(op + 1 == 0) {
				game.getUCMShip().setY(op + 1);
			}
				
			return true;
		}
		else return false;
	}
	private void shoot() {
		int pos = game.getBombList().getTam() - 1;
		if(game.getBombList().getPos(pos) != null) {
			int x = game.getUCMShip().getX();
			int y = game.getUCMShip().getY();
			Bomb bomb = new Bomb(x,y);
			game.getBombList().insertIn(pos, bomb);
		}
	}
	private void shockwave() {
		if(game.getUCMShip().getShockwave()) {
			int n = game.getRegularShipList().getTam();
			int m = game.getDestroyerShipList().getTam();
			for(int i = 0; i < n ; i++) {
				game.getRegularShipList().getPos(i).damage(game);
				if( i < m) {
					game.getDestroyerShipList().getPos(i).damage(game);
				}
			}
			if(game.getOvni() != null) {
				game.getOvni().damage(game);
			}
			game.getUCMShip().setShockwave(false);
		}
	}
	private void reset() {
		game.reset();
	}
	private void list() {
		System.out.println("[R]egular ship: Points: 5 - Harm: 0 - Shield: 2");
		System.out.println("[D]estroyer ship: Points: 10 - Harm: 1 - Shield: 1");
		System.out.println("[O]vni: Points: 25 - Harm: 0 - Shield: 1");
		System.out.println("^__^: Harm: 1 - Shield: 3");
	}
	private void exit() {
		game.setPlayerDefeated();
	}
	private void help() {
		System.out.println("move <left|right><1|2>: Moves UCM-Ship to the indicated direction.");
		System.out.println("shoot: UCM-Ship launches a missile.");
		System.out.println("shockWave: UCM-Ship releases a shock wave.");
		System.out.println("list: Prints the list of available ships.");
		System.out.println("reset: Starts a new game.");
		System.out.println("help: Prints this help message.");
		System.out.println("exit: Terminates the program.");
		System.out.println("[none]: Skips one cycle.");
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
