package tp.p1.object;

import tp.p1.game.Game;

public class Bomb extends Weapon{
	
	private DestroyerShip father;
	
	public Bomb(Game game, int x, int y, DestroyerShip father) {
		super(game,x,y,1,1);
		this.father = father;
	}

	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		this.father.deleteBomb();
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return ".";
	}
	
	/*
	// Getters
	public int getX() {
		return _x;
	}
	public int getY() {
		return _y;
	}
	public int getDamage() {
		return _damage;
	}
	
	// Setters
	public void setX(int x) {
		this._x = x;
	}
	public void setY(int y) {
		this._y = y;
	}
	
	// Logica
	public String toString() {
		return ".";
	}
	public boolean update() {
		this._y++;
		if(this._y < this._game.getX_SIZE()) {
			return !this._game.impactBomb(this._x, this._y);
		}
		else return false;
	}*/
}
