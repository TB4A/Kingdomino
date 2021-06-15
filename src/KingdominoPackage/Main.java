package KingdominoPackage;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		int entry_numberOfPlayer = 4;


		///[graph] each player will be prompted to write their name and king color
		//gameloop(entry_numberOfPlayer);
		///[graph] player ranked first inside the player order list is prompted to pick a domino
		///[graph] player ranked first inside the player order list is prompted to pick a domino
		Gamedata game = new Gamedata(entry_numberOfPlayer);

		game.drawFromDrawPile();


		///[graph] drawn dominos appear on screen
		System.out.println(game.currentDraw);
		System.out.println(game.player.get(2).playerName);
		int[] playerOrder=game.getPlayerOrder();
		int k = 0;
		int currentplayerID = playerOrder[k];
		//cb.setValue(currentplayerID);

		Group root = new Group();
		primaryStage.setTitle("Kingdomino");
		primaryStage.setScene(new Scene(root, 1250, 750));

		Image[] img = new Image[11];
		img[0] = new Image("file:prairie.png", 100,100, true, false);
		img[1] = new Image("file:mines.png", 100,100, true, false);
		img[2] = new Image("file:foret.png", 100,100, true, false);
		img[3] = new Image("file:montagne.png", 100,100, true, false);
		img[4] = new Image("file:champs.png", 100,100, true, false);
		img[5] = new Image("file:mer.png", 100,100, true, false);
		img[6] = new Image("file:terrainTile1.png", 100,100, true, false);
		img[7] = new Image("file:pieceBlue.png", 64,64, true, false);
		img[8] = new Image("file:piecePurple.png", 64,64, true, false);
		img[9] = new Image("file:pieceRed.png", 64,64, true, false);
		img[10] = new Image("file:pieceYellow.png", 64,64, true, false);
		ImageView[][] imgv = new ImageView[9][9];
		ChoiceBox<Object> cb = new ChoiceBox<Object>(FXCollections.observableArrayList("Joueur 1", "Joueur 2", "Joueur 3", "Joueur 4"));
		cb.setValue("Joueur 1");
		System.out.println(cb.getValue());
		root.getChildren().addAll(cb);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				imgv[i][j] = new ImageView(img[6]);
				imgv[i][j].setX(100+100*i);
				imgv[i][j].setY(100+100*j);
				root.getChildren().addAll(imgv[i][j]);
			}
		}

		ImageView[][] imgv2 = new ImageView[4][2];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				imgv2[i][j] = new ImageView(img[i]);
				imgv2[i][j].setX(1050 + 150*i);
				imgv2[i][j].setY(100 + 100 * j);
				root.getChildren().addAll(imgv2[i][j]);
			}
		}

		ImageView[][] imgv3 = new ImageView[4][2];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				imgv3[i][j] = new ImageView(img[i]);
				imgv3[i][j].setX(1050 + 150*i);
				imgv3[i][j].setY(350 + 100 * j);
				root.getChildren().addAll(imgv3[i][j]);
			}
		}

		ImageView[][] imgv4 = new ImageView[4][2];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				imgv4[i][j] = new ImageView();
				imgv4[i][j].setX(1068 + 150*i);
				imgv4[i][j].setY(168 + 250 * j);
				root.getChildren().addAll(imgv4[i][j]);
			}
		}

		primaryStage.show();

		cb.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> obsVal, Number oldVal, Number newVal) -> {
					System.out.println(newVal);
				}
		);

		imgv2[0][0].setOnMousePressed((MouseEvent e) -> {
					System.out.println("0");
					imgv[4][4].setImage(img[2]);
					game.playerPick(currentplayerID,0);
					imgv4[0][0].setImage(img[currentplayerID+7]);
				}
		);
		imgv2[0][1].setOnMousePressed((MouseEvent e) -> {
					System.out.println("0");
					root.getChildren().remove(imgv[4][4]);
					game.playerPick(currentplayerID,0);
					imgv4[0][0].setImage(img[currentplayerID+7]);
				}
		);

		imgv2[1][0].setOnMousePressed((MouseEvent e) -> {
					System.out.println("1");
					game.playerPick(currentplayerID,1);
			imgv4[1][0].setImage(img[currentplayerID+7]);
				}
		);
		imgv2[1][1].setOnMousePressed((MouseEvent e) -> {
					System.out.println("1");
					game.playerPick(currentplayerID,1);
			imgv4[1][0].setImage(img[currentplayerID+7]);
				}
		);

		imgv2[2][0].setOnMousePressed((MouseEvent e) -> {
					System.out.println("2");
					game.playerPick(currentplayerID,2);
			imgv4[2][0].setImage(img[currentplayerID+7]);
				}
		);
		imgv2[2][1].setOnMousePressed((MouseEvent e) -> {
			System.out.println("2");
			game.playerPick(currentplayerID,2);
			imgv4[2][0].setImage(img[currentplayerID+7]);
				}
		);

		imgv2[3][0].setOnMousePressed((MouseEvent e) -> {
					System.out.println("3");
			game.playerPick(currentplayerID,3);
			imgv4[3][0].setImage(img[currentplayerID+7]);
				}
		);
		imgv2[3][1].setOnMousePressed((MouseEvent e) -> {
					System.out.println("3");
			game.playerPick(currentplayerID,3);
			imgv4[3][0].setImage(img[currentplayerID+7]);
				}
		);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				imgv[i][j].setOnMousePressed((MouseEvent e) -> {
					System.out.println("test");
					//imgv[i][j].setImage(img[2]);
				});
			}
		}


		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				//System.out.println(game.currentDraw.get(i).getTile(j).biome);
				if (game.currentDraw.get(i).getTile(j).biome.equals("Foret")) {
					imgv2[i][j].setImage(img[2]);
				}
				if (game.currentDraw.get(i).getTile(j).biome.equals("Champs")) {
					imgv2[i][j].setImage(img[4]);
				}

				if (game.currentDraw.get(i).getTile(j).biome.equals("Mine")) {
					imgv2[i][j].setImage(img[1]);
				}

				if (game.currentDraw.get(i).getTile(j).biome.equals("Montagne")) {
					imgv2[i][j].setImage(img[3]);
				}

				if (game.currentDraw.get(i).getTile(j).biome.equals("Mer")) {
					imgv2[i][j].setImage(img[5]);
				}

				if (game.currentDraw.get(i).getTile(j).biome.equals("Prairie")) {
					imgv2[i][j].setImage(img[0]);
				}

			}
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/////////ini
		launch(args);

	}



	private static void gameloop(int numberOfPlayer) {
		// TODO Auto-generated method stub
		//while(true) { 			///////// Gameloop///////////
			
			/////////initialize the game object
			Gamedata game = new Gamedata(numberOfPlayer);

			game.drawFromDrawPile();

			
			///[graph] drawn dominos appear on screen
			System.out.println(game.currentDraw);
			System.out.println(game.player.get(2).playerName);
			int[] playerOrder=game.getPlayerOrder();

			//for(int i = 0 ; i<playerOrder.length;i++) {
				//int currentplayerID = playerOrder[i];
				//game.playerPick(currentplayerID);
				// not used right now game.kingdomCheck(i);
				//game.playerKingdomPlace(currentplayerID);
			//}
			//game.setPlayerOrder();
		//}
	}
	
	
	///////////////////////////////////////// methodes

}