package invaders.model;

public class GameObjectBoard {
	
	private GameObject[] _objects;
	private int _size;
	
	
	public GameObjectBoard (int width, int height) {
		_size = width * height;
		_objects = new GameObject[_size];
	}
	
	
	private void removeDead() {
		for(int i = 0; i < _size; i++) {
			if(_objects[i] != null && (!_objects[i].isAlive() || _objects[i].isOut())) {
				_objects[i].onDelete();
				_objects[i] = null;
			}
		}
	}	
	
	private void checkAttacks(GameObject object) {
		boolean attacked = false;
		for(int i = 0; i < _size && !attacked; i++) {
			attacked = (_objects[i] != null && (_objects[i].performAttack(object) || object.performAttack(_objects[i])));
		}
	}
	
	public boolean haveLanded() {
		boolean landed = false;
		for(int i = 0; i < _size && !landed; i++) {
			landed = (_objects[i] != null && _objects[i].hasLanded());
		}
		return landed;
	}
	
	public void shockwaveAttack(int damage) {
		for(int i = 0; i < _size; i++) {
			if(_objects[i] != null) _objects[i].receiveShockWaveAttack(damage);
		}
		removeDead();
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
		for(int i = 0; i < _size; i++) {
			if(_objects[i] != null) _objects[i].computerAction();
		}
	}

	public void update() {
		for(int i = 0; i < _size; i++) {
			if(_objects[i] != null) {
				//1) Se mueve cada objeto individualmente
				_objects[i].move();
				//2) Se comprueba que no recibe ningun ataque
				checkAttacks(_objects[i]);
			}
		}
		removeDead();
	}
	
	// GamePrinter Methods
	public String toString(int row, int col) {
		String str = "";
		for(int i = 0; i < _size; i++) {
			if(_objects[i] != null && _objects[i].isAlive() && _objects[i].isIn(row, col)) str = _objects[i].toString();
		}
		return str;
	}

}
