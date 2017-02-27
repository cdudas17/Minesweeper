import javax.swing.JFrame;
import javax.swing.JPanel;

public class Minesweeper {
	public static void main(String[] args) {
		System.out.println("executed");
		JPanel board;
		board = new MineSweeperPanel();
		JFrame frame = new JFrame("Mine Sweeper");
		frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}
}
