package invaders.model;

import extra.utils.FileContentsVerifier;
import invaders.game.Game;
import invaders.interfaces.IExecuteRandomActions;

public class Ovni extends EnemyShip{
	
	private boolean _enable;
	
	
	public Ovni() {}
	
	public Ovni(Game game) {
		super(Game._X - 1, 0, game);
		_life = 1;
		_points = 25;
		_enable = false;
	}
	
	private Ovni(int x, int y, int life, Game game) {
		super(x,y,game);
		_life = life;
		_points = 25;
		_enable = true;
	}
	
	
	private void reset() {
		_x = Game._X - 1;
		_life = 1;
	}
	
	// IAttack Interface Method
		@Override
		public boolean receiveLaserAttack(int damage) {
			if(_enable) {
				_life -= damage;
				if(!isAlive()) {
					onDelete();
					reset();
				}
				return true;
			}
			else return false;
		}
	
	// GameObject Abstract Methods
	@Override
	public void computerAction() {
		if(!_enable && IExecuteRandomActions.canGenerateRandomOvni(_game)) {
			reset();
			_enable = true;
		}
	}
	
	@Override
	public void onDelete() {
			if(!isAlive()) {
				super.onDelete();
				_game.enableShockWave();
			}
			_enable = false;
	}

	@Override
	public void move() {
		if(_enable) {
			_x--;
			if(isOut()) {
				onDelete();
				reset();
			}
		}
	}

	@Override
	public String toString() {
		if(_enable) return "O[" + _life + "]";
		else return "";
	}
	
	@Override
	public String toSerialize() {
		if(_enable) return "O;"+ _x +","+ _y +";"+ _life;
		else return "";
	}

	// GameObjectGenerator Method
	@Override
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
		if(!verifier.verifyOvniString(stringFromFile, game, 10)) return null;
		else {
			String [] words = stringFromFile.split(";");
			if(!words[0].equals("O")) return null;
			else {
				String [] coords = words[1].split(",");
				return new Ovni(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(words[2]), game);
			}
		}
	}
	
}
