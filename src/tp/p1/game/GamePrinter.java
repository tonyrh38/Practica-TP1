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
		this.board = new String[numRows][numCols];
		initBoard(rows,cols);
		encodeGame(game);
	}
	
	private void initBoard(int rows, int cols) {
		for(int i = 0; i < rows; i++) {
			for(int  j = 0; j < cols; j++) {
				board[i][j] = " ";
			}
		}
	}
	
	private void printInfo(Game game) {
		System.out.println("Life: " + game.getUCMShip().getVida());
		System.out.println("Number of cycles: " + game.getCycleCounter());
		System.out.println("Points: " + game.getPuntuation());
		System.out.println("Remaining aliens: " + game.getRemaining());
		System.out.println("Shockwave: " + game.getShockwaveUCM());
	}
	
	private void encodeGame(Game game) {
		initBoard(numRows,numCols);
		encodeDestroyerShip(game);
		encodeRegularShip(game);
		encodeBomb(game);
		encodeUCMShip(game);
		encodeOvni(game);	
	}
	private void encodeDestroyerShip(Game game) {
		int tam = game.getDestroyerShipList().getTam();
		for(int i = 0; i < tam;i++) {
			if(game.getDestroyerShipList().getPos(i).getVida() != 0) {
				int x = game.getDestroyerShipList().getPos(i).getX();
				int y = game.getDestroyerShipList().getPos(i).getY();
				board[x][y] = "D[" + game.getDestroyerShipList().getPos(i).getVida() + "]";
			}
		}
	}
	private void encodeRegularShip(Game game) {
		int tam = game.getRegularShipList().getTam();
		for(int i = 0; i < tam;i++) {
			if(game.getRegularShipList().getPos(i).getVida() != 0) {
				int x = game.getRegularShipList().getPos(i).getX();
				int y = game.getRegularShipList().getPos(i).getY();
				board[x][y] = "C[" + game.getRegularShipList().getPos(i).getVida() + "]";
			}
		}
	}
	private void encodeBomb(Game game) {
		for(int i = 0; i < game.getBombList().getTam() - 1;i++) {
			if(game.getBombList().getPos(i) != null) {
				int x = game.getBombList().getPos(i).getX();
				int y = game.getBombList().getPos(i).getY();
				board[x][y] = ".";
			}
		}
		if((game.getBombList().getPos(game.getBombList().getTam() - 1) != null)) {
			int x = game.getBombList().getPos(game.getBombList().getTam() - 1).getX();
			int y = game.getBombList().getPos(game.getBombList().getTam() - 1).getY();
			board[x][y] = "oo";
		}
	}
	private void encodeUCMShip(Game game) {
		int x = game.getUCMShip().getX();
		int y = game.getUCMShip().getY();
		if(game.getUCMShip().getVida() > 0) {
			board[x][y] = "^_^";
		}
		else {
			board[x][y] = "!xx!";
		}
	}
	private void encodeOvni(Game game) {
		if(game.isOvni()) {
			int x = game.getOvni().getX();
			int y = game.getOvni().getY();
			board[x][y] = "O[" + game.getOvni().getVida() + "]";
		}
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
