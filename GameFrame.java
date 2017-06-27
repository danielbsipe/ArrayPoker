package array_poker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameFrame extends JPanel implements ActionListener {

	Main game;
	
	Button BidButton;
	Button RollButton;
	Button QuitButton;
	Button ReRollButton;
	
	TextArea player = new TextArea("Player\n--------------\n");
	TextArea opponent = new TextArea("Opponent\n--------------\n");
	JPanel container = new JPanel();
	boolean readyRoll;

	public GameFrame() {

		this.setLayout(new BorderLayout());

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(1, 2));

		player.setFont(new Font("Arial", Font.PLAIN, 18));
		player.setEditable(false);
		player.setSize(100, 100);

		opponent.setFont(new Font("Arial", Font.PLAIN, 18));
		opponent.setEditable(false);
		opponent.setSize(100, 100);

		textPanel.add(player);
		textPanel.add(opponent);

		RollButton = new Button("Roll the Dice");
		BidButton = new Button("Place a Bid");
		QuitButton = new Button("Quit the Game");
		ReRollButton = new Button("Re-Roll a Die");

		container.add(BidButton);
		container.add(RollButton);
		container.add(ReRollButton);
		container.add(QuitButton);
		
		//player.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		this.add(textPanel, BorderLayout.CENTER);

		this.add(container, BorderLayout.SOUTH);
		RollButton.addActionListener(this);
		BidButton.addActionListener(this);
		ReRollButton.addActionListener(this);
		QuitButton.addActionListener(this);

	}
	
	public void setGame(Main main){
		this.game = main;
	}

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == BidButton) {
			
		}

		if (ae.getSource() == RollButton) {
			//game.setContinue(true);	
		}

		if (ae.getSource() == QuitButton) {
			System.exit(0);
		}
		
		if (ae.getSource() == ReRollButton) {
			
		}
	}

}