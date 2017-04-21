package tictactoe;

/**
 * This class creates a fully functional Tic-Tac-Toe board and makes all
 * decisions regarding the state of the game.
 * 
 * @author Matthew Shampine, Connor Dudas
 * @version 1.0
 */
public class TicTacToeGame {
	
	// CHECKSTYLE:OFF
	// I disagreed with Checkstyles naming conventions for finals
	/** Default board size for a beginner (8x8). */
	private final int SIZE = 3;
	// CHECKSTYLE:ON
	
	/** 2D array of cells that resembles a Tic-Tac-Toe board. */
	private Cell[][] board;
	
	/** Holds the current state of the game. */
	private GameStatus gameStatus;
	
	/** Holds the current Player's turn. */
	private Player player;
	
	/**
	 * Constructor that initializes board, filling all of the cells with 
	 * default values. In addition, gameStatus is initialized to NotOverYet
	 * and the player is set to X.
	 */
	public TicTacToeGame() {
		setGameStatus(GameStatus.NotOverYet);
		setPlayer(Player.X);
		board = new Cell[SIZE][SIZE];
		initialize();
	}
	
	/**
	 * This method handles the logic when a user clicks on a cell. It first
	 * marks the cell, then checks whether or not the game is over.
	 * If the game is not yet over it will toggle the player.
	 * 
	 * @param row
	 *            The row of the cell location
	 * @param col
	 *            The col of the cell location
	 */
	public void selectCell(final int row, final int col) {
		board[row][col].setMarked(true);
		board[row][col].setPlayer(this.player);
		setGameStatus(checkStatus(row, col));
		togglePlayer(this.player);
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
		return (row < 0 || col < 0 || row >= SIZE
				|| col >= SIZE) ? null : board[row][col];
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
	 * Getter method that returns the current player's turn.
	 * 
	 * @return player The current player's turn.
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Setter method that sets the current status of the game.
	 * 
	 * @param gameStatus
	 *            The current status of the game
	 */
	private void setGameStatus(final GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	/**
	 * Setter method that sets the current player's turn.
	 * 
	 * @param player
	 *            The current player's turn
	 */
	private void setPlayer(final Player player) {
		this.player = player;
	}
	
	/**
	 * Setter method that sets the current player's turn.
	 * 
	 * @param player
	 *            The current player's turn
	 */
	private void togglePlayer(final Player player) {
		if(player == Player.X)
			this.player = Player.O;
		else
			this.player = Player.X;
	}
	
	/**
	 * This helper method is used to check the current status of the game. 
	 * It checks to see if the board is completely marked or if a Player has
	 * 3 marks in a row.
	 * 
	 * @param pRow
	 *            The row of the cell location the player selected
	 * @param col
	 *            The col of the cell location the player selected
	 *            
	 * @return Player The current status of the game.
	 */
	private GameStatus checkStatus(int pRow, int pCol) {	//FIXME
		GameStatus status = GameStatus.NotOverYet;
		int cellsMarked = 0;
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (board[row][col].isMarked()) {
					cellsMarked += 1;
				}
			}
		}
		if(cellsMarked == SIZE*SIZE){
			status = GameStatus.Tie;
		}
		if(checkVert(pRow, pCol) || checkHor(pRow, pCol) || checkBS(pRow, pCol) || checkFS(pRow, pCol))
			status = GameStatus.Won;
		return status;
	}
	
	/**
	 * This helper method is used to check if a selected cell creates
	 * SIZE marks in a row Horizontally.
	 * 
	 * @param row
	 *            The row of the cell location
	 * @param col
	 *            The col of the cell location
	 */
	private boolean checkHor(final int row, final int col) {
		int inARow = 1;
		int c = col;
		while(getCell(row, c+1) != null && board[row][c+1].getPlayer() == board[row][col].getPlayer()){
			++inARow;
			++c;
		}
		c = col;
		while(getCell(row, c-1) != null && board[row][c-1].getPlayer() == board[row][col].getPlayer()){
			++inARow;
			--c;
		}
		if(inARow == SIZE)
			return true;
		else
			return false;
	}
	
	/**
	 * This helper method is used to check if a selected cell creates
	 * SIZE marks in a row Vertically.
	 * 
	 * @param row
	 *            The row of the cell location
	 * @param col
	 *            The col of the cell location
	 */
	private boolean checkVert(final int row, final int col) {
		int inARow = 1;
		int r = row;
		while(getCell(r+1, col) != null && board[r+1][col].getPlayer() == board[row][col].getPlayer()){
			++inARow;
			++r;
		}
		r = row;
		while(getCell(r-1, col) !=null && board[r-1][col].getPlayer() == board[row][col].getPlayer()){
			++inARow;
			--r;
		}
		if(inARow == SIZE)
			return true;
		else
			return false;
	}
	
	/**
	 * This helper method is used to check if a selected cell creates
	 * SIZE marks in a row as a forward slash.
	 * 
	 * @param row
	 *            The row of the cell location
	 * @param col
	 *            The col of the cell location
	 */
	private boolean checkFS(final int row, final int col) {
		int inARow = 1;
		int r = row;
		int c = col;
		while(getCell(r+1, c+1) != null && board[r+1][c+1].getPlayer() == board[row][col].getPlayer()){
			++inARow;
			++r;
			++c;
		}
		r = row;
		c = col;
		while(getCell(r-1, c-1) != null && board[r-1][c-1].getPlayer() == board[row][col].getPlayer()){
			++inARow;
			--r;
			--c;
		}
		if(inARow == SIZE)
			return true;
		else
			return false;
	}
	
	/**
	 * This helper method is used to check if a selected cell creates
	 * SIZE marks in a row as a backwards slash.
	 * 
	 * @param row
	 *            The row of the cell location
	 * @param col
	 *            The col of the cell location
	 */
	private boolean checkBS(final int row, final int col) {
		int inARow = 1;
		int r = row;
		int c = col;
		while(getCell(r+1, c-1) != null && board[r+1][c-1].getPlayer() == board[row][col].getPlayer()){
			++inARow;
			++r;
			--c;
		}
		r = row;
		c = col;
		while(getCell(r-1, c+1) != null && board[r-1][c+1].getPlayer() == board[row][col].getPlayer()){
			++inARow;
			--r;
			++c;
		}
		if(inARow == SIZE)
			return true;
		else
			return false;
	}
	
	/**
	 * This method instantiates a new default cell for each location on the 
	 * 2D array.
	 * 
	 */
	private void initialize() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				board[row][col] = new Cell();
			}
		}
	}
}
