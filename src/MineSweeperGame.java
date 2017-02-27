import javax.swing.JOptionPane;

public class MineSweeperGame {

	private Cell[][] board;

	private GameStatus gameStatus;

	private int row;

	private int col;

	private final int DEFAULT_SIZE = 8;

	public MineSweeperGame(int size) {
		gameStatus = GameStatus.NotOverYet;
		board = new Cell[row][col];
		createBoardDimensions(size);
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
			JOptionPane.showMessageDialog(null, "Error: Using default board size! ");
			row = DEFAULT_SIZE;
			col = DEFAULT_SIZE;
		}
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
