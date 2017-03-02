package minesweeper;

/**
 * This class holds the properties of an actual Minesweeper cell. These
 * properties include the mine count, whether or not it's a mine, whether or not
 * the cell has been flagged, and lastly whether or not the cell has been
 * clicked on.
 * 
 * 
 * @author Matthew Shampine, Connor Dudas
 * @version 1.0
 */
public class Cell {

	/**
	 * Non-negative integer that counts the number of mines that cell is
	 * next to.
	 */
	private int mineCount;

	/** Boolean to mark whether or not the cell is a mine. */
	private boolean isMine;

	/**
	 * Boolean to mark whether or not the cell has been flagged by the user.
	 */
	private boolean isFlagged;

	/** Boolean to mark whether or not the user has clicked on the cell. */
	private boolean isExposed;

	/**
	 * Constructor that instantiates a new cell and sets up the cell with
	 * default properties.
	 * 
	 */
	public Cell() {
		mineCount = 0;
		isMine = false;
		isFlagged = false;
		isExposed = false;
	}

	/**
	 * Getter method that returns the mine count of the cell.
	 * 
	 * @return int The count of how many mines are neighboring that 
	 * particular cell
	 */
	public int getMineCount() {
		return mineCount;
	}

	/**
	 * Setter method that adds one to the mine count.
	 * 
	 * @param mineCount
	 *            The current mine count of that cell
	 */
	public void setMineCount(final int mineCount) {
		this.mineCount += mineCount;
	}

	/**
	 * Getter method that returns whether or not the cell is a mine.
	 * 
	 * @return boolean Whether or not this cell is a mine
	 */
	public boolean isMine() {
		return isMine;
	}

	/**
	 * Setter method that sets whether or not the cell is a mine.
	 * 
	 * @param isMine
	 *            Whether or not the cell is going to contain a mine
	 */
	public void setMine(final boolean isMine) {
		this.isMine = isMine;
	}

	/**
	 * Getter method that returns if that cell has been flagged or not.
	 * 
	 * @return boolean Whether or not the cell has been flagged by the user
	 */
	public boolean isFlagged() {
		return isFlagged;
	}

	/**
	 * Setter method that sets whether or not the cell has been flagged by
	 * the user.
	 * 
	 * @param isFlagged
	 *            Whether or not the cell is flagged
	 */
	public void setFlagged(final boolean isFlagged) {
		this.isFlagged = isFlagged;
	}

	/**
	 * Getter method that returns if that cell has been exposed or not.
	 * 
	 * @return boolean If the cell has been exposed or not.
	 */
	public boolean isExposed() {
		return isExposed;
	}

	/**
	 * Setter method that sets whether or not the user has clicked on the
	 * cell.
	 * 
	 * @param isExposed
	 *            Whether or not the cell has been exposed.
	 */
	public void setExposed(final boolean isExposed) {
		this.isExposed = isExposed;
	}
}
