package invaders.model;

import invaders.exceptions.CommandExecuteException;
import invaders.game.Game;

public class UCMShip extends Ship{
	
	private Laser _laser;
	private Shockwave _shockwave;


	public UCMShip(Game game) {
		super(Game._X/2, Game._Y - 1, game);
		_life = 3;
	}

	
	public Laser hasLaser() {
		return _laser.isEnable();
	}
	
	public boolean hasShockwave() {
		return _shockwave.isEnable();
	}
	
	// IPlayerController Methods
	public void move(int numCells) throws CommandExecuteException {
		if(_x + numCells < 0 || _x + numCells >= Game._X) throw new CommandExecuteException("No se puede avanzar ese numero de casillas");
		else _x += numCells;
	}
	
	public void shootLaser() throws CommandExecuteException {
		if(!_laser.isEnable()) throw new CommandExecuteException("Ya se ha disparado el laser");  
		else _laser.shoot(_x, _y);
	}
	
	public void shockwave() throws CommandExecuteException {
		if(!_shockwave.isEnable()) throw new CommandExecuteException("El shockwave no esta disponible");
		else _shockwave.shoot();
	}
	
	// GameObject Abstract Methods
	@Override
	public void computerAction() {}

	@Override
	public void onDelete() {}

	@Override
	public void move() {}
	
	@Override
	public String toString() {
		if(_life > 0) return "^__^";
		else return "!xx!";	
	}
	
}