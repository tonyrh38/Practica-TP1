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
		}
		
		if (this.game.playerDefeated() ) {
			this.gamePrinter.printGameOver();
		} else {
			this.gamePrinter.printWin();
		}
	}
	private void userCommand() {
		boolean command;
		do {
			command = true;
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
				System.out.print("Comando erróneo o no reconocido.");
				command = false;
			}
		}while(!command);
		
	}
	private void computerAction() {
		game.getDestroyerShipList().dropBomb(game.getRand(), game.getBombList());
		game.createOvni();		
	}
	private void update() {
		game.moveBombs();
		game.moveShips();
		game.moveOvni();
		game.calculatePuntuation();
		game.setCycleCounter(game.getCycleCounter() + 1);
		this.gamePrinter.printGame(game);
	}
	
	private boolean move(String dir, String pos) {
		if((dir.equals("left") || dir.equals("right")) && (pos.equals("1") || pos.equals("2"))) {
			int move = (dir.equals("left"))? -1 : 1;
			int num = (pos.equals("1"))? 1 : 2;
			int op = game.getXUCMShip() + move * num;
			if(op >= 0 && op <= 8) {
				game.setXUCMShip(op);
			}
			else if(op - 1 == 8) {
				game.setXUCMShip(op - 1);
			}
			else if(op + 1 == 0) {
				game.setXUCMShip(op + 1);
			}				
			return true;
		}
		else return false;
	}
	private void shoot() {
		int pos = game.getBombList().getTam() - 1;
		if(game.getBombList().isPosNull(pos)) {
			int x = game.getXUCMShip();
			int y = game.getYUCMShip();
			Bomb bomb = new Bomb(x,y);
			game.getBombList().insertIn(pos, bomb);
		}
	}
	private void shockwave() {
		if(game.getShockwaveUCMShip()) {
			game.getDestroyerShipList().damageAll();
			game.getRegularShipList().damageAll();
			if(game.getEnableOvni()) {
				game.damageOvni();
			}
			game.setShockwaveUCMShip(false);
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
}
