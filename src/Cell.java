
public class Cell {

	/** non-negative integer that counts the number of mines that cell is next to */
	private int mineCount;
	
	/** boolean to mark whether or not the cell is a mine */
	private boolean isMine;
	
	/** boolean to mark whether or not the cell has been flagged by the user */
	private boolean isFlagged;
	
	/** boolean to mark whether or not the user has clicked on the cell */
	private boolean isExposed;
	
	public Cell () {
		mineCount = 0;
		isMine = false;
		isFlagged  = false;
		isExposed = false;
	}

    public Cell(boolean exposed, boolean mine) {
        isExposed = exposed;
        isMine = mine;
    }
	
	public int getMineCount() {
		return mineCount;
	}

	public void setMineCount(int mineCount) {
		this.mineCount += mineCount;
	}

	public boolean isMine() {
		return isMine;
	}

	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}

	public boolean isFlagged() {
		return isFlagged;
	}

	public void setFlagged(boolean isFlagged) {
		this.isFlagged = isFlagged;
	}

	public boolean isExposed() {
		return isExposed;
	}

	public void setExposed(boolean isExposed) {
		this.isExposed = isExposed;
	}
}
