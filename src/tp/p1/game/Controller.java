package tp.p1.game;

import java.util.Scanner;

public class Controller {
	// Atributos
		private Game _game;
		private Scanner _in;
		private static final String PROMPT = "Command > ";
	
	// Metodos	
		
	// Constructor	
		public Controller(Game game){
			this._game = game;
			this._in = new Scanner(System.in);
		}
		
	// Logica
		private void _userCommand() {
			boolean command;
			do {
				command = true;
				System.out.print(Controller.PROMPT);
				String[]  words = this._in.nextLine().toLowerCase().trim().split("\\s+");
				if(words[0].equals("move") || words[0].equals("m") && words.length == 3) {
					this._move(words[1],words[2]);
				}
				else if (words[0].equals("shoot") ||words[0].equals("s")) {
					this._shoot();
				}
				else if (words[0].equals("shockwave") ||words[0].equals("w")) {
					this._shockwave();
				}
				else if (words[0].equals("none") ||words[0].equals("n")) {
				}
				else if (words[0].equals("list") ||words[0].equals("l")) {
					this._list();
				}
				else if (words[0].equals("reset") ||words[0].equals("r")) {
					this._reset();
				}
				else if (words[0].equals("help") ||words[0].equals("h")) {
					this._help();
				}
				else if (words[0].equals("exit") ||words[0].equals("e")) {
					this._exit();
				}
				else {
					System.out.print("Comando erróneo o no reconocido.");
					command = false;
				}
			}while(!command);
			
		}
		private void _move(String dir, String pos) {
			if((dir.equals("left") || dir.equals("right")) && (pos.equals("1") || pos.equals("2"))) {
				int move = (dir.equals("left"))? -1 : 1;
				int num = (pos.equals("1"))? 1 : 2;
				this._game.move(move,num);	
			}
		}
		private void _shoot() {
			this._game.shoot();
		}
		private void _shockwave() {
			this._game.shockwave();
		}
		private void _reset() {
			this._game.reset();
		}
		private void _list() {
			System.out.println("[R]egular ship: Points: 5 - Harm: 0 - Shield: 2");
			System.out.println("[D]estroyer ship: Points: 10 - Harm: 1 - Shield: 1");
			System.out.println("[O]vni: Points: 25 - Harm: 0 - Shield: 1");
			System.out.println("^__^: Harm: 1 - Shield: 3");
		}
		private void _exit() {
			this._game.setPlayerDefeated();
		}
		private void _help() {
			System.out.println("move <left|right><1|2>: Moves UCM-Ship to the indicated direction.");
			System.out.println("shoot: UCM-Ship launches a missile.");
			System.out.println("shockWave: UCM-Ship releases a shock wave.");
			System.out.println("list: Prints the list of available ships.");
			System.out.println("reset: Starts a new game.");
			System.out.println("help: Prints this help message.");
			System.out.println("exit: Terminates the program.");
			System.out.println("[none]: Skips one cycle.");
		}

		public void run() {
			while(!this._game.playerDefeated() && !this._game.enemyDefeated() ) {
				this._game.draw();	
				this._userCommand();
				this._game.computerAction();
				this._game.update();	
			}
			if (this._game.playerDefeated() ) {
				this._game.printGameOver();
			} else {
				this._game.printWin();
			}
		}
}
/*
 *	while (!game.isFinished()){
		System.out.println(PROMPT);
		String[] words = in.nextLine().toLowerCase().trim().split ("\\s+");
		Command command = CommandGenerator.parse(words);
		if (command != null) {
			if (command.execute(game))
				System.out.println(game);
			}
			else {
				System.out.format(unknownCommandMsg);
		}
	}
*/