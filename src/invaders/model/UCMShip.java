package invaders.model;

import invaders.exceptions.CommandExecuteException;
import invaders.game.Game;

public class UCMShip {
	
	private int _x;
	private int _y;
	private int _life;
	
	private Game _game;
	
	private Laser _laser;
	private Shockwave _shockwave;


	public UCMShip(Game game) {
		_x = 4;
		_y = 7;
		_life = 3;
		_game = game;
	}


	public int getLife() {
		return _life;
	}
	
	public Laser getLaser() {
		return _laser;
	}
	
	public boolean isAlive() {
		return _life > 0;
	}
	
	public boolean hasShockwave() {
		return _shockwave != null;
	}

	public boolean isIn(int row, int col) {
		return _x == col && _y == row;
	}
	
	public void move(int numCells) throws CommandExecuteException {
		if(_x + numCells < 0 || _x + numCells >= Game._X) throw new CommandExecuteException("No se puede avanzar ese numero de casillas");
		else _x += numCells;
	}
	
	public void shootLaser() throws CommandExecuteException{
		if(_laser == null) _laser = new Laser(_x, _y, _game);
		else throw new CommandExecuteException("Ya se ha disparado el laser");
	}
	
	public void shockwave() throws CommandExecuteException {
		if(_shockwave != null) {
			_shockwave.impacts();
			_shockwave = null;
		}
		else throw new CommandExecuteException("El shockwave no esta disponible");
	}
	
	public String toString() {
		return "^__^";
		
	}

}