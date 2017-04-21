package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class tictactoe {
	
	/** Creates a menu at the top of the GUI. */
	private static JMenuBar menuBar;

	/** For a tab 'menu' in menuBar. */
	private static JMenu menu;
	
	/** An item in a menu to select a new game. */
	private static JMenuItem menuNewGame;

	/** An item in the menu to exit the game. */
	private static JMenuItem menuExit;
	
	/** A JFrame to add elements to. */
	private static JFrame frame;
	
	/** For a MineSweeperPanel that holds the buttons for the game. */
	private static TicTacToePanel board;
	
	public static void main(final String string) {
		board = new TicTacToePanel();
		frame = new JFrame("Tic-Tac-Toe");
		frame.add(board);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		/**
		 * This class is used for the different menu options.
		 * 
		 * @author Matthew Shampine, Connor Dudas
		 */
		class MenuActionListener implements ActionListener {
			@Override
			/**
			 * Performs an action based on a menu selection.
			 * 
			 */
			public void actionPerformed(final ActionEvent e) {
				
				if (e.getSource() == menuNewGame) {
					frame.remove(board);
					board = new TicTacToePanel();
					clearBoard();
				}
				
				if (e.getSource() == menuExit) {
					System.exit(0);
				}
			}
			
			/**
			 * This method adds the new board to the frame.
			 */
			private void clearBoard() {
				frame.add(board);
				frame.pack();
				frame.setLocationRelativeTo(null);
			}
		}
		
		menuBar = new JMenuBar();
		menu = new JMenu("Options");
		menuBar.add(menu); // adds Options
		
		menuNewGame = new JMenuItem("New Game");
		menuNewGame.addActionListener(new MenuActionListener());
		menu.add(menuNewGame); // adds New Game
		
		menu.addSeparator();
		menuExit = new JMenuItem("Exit");
		menuExit.addActionListener(new MenuActionListener());
		menu.add(menuExit); // adds Exit
		
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
	}
}
