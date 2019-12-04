package tp.practica1Ships;

import tp.practica1Game.Game;

public abstract class AlienShip extends EnemyShip {

	public AlienShip(Game game, int x, int y, int life, int points) {
		super(game, x, y, life, points);
	}

	@Override
	public void move(boolean down,boolean movement) {
		if(this.game.canMove()) {
			if(down) this.y++;
			else if(movement) this.x++;
			else this.x--;
		}
	}
}
