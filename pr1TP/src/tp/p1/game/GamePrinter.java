package tp.p1.game;

import tp.p1.shipList.*;
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
		System.out.println("Life: " + game.getVidaUCMShip());
		System.out.println("Number of cycles: " + game.getCycleCounter());
		System.out.println("Points: " + game.getPuntuation());
		System.out.println("Remaining aliens: " + game.getRemaining());
		System.out.println("Shockwave: " + game.getShockwaveUCMShip());
	}
	
	private void encodeGame(Game game) {
		initBoard(numRows,numCols);
		encodeBomb(game.getBombList());
		encodeDestroyerShip(game.getDestroyerShipList());
		encodeRegularShip(game.getRegularShipList());
		encodeUCMShip(game.getXUCMShip(),game.getYUCMShip(),game.getVidaUCMShip());
		if(game.isOvni()) {
			encodeOvni(game.getXOvni(),game.getYOvni(),game.getVidaOvni());	
		}
	}
	private void encodeDestroyerShip(DestroyerShipList desShipList) {
		for(int i = 0; i < desShipList.getTam();i++) {
			if(desShipList.getVidaPos(i) != 0) {
				int x = desShipList.getXPos(i);
				int y = desShipList.getYPos(i);
				board[y][x] = "D[" + desShipList.getVidaPos(i) + "]";
			}
		}
	}
	private void encodeRegularShip(RegularShipList regShipList) {
		for(int i = 0; i < regShipList.getTam();i++) {
			if(regShipList.getVidaPos(i) != 0) {
				int x = regShipList.getXPos(i);
				int y = regShipList.getYPos(i);
				board[y][x] = "C[" + regShipList.getVidaPos(i) + "]";
			}
		}
	}
	private void encodeBomb(BombList bombList) {
		for(int i = 0; i < bombList.getTam() - 1;i++) {
			if(!bombList.isPosNull(i)) {
				int x = bombList.getXPos(i);
				int y = bombList.getYPos(i);
				board[y][x] = ".";
			}
		}
		if(!bombList.isPosNull(bombList.getPosPlayer())) {
			int x = bombList.getXPos(bombList.getPosPlayer());
			int y = bombList.getYPos(bombList.getPosPlayer());
			board[y][x] = "oo";
		}
	}
	private void encodeUCMShip(int x, int y, int vida) {
		if(vida > 0) {
			board[y][x] = "^_^";
		}
		else {
			board[y][x] = "!xx!";
		}
	}
	private void encodeOvni(int x, int y, int vida) {	
		board[y][x] = "O[" + vida + "]";
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
	public void printGameOver() {
		System.out.println("Game Over.");
	}
	public void printWin() {
		System.out.println("Win.");
	}

}
