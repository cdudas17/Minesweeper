import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Minesweeper{

	private static JMenuBar menuBar;
	private static JMenu menu;
	private static JMenuItem menuExit;
	private static JMenu menuDifficulty;
	private static JMenuItem menuBeginner;
	private static JMenuItem menuIntermediate;
	private static JMenuItem menuExpert;
	private static MineSweeperPanel board;
	private static JFrame frame;
	
	private final static int DEFAULT_SIZE = 8;
	private final static int DEFAULT_MINE = 10;
	
	public static void main(String[] args) {
		System.out.println("executed");
		board = new MineSweeperPanel(DEFAULT_SIZE, DEFAULT_MINE);
		frame = new JFrame("Mine Sweeper");
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
				frame.remove(board);
				if (e.getSource() == menuBeginner)
					board = new MineSweeperPanel(BEGINNER, BEGINNER_MINE);

				if (e.getSource() == menuIntermediate)
					board = new MineSweeperPanel(INTERMEDIATE, INTERMEDIATE_MINE);
				
				if (e.getSource() == menuExpert) 
					board = new MineSweeperPanel(EXPERT, EXPERT_MINE);
				
				frame.add(board);
				frame.pack();
				frame.setLocationRelativeTo(null);
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

