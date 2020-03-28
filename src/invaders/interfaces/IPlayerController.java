package invaders.interfaces;

import invaders.exceptions.CommandExecuteException;

public interface IPlayerController {
	// Player actions
	public void move (int numCells) throws CommandExecuteException;
	public void shootLaser() throws CommandExecuteException;
	public void shootSuperLaser() throws CommandExecuteException;
	public void shockwave() throws CommandExecuteException;
	
	// Callback
	public void receivePoints(int points);
	public void enableShockWave();
	public void enableLaser();
}
