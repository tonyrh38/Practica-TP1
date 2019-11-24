package tp.practica1Printer;

import tp.practica1Game.Game;

public class Serializer implements GamePrinter {

	private static final String SerializerPrinterName = "SerializerName";

	public Serializer() {
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public String toString(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GamePrinter parse(String name) {
		if (SerializerPrinterName.equals(name))
		{
			return this;  //Devuelve el propio objeto Serializer
		}
		else
		{
		return null;
		}
	}

	@Override
	public String helpText() {
		
		return SerializerPrinterName;
	}

}
