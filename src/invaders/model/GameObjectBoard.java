package invaders.model;

public class GameObjectBoard {
	
	private GameObject[] _objects;
	private int _size;
	
	private int _currentObjects;
	
	
	public GameObjectBoard (int width, int height) {
		_size = width * height;
		_objects = new GameObject[_size];
		_currentObjects = 0;
	}
	
	
	public boolean add(GameObject object) {
		boolean added = false;
		for(int i = 0; i < _size && !added; i++) {
			if(_objects[i] == null) {
				_objects[i] = object;
				added = true;
			}
		}
		return added;
	}
	
	private boolean remove (GameObject object) {
		boolean removed = false;
		for(int i = 0; i < _size && !removed; i++) {
			if(_objects[i].equals(object)) {
				_objects[i] = object;
				removed = true;
			}
		}
		return removed;
	}
	
	private GameObject objectIn(int row, int col) {
		GameObject go = null;
		for(int i = 0; i < _size && go == null; i++) {
			go = (_objects[i] != null && _objects[i].isIn(row,col))? _objects[i]:null;
		}
		return go;
	}
	
	private int getIndex( /* coordinadas */ ) {
		// TODO implement
	}	
	
	public void update() {
		// TODO implement
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

	public String toString( /* coordinadas */ ) {
		// TODO implement
	}

}
