package tp.practica1Ships;

import tp.practica1Game.Game;
import tp.practica1GameObjects.GameObject;

public class Bomb extends Weapon{
	
	private DestroyerShip father;
	
	public Bomb(Game game, int x, int y, DestroyerShip father) {
		super(game,x,y,1,1);
		this.father = father;
	}

	@Override
	public void computerAction() {}

	@Override
	public void onDelete() {
		this.father.deleteBomb();
	}

	@Override
	public void move(boolean down,boolean movement) {
		this.y++;
		if(!this.game.isOnBoard(this.x,this.y)) this.onDelete();
	}

	@Override
	public String toString() {
		return ".";
	}
	
	@Override
	public String toSerialize() {
		return "B; "+ this.x +";"+ this.y;
	}
	
	@Override
	public boolean performAttack(GameObject other){
		other.receiveBombAttack(this.damage);
		this.onDelete();
		return true;
	}
	
	@Override
	public boolean receiveLaserAttack(int damage) {
		this.getDamage(damage);
		return true;
	 }
}
