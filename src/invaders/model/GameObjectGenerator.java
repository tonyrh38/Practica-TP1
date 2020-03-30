package invaders.model;

import extra.utils.FileContentsVerifier;
import invaders.exceptions.FileContentsException;
import invaders.game.Game;

public class GameObjectGenerator {
	
	private static GameObject[] availableGameObjects = {
		new UCMShip(),
		new Ovni(),
		new RegularShip(),
		new DestroyerShip(),
		new Shockwave(),
		new Bomb(),
		new Laser(),
		new SuperLaser()
	};
	
	public static GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier)	throws FileContentsException {
		GameObject gameObject = null;
		for (GameObject go: availableGameObjects) {
			gameObject = go.parse(stringFromFile, game, verifier);
			if (gameObject != null) break;
		}
		return gameObject;
	}
}
