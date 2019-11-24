package tp.practica1GameObjects;
import tp.practica1Game.Game;
import tp.practica1Ships.Ovni;
import tp.practica1cosasRandom.FileContentsVerifier;


public class GameObjectGenerator {

	private static GameObject[] availableGameObjects = {
			new UCMShip(),
			new Ovni(),
			new RegularAlien(),
			new DestroyerAlien(),
			new ExplosiveAlien(),
			new ShockWave(),
			new Bomb(),
			new Missile()
			new Supermissile()
		};

	public static GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier)
					throws FileContentsException {		
		GameObject gameObject = null;
		for (GameObject go: availableGameObjects) {
			gameObject = go.parse(stringFromFile, game, verifier);
			if (gameObject != null) break;
		}
		return gameObject;
	}

}
