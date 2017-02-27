//import javax.swing.JOptionPane;

public class MineSweeperGame {

	private Cell[][] board;

	private GameStatus gameStatus;

	private int row;

	private int col;

	private final int DEFAULT_SIZE = 8;

	private final int DEFAULT_MINES = 10;

	public MineSweeperGame() {
		gameStatus = GameStatus.NotOverYet;
		createBoardDimensions(getDEFAULT_SIZE());
		board = new Cell[row][col];
		initialize();
		setMines(DEFAULT_MINES);
	}

	public MineSweeperGame(int size, int numMines) {
		gameStatus = GameStatus.NotOverYet;
		createBoardDimensions(size);
		board = new Cell[row][col];
		initialize();
		setMines(numMines);
	}

	private void createBoardDimensions(int size) {
		try {
			if (size < 2 || size > 24)
				throw new NumberFormatException();
			else {
				row = size;
				col = size;
			}
		} catch (NumberFormatException e) {
			// JOptionPane.showMessageDialog(null, "Error: Using default board
			// size! ");
			row = DEFAULT_SIZE;
			col = DEFAULT_SIZE;
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
}
