package invaders.game;

import invaders.model.DestroyerShip;
import invaders.model.Ovni;
import invaders.model.RegularShip;

public class BoardInitializer {
	
	private Level level;
	private GameObjectBoard board;
	private Game game;
	
	public  GameObjectBoard initialize(Game game, Level level) {
		this.level = level;
		this.game = game;
		board = new GameObjectBoard(Game.DIM_X, Game.DIM_Y);
		
		initializeOvni();
		initializeRegularAliens();
		initializeDestroyerAliens();
		return board;
	}
	
	private void initializeOvni () {
		Ovni ovni = new Ovni(this.game);
		this.board.add(ovni);
	}

	private void initializeRegularAliens () {
		for(int i = 0; i < this.level.getRegularShip(); i++) {
			RegularShip ship = new RegularShip(this.game,3 + (i % 4),1 + (i / 4));
			this.board.add(ship);
		}
	}
	
	private void initializeDestroyerAliens() {
		if(level.name() == "EASY") {
			for(int i = 0; i < 2; i++) {
				DestroyerShip ship = new DestroyerShip(this.game,4 + i,2);
				this.board.add(ship);
			}
		}
		if(level.name() == "HARD") {
			for(int i = 0; i < 2; i++) {
				DestroyerShip ship = new DestroyerShip(this.game,4 + i,3);
				this.board.add(ship);
			}
		}
		if(level.name() == "INSANE") {
			for(int i = 0; i < 4; i++) {
				DestroyerShip ship = new DestroyerShip(this.game,3 + i,3);
				this.board.add(ship);
			}
		}
	}
}
