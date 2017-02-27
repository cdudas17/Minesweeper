
public class MineSweeperGame {

	private Cell[][] board;
	
	private GameStatus gameStatus; 
	
	private int row;
	
	private int col;
	
	public MineSweeperGame() {
//		gameStatus = GameStatus.NotOverYet;
		board = new Cell[row][col];
	}
	
	public void createBoardDimensions(int size) {
		try {
			if (size < 2 || size > 24)
				throw new NumberFormatException();
		} catch (NumberFormatException e) {
			
		}
		
	}
}
