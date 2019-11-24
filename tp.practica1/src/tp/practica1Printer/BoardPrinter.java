//package tp.practica1Game;
//
//public class GamePrinter {
//	
//	//atributos de la clase
//	private int cellSize;
//	private String space;
//	private String vDelimeter;
//	private String hDelimeter;
//	private Game game;
//
//	public GamePrinter(Game game) {
//		this.cellSize = 7;
//		this.space = "";
//		this.vDelimeter = "|";
//		this.hDelimeter = "-";
//		this.game = game;
//		
//	}
//	
//	public String toString() {
//		return str; 
//	}
//
//}
package tp.practica1Printer;

import tp.practica1Game.Coordinates;
import tp.practica1Game.Game;
import tp.practica1Utils.MyStringUtils;

public class BoardPrinter implements GamePrinter{
	
	int numRows; 
	int numCols;
	final String space = " ";
	final String BoardPrinterName = "BoardPrinter";
	
	
	public BoardPrinter (int rows, int cols) {
		this.numRows = rows;
		this.numCols = cols;
	}


	@Override
	public String toString(Game game) {
		
		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (numCols * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		
		str.append(lineDelimiter);
		
		for(int i=0; i<numRows; i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j<numCols; j++) {
					str.append( MyStringUtils.centre(game.positionToString(new Coordinates(i,j)), cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
		}
		return str.toString();
	}

	@Override
	public GamePrinter parse(String name) {
		if (BoardPrinterName.equals(name))
		{
			return this;  //Devuelve el propio objeto BoardPrinter
		}
		else
		{
		return null;
		}
	}

	@Override
	public String helpText() {
		
		return BoardPrinterName;
	}
}