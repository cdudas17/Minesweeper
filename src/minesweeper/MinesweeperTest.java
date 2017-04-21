package minesweeper;
// CHECKSTYLE:OFF
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class MinesweeperTest {

	private MineSweeperGame test;

	// checks to see if the constructor initializes the correct properties of
	// each cell
	@Test
	public void test() {
		test = new MineSweeperGame(8, 10);
		assertEquals(test.getGameStatus(), GameStatus.NotOverYet);
		assertEquals(test.getRow(), 8);
		assertEquals(test.getNumMines(), 10);
		boolean verifyCells = true;
		for (int row = 0; row < test.getRow(); row++) {
			for (int col = 0; col < test.getCol(); col++) {
				if (test.getCell(row, col) == null)
					verifyCells = false;
			}
		}
		assertEquals(verifyCells, true);
	}

	// checks to see if the game can be won on an (8x8) board
	@Test
	public void test2() {
		test = new MineSweeperGame(8, 10);
		for (int row = 0; row < test.getRow(); row++) {
			for (int col = 0; col < test.getCol(); col++) {
				if (!test.getCell(row, col).isMine())
					test.selectCell(row, col);
			}
		}
		assertEquals(test.getGameStatus(), GameStatus.Won);
	}

	// checks to see if the game can be won on an (8x8) board
	@Test
	public void test3() {
		test = new MineSweeperGame(16, 40);
		for (int row = 0; row < test.getRow(); row++) {
			for (int col = 0; col < test.getCol(); col++) {
				if (!test.getCell(row, col).isMine())
					test.selectCell(row, col);
			}
		}
		assertEquals(test.getGameStatus(), GameStatus.Won);
	}

	// checks to see if the game can be won on an (24x24) board
	@Test
	public void test4() {
		test = new MineSweeperGame(24, 99);
		for (int row = 0; row < test.getRow(); row++) {
			for (int col = 0; col < test.getCol(); col++) {
				if (!test.getCell(row, col).isMine())
					test.selectCell(row, col);
			}
		}
		assertEquals(test.getGameStatus(), GameStatus.Won);
	}

	// checks to see if the game can be lost on an (8x8) board
	@Test
	public void test5() {
		test = new MineSweeperGame(8, 10);
		for (int row = 0; row < test.getRow(); row++) {
			for (int col = 0; col < test.getCol(); col++) {
				if (test.getCell(row, col).isMine()) {
					test.selectCell(row, col);
					assertEquals(test.getGameStatus(), GameStatus.Lost);
					return;
				}
			}
		}
	}

	// checks to see if the game can be lost on an (16x16) board
	@Test
	public void test6() {
		test = new MineSweeperGame(16, 40);
		for (int row = 0; row < test.getRow(); row++) {
			for (int col = 0; col < test.getCol(); col++) {
				if (test.getCell(row, col).isMine()) {
					test.selectCell(row, col);
					assertEquals(test.getGameStatus(), GameStatus.Lost);
					return;
				}
			}
		}
	}

	// checks to see if the game can be lost on an (24x24) board
	@Test
	public void test7() {
		test = new MineSweeperGame(24, 99);
		for (int row = 0; row < test.getRow(); row++) {
			for (int col = 0; col < test.getCol(); col++) {
				if (test.getCell(row, col).isMine()) {
					test.selectCell(row, col);
					assertEquals(test.getGameStatus(), GameStatus.Lost);
					return;
				}
			}
		}
	}

	// checks to see the status of the game is not over yet after
	// revealing 5 cells and flagging 5 cells of an (8x8) board
	@Test
	public void test8() {
		test = new MineSweeperGame(8, 10);

		int tries = 0;
		Random randomSpot = new Random();
		while (tries < 5) {
			int row = randomSpot.nextInt(test.getRow());
			int col = randomSpot.nextInt(test.getCol());
			int rFlag = randomSpot.nextInt(test.getRow());
			int cFlag = randomSpot.nextInt(test.getCol());

			Cell testCell = test.getCell(row, col);
			Cell flagCell = test.getCell(rFlag, cFlag);

			if (!testCell.isMine() && !testCell.isExposed() && !testCell.isFlagged()) {
				test.selectCell(row, col);
				if (!flagCell.isExposed() && !flagCell.isFlagged())
					flagCell.setFlagged(true);
				tries++;
			}
		}
		assertEquals(test.getGameStatus(), GameStatus.NotOverYet);
	}

	// checks to see the status of the game is not over yet after
	// revealing 10 cells and flagging 10 cells of an (16x16) board
	@Test
	public void test9() {
		test = new MineSweeperGame(16, 40);

		int tries = 0;
		Random randomSpot = new Random();
		while (tries < 10) {
			int row = randomSpot.nextInt(test.getRow());
			int col = randomSpot.nextInt(test.getCol());
			int rFlag = randomSpot.nextInt(test.getRow());
			int cFlag = randomSpot.nextInt(test.getCol());

			Cell testCell = test.getCell(row, col);
			Cell flagCell = test.getCell(rFlag, cFlag);

			if (!testCell.isMine() && !testCell.isExposed() && !testCell.isFlagged()) {
				test.selectCell(row, col);
				if (!flagCell.isExposed() && !flagCell.isFlagged())
					flagCell.setFlagged(true);
				tries++;
			}
		}
		assertEquals(test.getGameStatus(), GameStatus.NotOverYet);
	}

	// checks to see the status of the game is not over yet after
	// revealing 30 cells and flagging 30 cells of an (24x24) board
	@Test
	public void test10() {
		test = new MineSweeperGame(24, 99);

		int tries = 0;
		Random randomSpot = new Random();
		while (tries < 30) {
			int row = randomSpot.nextInt(test.getRow());
			int col = randomSpot.nextInt(test.getCol());
			int rFlag = randomSpot.nextInt(test.getRow());
			int cFlag = randomSpot.nextInt(test.getCol());

			Cell testCell = test.getCell(row, col);
			Cell flagCell = test.getCell(rFlag, cFlag);

			if (!testCell.isMine() && !testCell.isExposed() && !testCell.isFlagged()) {
				test.selectCell(row, col);
				if (!flagCell.isExposed() && !flagCell.isFlagged())
					flagCell.setFlagged(true);
				tries++;
			}
		}
		assertEquals(test.getGameStatus(), GameStatus.NotOverYet);
	}

	// the board size is bigger than 24 so a default (8x8) board size is enabled
	@Test
	public void test11() {
		test = new MineSweeperGame(25, 99);
		assertEquals(test.getRow(), 8);
		assertEquals(test.getCol(), 8);
		assertEquals(test.getNumMines(), 10);
	}

	// the board size is smaller than 3 so a default (8x8) board size is enabled
	@Test
	public void test12() {
		test = new MineSweeperGame(2, 5);
		assertEquals(test.getRow(), 8);
		assertEquals(test.getCol(), 8);
		assertEquals(test.getNumMines(), 10);
	}

	// Testing the save feature
	@Test
	public void test13() {
		test = new MineSweeperGame(8, 10);
		int tries = 0;
		Random randomSpot = new Random();
		while (tries < 10) {
			int row = randomSpot.nextInt(test.getRow());
			int col = randomSpot.nextInt(test.getCol());
			int rFlag = randomSpot.nextInt(test.getRow());
			int cFlag = randomSpot.nextInt(test.getCol());

			Cell testCell = test.getCell(row, col);
			Cell flagCell = test.getCell(rFlag, cFlag);

			if (!testCell.isMine() && !testCell.isExposed() && !testCell.isFlagged()) {
				test.selectCell(row, col);
				if (!flagCell.isExposed() && !flagCell.isFlagged())
					flagCell.setFlagged(true);
				tries++;
			}
		}
		test.save("saveTest");
	}

	// Testing the save feature and trying to save a file with no name
	@Test
	public void test14() {
		test = new MineSweeperGame(8, 10);
		int tries = 0;
		Random randomSpot = new Random();
		while (tries < 5) {
			int row = randomSpot.nextInt(test.getRow());
			int col = randomSpot.nextInt(test.getCol());

			Cell testCell = test.getCell(row, col);

			if (!testCell.isMine() && !testCell.isExposed() && !testCell.isFlagged()) {
				test.selectCell(row, col);
				tries++;
			}
		}
		test.save("");
	}

	// Testing the save feature and trying to save an already lost game
	@Test
	public void test15() {
		test = new MineSweeperGame(8, 10);
		int lastMine = 0;
		for (int row = 0; row < test.getRow(); row++) {
			for (int col = 0; col < test.getCol(); col++) {
				if (test.getCell(row, col).isMine()) {
					lastMine++;
					if (lastMine == 10)
						test.selectCell(row, col);
				}
			}
		}
		test.save("saveTest2");
	}
	
	// trying to load a saved game
	@Test
	public void test16() {
		test = new MineSweeperGame(24, 99);
		int size = test.getSize("saveTest");
		test = new MineSweeperGame(size);
		test.load("saveTest");
		assertEquals(test.isloadSuccessful(), true);
		assertEquals(test.getRow(), 8);
		assertEquals(test.getNumMines(), 10);
	}
	
	// trying to load a game with a corrupt file format
	@Test
	public void test17() {
		test = new MineSweeperGame(24, 99);
		int size = test.getSize("corrupt2");
		test = new MineSweeperGame(size);
	}
	
	// trying to load a game with a corrupt file format
	@Test
	public void test18() {
		test = new MineSweeperGame(24, 99);
		int size = test.getSize("FileDoesntExist");
		test = new MineSweeperGame(size);
		assertEquals(test.isloadSuccessful(), false);
	}
	
	// trying to load a corrupted saved game
	@Test
	public void test19() {
		test = new MineSweeperGame(10, 8);
		int size = test.getSize("corrupt1");
		test = new MineSweeperGame(size);
		test.load("corrupt1");
		assertEquals(test.isloadSuccessful(), false);
		assertEquals(test.getRow(), 8);
		assertEquals(test.getNumMines(), 10);
	}
	
	// trying to load a corrupted saved game
	@Test
	public void test20() {
		test = new MineSweeperGame(16, 40);
		int size = test.getSize("corrupt3");
		test = new MineSweeperGame(size);
		test.load("corrupt3");
		assertEquals(test.isloadSuccessful(), false);
		assertEquals(test.getRow(), 8);
		assertEquals(test.getNumMines(), 10);
	}
}
