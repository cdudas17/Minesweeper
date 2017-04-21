package tictactoe;

/**
 * This class holds the properties of an actual tic-tac-toe cell. These
 * properties include the player that selected the cell
 * or if the cell is still valid to be used
 * 
 * 
 * @author Matthew Shampine, Connor Dudas
 * @version 1.0
 */
public class Cell {


	/** Boolean to mark whether or not the cell is marked. */
	private boolean isMarked;

	/** Use enum class o determine f the cell is X, O or unused*/
	private Player markedBy;

	/**
	 * Constructor that instantiates a new cell and sets up the cell with
	 * default properties.
	 * 
	 */
	public Cell() {
		isMarked = false;
		markedBy = Player.NA;
	}

	/**
	 * Getter method that returns whether or not the cell is marked.
	 * 
	 * @return boolean Whether or not this cell is marked
	 */
	public boolean isMarked() {
		return isMarked;
	}

	/**
	 * Setter method that sets whether or not the cell is marked
	 * 
	 * @param isMarked
	 *            Whether or not the cell is going to be marked.
	 */
	public void setMarked(final boolean isMarked) {
		this.isMarked = isMarked;
	}
	
	/**
	 * Getter method that returns whether or not the cell is marked.
	 * 
	 * @return boolean Whether or not this cell is marked
	 */
	public Player getPlayer() {
		return markedBy;
	}

	/**
	 * Setter method that sets what player owns a cell.
	 * 
	 * @param markedBy
	 *            Who is going to mark the cell.
	 */
	public void setPlayer(final Player player) {
		this.markedBy = player;
	}
}