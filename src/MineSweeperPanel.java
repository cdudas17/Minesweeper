import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MineSweeperPanel extends JPanel implements ActionListener {

	private JButton[][] board;

	private MineSweeperGame game;

	private JPanel center;

	private int length;

	private int DEFAULT_SIZE = 8;

	public MineSweeperPanel() {				//Make consturctor go by size
		length = DEFAULT_SIZE;
		game = new MineSweeperGame(length);
		board = new JButton[length][length];
		setLayout(new BorderLayout());

		center = new JPanel();
		center.setLayout(new GridLayout(length, length));
		add(center, BorderLayout.CENTER);

		for (int row = 0; row < length; row++) { // ADD BUTTONS
			for (int col = 0; col < length; col++) {
				board[row][col] = new JButton("");
				board[row][col].setPreferredSize(new Dimension(45, 45));
				board[row][col].setBackground(Color.LIGHT_GRAY);
				board[row][col].addActionListener(this);
				center.add(board[row][col]);
			}
		}
	}

	public void actionPerformed(ActionEvent event) {
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				if (event.getSource() == board[row][col]) {
					// do nothing
				}
			}
		}
	}
}
