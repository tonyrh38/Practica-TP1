package invaders.launcher;

import java.util.Random;
import invaders.control.Controller;
import invaders.game.*;

public class Main {

	public static void main(String[] args) {
		Game game;
		try {
			if (args.length == 1 || args.length == 2) {
				Level level = Level.valueOf(args[0]);
				if (args.length == 2) {
					int seed = Integer.parseInt(args[1]);
					Random rand = new Random(seed);
					game = new Game(level, rand);
				} else {
					Random rand = new Random(314);
					game = new Game(level, rand);
				}
				Controller controller = new Controller(game);
				controller.run();
				System.exit(0);
			}
		} 
		catch (NumberFormatException e) {
			System.out.println("Seed must be an integer");
		}
	}
	
}
