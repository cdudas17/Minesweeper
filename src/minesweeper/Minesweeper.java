package minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * This class creates a frame for the MineSweeper panel
 * to attach to. This class is responsible for setting up and
 * running the Minesweeper game. 
 * 
 * @author Matthew Shampine, Connor Dudas
 * @version 1.0
 */
public class Minesweeper {

	/** Creates a menu at the top of the GUI.*/
	private static JMenuBar menuBar;
	
	/** For a tab 'menu' in menuBar.*/
	private static JMenu menu;
	
	/** An item in the menu to exit the game.*/
	private static JMenuItem menuExit;
	
	/** A menu in the menu to select a difficulty.*/
	private static JMenu menuDifficulty;
	
	/** A beginner difficulty select in Difficulty.*/
	private static JMenuItem menuBeginner;
	
	/** An intermediate difficulty select in Difficult.y*/
	private static JMenuItem menuIntermediate;
	
	/** An expert difficulty select in Difficulty.*/
	private static JMenuItem menuExpert;
	
	/** Menu item to save current game.*/
	private static JMenuItem menuSave;
	
	/** Menu item to load a saved game.*/
	private static JMenuItem menuLoad;

	/** For a MineSweeperPanel that holds the buttons for the game.*/
	private static MineSweeperPanel board;
	
	/** A JFrame to add elements to.*/
	private static JFrame frame;
	
	// CHECKSTYLE:ON
	/** A Default size of a board.*/
	private static final int DEFAULT_SIZE = 8;
	
	/** A default mine count of a board.*/
	private static final int DEFAULT_MINE = 10;
	// CHECKSYLE:OFF
	
	/**
	 * Main method that runs the Minesweeper game.
	 * 
	 * @param args Default arguments used for debugging 
	 */
	public static void main(final String[] args) {
		board = new MineSweeperPanel(DEFAULT_SIZE, DEFAULT_MINE);
		frame = new JFrame("Mine Sweeper");
		frame.add(board);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		/**
		 * 
		 * @author Matthew Shampine, Connor Dudas
		 */
		class MenuActionListener implements ActionListener {
			// CHECKSTYLE:OFF
			/** The default size of a beginner board (8x8). */
			private final int BEGINNER = 8;
			
			/** The default number of mines for a beginner. */
			private final int BEGINNER_MINES = 10;
			
			/** The default size of an intermediate board (16x16). */
			private final int INTERMEDIATE = 16;
			
			/** The default amount of mines on an intermediate board (16x16).*/
			private final int INTERMEDIATE_MINES = 40;
			
			/** The default size of an expert board (24x24).*/
			private final int EXPERT = 24;
			
			/** The default amount of mines on an expert board (24x24).*/
			private final int EXPERT_MINES = 99;
			// CHECKSTYLE:ON

			@Override
			/**
			 * Performs an action based on a menu selection.
			 */
			public void actionPerformed(final ActionEvent e) {
				if (e.getSource() == menuBeginner) {
					frame.remove(board);
					board = new MineSweeperPanel(
							BEGINNER,
							BEGINNER_MINES);
					clearBoard();
				}

				if (e.getSource() == menuIntermediate) {
					frame.remove(board);
					board = new MineSweeperPanel(
							INTERMEDIATE, 
							INTERMEDIATE_MINES);
					clearBoard();
				}

				if (e.getSource() == menuExpert) {
					frame.remove(board);
					board = new MineSweeperPanel(
							EXPERT, EXPERT_MINES);
					clearBoard();
				}

				if (e.getSource() == menuExit) {
					System.exit(0);
				}

				if (e.getSource() == menuSave) {
					String filename = JOptionPane.
							showInputDialog(null,
							"File Name:", "Save", 
							JOptionPane.
							INFORMATION_MESSAGE);
					if (filename == null) {
						return;
					}
					board.save(filename);
				}

				if (e.getSource() == menuLoad) {
					String filename = JOptionPane.
							showInputDialog(null, 
							"File Name:", "Load", 
							JOptionPane.
							INFORMATION_MESSAGE);
					if (filename == null) {
						return;
					}
					int size = board.boardSize(filename);
					if (size == 0) {
						return;
					}
					frame.remove(board);
					board = new MineSweeperPanel(size,
							filename);
					if (!board.isSuccess()) {
						board = new MineSweeperPanel(
								BEGINNER,
								BEGINNER_MINES);
					}
					clearBoard();
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
		
		menuDifficulty = new JMenu("Difficulty");
		menuDifficulty.addActionListener(new MenuActionListener());
		menu.add(menuDifficulty); // adds Difficulty
		
		menuBeginner = new JMenuItem("Beginner");
		menuBeginner.addActionListener(new MenuActionListener());
		menuDifficulty.add(menuBeginner); // adds Beginner
		
		menuIntermediate = new JMenuItem("Intermediate");
		menuIntermediate.addActionListener(new MenuActionListener());
		menuDifficulty.add(menuIntermediate); // adds Intermediate
		
		menuExpert = new JMenuItem("Expert");
		menuExpert.addActionListener(new MenuActionListener());
		menuDifficulty.add(menuExpert); // adds Expert
		
		menu.addSeparator();
		menuSave = new JMenuItem("Save");
		menuSave.addActionListener(new MenuActionListener());
		menu.add(menuSave); // adds Save option
		
		menu.addSeparator();
		menuLoad = new JMenuItem("Load");
		menuLoad.addActionListener(new MenuActionListener());
		menu.add(menuLoad); // adds Load option
		
		menu.addSeparator();
		menuExit = new JMenuItem("Exit");
		menuExit.addActionListener(new MenuActionListener());
		menu.add(menuExit); // adds Exit
		
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
	}
}
