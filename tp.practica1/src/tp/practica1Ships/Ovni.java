package tp.practica1Ships;

public class Ovni extends EnemyShip{
	//Atributos de la clase
	private int row;
	private int col;
	private int life;
	private int puntos;

	public Ovni(int r, int c, int l, int p) {
		// TODO Auto-generated constructor stub
		this.row = r;
		this.col = c;
		this.life = l;
		this.puntos = p;
	}
	
	//Getters para mostrar a quien pida ver las cosas
	public int getLife () 
	{
		return this.life;
	}
	
	public int getCol ()
	{
		return this.col;
	}
	
	public int getRow ()
	{
		return this.row;
	}
	
	public int getPuntos()
	{
		return this.puntos;
	}
	
	//Setters para poder cambiar los atributos
	public void setLife (int damage)
	{
		this.life -= damage;
	}
	
	public void setRow ()
	{
		this.row -= 1;//así se mueve una casilla por ciclo
	}
	
	//inicialization of the ovni
//	Ovni ov = new Ovni(0, 8, 1, 25);

}
