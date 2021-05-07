import javax.swing.*;
import java.awt.*;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/////////ini
		int entry_numberOfPlayer = 4;
		
		
		///[graph] each player will be prompted to write their name and king color 
		gameloop(entry_numberOfPlayer);
		///[graph] player ranked first inside the player order list is prompted to pick a domino
		///[graph] player ranked first inside the player order list is prompted to pick a domino
	}

	private static void gameloop(int numberOfPlayer) {
		// TODO Auto-generated method stub
		while(true) { 			///////// Gameloop///////////
			
			/////////initialize the game object
			Gamedata game = new Gamedata(numberOfPlayer);
			
			game.drawFromDrawPile();

			JFrame frame = new JFrame("Kingdomino");
			frame.setSize(new Dimension(500,500));
			JPanel Kingdom2 = new JPanel();
			frame.add(Kingdom2);
			Kingdom2.setLayout( new GridBagLayout() );
			Kingdom2.setSize(new Dimension(300,300));
			//Kingdom.setBounds(0, 0, boardSize.width, boardSize.height);
			/*
			for (int i = 1; i<26; i++) {
				JLabel label = new JLabel();
				label.setIcon(new ImageIcon("terrainTile1.png"));
				frame.add(label);
			}

			 */

			GridBagConstraints constraints = new GridBagConstraints();

			constraints.fill = GridBagConstraints.BOTH;
			constraints.insets = new Insets(0, 0, 0, 0);
			constraints.ipady = constraints.anchor = GridBagConstraints.CENTER;
			constraints.weightx = 5;
			constraints.weighty = 5;

			for (int j = 0; j<5; j++) {
				for (int i = 0; i<5; i++) {
					JLabel label = new JLabel();
					label.setIcon(new ImageIcon("terrainTile1.png"));
					constraints.gridx = i;
					constraints.gridy = j;
					Kingdom2.add(label, constraints);
				}
			}
			/*
			JPanel panel = new JPanel();

			JButton jbutton1 = new JButton();
			jbutton1.setIcon(new ImageIcon("terrainTile1.png"));
			panel.add(jbutton1);

			JButton jbutton2 = new JButton();
			jbutton2.setIcon(new ImageIcon("terrainTile2.png"));
			panel.add(jbutton2);

			JButton jbutton3 = new JButton();
			jbutton3.setIcon(new ImageIcon("terrainTile3.png"));
			panel.add(jbutton3);

			JButton jbutton4 = new JButton();
			jbutton4.setIcon(new ImageIcon("terrainTile4.png"));
			panel.add(jbutton4);

			frame.add(panel);


			/*
			JButton jbutton = new JButton("test");
			jbutton.setIcon(new ImageIcon("terrainTile2.png"));
			jbutton.setVerticalTextPosition(SwingConstants.TOP);
			jbutton.setVerticalAlignment(SwingConstants.CENTER);
			frame.add(jbutton);
			*/

			JLabel l = new JLabel();

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);

			///[graph] drawn dominos appear on screen
			System.out.println(game.currentDraw);
			System.out.println(game.player.get(2).playerName);
			int[] playerOrder=game.getPlayerOrder();
			for(int i = 0 ; i<playerOrder.length;i++) {
				int currentplayerID = playerOrder[i];
				game.playerPick(currentplayerID);
				//game.kingdomCheck(i);
				game.playerKingdomPlace(currentplayerID);
			}
			game.setPlayerOrder();
		}
	}
	
	
	///////////////////////////////////////// methodes

}
