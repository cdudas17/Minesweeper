package minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This class lays out all of the buttons for Minesweeper and displays them. It
 * also communicates with MineSweeperGame to retrieve the board and select
 * cells.
 * 
 * @author Connor Dudas, Matthew Shampine
 * @version 1.0
 *
 */
public class MineSweeperPanel extends JPanel implements ActionListener,
MouseListener {

	/** This is used to serialize the MineSweeperPanel class. */
	private static final long serialVersionUID = 2279352759025366829L;

	/** 2D array of buttons to create a MineSweeper board. */
	private JButton[][] board;

	/** Creates a Minesweeper game to interact with. */
	private MineSweeperGame game;

	/** A Cell in order to grab cells from game. */
	private Cell iCell;

	/** JPanel to place board in. */
	private JPanel center;

	/** Keep track of the length of the board. */
	private int length;

	/** Icon for mines. */
	private ImageIcon mineIcon = new ImageIcon("src/mine.png");

	/** Icon for flags. */
	private ImageIcon flagIcon = new ImageIcon("src/flag.png");

	/**
	 * Constructor that initializes the game, sets a length, and adds a
	 * board of buttons that creates a MineSweeper board and displays it.
	 * 
	 * @param pLength
	 *            The desired length of the side of a board
	 * @param mineNum
	 *            The desired number of mines on a board
	 */
	public MineSweeperPanel(final int pLength, final int mineNum) {
		game = new MineSweeperGame(pLength, mineNum);
		setLength(pLength);
		board = new JButton[length][length];
		setLayout(new BorderLayout());

		center = new JPanel();
		center.setLayout(new GridLayout(length, length));
		add(center, BorderLayout.CENTER);

		createButtons();
		displayBoard();
	}

	/**
	 * Constructor that initializes the game, sets a length, and adds a
	 * board of buttons that creates a MineSweeper board and displays it
	 * from a saved file.
	 * 
	 * @param size
	 *            The desired length of a board
	 * @param filename
	 *            The file the MineSweeper game is to be loaded from
	 */
	public MineSweeperPanel(final int size, final String filename) {
		game = new MineSweeperGame(size);
		game.load(filename);
		setLength(size);
		board = new JButton[size][size];
		setLayout(new BorderLayout());

		center = new JPanel();
		center.setLayout(new GridLayout(size, size));
		add(center, BorderLayout.CENTER);

		createButtons();
		displayBoard();
	}

	/**
	 * This lays out the buttons for MineSweeper in rows and columns. It 
	 * sets the preferred size and color and also adds action listeners
	 * to each button.
	 * 
	 */
	private void createButtons() {
		for (int row = 0; row < length; row++) { // ADD BUTTONS
			for (int col = 0; col < length; col++) {
				board[row][col] = new JButton("");
				board[row][col].setPreferredSize(
						new Dimension(45, 45));
				board[row][col].setBackground(Color.LIGHT_GRAY);
				board[row][col].addActionListener(this);
				board[row][col].addMouseListener(this);
				center.add(board[row][col]);
			}
		}
	}

	/**
	 * This displays the board. It sets mines to mine icons, flags to flag
	 * icons, and number to the mineCount of the cell. If the cells 
	 * mineCount is simply zero, it creates an empty button.
	 * 
	 */
	private void displayBoard() {
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				iCell = game.getCell(row, col);
				board[row][col].setIcon(null);
				board[row][col].setText("");

				if (iCell.isFlagged()) { // Adds icon to flags
					board[row][col].setIcon(flagIcon);
					// board[row][col].setEnabled(false);
					// This dims the imageIcon of the flag
				}

				// checks for exposed cells
				if (iCell.isExposed()) {
					board[row][col].setEnabled(false);
					board[row][col].setBackground(
							Color.WHITE);
					if (iCell.isMine()) {
						board[row][col].setIcon(
								mineIcon);
					} else if (iCell.getMineCount() == 0) {
						board[row][col].setText("");
					} else {
						board[row][col].setText(""
					+ iCell.getMineCount());	
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
	 * Disables all button on the MineSweeper board.
	 * 
	 */
	private void disableButtons() {
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				board[row][col].setEnabled(false);
			}
		}
	}

	/**
	 * Sets the length.
	 * 
	 * @param length
	 *            The desired length of the board
	 */
	public void setLength(final int length) {
		this.length = length;
	}

	/**
	 * Exposes cells based on button pressed.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed
	 * (java.awt.event.ActionEvent)
	 * @param event
	 *            When the user clicks on a cell it triggers an event
	 */
	public void actionPerformed(final ActionEvent event) {
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				if (event.getSource() == board[row][col]) {
					// iCell = game.getCell(row, col);
					// iCell.setExposed(true);
					game.selectCell(row, col);
				}
			}
		}

		displayBoard();

		if (game.getGameStatus() == GameStatus.Lost) {
			disableButtons();
			JOptionPane.showMessageDialog(null, "You Lost");
		}
		if (game.getGameStatus() == GameStatus.Won) {
			disableButtons();
			JOptionPane.showMessageDialog(null, "You Won");
		}
	}

	/**
	 * Returns a boolean value if a game load is successful.
	 * 
	 * @return boolean The success of the game loading
	 */
	public boolean isSuccess() {
		return game.isloadSuccessful();
	}

	/**
	 * Saves the current MineSweeper game.
	 * 
	 * @param filename
	 *            The name of what to save the game as
	 */
	public void save(final String filename) {
		game.save(filename);
	}

	/**
	 * Returns the board size of a specific file.
	 * 
	 * @param filename
	 *            The name of the file to get the board size from
	 * @return int The length of the board from the file
	 */
	public int boardSize(final String filename) {
		return game.getSize(filename);
	}

	/**
	 * A mouse listener to keep track of when a button is right clicked so
	 * it will be toggled for isFlag. This also displays the board after 
	 * the flag has been toggled.
	 * 
	 * @param e
	 *            Event when the mouse is clicked
	 */
	public void mouseClicked(final MouseEvent e) {
	}

	/**
	 * This method is not used in our class.
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered
	 *      (java.awt.event.MouseEvent)
	 * @param e
	 *            Event when the mouse is entered
	 */
	public void mouseEntered(final MouseEvent e) {
	}

	/**
	 * This method is not used in our class.
	 * 
	 * @see java.awt.event.MouseListener#mouseExited 
	 * (java.awt.event.MouseEvent)
	 * @param e
	 *            Event when the mouse exits
	 */
	public void mouseExited(final MouseEvent e) {
	}

	/**
	 * This method is not used in our class.
	 * 
	 * @see java.awt.event.MouseListener#mousePressed
	 *      (java.awt.event.MouseEvent)
	 * @param e
	 *            Event when the mouse is pressed
	 */
	public void mousePressed(final MouseEvent e) {
	}

	/**
	 * This method is not used in our class.
	 * 
	 * @see java.awt.event.MouseListener#mouseReleased
	 *      (java.awt.event.MouseEvent)
	 * @param e
	 *            Event when the mouse is released
	 */
	public final void mouseReleased(final MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			for (int row = 0; row < length; row++) {
				for (int col = 0; col < length; col++) {
					if (e.getSource() == board[row][col]) {
						iCell = game.getCell(row, col);
						if (iCell.isFlagged()) {
							iCell.setFlagged(false);
						} else {
							iCell.setFlagged(true);
						}
					}
				}
			}
			displayBoard();
		}
	}
}
