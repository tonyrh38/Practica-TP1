package invaders.game;

import invaders.model.GameObjectBoard;
import invaders.model.Ovni;
import invaders.model.RegularShip;
import invaders.model.DestroyerShip;

public class BoardInitializer {

	private Level _level;
	private Game _game;
	private GameObjectBoard _board;
	
	
	private void initializeOvni () {
		_board.add(new Ovni(_game));
	}

	private void initializeRegularAliens () {
		for(int i = 0; i < _level.getRegularShip(); i++) {
			RegularShip ship = new RegularShip(3 + (i % 4), 1 + (i / 4), _game);
			_board.add(ship);
		}
	}
	
	private void initializeDestroyerAliens() {
		if(_level.toString() == "EASY") {
			for(int i = 0; i < _level.getDestroyerShip(); i++) {
				DestroyerShip ship = new DestroyerShip(4 + i, 2, _game);
				_board.add(ship);
			}
		}
		if(_level.toString() == "HARD") {
			for(int i = 0; i < _level.getDestroyerShip(); i++) {
				DestroyerShip ship = new DestroyerShip(4 + i, 3, _game);
				_board.add(ship);
			}
		}
		if(_level.toString() == "INSANE") {
			for(int i = 0; i < _level.getDestroyerShip(); i++) {
				DestroyerShip ship = new DestroyerShip(3 + i, 3, _game);
				_board.add(ship);
			}
		}
	}
	
	public  GameObjectBoard initialize(Game game, Level level) {
		_level = level;
		_game = game;
		_board = new GameObjectBoard(Game._X, Game._Y);	
		initializeOvni();
		initializeRegularAliens();
		initializeDestroyerAliens();
		return _board;
	}

}
