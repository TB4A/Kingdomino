import java.util.Arrays;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/////////ini
		int entry_numberOfPlayer = 2;
		
		
		///[graph] each player will be prompted to write their name and king color 
		gameloop(entry_numberOfPlayer);
		///[graph] player ranked first inside the player order list is prompted to pick a domino
		///[graph] player ranked first inside the player order list is prompted to pick a domino
	}

	private static void gameloop(int numberOfPlayer) {
		// TODO Auto-generated method stub
		
		/////////initialize the game object
		Gamedata game = new Gamedata(numberOfPlayer);
		
		while(true) { 		///////// Gameloop///////////
			
			game.drawFromDrawPile();
			///[graph] drawn dominos appear on screen
			System.out.println(game.currentDraw);
			//System.out.println(game.player.get(2).playerName);
			int[] playerOrder=game.getPlayerOrder();
			System.out.println(Arrays.toString(game.playerOrder));
			for(int i = 0 ; i<playerOrder.length;i++) {
				int currentplayerID = playerOrder[i];
				game.playerPick(currentplayerID);
				//game.kingdomCheck(i);
				game.playerKingdomPlace(currentplayerID);
			}
			game.setPlayerOrder(); // set player order that will be use on the next loop
		}
	}
	
	
	///////////////////////////////////////// methodes

}
