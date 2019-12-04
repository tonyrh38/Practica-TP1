package tp.p1.game;

public enum Level {

	EASY (4, 2, 0.1, 3, 0.5),
	HARD (8, 2, 0.3, 2, 0.2),
	INSANE (8, 4, 0.5, 1, 0.1);
	
	private int regularShip;
	private int destroyerShip;
	private double freq;
	private int vel;
	private double ovni;
	private double explodeFreq = 0.05;
	
	private Level(int regularShip, int destroyerShip, double freq, int vel, double ovni) {
		this.regularShip = regularShip;
		this.destroyerShip = destroyerShip;
		this.freq = freq;
		this.vel = vel;
		this.ovni = ovni;
	}
	
	public int getRegularShip() { 
		return this.regularShip; 
	}
	public int getDestroyerShip() { 
		return this.destroyerShip; 
	}	
	public double getFreq() { 
		return this.freq;
	}
	public double getVel() { 
		return this.vel;
	}
	public double getOvni() { 
		return this.ovni;
	}
	public double getExplodeFreq() {
		return this.explodeFreq;
	}
}
