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
	
	
	private GameObject objectIn(int row, int col) {
		GameObject go = null;
		for(int i = 0; i < _size && go == null; i++) {
			go = (_objects[i] != null && _objects[i].isIn(row,col))? _objects[i]:null;
		}
		return go;
	}
	
	private void removeDead() {
		// TODO implement
	}
	
	private int getIndex( /* coordinadas */ ) {
		// TODO implement
	}	
	
	private void checkAttacks(GameObject object) {
		// TODO implement
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
	
	public boolean remove(GameObject object) {
		boolean removed = false;
		for(int i = 0; i < _size && !removed; i++) {
			if(_objects[i].equals(object)) {
				object.onDelete();
				_objects[i] = null;
				removed = true;
			}
		}
		return removed;
	}
	
	public void computerAction() {
		// TODO implement
	}

	public void update() {
		// TODO implement
	}
	
	public String toString( /* coordinadas */ ) {
		// TODO implement
	}

}
