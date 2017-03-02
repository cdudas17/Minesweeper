import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * This class creates a fully functional Minesweeper board and makes all
 * decisions regarding the state of the game.
 * 
 * @author Matthew Shampine, Conor Dudas
 * @version 1.0
 */
public class MineSweeperGame {

	/** 2D array of cells that resembles a MineSweeper board. */
	private Cell[][] board;

	/** Holds the current state of the game. */
	private GameStatus gameStatus;

	/** Board row. */
	private int row;

	/** Board column. */
	private int col;

	/** Number of mines the game currently has. */
	private int numMines;

	/** Whether or not the file was loaded successfully. */
	private boolean loadSuccessful;

	/** Default board size for a beginner (8x8). */
	private final int DEFAULT_SIZE = 8;

	/**
	 * Constructor that initializes a 2D array of cells, filling all of the
	 * cells with default values. In addition, mines are placed randomly
	 * throughout the board and the mine count of neighboring cells are
	 * established.
	 * 
	 * @param size
	 *            The desired size of the board to be created
	 * @param numMines
	 *            The amount of mines that will in the game
	 */
	public MineSweeperGame(final int size, final int numMines) {
		setGameStatus(GameStatus.NotOverYet);
		setLoadSuccessful(false);
		createBoardDimensions(size);
		setNumMines(numMines);
		board = new Cell[row][col];
		initialize();
		setMines(numMines);
		mineCount();
	}

	/**
	 * Constructor that initializes a 2D array of cells, filling all of the
	 * cells with default values. This constructor creates a new game when a
	 * file is loaded. It does not add the mines to the board or set the
	 * mine count.
	 * 
	 * @param size
	 *            The desired size of the board to be created
	 */
	public MineSweeperGame(final int size) {
		setGameStatus(GameStatus.NotOverYet);
		setLoadSuccessful(false);
		createBoardDimensions(size);
		board = new Cell[row][col];
		initialize();
	}

	/**
	 * This method is used to set the values for row and col so the board 
	 * can be created using these dimensions.
	 * 
	 * @param size
	 *            The desired size of the board to be created
	 * @throws NumberFormatException
	 *             Throws an exception when the size provided is smaller 
	 *             than a (3x3) board or exceeds a (24x24 size board).
	 */
	private void createBoardDimensions(final int size) {
		try {
			if (size < 3 || size > 24) {
				throw new NumberFormatException();
			} else {
				setRow(size);
				setCol(size);
			}
		} catch (NumberFormatException e) {
			setRow(DEFAULT_SIZE);
			setCol(DEFAULT_SIZE);
		}
	}

	/**
	 * This method instantiates a new default cell for each location on the 
	 * 2D array.
	 * 
	 */
	private void initialize() {
		for (int row = 0; row < this.row; row++) {
			for (int col = 0; col < this.col; col++) {
				board[row][col] = new Cell();
			}
		}
	}

