package invaders.game;

public enum Level {

	EASY (4, 2, 0.1, 3, 0.5),
	HARD (8, 2, 0.3, 2, 0.2),
	INSANE (8, 4, 0.5, 1, 0.1);
	
	private int _regularShip;
	private int _destroyerShip;
	private double _freq;
	private int _vel;
	private double _ovni;
	private double _explosiveFreq = 0.05;
	
	
	private Level(int regularShip, int destroyerShip, double freq, int vel, double ovni) {
		_regularShip = regularShip;
		_destroyerShip = destroyerShip;
		_freq = freq;
		_vel = vel;
		_ovni = ovni;
	}
	
	
	public int getRegularShip() { 
		return _regularShip; 
	}
	
	public int getDestroyerShip() { 
		return _destroyerShip; 
	}	
	
	public double getFreq() { 
		return _freq;
	}
	
	public double getVel() { 
		return _vel;
	}
	
	public double getOvni() { 
		return _ovni;
	}

	public double getExplosive() {
		return _explosiveFreq;
	}
	
}
