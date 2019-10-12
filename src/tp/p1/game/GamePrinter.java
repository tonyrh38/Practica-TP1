package tp.p1.game;

import tp.p1.util.MyStringUtils;

public class GamePrinter {
	
	int numRows; 
	int numCols;
	String[][] board;
	final String space = " ";
	
	
	public GamePrinter (Game game, int rows, int cols) {
		this.numRows = rows;
		this.numCols = cols;
		initBoard(rows,cols);
		encodeGame(game);
	}
	
	private void initBoard(int rows, int cols) {
		this.board = new String[numRows][numCols];
		for(int i = 0; i < cols; i++) {
			for(int  j = 0; j < rows; j++) {
				board[i][j] = " ";
			}
		}
	}
	
	private void printInfo(Game game) {
		System.out.println("Life: " + game.getVidaUCM());
		System.out.println("Number of cycles: " + game.getCycleCounter());
		System.out.println("Points: " + game.getPuntuation());
		System.out.println("Remaining aliens: " + game.getRemaining());
		System.out.println("Shockwave: " + game.getShockwaveUCM());
	}
	
	private void encodeGame(Game game) {
		encodeDestroyerShip(game);
		encodeRegularShip(game);
		encodeBomb(game);
		encodeUCMShip(game);
		encodeOvni(game);
		
	}
	private void encodeDestroyerShip(Game game) {
		for(int i = 0; i < game.getDestroyerShipList().getTam();i++) {
			
		}
	}
	private void encodeRegularShip(Game game) {
		for(int i = 0; i < game.getRegularShipList().getTam();i++) {
					
				}
	}
	private void encodeBomb(Game game) {
		for(int i = 0; i < game.getBombList().getTam();i++) {
					
				}
		}
	private void encodeUCMShip(Game game) {
		
	}
	private void encodeOvni(Game game) {
		
	}
	
	public String toString() {

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
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
		}
		return str.toString();
	}
	
	public void printGame(Game game) {
		this.encodeGame(game);
		this.printInfo(game);
		System.out.println(this.toString());		
	}

}
