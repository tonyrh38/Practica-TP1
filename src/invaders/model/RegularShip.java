package invaders.model;

import extra.utils.FileContentsVerifier;
import invaders.game.Game;
import invaders.interfaces.IExecuteRandomActions;

public class RegularShip extends AlienShip{
	
	private boolean _explosive;	
	
	
	public RegularShip() {}
	
	public RegularShip(int x, int y, Game game) {
		super(x, y, game);
		_life = 2;
		_points = 5;
		_explosive = false;
	}
	
	private RegularShip(int x, int y, int life, boolean direction, boolean explosive, Game game) {
		super(x,y,game);
		_life = life;
		_points = 5;
		AlienShip._direction =  direction;
		_explosive = explosive;
	}
	
	
	// GameObject Abstract Methods
	@Override
	public void computerAction() {
		if(!_explosive && IExecuteRandomActions.canGenerateExplosiveShip(_game)) _explosive = true;
	}
	
	@Override
	public void onDelete() {
		super.onDelete();
		if(_explosive) {
			_points = 0;
			_explosive = false;
			_game.explosionIn(_x, _y);
		}
	}
	
	@Override
	public String toString() {
		if(_explosive) return "E[" + _life + "]";
		else return "C[" + _life + "]";
	}
	
	@Override
	public String toSerialize() {
		int turn = _game.getLevel().getVel() - _game.getCycle() % _game.getLevel().getVel();
		return (_explosive?"E":"R") +";"+ _x +","+ _y +";"+ _life +";"+ turn +";"+ (_direction?"right":"left");
	}

	// GameObjectGenerator Method
	@Override
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
		if(!verifier.verifyAlienShipString(stringFromFile, game, 10)) return null;
		else {
			String [] words = stringFromFile.split(";");
			if(words[0] != "E" || words[0] != "R") return null;
			else {
				String [] coords = words[1].split(",");
				boolean direction = (words[4] == "right")? true : false;
				boolean explosive = (words[0] == "E")? true: false;
				return new RegularShip(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]),
						Integer.parseInt(words[2]),	direction, explosive, game);
			}
		}
	}
	
}
