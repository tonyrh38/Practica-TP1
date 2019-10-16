package tp.p1.ship;

public class Ovni {
	//Atributos
	private int _x;
	private int _y;
	private int _vida;
	private int _puntos;
	private boolean enable;
	
	//Metodos

	//Constructor
		public Ovni() {
			this.setVida(1);
			this.setPuntos(25);
			this.setEnable(false);
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
		public boolean getEnable() {
			return enable;
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
		public void setEnable(boolean set) {
			this.enable = set;
			if(set) {
				this.setVida(1);
				this.setX(8);
				this.setY(0);
			}
		}
}
