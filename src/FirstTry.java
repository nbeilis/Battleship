/*Name: Nicole Beilis
		  Class: ICS201-01
		  Date: 17/1/16
		  Teacher: Mr.Naccarato
		  Program Summary: This program lets you play a game of tic tac toe.  
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class FirstTry 
{
	private static JLabel scoreLabel = new JLabel();
	private static JLabel locationLabel = new JLabel();
	private static JButton[][] gameButtons = new JButton[10][10];

	public static void main(String[] args) 
	{
		JFrame jframe = new JFrame("Battleship");
		jframe.setVisible(true);
		JPanel jpanel = new JPanel();
		JLayeredPane pane = new JLayeredPane();

		addScoreLabel(jpanel);

		addLocationLabel(jpanel, pane);

		createPlayerBoard( jframe, jpanel, pane );
		createSetupBoard(jframe, jpanel);

		// Game initialize
		initializeGame();

	}

	private static void initializeGame() {
		for (int i = 0;i<10;i++)
		{
			for(int j = 0; j<10; j++)
			{
				gameButtons[i][j].setVisible( true );
			}
		}

	}

	private static void createPlayerBoard( JFrame jframe, JPanel jpanel, JLayeredPane pane) {
		//Panels for game squares
		JPanel[][] boardSquares = new JPanel[10][10];
		for(int i = 0;i<10;i++)
		{
			for(int j = 0; j<10; j++)
			{
				boardSquares[i][j] = new JPanel();
			}
		}

		
		{
			jframe.setSize(1050, 1080);
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jframe.add(pane, BorderLayout.CENTER);
			pane.setBounds(0, 0, 1050, 1080);
			jpanel.setBackground(Color.BLACK);
			jpanel.setBounds(0, 0, 1050, 1080);
			for(int i = 0;i<10;i++)
			{
				for(int j = 0; j<10; j++)
				{
					boardSquares[i][j].setBackground(Color.black);
					boardSquares[i][j].setBounds(i*50, j*50+50, 50, 50);
				}
			}
			pane.add(jpanel, new Integer(0), 0);

			for(int i = 0;i<10;i++)
			{
				for(int j = 0; j<10; j++)
				{
					pane.add(boardSquares [i][j], new Integer(1), 0);
				}
			}
		}

		createClickableBoardAreas(jframe, boardSquares);
	}

	private static void createSetupBoard( JFrame jframe, JPanel jpanel ) {
		//Panels for game squares
		JPanel[][] boardSquares = new JPanel[10][10];
		for(int i = 0;i<10;i++)
		{
			for(int j = 0; j<10; j++)
			{
				boardSquares[i][j] = new JPanel();
			}
		}

		JLayeredPane pane = new JLayeredPane();
		{
			jframe.setSize(1050, 1080);
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jframe.add(pane, BorderLayout.CENTER);
			pane.setBounds(0, 0, 1050, 1080);
			jpanel.setBackground(Color.BLACK);
			jpanel.setBounds(0, 0, 1050, 1080);
			for(int i = 0;i<10;i++)
			{
				for(int j = 0; j<10; j++)
				{
					boardSquares[i][j].setBackground(Color.black);
					boardSquares[i][j].setBounds(i*50+550, j*50+50, 50, 50);
				}
			}
			pane.add(jpanel, new Integer(0), 0);

			for(int i = 0;i<10;i++)
			{
				for(int j = 0; j<10; j++)
				{
					pane.add(boardSquares [i][j], new Integer(1), 0);
				}
			}
		}

		createClickableBoardAreas(jframe, boardSquares);
	}

	
	private static void createClickableBoardAreas( JFrame jframe, JPanel[][] boardSquares ) {
		for(int i = 0;i<10;i++)
		{
			for(int j = 0; j<10; j++)
			{
				gameButtons[i][j] = new JButton();
				gameButtons[i][j].setPreferredSize(new Dimension(50, 50));
				boardSquares[i][j].add(gameButtons[i][j]);
				boardSquares[i][j].setVisible(true);
				jframe.setVisible(true);
			}
		}

	}
	private static void addScoreLabel( JPanel jpanel) 
	{
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 15));
		scoreLabel.setForeground(Color.white);
		scoreLabel.setVisible(true);
		scoreLabel.setText("Number of Boats Left");
		jpanel.add(scoreLabel);
	}
	
	private static void addLocationLabel( JPanel jpanel, JLayeredPane pane) 
	{
		List<JLabel> listOfLabels = new ArrayList<JLabel>();
		
		//then get the button at index 0 for example
		listOfLabels.get(0);
		locationLabel.setFont(new Font("Arial", Font.BOLD, 20));
		locationLabel.setForeground(Color.white);
		Dimension size = locationLabel.getPreferredSize();
		for (char c = 'a'; c <= 'j'; c++) {
			char[] alphabet;
			alphabet = new char[10];
			alphabet[c - 'a'] = c;
			locationLabel.setText(Character.toString(c));
			int y = 60;
			locationLabel.setBounds(515, y, size.width, size.height);
			y+=50;
			locationLabel.setVisible(true);
			for(int i=1; i<=10; i++){
			    listOfLabels.add( locationLabel ); 
			    pane.add(locationLabel);
				listOfLabels.get(0);
			}
		}
		
	}

}