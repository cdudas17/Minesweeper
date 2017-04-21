package tictactoe;
// CHECKSTYLE:OFF
import static org.junit.Assert.*;

import org.junit.Test;

public class TicTacToeTest {

	private TicTacToeGame test;
	
	//Check to see the constructor works and the board is initialized
	@Test
	public void test1() {
		test = new TicTacToeGame();
		assertEquals(test.getGameStatus(), GameStatus.NotOverYet);
		boolean verifyCells = true;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (test.getCell(row, col) == null) {
					verifyCells = false;
				}
			}
		}
		assertTrue(verifyCells);
	}

	//Check to see that X can win the game
	@Test
	public void test2() {
		test = new TicTacToeGame();
		test.selectCell(1, 1);
		test.selectCell(0, 0);
		test.selectCell(1, 2);
		test.selectCell(0, 2);
		test.selectCell(1, 0);
		
		assertEquals(test.getGameStatus(), GameStatus.Won);
	}
	
	//Check to see that O can win the game
	@Test
	public void test3() {
		test = new TicTacToeGame();
		test.selectCell(1, 1);
		test.selectCell(0, 0);
		test.selectCell(1, 2);
		test.selectCell(0, 2);
		test.selectCell(2, 1);
		test.selectCell(0, 1);
		
		assertEquals(test.getGameStatus(), GameStatus.Won);
	}
	
	//Check to see that diaginol can win
	@Test
	public void test4() {
		test = new TicTacToeGame();
		test.selectCell(1, 1);
		test.selectCell(1, 0);
		test.selectCell(0, 0);
		test.selectCell(2, 1);
		test.selectCell(2, 2);
		
		assertEquals(test.getGameStatus(), GameStatus.Won);
	}
	
	//Check to see that other diaginol can win
	@Test
	public void test5() {
		test = new TicTacToeGame();
		test.selectCell(1, 1);
		test.selectCell(1, 0);
		test.selectCell(0, 2);
		test.selectCell(2, 1);
		test.selectCell(2, 0);
		
		assertEquals(test.getGameStatus(), GameStatus.Won);
	}
	
	//Check to see that vertical can win
	@Test
	public void test6() {
		test = new TicTacToeGame();
		test.selectCell(1, 1);
		test.selectCell(1, 0);
		test.selectCell(0, 1);
		test.selectCell(0, 0);
		test.selectCell(2, 1);
			
		assertEquals(test.getGameStatus(), GameStatus.Won);
	}
	
	//Check to see that the game can tie
	@Test
	public void test7() {
		test = new TicTacToeGame();
		test.selectCell(1, 1);
		test.selectCell(1, 0);
		test.selectCell(2, 0);
		test.selectCell(0, 2);
		test.selectCell(2, 2);
		test.selectCell(0, 0);
		test.selectCell(0, 1);
		test.selectCell(2, 1);
		test.selectCell(1, 2);
		assertEquals(test.getGameStatus(), GameStatus.Tie);
	}
}
