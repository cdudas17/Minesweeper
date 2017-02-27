import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Minesweeper {
											//make board = new MinesweeperPanel(length) 
	private static JMenuBar menuBar;		// Put DEFAULT SIZE in this class
	private static JMenu menu;
	private static JMenuItem menuExit;
	private static JMenu menuDifficulty;
	private static JMenuItem menuBeginner;
	private static JMenuItem menuIntermediate;
	private static JMenuItem menuExpert;
	
	public static void main(String[] args) {
		System.out.println("executed");
		MineSweeperPanel board;
		board = new MineSweeperPanel();
		JFrame frame = new JFrame("Mine Sweeper");
		frame.add(board);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		class MenuActionListener implements ActionListener {
			
			private final int BEGINNER= 8;
			private final int BEGINNER_MINE = 10;
			private final int INTERMEDIATE = 16;
			private final int INTERMEDIATE_MINE = 40;
			private final int EXPERT = 24;
			private final int EXPERT_MINE = 99;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == menuBeginner)
					board.newGame(BEGINNER, BEGINNER_MINE);

				if (e.getSource() == menuIntermediate)
					board.newGame(INTERMEDIATE, INTERMEDIATE_MINE);
				
				if (e.getSource() == menuExpert) 
					board.newGame(EXPERT, EXPERT_MINE);
				
				if (e.getSource() == menuExit)
					System.exit(0);
			}
		}

		menuBar = new JMenuBar();
		menu = new JMenu("Options");
		menuBar.add(menu);
		
		menuDifficulty = new JMenu("Difficulty");
		menuDifficulty.addActionListener(new MenuActionListener());
		menu.add(menuDifficulty);
		
		menuBeginner = new JMenuItem("Beginner");
		menuBeginner.addActionListener(new MenuActionListener());
		menuDifficulty.add(menuBeginner);
		
		menuIntermediate = new JMenuItem("Intermediate");
		menuIntermediate.addActionListener(new MenuActionListener());
		menuDifficulty.add(menuIntermediate);
		
		menuExpert = new JMenuItem("Expert");
		menuExpert.addActionListener(new MenuActionListener());
		menuDifficulty.add(menuExpert);
		
		menu.addSeparator();
		
		menuExit= new JMenuItem("Exit");
		menuExit.addActionListener(new MenuActionListener());
		menu.add(menuExit);
		
		frame.setJMenuBar(menuBar);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
	}
}
