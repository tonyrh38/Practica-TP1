package tp.practica1GameObjects;
import tp.practica1Game.Coordinates;

/*Objeto que controla la lista de los objetos del juego
*/

public class GameObjectBoard {
	private GameObject[] objects;
	private int currentObjects;
	
	public GameObjectBoard (int width, int height) {
		// TODO implement
	}
	
	private int getCurrentObjects () {
		// TODO implement
	}
	
	public void add (GameObject object) {
		// TODO implement
	}
	
	private GameObject getObjectInPosition (Coordinates coord ) {
		// TODO implement
	}
	
	private int getIndex( Coordinates coord  ) {
		// TODO implement
	}

	private void remove (GameObject object) {
		// TODO implement
	}
	
	public void update() {
		// TODO implement
		//Recorrer el array de objetos llamando al update de cada uno
	}
	
	private void checkAttacks(GameObject object) {
		// TODO implement
	}
	
	public void computerAction() {
		// TODO implement
	}
	
	private void removeDead() {
		// TODO implement
	}

	public String toString( Coordinates coord ) {
		// TODO implement
	}

}
