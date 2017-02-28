import java.util.Random;

public class MineSweeperGame {

	/** 2D array of cells that resembles a MineSweeper board */
	private Cell[][] board;

	/** holds the current status of the game */
	private GameStatus gameStatus;

	/** board row */
	private int row;

	/** board column */
	private int col;

	private int numMines;

	// TO-DO: maybe do not need this int, and method
	private final int DEFAULT_SIZE = 8;

	private final int DEFAULT_MINES = 10;

	public MineSweeperGame(int size, int numMines) {
		setGameStatus(GameStatus.NotOverYet);
		createBoardDimensions(size);
		setNumMines(numMines);
		board = new Cell[row][col];
		initialize();
		setMines(numMines);
		mineCount();
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
		board[row][col].setExposed(true); // selects the cell the user clicked on

		if (checkStatus())
			setGameStatus(GameStatus.Won);
		else
			setGameStatus(GameStatus.NotOverYet);
		
		if (board[row][col].isMine()) {
			showMines(); // shows all of the mines when the user clicks on a mine
			setGameStatus(GameStatus.Lost);
		}

		if (board[row][col].getMineCount() == 0)
			zeroCell(row, col);
	}

	private void showMines() {
		for (int row = 0; row < this.row; row++) {
			for (int col = 0; col < this.col; col++) {
				if (board[row][col].isMine() && !board[row][col].isExposed())
					board[row][col].setExposed(true); // TO-DO: needs to check if a flag is on a non-mine
			}
		}
	}
	
	// TO-DO: finish when the user clicks on a zero spot
	private void zeroCell(int row, int col) {
		for (int r = (row-1); r <= (row+1); r++) {
			for (int c = (col-1); c <= (col+1); c++) {
				
				if (board[row + r][col + c].getMineCount() == 0 && !board[row + r][col + c].isExposed()) {
					board[row + r][col + c].setExposed(true);
					zeroCell(row + r, col + c);
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

	public int getNumMines() {
		return numMines;
	}

	public void setNumMines(int numMines) {
		this.numMines = numMines;
	}
}
