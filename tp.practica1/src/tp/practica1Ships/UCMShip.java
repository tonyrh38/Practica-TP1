package tp.practica1Ships;

import tp.practica1Game.Game;

public class UCMShip extends Ship {
	//Atributos de la clase
	private int life; //valores iniciales (hace getter y setter para estos
	private int damage;
	private boolean Shockwave; //tiene disponible Shockwave?
	private boolean Misil; //ha lanzado el misil?
	private int row; //Linea en la que está la nave
	private int col; //Columna en la que está la nave
	
	//Constructor
		public UCMShip(int l, int dam, boolean SW, boolean ms, int r, int c){
			this.life = l;
			this.damage = dam;
			this.Shockwave = SW;
			this.Misil = ms;
			this.row = r;
			this.col = c;
		}
	
	public UCMShip(Game game, int i, int j) {
			// TODO Auto-generated constructor stub
		}

	//Getters para mostrar a quien pida ver las cosas
	public int getLife ()
	{
		return this.life;
	}
	
	public int getRow ()
	{
		return this.row;
	}
	
	public int getCol ()
	{
		return this.col;
	}
	
	public boolean getShock()
	{
		return this.Shockwave;
	}
	
	public boolean getMisil ()
	{
		return this.Misil;
	}
	
	public int getDam ()
	{
		return this.damage;
	}
	
	//Setters para poder cambiar los atributos
	public void setLife(int damage) 
	{
		this.life -= damage;
	}
	
	public void setDam (int damage)
	{
		this.damage += damage;
	}
	
	public void setCol(int ncasillas)
	{
		this.col += ncasillas;
	}
	//initializing the ship, creating the object with its original values
//	UCMShip ship = new UCMShip(3,0,false,false,7,4);
	
	//métodos
//	private void move (String dir, int cellsn){
//		if (UCMShip row != ) {
//			
//		}
//	}
	

}
