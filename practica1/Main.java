package tp.practica1;

import java.util.Random;

import tp.practica1Controller.Controller;
import tp.practica1Game.Game;
import tp.practica1Game.Level;

public class Main {

	public static void main(String[] args) {
		
		Game game;
		if (args.length == 1 || args.length == 2) {
		
			Level level = Level.valueOf(args[0]);
		
			if (args.length == 2) {
				Random seed = new Random(Integer.parseInt(args[1]));
				game = new Game(level, seed);
			} else {
				Random seed = new Random(314);
				game = new Game(level, seed);
			}
		
			Controller controller = new Controller(game);
			controller.run();
		
			System.exit(0);
		}
		else System.exit(1);
	}
}