	/**
	 * This method randomly places mines on the board.
	 * 
	 * @param numMines
	 *            The number of mines to be places on the board
	 */
	private void setMines(final int numMines) {
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

	/**
	 * This method handles the logic when a user clicks on a cell. It first
	 * exposes the cell then checks whether or not there are any non-mine
	 * cells that haven't been exposed. Next it checks if the user clicked
	 * on a mine, and lastly if the cell has a mine count of 0 it opens up 
	 * the neighboring cells.
	 * 
	 * @param row
	 *            The row of the cell location
	 * @param col
	 *            The col of the cell location
	 */
	public void selectCell(final int row, final int col) {
		board[row][col].setExposed(true);

		if (checkStatus()) {
			setGameStatus(GameStatus.Won);
		} else {
			setGameStatus(GameStatus.NotOverYet);
		}

		if (board[row][col].isMine()) {
			// shows all of the mines when the user clicks on a mine
			showMines();
			setGameStatus(GameStatus.Lost);
		}

		if (board[row][col].getMineCount() == 0) {
			zeroCell(row, col);
		}
	}

	/**
	 * This helper method is used to expose all of the mines when the user
	 * clicks on a mine and loses the game.
	 * 
	 */
	private void showMines() {
		for (int row = 0; row < this.row; row++) {
			for (int col = 0; col < this.col; col++) {
				if (board[row][col].isMine() && !board
						[row][col].isExposed()) {
					// TO-DO: needs to check if a flag is 
					// non-mine
					board[row][col].setExposed(true);
				}
			}
		}
	}

	/**
	 * This helper method is used when a user clicks on a cell that
	 * has a mine count of zero. It opens up the surrounding cells 
	 * and checks to see if any of the neighboring cells that have 
	 * a mine count of zero and repeats the process.
	 * 
	 * @param row
	 *            The row of the cell location
	 * @param col
	 *            The col of the cell location
	 */
	private void zeroCell(final int row, final int col) {
		for (int r = (row - 1); r <= (row + 1); r++) {
			for (int c = (col + 1); c >= (col - 1); c--) {

				if (getCell(r, c) != null && !board[r][c].
						isExposed() && !board[r][c].
						isMine()) {
					board[r][c].setExposed(true);
					if (board[r][c].getMineCount() == 0) {
						zeroCell(r, c);
					}
				}
			}
		}
	}

	/**
	 * This helper method is used to check the current status of the game. 
	 * It checks to see if there are any non-mine cells that haven't been
	 * exposed. If there are remaining cells the game is not over.
	 * 
	 * @return boolean The current status of the game, true if the game is
	 * 				   won and false if the game is over.
	 */
	private boolean checkStatus() {
		boolean status = true;
		for (int row = 0; row < this.row; row++) {
			for (int col = 0; col < this.col; col++) {
				if (!board[row][col].isMine() 
						&& !board[row][col].
						isExposed()) {
					status = false;
				}
			}
		}
		return status;
	}

	/**
	 * This helper method finds the row and col of a mine and then calls
	 * addMine() to add the mine count of the neighboring cells.
	 * 
	 */
	private void mineCount() {
		for (int row = 0; row < this.row; row++) {
			for (int col = 0; col < this.col; col++) {
				if (board[row][col].isMine()) {
					addMine(row, col);
				}
			}
		}
	}

	/**
	 * This helper method adds one to the mine count of the eight
	 * neighboring cells.
	 * 
	 * @param row
	 *            The row of the cell location
	 * @param col
	 *            The col of the cell location
	 */
	private void addMine(final int row, final int col) {
		for (int r = -1; r <= 1; r++) {
			for (int c = 1; c >= -1; c--) {

				if (getCell(row + r, col + c) != null) {
					board[row + r][col + c].setMineCount(1);
				}
			}
		}
	}

	/**
	 * This method saves off the current status of the game to a file. The
	 * properties of the game that are stored is the size of the game board
	 * and the number of mines the game has. In addition to this, the game 
	 * saves for each cell: the row of the board, the col of the board, the 
	 * current mine count of that cell, whether of not the cell is a mine, 
	 * has been flagged, and lastly whether or not the cell has been 
	 * exposed.
	 * 
	 * @param filename
	 *            The name of the file that is saved and written to
	 * @throws I0Exception
	 *             Does not save the game if the name of the file is 0 in
	 *             length
	 * @throws InputMisMatchException
	 *             If the user tries to save a lost game
	 */
	public void save(final String filename) {
		try {
			if (filename.length() == 0) {
				throw new IOException();
			}

			if (getGameStatus() == GameStatus.Lost) {
				throw new InputMismatchException();
			}

			PrintWriter writer = new PrintWriter(filename);

			writer.print(getRow() + " ");
			writer.println(getNumMines());

			for (int row = 0; row < this.row; row++) {
				for (int col = 0; col < this.col; col++) {

					writer.print(row + " ");
					writer.print(col + " ");

					writer.print(board[row][col].
							getMineCount() + " ");

					if (board[row][col].isMine()) {
						writer.print("t ");
					} else {
						writer.print("f ");
					}

					if (board[row][col].isFlagged()) {
						writer.print("t ");
					} else {
						writer.print("f ");
					}

					if (board[row][col].isExposed()) {
						writer.println("t");
					} else {
						writer.println("f");
					}
				}
			}
			setLoadSuccessful(true);
			writer.close();
		} catch (InputMismatchException e) {
			JOptionPane.showMessageDialog(null, 
					"Error: Cannot save a lost game!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, 
					"Error: Unable to save game!");
		}
	}

	/**
	 * This method loads the saved game and recreates the board from the
	 * properties in the file.
	 * 
	 * @param filename
	 *            The name of the file that the properties are loaded from
	 * @throws FileNotFoundException
	 *             If the file could not be found and loaded
	 * @throws InputMismatchException
	 *             If the contents of the file has been altered and it
	 *             cannot parse correctly
	 * @throws NumberFormatException
	 *             If the contents of the file has been altered and it
	 *             cannot parse correctly
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the contents of the file has been altered and it
	 *             cannot parse correctly
	 */
	public void load(final String filename) {
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
				boolean isMine = (inputSplit[3].equals("t")) 
						? true : false;
				boolean isFlagged = (inputSplit[4].equals("t")) 
						? true : false;
				boolean isExposed = (inputSplit[5].equals("t")) 
						? true : false;
				setCell(row, col, mineCount, isMine, isFlagged, 
						isExposed);
			}
			setLoadSuccessful(true);
			fileReader.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, 
					"Error: Unable to load game!");
		} catch (InputMismatchException e) {
			JOptionPane.showMessageDialog(null, 
					"Error: Corrupt file!");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, 
					"Error: Corrupt file!");
		} catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, 
					"Error: Corrupt file!");
		}
	}

	/**
	 * This helper method sets a cell with the provided parameters to 
	 * recreate a cell that was saved off from a previous game.
	 * 
	 * @param row
	 *            The row of the cell location
	 * @param col
	 *            The col of the cell location
	 * @param mineCount
	 *            The mine count of that current cell
	 * @param isMine
	 *            Whether or not the cell is a mine
	 * @param isFlagged
	 *            Whether or not the cell has been flagged
	 * @param isExposed
	 *            Whether or not the cell has been exposed
	 */
	private void setCell(final int row, final int col, final int mineCount,
			final boolean isMine, final boolean isFlagged,
			final boolean isExposed) {
		board[row][col].setMineCount(mineCount);
		board[row][col].setMine(isMine);
		board[row][col].setFlagged(isFlagged);
		board[row][col].setExposed(isExposed);
	}

	/**
	 * This method just gets the size of the board from a file.
	 * 
	 * @param filename
	 *            The name of the file that the properties are loaded from
	 * @throws FileNotFoundException
	 *             If the file could not be found and loaded
	 * @throws NumberFormatException
	 *             If the contents of the file has been altered and it
	 *             cannot parse correctly
	 * @return int The size of the board
	 */
	public int getSize(final String filename) {
		int size = 0;
		try {
			if (filename.length() == 0) {
				throw new FileNotFoundException();
			}

			Scanner fileReader = new Scanner(new File(filename));
			String input = fileReader.nextLine();
			String[] inputSplit = input.split("\\s+");
			size = Integer.parseInt(inputSplit[0]);
			setLoadSuccessful(true);
			fileReader.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, 
					"Error: Unable to load game!");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, 
					"Error: Corrupt file!");
		}
		return size;
	}

	/**
	 * This setter method sets the status of whether or not the file has
	 * been loaded correctly.
	 * 
	 * @param loadSuccessful
	 *            Whether or not the file has been loaded correctly
	 */
	public void setLoadSuccessful(final boolean loadSuccessful) {
		this.loadSuccessful = loadSuccessful;
	}

	/**
	 * This getter method returns whether or not the file was properly
	 * loaded.
	 * 
	 * @return boolean If the file has been loaded correctly
	 */
	public boolean isloadSuccessful() {
		return loadSuccessful;
	}

	/**
	 * This helper method checks that the current cell exists. If the cell
	 * does not exist it returns null and if the cell does exist it returns
	 * that cell.
	 * 
	 * @param row
	 *            The row of the cell location
	 * @param col
	 *            The col of the cell location
	 * @return Cell This returns the cell, provided that it exists
	 */
	public Cell getCell(final int row, final int col) {
		return (row < 0 || col < 0 || row >= this.row
				|| col >= this.col) ? null : board[row][col];
	}

	/**
	 * Getter method that returns the row of the board.
	 * 
	 * @return int The size of the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Setter method that sets the row of the board.
	 * 
	 * @param row
	 *            The size of the row to create the board
	 */
	public void setRow(final int row) {
		this.row = row;
	}

	/**
	 * Getter method that returns the col of the board.
	 * 
	 * @return int The size of the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Setter method that sets the col of the board.
	 * 
	 * @param col
	 *            The size of the col to create the board
	 */
	public void setCol(final int col) {
		this.col = col;
	}

	/**
	 * Getter method that returns what the default size of the board is.
	 * In this case a Minesweeper board on beginner has the size of an
	 * (8x8) board.
	 * 
	 * @return int The size of the board
	 */
	public int getDEFAULT_SIZE() {
		return DEFAULT_SIZE;
	}

	/**
	 * Getter method that returns the current status of the game.
	 * 
	 * @return gameStatus The current status of the game
	 */
	public GameStatus getGameStatus() {
		return gameStatus;
	}

	/**
	 * Setter method that sets the current status of the game.
	 * 
	 * @param gameStatus
	 *            The current status of the game
	 */
	public void setGameStatus(final GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	/**
	 * Getter method that returns the number of mines that the game has.
	 * 
	 * @return int The number of mines the game has
	 */
	public int getNumMines() {
		return numMines;
	}

	/**
	 * Setter method that sets the number of mines that the game will have.
	 * 
	 * @param numMines
	 *            The number of mines that the game will have
	 */
	public void setNumMines(final int numMines) {
		this.numMines = numMines;
	}
}