package tp.practica1GameObjects;

import tp.practica1Game.Game;

public abstract class GameObject implements IAttack {
	
	// Atributos
		protected int x;
		protected int y;
		protected int life;
		
		protected Game game;
		
	// Metodos
		
	// Constructor
		public GameObject( Game game,int x, int y, int life) {
			this.x = x;
			this.y = y;
			this.game = game;
			this.life = life;
		}
	
	// Getters
		public int getX() {
			return this.x;
		}
		public int getY() {
			return this.y;
		}
		public int getLive() {
			return this.life;
		}
	// Logica
		public boolean isAlive() {
			return this.life > 0;
		}
	
		public boolean isOnPosition(int x, int y) {
			return this.x == x && this.y == y;
		}
	
		public void getDamage (int damage) {
			this.life = damage >= this.life ? 0 : this.life - damage;
		}
		
		public boolean isOut() {
			return !game.isOnBoard(this.x, this.y);
		}
	
		public abstract void computerAction();
		public abstract void onDelete();
		public abstract void move(boolean down, boolean movement);
		public abstract String toString();
		public abstract String toSerialize();
}