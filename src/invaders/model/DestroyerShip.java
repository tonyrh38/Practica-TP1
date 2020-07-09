package invaders.model;

import extra.utils.FileContentsVerifier;
import invaders.game.Game;
import invaders.interfaces.IExecuteRandomActions;

public class DestroyerShip extends AlienShip{
	
	private Bomb _bomb;


	public DestroyerShip() {}
	
	public DestroyerShip(int x, int y, Game game) {
		super(x, y, game);
		_life = 1;
		_points = 10;
	}

	private DestroyerShip(int x, int y, int life, boolean direction, Game game) {
		super(x,y,game);
		_life = life;
		_points = 10;
		AlienShip._direction =  direction;
	}
	
	
	public int getId() {
		return _id;
	}
	
	public void resetBomb() {
		_bomb = null;
	}
	
	// GameObject Abstract Methods
	@Override
	public void computerAction() {
		if(_bomb == null && IExecuteRandomActions.canGenerateRandomBomb(_game)) {
			_bomb = new Bomb(_x, _y, this, _game);
			_game.add(_bomb);
		}
	}	
	
	@Override
	public String toString() {
		return "D[" + _life + "]";
	}

	@Override
	public String toSerialize() {
		int turn = _game.getLevel().getVel() - _game.getCycle() % _game.getLevel().getVel();
		return "D;"+ _x +","+ _y +";"+ _life +";"+ turn +";"+ (_direction?"right":"left")+";"+ _id;
	}

	// GameObjectGenerator Method
	@Override
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
		if(!verifier.verifyAlienShipString(stringFromFile, game, 10)) return null;
		else {
			String [] words = stringFromFile.split(";");
			if(words[0] != "D") return null;
			else {
				String [] coords = words[1].split(",");
				boolean direction = (words[4] == "right")? true : false;
				return new DestroyerShip(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]),
						Integer.parseInt(words[2]),	direction, game);
			}
		}
	}
	
}
