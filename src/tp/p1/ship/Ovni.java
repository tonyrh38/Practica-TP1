package tp.p1.ship;

public class Ovni {
	//Atributos
	private int _x;
	private int _y;
	private int _vida;
	private int _puntos;
	
	//Metodos

	//Constructor
		public Ovni() {
			this.setX(0);
			this.setY(8);
			this.setVida(1);
			this.setPuntos(25);
		}
	
	//Getters
		public int getX() {
			return _x;
		}
		public int getY() {
			return _y;
		}
		public int getVida() {
			return _vida;
		}
		public int getPuntos() {
			return _puntos;
		}
	
	//Setters
		public void setX(int x) {
			this._x = x;
		}
		public void setY(int y) {
			this._y = y;
		}
		public void setVida(int vida) {
			this._vida = vida;
		}
		public void setPuntos(int puntos) {
			this._puntos = puntos;
		}
	
}
