import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

/***********************************************************************
 *
 * This class makes all the decisions on the Minesweeper board.
 *
 * @author Matthew Shampine, Connor Dudas
 * @version 1.0
 ***********************************************************************/
public class MineSweeperGame {

	/** 2D array of cells that resembles a MineSweeper board */
	private Cell[][] board;

	/** holds the current state of the game */
	private GameStatus gameStatus;

	/** board row */
	private int row;

	/** board column */
	private int col;

	// added this
	private int numMines;
	
	private boolean loadSuccessful;

	/** default board size for a beginner (8x8) */
	private final int DEFAULT_SIZE = 8;

	/*************************************************************************
	 * Constructor that initializes a 2D array of cells, filling all of the
	 * cells with default values. In addition, mines are placed randomly
	 * throughout the board and the mine count of neighboring cells are
	 * established.
	 * 
	 * @param size
	 *            the desired size of the board to be created
	 * @param numMines
	 *            the amount of mines that will in the game
	 ************************************************************************/
	public MineSweeperGame(int size, int numMines) {
		setGameStatus(GameStatus.NotOverYet);
		setLoadSuccessful(false);
		createBoardDimensions(size);
		setNumMines(numMines);
		board = new Cell[row][col];
		initialize();
		setMines(numMines);
		mineCount();
	}

	public MineSweeperGame(int size) {
		setGameStatus(GameStatus.NotOverYet);
		setLoadSuccessful(false);
		createBoardDimensions(size);
		board = new Cell[row][col];
		initialize();
	}

	private void createBoardDimensions(int size) {
		try {
			if (size < 3 || size > 24)
				throw new NumberFormatException();
			else {
				setRow(size);
				setCol(size);
			}
		} catch (NumberFormatException e) {
			setRow(DEFAULT_SIZE);
			setCol(DEFAULT_SIZE);
		}
	}

	private void initialize() {
		for (int row = 0; row < this.row; row++) {
			for (int col = 0; col < this.col; col++) {
				board[row][col] = new Cell();
			}
		}
	}

	private void setMines(int numMines) {
		int count = 0;
		Random randomLoc = new Random();
		while (count < numMines) {
			int row = randomLoc.nextInt(this.row);
			int col = randomLoc.nextInt(this.col);

			if (!board[row][col].isMine()) {
				board[row][col].setMine(true);
				count++;
			}
		}
	}

	public void selectCell(int row, int col) {
		board[row][col].setExposed(true); // selects the cell the user clicked
											// on

		if (checkStatus())
			setGameStatus(GameStatus.Won);
		else
			setGameStatus(GameStatus.NotOverYet);

		if (board[row][col].isMine()) {
			showMines(); // shows all of the mines when the user clicks on a
							// mine
			setGameStatus(GameStatus.Lost);
		}

		if (board[row][col].getMineCount() == 0)
			zeroCell(row, col);
	}

	private void showMines() {
		for (int row = 0; row < this.row; row++) {
			for (int col = 0; col < this.col; col++) {
				if (board[row][col].isMine() && !board[row][col].isExposed())
					board[row][col].setExposed(true); // TO-DO: needs to check
														// if a flag is on a
														// non-mine
			}
		}
	}

	private void zeroCell(int row, int col) {
		for (int r = (row - 1); r <= (row + 1); r++) {
			for (int c = (col + 1); c >= (col - 1); c--) {

				if (getCell(r, c) != null && !board[r][c].isExposed() && !board[r][c].isMine()) {
					board[r][c].setExposed(true);
					if (board[r][c].getMineCount() == 0)
						zeroCell(r, c);
				}
			}
		}
	}

	private boolean checkStatus() {
		boolean status = true;
		for (int row = 0; row < this.row; row++) {
			for (int col = 0; col < this.col; col++) {
				if (!board[row][col].isMine() && !board[row][col].isExposed())
					status = false;
			}
		}
		return status;
	}

