package invaders.model;

import invaders.game.Game;

public class Shockwave extends Weapon{

	private boolean _enable;
	
	
	public Shockwave(Game game) {
		super(Game._X, Game._Y, game);
		_damage = 1;
		_enable = false;
	}


	public boolean isEnable() {
		return _enable;
	}

	public void setEnable(boolean b) {
		_enable = b;
	}
	
	public void shoot() {
		_game.shockwaveAttack(_damage);
	}
	
	// GameObject Abstract Methods
	@Override
	public void computerAction() {}

	@Override
	public void onDelete() {
		_enable = false;	
	}

	@Override
	public void move() {}

	@Override
	public String toString() {
		return "";
	}
	
	@Override
	public String toSerialize() {
		if(_enable) return "ShockWave";
		else return "none";
	}

}