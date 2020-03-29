package invaders.model;

import invaders.exceptions.CommandExecuteException;
import invaders.game.Game;

public class UCMShip extends Ship{
	
	private Laser _laser;
	private Shockwave _shockwave;
	private int _superlaser;
	

	public UCMShip(Game game) {
		super(Game._X/2, Game._Y - 1, game);
		_life = 3;
		_shockwave = new Shockwave(game);
		_superlaser = 0;
	}

	
	public boolean hasLaser() {
		return _laser != null;
	}
	
	public boolean hasShockwave() {
		return _shockwave.isEnable();
	}
	
	public void addSuperLaser() {
		_superlaser++;
	}
	
	public int getSuperLaser() {
		return _superlaser;
	}
	
	// IAttack Interface Method
	public boolean receiveBombAttack(int damage) {
		_life -= damage;
		return true;
	}
	
	// IPlayerController Interface Methods
	public void move(int numCells) throws CommandExecuteException {
		if(_x + numCells < 0 || _x + numCells >= Game._X) throw new CommandExecuteException("No se puede avanzar ese numero de casillas");
		else _x += numCells;
	}
	
	public void shootLaser() throws CommandExecuteException {
		if(_laser != null) throw new CommandExecuteException("Ya se ha disparado el laser");  
		else {
			_laser = new Laser(_x, _y, _game);
			_game.add(_laser);
		}
	}
	
	public void shootSuperLaser()throws CommandExecuteException {
		if(_superlaser <= 0) throw new CommandExecuteException("No te quedan supermisiles");  
		else {
			_game.add(new SuperLaser(_x, _y, _game));
			_superlaser--;
		}
	}
	
	public void shockwave() throws CommandExecuteException {
		if(!_shockwave.isEnable()) throw new CommandExecuteException("El shockwave no esta disponible");
		else _shockwave.shoot();
	}
	
	public void enableShockwave() {
		_shockwave.setEnable(true);
	}
	
	public void enableLaser() {
		_laser = null;
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
	
	@Override
	public String toSerialize() {
		return "P;"+ _x +","+ _y +";" + _life +";"+ _game.getScore() +";"+ _shockwave.toSerialize() +";"+ _superlaser;
	}
	
}