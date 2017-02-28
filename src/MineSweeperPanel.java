import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MineSweeperPanel extends JPanel implements ActionListener {

	private JButton[][] board;
	private MineSweeperGame game;
	private Cell iCell;
	private JPanel center;

	private int length;
	
	ImageIcon mineIcon = new ImageIcon("src/mine.png");
	ImageIcon flagIcon = new ImageIcon("src/flag.png");

	public MineSweeperPanel(int pLength, int mineNum) {
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
	
	public void createButtons(){
		for (int row = 0; row < length; row++) { //ADD BUTTONS
			for (int col = 0; col < length; col++) {
				board[row][col] = new JButton("");
				board[row][col].setPreferredSize(new Dimension(45, 45));
				board[row][col].setBackground(Color.LIGHT_GRAY);
				board[row][col].addActionListener(this);
				center.add(board[row][col]);
			}
		}
	}
	
	private void displayBoard(){
		for (int row = 0; row < length; row++){
			for (int col = 0; col < length; col++){
				iCell = game.getCell(row,col);
				board[row][col].setIcon(null);
				board[row][col].setText("");
				if (iCell.isExposed()){
					board[row][col].setEnabled(false);
					board[row][col].setBackground(Color.WHITE);
					if(iCell.isMine())
						board[row][col].setIcon(mineIcon);
					else if (iCell.getMineCount() == 0)
						board[row][col].setText("");
					else
						board[row][col].setText("" + iCell.getMineCount());
				}
				else{
					board[row][col].setEnabled(true);
					board[row][col].setBackground(Color.LIGHT_GRAY);
				}
				if(iCell.isFlagged()){
					board[row][col].setIcon(flagIcon);
					board[row][col].setEnabled(false);
				}
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
					iCell = game.getCell(row, col);
					iCell.setExposed(true);
				}
			}
		}
		
		displayBoard();
	}
}
