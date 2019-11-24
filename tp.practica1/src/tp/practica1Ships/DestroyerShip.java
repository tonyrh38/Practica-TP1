package tp.practica1Ships;

public class DestroyerShip extends AlienShip {
	//atributos de la clase
	
	private int damage;
	private int puntos;

	
	public DestroyerShip(int r, int c) {

		this.damage = 1;
		this.puntos = 10;
		
	}
	

	//Getters para mostrar a quien pida ver las cosas
	public int getDam()
	{
		return this.damage;
	}
	
	
	public int getPuntos ()
	{
		return this.puntos;
	}
	
	//
//	DestroyerShip DS = new DestroyerShip (r, c, 1, 1, 10); AL MAIN TORPEEEE

}
