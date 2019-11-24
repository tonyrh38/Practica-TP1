package tp.practica1Ships;

public class RegularShip extends AlienShip {
	//atributos
	private int col;
	private int row;
	private int puntos;
	private int life;
	
	

	public RegularShip(int c, int r, int p, int l) {
		// TODO Auto-generated constructor stub
		this.col = c;
		this.row = r;
		this.puntos = p;
		this.life = l;
		}
	
	//Getters para mostrar a quien pida ver las cosas
	public int getCol()
	{
		return this.col;
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	public int getPuntos()
	{
		return this.puntos;
	}
	
	public int getLife ()
	{
		return this.life;
	}
	
	//Setters para poder cambiar los atributos
	public void setLife(int damage)
	{
		this.life -= damage;
	}
	
	public void setRow (int mover)
	{
		this.row += mover;
	}
	
	public void setCol (int movec)
	{
		this.col += movec;
	}
	
	//Creating the new object
//	RegularShip RS = new RegularShip (c, r, 5, 2);

}
