import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class MineSweeperGame {

	/** 2D array of cells that resembles a MineSweeper board */
	private Cell[][] board;

	/** holds the current status of the game */
	private GameStatus gameStatus;

	/** board row */
	private int row;

	/** board column */
	private int col;

	// TO-DO: maybe do not need this int, and method
	private final int DEFAULT_SIZE = 8;

	private final int DEFAULT_MINES = 10;

	public MineSweeperGame(int size, int numMines) {
		setGameStatus(GameStatus.NotOverYet);
		createBoardDimensions(size);
		board = new Cell[row][col];
		initialize();
		setMines(numMines);
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
			// JOptionPane.showMessageDialog(null, "Error: Using default board
			// size! ");
			setRow(DEFAULT_SIZE);
			setCol(DEFAULT_SIZE);
		}
	}

	public void initialize() {
		for (int row = 0; row < this.row; row++) {
			for (int col = 0; col < this.col; col++) {
				board[row][col] = new Cell(false, false);
			}
		}
	}

	public void setMines(int numMines) {
		int count = 0;
		Random randomLoc = new Random();
		while (count < numMines) {
			int r = randomLoc.nextInt(row);
			int c = randomLoc.nextInt(col);

			if (!board[r][c].isMine()) {
				board[r][c].setMine(true);
				count++;
			}
		}
	}

	public void selectCell(int row, int col) {
		board[row][col].setExposed(true); // selects the cell the user clicked on

		if (board[row][col].isMine()) {
			showMines(); // shows all of the mines when the user clicks on a mine
			setGameStatus(GameStatus.Lost);
		}

		if (board[row][col].getMineCount() == 0)
			zeroCell(row, col);

		if (checkStatus())
			setGameStatus(GameStatus.Won);
		else
			setGameStatus(GameStatus.NotOverYet);
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

	private void showMines() {
		for (int row = 0; row < this.row; row++) {
			for (int col = 0; col < this.col; col++) {
				if (board[row][col].isMine() && !board[row][col].isExposed())
					board[row][col].setExposed(true);
			}
		}
	}
	
	// TO-DO: finish when the user clicks on a zero spot
	private void zeroCell(int row, int col) {

	}

	public void mineCount() {
		for (int row = 0; row < this.row; row++) {
			for (int col = 0; col < this.col; col++) {
				if (board[row][col].isMine())
					addMine(row, col);
			}
		}
	}

	private void addMine(int row, int col) {
		if (getCell(row - 1, col) != null)
			board[row - 1][col].setMineCount(1);
		if (getCell(row - 1, col + 1) != null)
			board[row - 1][col + 1].setMineCount(1);
		if (getCell(row, col + 1) != null)
			board[row][col + 1].setMineCount(1);
		if (getCell(row + 1, col + 1) != null)
			board[row + 1][col + 1].setMineCount(1);
		if (getCell(row + 1, col) != null)
			board[row + 1][col].setMineCount(1);
		if (getCell(row + 1, col - 1) != null)
			board[row + 1][col - 1].setMineCount(1);
		if (getCell(row, col - 1) != null)
			board[row][col - 1].setMineCount(1);
		if (getCell(row - 1, col - 1) != null)
			board[row - 1][col - 1].setMineCount(1);
	}

	public void save(String filename) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filename + ".txt");
			writer.print(board[row][col].getMineCount() + ",");

			for (int row = 0; row < this.row; row++) {
				for (int col = 0; col < this.col; col++) {

					if (board[row][col].isMine())
						writer.print("t,");
					else
						writer.print("f,");

					if (board[row][col].isFlagged())
						writer.print("t,");
					else
						writer.print("f,");

					if (board[row][col].isMine())
						writer.println("t,");
					else
						writer.println("f,");
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: Unable to save game! ");
		} finally {
			writer.close();
		}
	}

	public void load(String filename) {
		Scanner fileReader = null;

		try {
			fileReader = new Scanner(new File(filename));

			while (fileReader.hasNextLine()) {
				// String input = fileReader.nextLine();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error: Unable to load game! ");
		}
	}

	// TO-DO: maybe do not need this method
	public Cell[][] getBoard() {
		return board;
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

	// TO-DO: might not need these two methods
	public int getDEFAULT_SIZE() {
		return DEFAULT_SIZE;
	}

	public int getDEFAULT_MINES() {
		return DEFAULT_MINES;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}
}
