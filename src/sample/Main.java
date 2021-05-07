package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
		primaryStage.setTitle("Hello World");
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/////////ini
		launch(args);
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
			sample.Gamedata game = new sample.Gamedata(numberOfPlayer);
			
			game.drawFromDrawPile();


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
