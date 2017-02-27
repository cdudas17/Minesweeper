import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MineSweeperPanel extends JPanel implements ActionListener {
	private JButton[][] board;
	private int length;
	
	public MineSweeperPanel(){
		length = 10;
		board = new JButton[length][length];
		setLayout(new BorderLayout());
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(length,length));
		add(center, BorderLayout.CENTER);
		
		for (int row = 0; row < length; row++){		//ADD BUTTONS
			for (int col = 0; col < length; col++) {
			board[row][col] = new JButton ("");
			board[row][col].setPreferredSize(new Dimension(45,45));
			board[row][col].setBackground(Color.LIGHT_GRAY);
			board[row][col].addActionListener(this);
			center.add(board[row][col]);
			}
		}
	}
	
	public void actionPerformed(ActionEvent event){
		for (int row = 0; row < length; row++){
			for (int col = 0; col < length; col++){
				if(event.getSource() == board[row][col]){
					//do nothing
				}
			}
		}
	}
}
