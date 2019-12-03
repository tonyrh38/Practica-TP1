package tp.p1.object;

import tp.p1.game.Game;

public class UCMShip extends Ship{
	
		private Shockwave shockwave;
		private Laser laser;
		private int supermisile;

		public UCMShip(Game game,int x,int y) {
			super(game,x,y,3,0);
			this.shockwave = new Shockwave(game,0,0);
			this.laser = new Laser(this.game, this.x, this.y);
			this.game.addObject(this.laser);
			this.supermisile = 0;
		}

		public int getTotalPuntuation() {
			return this.points;
		}
		
		public int getNumSupermisile() {
			return this.supermisile;
		}
		
		@Override
		public boolean receiveBombAttack(int damage){
			this.getDamage(damage);
			return true;
		}
		
		@Override
		public void computerAction() {}

		@Override
		public void onDelete() {}
		
		@Override
		public void move(boolean down,boolean movement) {}

		public void move(int numCells) {
			this.x += numCells;
			if(this.x < 0) this.x = 0;
			else if(this.x >= Game.DIM_X) this.x = Game.DIM_X - 1;
		}

		@Override
		public String toString() {
			if(this.isAlive()) return "^__^";
			else return "!xx!";
		}

		public String shockwaveToString() {
			return this.shockwave.toString();
		}

		public boolean shoot() {
			if(this.laser == null) {
				this.laser = new Laser(this.game, this.x,this.y);
				this.game.addObject(this.laser);
				return true;
			}
			else return false;
		}

		public boolean shockwaveAttack() {
			return this.shockwave.attack();
		}
		
		public void addSupermisile() {
			this.supermisile++;
		}
		
		public void updatePoints(int points) {
			this.points += points;
		}

		public void enableShockwave() {
			this.shockwave.enable();
		}

		public void enableLaser() {
			this.laser = null;
		}

		public boolean shootSupermisile() {
			if(this.supermisile > 0) {
				Supermisile misil = new Supermisile(this.game,this.x,this.y);
				this.game.addObject(misil);
				this.supermisile--;
				return true;
			}
			else return false;
		}

		public String toSerialize() {
			if(this.shockwave != null) return "P;"+ this.x +";"+ this.y + ";"+ this.life +
					";" + this.points +";on;"+ this.supermisile ;
			else return "P;"+ this.x +";"+ this.y + ";"+ this.life +
					";" + this.points +";off;"+ this.supermisile ;
		}
}