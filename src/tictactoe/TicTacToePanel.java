package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.SwingUtilities;

/**
 * This class lays out all of the buttons for TicTacToe and displays them. It
 * also communicates with TicTacToeGame to retrieve the board and select
 * cells.
 * 
 * @author Connor Dudas, Matthew Shampine
 * @version 1.0
 *
 */
public class TicTacToePanel extends JPanel implements ActionListener{

	/** This is used to serialize the TicTacToePanel class. */
	private static final long serialVersionUID = 7343878172857960522L;
	
	// CHECKSTYLE:OFF
	// I disagreed with Checkstyles naming conventions for finals
	/** Default board size for a beginner (8x8). */
	private final int SIZE = 3;
	// CHECKSTYLE:ON
	
	/** 2D array of buttons to create a TicTacToe board. */
	private JButton[][] board;
	
	/** Creates a TicTacToe game to interact with. */
	private TicTacToeGame game;
	
	/** A Cell in order to grab cells from game. */
	private Cell iCell;
	
	/** JPanel to place board in. */
	private JPanel center;
	
	/**
	 * Constructor that initializes the game and adds a
	 * board of buttons that creates a TicTacToe board and displays it.
	 */
	public TicTacToePanel() {
		game = new TicTacToeGame();
		board = new JButton[SIZE][SIZE];
		setLayout(new BorderLayout());

		center = new JPanel();
		center.setLayout(new GridLayout(SIZE, SIZE));
		add(center, BorderLayout.CENTER);

		createButtons();
		displayBoard();
	}
	
	/**
	 * Marks cells based on button pressed.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed
	 * (java.awt.event.ActionEvent)
	 * @param event
	 *            When the user clicks on a cell it triggers an event
	 */
	public void actionPerformed(final ActionEvent event) {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (event.getSource() == board[row][col]) {
					// iCell = game.getCell(row, col);
					// iCell.setExposed(true);
					game.selectCell(row, col);
				}
			}
		}

		displayBoard();

		if (game.getGameStatus() == GameStatus.Tie) {
			disableButtons();
			JOptionPane.showMessageDialog(null, "The game was a tie");
		}
		if (game.getGameStatus() == GameStatus.Won) {
			disableButtons();
			if(game.getPlayer()== Player.X)
				JOptionPane.showMessageDialog(null, "Player O won the game");
			else
				JOptionPane.showMessageDialog(null, "Player X won the game");
		}
	}
	
	/**
	 * This lays out the buttons for MineSweeper in rows and columns. It 
	 * sets the preferred size and color and also adds action listeners
	 * to each button.
	 * 
	 */
	private void createButtons() {
		for (int row = 0; row < SIZE; row++) { // ADD BUTTONS
			for (int col = 0; col < SIZE; col++) {
				board[row][col] = new JButton("");
				board[row][col].setPreferredSize(
						new Dimension(45, 45));
				board[row][col].setBackground(Color.LIGHT_GRAY);
				board[row][col].addActionListener(this);
				center.add(board[row][col]);
			}
		}
	}
	
	/**
	 * This displays the board. It sets unmarked cell's buttons empty
	 * and will set marked cells to the corresponding Player that marked
	 * the cell.
	 */
	private void displayBoard() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				iCell = game.getCell(row, col);
				//board[row][col].setText("");

				// checks for exposed cells
				if (iCell.isMarked()) {
					board[row][col].setEnabled(false);
					board[row][col].setBackground(
							Color.WHITE);
					if (iCell.getPlayer() == Player.X) {
						board[row][col].setText("X");
					} else if (iCell.getPlayer() == Player.O) {
						board[row][col].setText("O");
					} else {
						board[row][col].setText("Error");	
					}
				} else {
					board[row][col].setEnabled(true);
					board[row][col].setBackground(
							Color.LIGHT_GRAY);
				}
			}
		}
	}
	
	/**
	 * Disables all button on the TicTacToe board.
	 */
	private void disableButtons() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				board[row][col].setEnabled(false);
			}
		}
	}
}
