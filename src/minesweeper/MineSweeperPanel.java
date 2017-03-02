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

public class MineSweeperPanel extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 2279352759025366829L;
	
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
	
	public MineSweeperPanel(int size, String filename) {
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
	
	private void createButtons(){
		for (int row = 0; row < length; row++) { //ADD BUTTONS
			for (int col = 0; col < length; col++) {
				board[row][col] = new JButton("");
				board[row][col].setPreferredSize(new Dimension(45, 45));
				board[row][col].setBackground(Color.LIGHT_GRAY);
				board[row][col].addActionListener(this);
				board[row][col].addMouseListener(this);
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
				
				if(iCell.isFlagged()){	//Adds icon to flags
					board[row][col].setIcon(flagIcon);
					//board[row][col].setEnabled(false); //This dims the imageIcon of the flag
				}
				
				if (iCell.isExposed()){	//checks for exposed cells
					board[row][col].setEnabled(false);
					board[row][col].setBackground(Color.WHITE);
					if(iCell.isMine())	//sets mine icons
						board[row][col].setIcon(mineIcon);
					else if (iCell.getMineCount() == 0)	//for 'zeroSpaces'
						board[row][col].setText("");
					else	//for cells bordering a mine
						board[row][col].setText("" + iCell.getMineCount());
				}
				else{
					board[row][col].setEnabled(true);
					board[row][col].setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}
	
	private void disableButtons(){
		for (int row = 0; row < length; row++){
			for (int col = 0; col < length; col++){
				board[row][col].setEnabled(false);
			}
		}
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	//Action Listener
	public void actionPerformed(ActionEvent event) {
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				if (event.getSource() == board[row][col]) {
					//iCell = game.getCell(row, col);
					//iCell.setExposed(true);
					game.selectCell(row,  col);
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
	
	public boolean isSuccess() {
		return game.isloadSuccessful();
	}

	public void save(String filename) {
		game.save(filename);
	}
	
	public int boardSize(String filename) {
		return game.getSize(filename);
	}
	
	//Mouse listener
	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)){
			for (int row = 0; row < length; row++){
				for (int col = 0; col < length; col++){
					if(e.getSource() == board[row][col]){
						iCell = game.getCell(row,col);
						if(iCell.isFlagged())
							iCell.setFlagged(false);
						else
							iCell.setFlagged(true);
					}
				}
			}
			displayBoard();
		}
	}
}
