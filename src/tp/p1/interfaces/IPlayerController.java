package tp.p1.interfaces;

import tp.p1.exception.CommandExecuteException;

public interface IPlayerController {
	
	// PLAYER ACTIONS	
	public void move (int numCells) throws CommandExecuteException;
	public void shootLaser() throws CommandExecuteException;
	public void shootSupermisile() throws CommandExecuteException;
	public void shockWave() throws CommandExecuteException;
	public void buy() throws CommandExecuteException;
	
	// CALLBACKS
	public void receivePoints(int points);
	public void enableShockWave();
	public void enableLaser();
	
}
