package minesweeper;

/**
 * This enum is used to easily track the status of the game.
 * 
 * @author Matthew Shampine, Connor Dudas
 * @version 1.0
 */
public enum GameStatus {
	
	/**
	 * The current status if the user lost.
	 * 
	 */
	Lost, 
	
	/**
	 * The current status if the user won.
	 */
	Won,
	
	/**
	 * The current status if the game is not over yet.
	 * 
	 */
	NotOverYet
}