	private void mineCount() {
		for (int row = 0; row < this.row; row++) {
			for (int col = 0; col < this.col; col++) {
				if (board[row][col].isMine())
					addMine(row, col);
			}
		}
	}

	private void addMine(int row, int col) {
		for (int r = -1; r <= 1; r++) {
			for (int c = 1; c >= -1; c--) {

				if (getCell(row + r, col + c) != null)
					board[row + r][col + c].setMineCount(1);
			}
		}
	}

	// added this
	public void save(String filename) {
		try {
			if (filename.length() == 0)
				throw new IOException();
			
			if (getGameStatus() == GameStatus.Lost)
				throw new InputMismatchException();
			
			PrintWriter writer = new PrintWriter(filename);

			writer.print(getRow() + " ");
			writer.println(getNumMines());

			for (int row = 0; row < this.row; row++) {
				for (int col = 0; col < this.col; col++) {

					writer.print(row + " ");
					writer.print(col + " ");

					writer.print(board[row][col].getMineCount() + " ");

					if (board[row][col].isMine())
						writer.print("t ");
					else
						writer.print("f ");

					if (board[row][col].isFlagged())
						writer.print("t ");
					else
						writer.print("f ");

					if (board[row][col].isExposed())
						writer.println("t");
					else
						writer.println("f");
				}
			}
			setLoadSuccessful(true);
			writer.close();
		} catch (InputMismatchException e) {
			JOptionPane.showMessageDialog(null, "Error: Cannot save a lost game!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: Unable to save game!");
		}
	}

	// added this
	public void load(String filename) {
		try {
			Scanner fileReader = new Scanner(new File(filename));
			String input = fileReader.nextLine();
			String[] inputSplit = input.split("\\s+");

			int numMines = Integer.parseInt(inputSplit[1]);
			setNumMines(numMines);

			while (fileReader.hasNextLine()) {
				input = fileReader.nextLine();
				inputSplit = input.split("\\s+");

				int row = Integer.parseInt(inputSplit[0]);
				int col = Integer.parseInt(inputSplit[1]);
				int mineCount = Integer.parseInt(inputSplit[2]);
				boolean isMine = (inputSplit[3].equals("t")) ? true : false;
				boolean isFlagged = (inputSplit[4].equals("t")) ? true : false;
				boolean isExposed = (inputSplit[5].equals("t")) ? true : false;
				setCell(row, col, mineCount, isMine, isFlagged, isExposed);
			}
			setLoadSuccessful(true);
			fileReader.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: Unable to load game!");
		} catch (InputMismatchException e) {
			JOptionPane.showMessageDialog(null, "Error: Corrupt file!");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error: Corrupt file!");
		} catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Error: Corrupt file!");
		}
	}

	private void setCell(int row, int col, int mineCount, boolean isMine, boolean isFlagged, boolean isExposed) {
		board[row][col].setMineCount(mineCount);
		board[row][col].setMine(isMine);
		board[row][col].setFlagged(isFlagged);
		board[row][col].setExposed(isExposed);
	}

	public int getSize(String filename) {
		int size = 0;
		try {
			if (filename.length() == 0)
				throw new FileNotFoundException();
			
			Scanner fileReader = new Scanner(new File(filename));
			String input = fileReader.nextLine();
			String[] inputSplit = input.split("\\s+");
			size = Integer.parseInt(inputSplit[0]);
			setLoadSuccessful(true);
			fileReader.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: Unable to load game!");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error: Corrupt file!");
		}
		return size;
	}
	

	public void setLoadSuccessful(boolean loadSuccessful) {
		this.loadSuccessful = loadSuccessful;
	}

	public boolean isloadSuccessful() {
		return loadSuccessful;
	}

	public Cell getCell(int row, int col) {
		return (row < 0 || col < 0 || row >= this.row || col >= this.col) ? null : board[row][col];
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getDEFAULT_SIZE() {
		return DEFAULT_SIZE;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	// added this
	public int getNumMines() {
		return numMines;
	}

	public void setNumMines(int numMines) {
		this.numMines = numMines;
	}
}