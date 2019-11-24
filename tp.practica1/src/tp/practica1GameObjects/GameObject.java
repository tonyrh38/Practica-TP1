package tp.practica1GameObjects;
import tp.practica1Game.Coordinates;
import tp.practica1Game.Game;

//Clase más alta de la jerarquía


public abstract class GameObject implements IAttack {
	protected Coordinates coord; 
	protected int live;
	protected Game game;
	
	public GameObject( Game game, Coordinates coord, int live) {
		/* almacenar coordinadas iniciales */
		this.coord = coord;
		this.game = game;
		this.live = live;
	}
	
	/* métodos que devuelven el valor de las coordinadas */

	public boolean isAlive() {
		return this.live > 0;
	}

	public int getLive() {
		return this.live;
	}
	
	public boolean isOnPosition( Coordinates coord ) {
		return this.coord.equalCoords(coord) ;
	}

	public void getDamage (int damage) {
		this.live = damage >= this.live ? 0 : this.live - damage;
	}
	
	public boolean isOut() {
		return !game.isOnBoard( this.coord );
	}

	public abstract void computerAction();
	public abstract void onDelete();
	public abstract void move();
	public abstract String toString();
}
