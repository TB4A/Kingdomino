package KingdominoPackage;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

		Image[] img = new Image[13];
		img[0] = new Image("file:Assets/prairie.png", 100,100, true, false);
		img[1] = new Image("file:Assets/mines.png", 100,100, true, false);
		img[2] = new Image("file:Assets/foret.png", 100,100, true, false);
		img[3] = new Image("file:Assets/montagne.png", 100,100, true, false);
		img[4] = new Image("file:Assets/champs.png", 100,100, true, false);
		img[5] = new Image("file:Assets/mer.png", 100,100, true, false);
		img[6] = new Image("file:Assets/terrainTile1.png", 100,100, true, false);
		img[7] = new Image("file:Assets/pieceBlue.png", 64,64, true, false);
		img[8] = new Image("file:Assets/piecePurple.png", 64,64, true, false);
		img[9] = new Image("file:Assets/pieceRed.png", 64,64, true, false);
		img[10] = new Image("file:Assets/pieceYellow.png", 64,64, true, false);
		img[11] = new Image("file:Assets/crown.png", 32,32, true, false);
		img[12] = new Image("file:Assets/castle_grey.png", 100,100, true, false);
		
		ChoiceBox<Object> cb = new ChoiceBox<Object>(FXCollections.observableArrayList("Joueur 1", "Joueur 2", "Joueur 3", "Joueur 4"));
		cb.setValue("Joueur 1");
		System.out.println(cb.getValue());
		root.getChildren().addAll(cb);

		Button buttonRotateRight = new Button("tourner vers la droite");
		buttonRotateRight.setLayoutX(1450);
		buttonRotateRight.setLayoutY(800);
		buttonRotateRight.setVisible(false);
		root.getChildren().addAll(buttonRotateRight);

		Button buttonRotateLeft = new Button("tourner vers la gauche");
		buttonRotateLeft.setLayoutX(1150);
		buttonRotateLeft.setLayoutY(800);
		buttonRotateLeft.setVisible(false);
		root.getChildren().addAll(buttonRotateLeft);
		
		ImageView[][] imgvKingdom = new ImageView[9][9];
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				imgvKingdom[i][j] = new ImageView(img[6]);
				imgvKingdom[i][j].setX(100+100*i);
				imgvKingdom[i][j].setY(100+100*j);
				root.getChildren().addAll(imgvKingdom[i][j]);
			}
		}

		ImageView[][][] imgvKingdomCrown = new ImageView[9][9][2];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				for (int l = 0; l < 2; l++) {
					imgvKingdomCrown[i][j][l] = new ImageView();
					imgvKingdomCrown[i][j][l].setX(100+100*i+32*l);
					imgvKingdomCrown[i][j][l].setY(100+100*j);
					root.getChildren().addAll(imgvKingdomCrown[i][j][l]);
				}
			}
		}

		ImageView imgCastle = new ImageView(img[12]);
		imgCastle.setX(500);
		imgCastle.setY(500);
		root.getChildren().addAll(imgCastle);

		ImageView[][] imgvDominoPick = new ImageView[4][2];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				imgvDominoPick[i][j] = new ImageView();
				imgvDominoPick[i][j].setX(1050 + 150*i);
				imgvDominoPick[i][j].setY(100 + 100 * j);
				root.getChildren().addAll(imgvDominoPick[i][j]);
			}
		}

		ImageView[][] imgvDominoPicked = new ImageView[4][2];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				imgvDominoPicked[i][j] = new ImageView();
				imgvDominoPicked[i][j].setX(1050 + 150*i);
				imgvDominoPicked[i][j].setY(350 + 100 * j);
				root.getChildren().addAll(imgvDominoPicked[i][j]);
			}
		}

		ImageView[][] imgvPawn = new ImageView[4][2];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				imgvPawn[i][j] = new ImageView();
				imgvPawn[i][j].setX(1068 + 150*i);
				imgvPawn[i][j].setY(168 + 250 * j);
				root.getChildren().addAll(imgvPawn[i][j]);
			}
		}

		ImageView[][][][] imgvCrown = new ImageView[4][2][2][2];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				for (int x = 0; x < 2; x++) {
					for (int l = 0; l < 2; l++) {
						imgvCrown[i][j][x][l] = new ImageView();
						imgvCrown[i][j][x][l].setX(1050 + 150*i + 32*x);
						imgvCrown[i][j][x][l].setY(100 + 250 * j + 168*l);
						root.getChildren().addAll(imgvCrown[i][j][x][l]);
					}
				}
			}
		}

		ImageView[] imgvDominoToPlace = new ImageView[2];
		for (int i = 0; i < 2; i++) {
			imgvDominoToPlace[i] = new ImageView();
			imgvDominoToPlace[i].setX(1200+100*i);
			imgvDominoToPlace[i].setY(650);
			root.getChildren().addAll(imgvDominoToPlace[i]);
		}

		ImageView[][] imgvDominoToPlaceCrown = new ImageView[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				imgvDominoToPlaceCrown[i][j] = new ImageView();
				imgvDominoToPlaceCrown[i][j].setX(1200+100*i);
				imgvDominoToPlaceCrown[i][j].setY(650);
				root.getChildren().addAll(imgvDominoToPlaceCrown[i][j]);
			}
		}

		primaryStage.show();

		cb.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> obsVal, Number oldVal, Number newVal) -> {
					System.out.println(newVal);

					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							imgvKingdom[i][j].setImage(img[6]);
							imgvKingdomCrown[i][j][0].setImage(null);
							imgvKingdomCrown[i][j][1].setImage(null);
						}
					}
					for (Domino domino: game.player.get((Integer) newVal).kingdom) {
						int x0 = domino.tile0.x+4;
						int y0 = -domino.tile0.y+4;
						String biome0 = domino.tile0.biome;
						int crown0 =domino.tile0.numberOfCrown;
						imgvKingdom[x0][y0].setImage(img[switchTile(biome0)]);
						if (crown0 >= 1) {
							imgvKingdomCrown[x0][y0][0].setImage(img[11]);

							if (crown0 == 2) {
								imgvKingdomCrown[x0][y0][1].setImage(img[11]);
							}
						}
						int x1 = domino.tile1.x+4;
						int y1 = -domino.tile1.y+4;
						String biome1 = domino.tile1.biome;
						int crown1 =domino.tile1.numberOfCrown;
						imgvKingdom[x1][y1].setImage(img[switchTile(biome1)]);
						if (crown1 >= 1) {
							imgvKingdomCrown[x1][y1][0].setImage(img[11]);

							if (crown1 == 2) {
								imgvKingdomCrown[x1][y1][1].setImage(img[11]);
							}
						}
					}
				}
		);

		buttonRotateRight.setOnAction(e -> {
			game.player.get(game.getCurrentPlayer()).rotateSelectedDominoLeft();
			System.out.println(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation);
			for (int l = 0; l < 2; l++) {
				imgvDominoToPlace[l].setX(1320 + 100 * l * Math.cos(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
				imgvDominoToPlace[l].setY(675 - 100 * l * Math.sin(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
				imgvDominoToPlaceCrown[l][0].setX(1320+100*l*Math.cos(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
				imgvDominoToPlaceCrown[l][0].setY(675-100*l*Math.sin(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
				imgvDominoToPlaceCrown[l][1].setX(1352+100*l*Math.cos(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
				imgvDominoToPlaceCrown[l][1].setY(675-100*l*Math.sin(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
			}
		});
		
		buttonRotateLeft.setOnAction(e -> {
			game.player.get(game.getCurrentPlayer()).rotateSelectedDominoRight();
			System.out.println(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation);
			for (int l = 0; l < 2; l++) {
				imgvDominoToPlace[l].setX(1320 + 100 * l * Math.cos(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
				imgvDominoToPlace[l].setY(675 - 100 * l * Math.sin(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
				imgvDominoToPlaceCrown[l][0].setX(1320+100*l*Math.cos(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
				imgvDominoToPlaceCrown[l][0].setY(675-100*l*Math.sin(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
				imgvDominoToPlaceCrown[l][1].setX(1352+100*l*Math.cos(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
				imgvDominoToPlaceCrown[l][1].setY(675-100*l*Math.sin(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
			}
		});
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				int lasti = i;
				imgvDominoPick[i][j].setOnMousePressed((MouseEvent e) -> {
					System.out.println(lasti);
					if(game.pick && game.playerPick(game.getCurrentPlayer(), lasti)) {

						imgvPawn[lasti][0].setImage(img[game.getCurrentPlayer()+7]);
						if (game.player.get(game.getCurrentPlayer()).selectedDominoPile.size() >= 2) {
							game.pick = false;
							buttonRotateRight.setVisible(true);
							buttonRotateLeft.setVisible(true);
							game.player.get(game.getCurrentPlayer()).resetSelectedDominoRotation();
							for (int l = 0; l < 2; l++) {
								imgvDominoToPlace[l].setX(1320+100*l*Math.cos(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
								imgvDominoToPlace[l].setY(675-100*l*Math.sin(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
								imgvDominoToPlace[l].setImage(img[switchTile(game.player.get(game.getCurrentPlayer()).selectedDominoPile.get(0).getTile(l).biome)]);
								imgvDominoToPlaceCrown[l][0].setX(1320+100*l*Math.cos(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
								imgvDominoToPlaceCrown[l][0].setY(675-100*l*Math.sin(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
								imgvDominoToPlaceCrown[l][1].setX(1352+100*l*Math.cos(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));
								imgvDominoToPlaceCrown[l][1].setY(675-100*l*Math.sin(Math.toRadians(game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)));

								if (game.player.get(game.getCurrentPlayer()).selectedDominoPile.get(0).getTile(l).numberOfCrown >= 1) {
									imgvDominoToPlaceCrown[l][0].setImage(img[11]);

									if (game.player.get(game.getCurrentPlayer()).selectedDominoPile.get(0).getTile(l).numberOfCrown == 2) {
										imgvDominoToPlaceCrown[l][1].setImage(img[11]);
									}
								}

							}
						}

						else {
							game.changePlayer();
							if (game.currentPlayer == 0) {
								for (int l = 0; l < 4; l++) {
									imgvPawn[l][1].setImage(imgvPawn[l][0].getImage());
									imgvPawn[l][0].setImage(null);
								}

								for (int m = 0; m < 4; m++) {
									for (int x = 0; x < 2; x++) {
										for (int l = 0; l < 2; l++) {
											imgvCrown[m][1][x][l].setImage(imgvCrown[m][0][x][l].getImage());
											imgvCrown[m][0][x][l].setImage(null);
											if (game.currentDraw.get(m).getTile(l).numberOfCrown > x) {
												imgvCrown[m][0][x][l].setImage(img[11]);
											}

										}
									}
								}

								for (int l = 0; l < 4; l++) {
									for (int m = 0; m < 2; m++) {
										imgvDominoPicked[l][m].setImage(imgvDominoPick[l][m].getImage());
										imgvDominoPick[l][m].setImage(img[switchTile(game.currentDraw.get(l).getTile(m).biome)]);
									}
								}
							}

						}
					}
				});
			}
		}
		
		// for every location on the kingdom
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int lasti = i;
				int lastj = j;
				imgvKingdom[i][j].setOnMousePressed((MouseEvent e) -> {
					int[] position = {lasti-4,-(lastj-4)};// use negative of y because of javafx inverting y axis
					if (!game.pick && game.player.get(game.getCurrentPlayer()).placeLastSelectedInKingdomAsTile(position,game.player.get(game.getCurrentPlayer()).desiredSelectedDominoRotation)) {
						//imgvKingdom[lasti][lastj].setImage(img[2]);
						game.changePlayer();
						if (game.currentPlayer == 0) {
							for (int l = 0; l < 4; l++) {
								imgvPawn[l][1].setImage(imgvPawn[l][0].getImage());
								imgvPawn[l][0].setImage(null);
							}

							for (int m = 0; m < 4; m++) {
								for (int x = 0; x < 2; x++) {
									for (int l = 0; l < 2; l++) {
										imgvCrown[m][1][x][l].setImage(imgvCrown[m][0][x][l].getImage());
										imgvCrown[m][0][x][l].setImage(null);
										if (game.currentDraw.get(m).getTile(l).numberOfCrown > x) {
											imgvCrown[m][0][x][l].setImage(img[11]);
										}
									}
								}
							}

							for (int l = 0; l < 4; l++) {
								for (int m = 0; m < 2; m++) {
									imgvDominoPicked[l][m].setImage(imgvDominoPick[l][m].getImage());
									imgvDominoPick[l][m].setImage(img[switchTile(game.currentDraw.get(l).getTile(m).biome)]);
								}
							}
						}
						cb.setValue("Joueur "+(game.getCurrentPlayer()+1));
						game.pick = true;//allow player to pick domino
						buttonRotateRight.setVisible(false);
						buttonRotateLeft.setVisible(false);
						imgvDominoToPlace[0].setImage(null);
						imgvDominoToPlace[1].setImage(null);
						imgvDominoToPlaceCrown[0][0].setImage(null);
						imgvDominoToPlaceCrown[0][1].setImage(null);
						imgvDominoToPlaceCrown[1][0].setImage(null);
						imgvDominoToPlaceCrown[1][1].setImage(null);
					}
				});
			}
		}


		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				imgvDominoPick[i][j].setImage(img[switchTile(game.currentDraw.get(i).getTile(j).biome)]);

			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 1; j++) {
				for (int l = 0; l < 2; l++) {
					System.out.println(game.currentDraw.get(i).getTile(l).numberOfCrown);
					System.out.println(game.currentDraw.get(i).dominoID);
					if (game.currentDraw.get(i).getTile(l).numberOfCrown >= 1) {
						imgvCrown[i][j][0][l].setImage(img[11]);

						if (game.currentDraw.get(i).getTile(l).numberOfCrown == 2) {
							imgvCrown[i][j][1][l].setImage(img[11]);
						}
					}
				}
			}
		}
	}

	private int switchTile(String biome) {
		int biomeID = 0;
		switch(biome) {
			case "Prairie": biomeID = 0;
				break;
			case "Mine": biomeID = 1;
				break;
			case "Foret": biomeID = 2;
				break;
			case "Montagne": biomeID = 3;
				break;
			case "Champs": biomeID = 4;
				break;
			case "Mer": biomeID = 5;
				break;
			case "king": biomeID = 6;
				break;
		}
		return biomeID;
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
