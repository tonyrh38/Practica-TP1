package invaders.model;

import invaders.exceptions.CommandExecuteException;
import invaders.game.Game;

public class UCMShip extends Ship{
	
	private Laser _laser;
	private Shockwave _shockwave;


	public UCMShip(Game game) {
		super(Game._X/2, Game._Y - 1, 3, game);
	}

	
	public Laser getLaser() {
		return _laser;
	}
	
	public boolean hasShockwave() {
		return _shockwave != null;
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
	
	public void cleanLaser() {
		if(_laser != null && !_laser.isAlive()) _laser = null;
	}
	
	public void addShockwave() {
		_shockwave = new Shockwave(_game);
	}

	// IAttack Interface Methods
	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		if(_life > 0) return "^__^";
		else return "!xx!";
		
	}
	
}