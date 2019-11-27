package tp.p1.game;

import tp.p1.object.*;

public class GameObjectBoard {
	private GameObject[] objects;
	private int currentObjects;
	
	public GameObjectBoard (int width, int height) {
		this.objects = new GameObject[width * height];
		this.currentObjects = 0;
	}
	
	private int getCurrentObjects () {
		return this.currentObjects;
	}
	
	public void add (GameObject object) {
		this.objects[this.currentObjects] = object;
		this.currentObjects++;
	}
	
	private GameObject getObjectInPosition (int x, int y) {
		int idx = getIndex(x,y);
		if(idx != -1) return this.objects[idx];
		else return null;
	}
	
	private int getIndex(int x, int y) {
		boolean found = false;
		int idx = -1;
		for(int i = 0; i < this.currentObjects && !found; i++) {
			if(this.objects[i] != null && this.objects[i].getX() == x && this.objects[i].getY() == y) {
				idx = i;
				found = true;
			}
		}
		return idx;
	}

	private void remove(GameObject object) {
		boolean removed = false;
		for(int i = 0; i < this.currentObjects && !removed; i++) {
			if(this.objects[i].equals(object)) {
				this.objects[i] = null;
				this.currentObjects--;
				removed = true;
			}
		}
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

	public String toString(int x, int y) {
		GameObject object = this.getObjectInPosition(x, y);
		if(object != null) return object.toString();
		else return "";
	}

	public boolean aliensLanded() {
		boolean landed = false;
		
		for(int i = 0; i < this.currentObjects && !landed; i++) {
			if(this.objects[i] != null && this.objects[i].getClass() == AlienShip.class) {
				landed = this.objects[i].getX() == Game.DIM_Y - 1;
			}
		}
		
		return landed;
	}

	public boolean aliensDead() {
		boolean dead = true;
		
		for(int i = 0; i < this.currentObjects && dead; i++) {
			dead = !(this.objects[i] != null && this.objects[i].getClass() == AlienShip.class);
		}
		
		return dead;
	}

	public int aliveAliens() {
		int alive = 0;
		
		for(int i = 0; i < this.currentObjects; i++) {
			if(this.objects[i] != null && this.objects[i].getClass() == AlienShip.class) alive++;
		}
		
		return alive;
	}

	public String objectAtToString(int x, int y) {
		boolean found = false;
		String str = "";
		
		for(int i = 0; i < this.currentObjects && !found; i++) {
			if(this.objects[i] != null && this.objects[i].isOnPosition(x, y)) {
				str = this.objects[i].toString();
				found = true;
			}
		}
		
		return str;
	}
}
