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
	private Cell iCell;

	private JPanel center;

	private int length;

	public MineSweeperPanel(int pLength, int mineNum) { // Make constructor go by size
		game = new MineSweeperGame(pLength, mineNum);
		setLength(pLength);
		board = new JButton[length][length];
		setLayout(new BorderLayout());

		createButtons();
	}

	public void createButtons(){
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
	
	public void displayBoard(){
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				iCell = game.getCell(row, col);
				board[row][col].setEnabled(true);
				if(iCell.isExposed()){
					board[row][col].setEnabled(false);
					board[row][col].setBackground(Color.WHITE);
					if(iCell.getMineCount() >= 0)
						board[row][col].setText(""+iCell.getMineCount());
					if(iCell.isMine())
						board[row][col].setBackground(Color.BLACK);
				} else if (iCell.isFlagged())
					board[row][col].setBackground(Color.RED);
					board[row][col].setEnabled(false);
			}
		}
	}

	public void setLength(int length) {
		this.length = length;
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