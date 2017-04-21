package Controller;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import minesweeper.*;
import tictactoe.*;
import connectfour.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class controller{
	
	/** A JFrame to add elements to. */
	private static JFrame frame;
	
	/** A JPanel to organize elements in*/
	private static JPanel panel;
	
	/** A JButton to open Minesweeper with */
	private static JButton mineSweeperButton;
	
	/** A JButton to open TicTacToe with */
	private static JButton ticTacToeButton;
	
	/** A JButton to open ConnectFour with */
	private static JButton connectFourButton;
	
	public static void main(String[] args) {
		frame = new JFrame("controller");
		
		/**
		 * This class is used for the different menu options.
		 * 
		 * @author Matthew Shampine, Connor Dudas
		 */
		class ButtonListener implements ActionListener {
			@Override
			/**
			 * Performs an action based on a menu selection.
			 * 
			 */
			public void actionPerformed(final ActionEvent e) {
				
				if (e.getSource() == mineSweeperButton) {
					Minesweeper.main("");
				}
				
				if (e.getSource() == ticTacToeButton) {
					tictactoe.main("");
				}
				
				if (e.getSource() == connectFourButton) {
					ConnectFour.main("");
				}
			}
		}
	
	panel = new JPanel();
	panel.setLayout(new BorderLayout());
	
	mineSweeperButton = new JButton("Mine Sweeper");
	mineSweeperButton.addActionListener(new ButtonListener());
	ticTacToeButton = new JButton("Tic-Tac-Toe");
	ticTacToeButton.addActionListener(new ButtonListener());
	connectFourButton = new JButton("Connect Four");
	connectFourButton.addActionListener(new ButtonListener());
	
	panel.add(mineSweeperButton, BorderLayout.WEST);
	panel.add(ticTacToeButton, BorderLayout.CENTER);
	panel.add(connectFourButton, BorderLayout.EAST);
	
	frame.add(panel);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	}
}
