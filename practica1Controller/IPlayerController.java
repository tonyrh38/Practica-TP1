package tp.practica1Controller;

public interface IPlayerController {
	
	// PLAYER ACTIONS	
	public boolean move (int numCells);
	public boolean shootLaser();
	public boolean shootSupermisile();
	public boolean shockWave();
	public boolean buy();
	
	// CALLBACKS
	public void receivePoints(int points);
	public void enableShockWave();
	public void enableLaser();
	
}
