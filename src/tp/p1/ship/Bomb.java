package tp.p1.ship;

public class Bomb {
	
	private int x;
	private int y;
	private int da�o;
	
	public Bomb(int x, int y) {
		this.setX(x);
		this.setY(y);
		this.da�o = 1;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getDa�o() {
		return da�o;
	}

}
