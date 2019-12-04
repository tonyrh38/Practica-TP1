package tp.practica1Ships;

import tp.practica1Game.Game;
import tp.practica1GameObjects.GameObject;



	public class Supermissile extends Laser {
		
		public Supermissile(Game game, int x, int y) {
			super(game,1,2);
		}

		@Override
		public void computerAction() {}

		@Override
		public void onDelete() {}

		@Override
		public void move(boolean down,boolean movement) {
			this.y--;
			if(!this.game.isOnBoard(this.x,this.y)) this.onDelete();
		}

		@Override
		public String toString() {
			return "A";
		}
		
		@Override
		public String toSerialize() {
			return "X; "+ this.x +";"+ this.y;
		}
		
		@Override
		public boolean performAttack(GameObject other){
			other.receiveLaserAttack(this.damage);
			this.onDelete();
			return true;
		}
		
		@Override
		public boolean receiveBombAttack(int damage){
			this.getDamage(damage);
			return true;
		}
	}
