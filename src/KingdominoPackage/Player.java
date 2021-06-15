package KingdominoPackage;

import java.util.ArrayList;
import java.util.List;
public class Player {
	
	///////////////////////////////////////// attributes /////////////////////////////////////////////////////
		
	int playerID;
	String playerName;
	String playerKingColor;
	
	List<Domino> kingdom;
	List<Group> biomeGroupList;//contain every valid biome group that will be used to count points later on
	List<Domino> selectedDominoPile;
	
	int desiredSelectedDominoRotation;
	int[] desiredSelectedDominoPosition;
	///////////////////////////////////////// constructor /////////////////////////////////////////////////////
	public Player(int entry_playerID,String entry_playerName,String entry_playerKingColor) {
		// TODO Auto-generated constructor stub
	
		this.playerID = entry_playerID;
		this.playerName = entry_playerName;
		this.playerKingColor = entry_playerKingColor;
		this.selectedDominoPile = new ArrayList<>();
		this.kingdom = new ArrayList<>();
		this.biomeGroupList = new ArrayList<>();
		this.desiredSelectedDominoRotation = 0;
		this.desiredSelectedDominoPosition = new int[2];
	}
	public void pick(int arg_playerPick) {
		

	}
	
	public void rotateSelectedDominoRight() {////////to replace overwrite current cardinal input when GUI is setup
		this.desiredSelectedDominoRotation = desiredSelectedDominoRotation + 90 ;
		if ((desiredSelectedDominoRotation == -360) || (desiredSelectedDominoRotation == 360)){
			desiredSelectedDominoRotation = 0;
		}
	}
	
	public void rotateSelectedDominoLeft() {
		this.desiredSelectedDominoRotation = desiredSelectedDominoRotation - 90 ;
		if ((desiredSelectedDominoRotation == -360) || (desiredSelectedDominoRotation == 360)){
			desiredSelectedDominoRotation = 0;
		}
	}
	
	public void resetSelectedDominoRotation() {
		desiredSelectedDominoRotation = 90;
	}
	
	public void addToSelectionPile(Domino domino) {
		// TODO Auto-generated method stub
		System.out.println(playerName+" picked "+domino);
		this.selectedDominoPile.add(domino);
	}
	
	public boolean checkPlacementValidity(Domino testedDomino/* the domino that the player tries to check validity of*/,int x_tile0,int y_tile0,int x_tile1,int y_tile1) {
		// both tile need to be check at the same time if only one has manage to made a connection
		
		int[] x_tile = new int[2];
		x_tile[0] = x_tile0;
		x_tile[1] = x_tile1;
		
		int[] y_tile = new int[2];
		y_tile[0] = y_tile0;
		y_tile[1] = y_tile1;
		
		Tile[] testedDomino_tile = {testedDomino.tile0,testedDomino.tile1};// ini testdomino as array for fast loop access
		//int numberOfNeighbour = 0;
		//int addedPoints = 0;
		
		for (int side = 0;side<2;side++) { // pass on both tiles of a domino
			

			// check for overlaps
			for(int i = 0 ; i < this.kingdom.size();i++) {
				Domino dominoOfKingdom = kingdom.get(i);
				// tile of int side check
					// not overlapping anything
				if((dominoOfKingdom.tile0.x == x_tile[side]) && (dominoOfKingdom.tile0.y == y_tile[side])) {
					return false;
				}
				if((dominoOfKingdom.tile1.x == x_tile[side]) && (dominoOfKingdom.tile1.y == y_tile[side])) {
					return false;
				}
					//check for neighbour at a given y on the left and right of the Tile
				if((((dominoOfKingdom.tile0.x + 1== x_tile[side] || dominoOfKingdom.tile0.x - 1== x_tile[side] )))&&(dominoOfKingdom.tile0.y == y_tile[side])){
					if (dominoOfKingdom.tile0.biome.equals(testedDomino_tile[side].biome)) {return true;}
					
				}
				if((((dominoOfKingdom.tile1.x + 1== x_tile[side] || dominoOfKingdom.tile1.x - 1== x_tile[side] )))&&(dominoOfKingdom.tile0.y == y_tile[side])){
					if (dominoOfKingdom.tile1.biome.equals(testedDomino_tile[side].biome)) {return true;}
					
				}
				
			}
		}
		
		
		return false;
	}
	public void placeLastSelectedInKingdomAsTile(int[] position,int orientation) {
		// TODO Auto-generated method stub
		
		///Domino Pick during Last round
		Domino domino = this.selectedDominoPile.get(0);
		
		//ini placement coordinates
		
			//Tile0
		int x_tile0 = position[0];
		int y_tile0 = position[1];
		
			//Tile1
		int x_tile1 = Math.toIntExact((position[0]+Math.round(Math.cos(Math.toRadians(this.desiredSelectedDominoRotation)))));
		int y_tile1 = Math.toIntExact(position[0]+Math.round(Math.sin(Math.toRadians(this.desiredSelectedDominoRotation))));
		
		//check if placement coordinates are valid
		
		if (checkPlacementValidity(domino,x_tile0,y_tile0,x_tile1,y_tile1) == false) {System.out.println("non-valid placement");return;}
		
		// set coordinates of tile 1 and tile 2 if check succeeded
		
			//set tile 1 coordinates
		domino.tile0.setPosition(x_tile0,y_tile0); 
		
			//set tile 2 coordinates
		domino.tile1.setPosition(x_tile1,y_tile1);
		
		processBiomeGroup(domino,x_tile0,y_tile0,x_tile1,y_tile1);
		
		System.out.println(domino.tile0);
		System.out.println(domino.tile1);
		
		
	}
	
	// process Groups of a given tile by depending of it's neighbor pos 
	public boolean processBiomeGroup(Domino testedDomino/* the domino that the player tries to check validity of*/,int x_tile0,int y_tile0,int x_tile1,int y_tile1) {
		// both tile need to be check at the same time if only one has manage to made a connection
		Tile[] tile = {testedDomino.tile0,testedDomino.tile0};
		
		int[] x_tile = new int[2];
		x_tile[0] = x_tile0;
		x_tile[1] = x_tile1;
		
		int[] y_tile = new int[2];
		y_tile[0] = y_tile0;
		y_tile[1] = y_tile1;
		

		
		for (int side = 0;side<2;side++) { // pass on both tiles of a domino that was selected
			
			// check that both tile of the same domino are not same else put them in the same group
			if(testedDomino.tile0.biome == testedDomino.tile1.biome) {
				tile[1].setGroupHandleOfTile(tile[0].getGroupHandleOfTile());
				}
			

			for(int i = 0 ; i < this.kingdom.size();i++) { // run on all domino placed in the kingdom
				Domino dominoOfKingdom = kingdom.get(i);
				
					//check for neighbor if there are pass to neighbor group on our soon to be placed domino
				if((((dominoOfKingdom.tile0.x + 1== x_tile[side] || dominoOfKingdom.tile0.x - 1== x_tile[side] )))&&(dominoOfKingdom.tile0.y == y_tile[side])){
					tile[side].setGroupHandleOfTile(dominoOfKingdom.tile0.getGroupHandleOfTile()); // create/add or merge groups with tile current group
				}
				
				if((((dominoOfKingdom.tile1.x + 1== x_tile[side] || dominoOfKingdom.tile1.x - 1== x_tile[side] )))&&(dominoOfKingdom.tile0.y == y_tile[side])){
					tile[side].setGroupHandleOfTile(dominoOfKingdom.tile1.getGroupHandleOfTile());
				}
			}
		}
		
		return false;
	}

		
}

