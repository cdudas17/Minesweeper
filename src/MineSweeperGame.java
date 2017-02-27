//import javax.swing.JOptionPane;

public class MineSweeperGame {

	private Cell[][] board;

	private GameStatus gameStatus;

	private int row;

	private int col;
	
	private final int DEFAULT_SIZE = 8;
	
	private final int DEFAULT_MINE = 10;

	public MineSweeperGame() {
		gameStatus = GameStatus.NotOverYet;
		setBoardDimensions(DEFAULT_SIZE);
		board = new Cell[row][col];
	}

	public MineSweeperGame(int size, int numMines) {
		gameStatus = GameStatus.NotOverYet;
		setBoardDimensions(size);
		board = new Cell[row][col];
	}

	private void setBoardDimensions(int size) {
		try {
			if (size < 10 || size > 24)
				throw new NumberFormatException();
			else {
				row = size;
				col = size;
			}
		} catch (NumberFormatException e) {
			// JOptionPane.showMessageDialog(null, "Error: Using default board
			// size!");
			row = DEFAULT_SIZE;
			col = DEFAULT_SIZE;
		}
	}
	
 	public void loadMines() {
 		
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
