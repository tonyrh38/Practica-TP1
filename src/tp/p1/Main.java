package tp.p1;

import tp.p1.game.*;

public class Main {

	public static void main(String[] args) {
		
	Game game;
	if (args.length < 1 || args.length > 2) {
	
		Level level = Level.valueOf(args[0]);
	
		if (args.length == 2) {
			int seed = Integer.parseInt(args[1]);
			game = new Game(level, seed);
		} else {
			game = new Game(level);
		}
	
		Controller controller = new Controller(game);
		controller.run();
	
		System.exit(0);
	}

}
