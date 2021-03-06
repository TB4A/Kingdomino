package KingdominoPackage;

import java.util.ArrayList;
import java.util.List;
public class Player {
	
	///////////////////////////////////////// attributes /////////////////////////////////////////////////////
		
	int playerID;
	String playerName;
	String playerKingColor;
	Domino kingDomino;
	List<Domino> kingdom;
	List<Group> biomeGroupList;//contain every valid biome group that will be used to count points later on
	List<Domino> selectedDominoPile	;
	
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
		this.desiredSelectedDominoRotation = 90;
		this.desiredSelectedDominoPosition = new int[2];
	
		// set a king domino that behave as domino with two tiles that are the same 
		String[] KingBaseDominoData = {"0","king","0","king","0"};
		this.kingDomino = new Domino(KingBaseDominoData);
		this.kingDomino.setOwner(this);
		
		// set the two tiles of the king domino on the same tile for compabilty overlap check
		this.kingDomino.tile0.x = 0 ; this.kingDomino.tile1.x = 0 ;
		this.kingDomino.tile0.y = 0 ; this.kingDomino.tile1.y = 0 ;
		
		this.kingdom.add(kingDomino);
		
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
	
	private boolean checkPlacementValidity(Domino testedDomino/* the domino that the player tries to check validity of*/,int x_tile0,int y_tile0,int x_tile1,int y_tile1) {
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
		int maxXoutwardvalue = 0;
		int minXoutwardvalue = 0;
		int maxYoutwardvalue = 0;
		int minYoutwardvalue = 0;
		
		// check if after placement the board will be bigger in size than 5x5
		for (int side = 0;side<2;side++) { // pass on both tiles of a domino	
			// check for overlaps
			for(int i = 0 ; i < this.kingdom.size();i++) {
				Domino dominoOfKingdom = kingdom.get(i);
				System.out.println("Procesing max size with tile "+kingdom.get(i).getTile(side).biome+ " at x = "+kingdom.get(i).getTile(side).x+" ,y = "+kingdom.get(i).getTile(side).y);
				// tile of int side check
					// not overlapping anything

				if(maxXoutwardvalue < dominoOfKingdom.getTile(side).x) {
					maxXoutwardvalue = dominoOfKingdom.getTile(side).x ;
					}
				
				if(minXoutwardvalue > dominoOfKingdom.getTile(side).x) {
					minXoutwardvalue = dominoOfKingdom.getTile(side).x ;
					}
				
				if(maxYoutwardvalue < dominoOfKingdom.getTile(side).x) {
					maxYoutwardvalue = dominoOfKingdom.getTile(side).x ;
					}
				
				if(minYoutwardvalue > dominoOfKingdom.getTile(side).x) {
					maxYoutwardvalue = dominoOfKingdom.getTile(side).x ;
					}
			}

		}
		
		for (int side = 0;side<2;side++) { 
			if(maxXoutwardvalue < x_tile[side]) {
				maxXoutwardvalue = x_tile[side] ;
				}
			if(minXoutwardvalue > x_tile[side]) {
				minXoutwardvalue = x_tile[side] ;
				}
			if(maxYoutwardvalue < y_tile[side]) {
				maxYoutwardvalue = y_tile[side] ;
				}
			if(minYoutwardvalue > y_tile[side]) {
				minYoutwardvalue = y_tile[side] ;
				}
			System.out.println("minYoutwardvalue : "+minYoutwardvalue);
			System.out.println("minYoutwardvalue : "+maxYoutwardvalue);
			if(maxXoutwardvalue+Math.abs(minXoutwardvalue) > 4) {
				System.out.println("kingdom will be too big on the x axis");return false;}
			if(maxYoutwardvalue+Math.abs(minYoutwardvalue) > 4) {
				System.out.println("kingdom will be too big on the y axis");return false;}
		}
		
		// for both side of every domino in the kingdom check for overlap
		for (int side = 0;side<2;side++) { // pass on both tiles of a domino	
			// check for overlaps
			for(int i = 0 ; i < this.kingdom.size();i++) {
				Domino dominoOfKingdom = kingdom.get(i);
				System.out.println("Procesing overlap with tile "+kingdom.get(i).getTile(side).biome+ " at x = "+kingdom.get(i).getTile(side).x+" ,y = "+kingdom.get(i).getTile(side).y);
				// tile of int side check
					// not overlapping anything
				System.out.println("...with x = "+ x_tile[side] +" ,y = "+y_tile[side]);
				if((dominoOfKingdom.tile0.x == x_tile[side]) && (dominoOfKingdom.tile0.y == y_tile[side])) {
					System.out.println("overlaping on tile 0");
					return false;
					}
				if((dominoOfKingdom.tile1.x == x_tile[side]) && (dominoOfKingdom.tile1.y == y_tile[side])) {
					System.out.println("overlaping on tile 1");
					return false;
					}
				
				}
		}
		// for both side of every domino in the kingdom check for neighbor
		for (int side = 0;side<2;side++) { 
			for(int i = 0 ; i < this.kingdom.size();i++) {
				Domino dominoOfKingdom = kingdom.get(i);
				//check for neighbour at a given y on the left and right of Tile0 and Tile1
				if((((dominoOfKingdom.tile0.x + 1== x_tile[side] || dominoOfKingdom.tile0.x - 1== x_tile[side] )))&&(dominoOfKingdom.tile0.y == y_tile[side])){
					System.out.println("checking bione validity of placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile1.biome);
					if (dominoOfKingdom.tile0.biome.equals(testedDomino_tile[side].biome) || dominoOfKingdom.tile1.biome.equals("king")) {
						System.out.println("placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile0.biome);
						return true;}
					
				}
				if((((dominoOfKingdom.tile1.x + 1== x_tile[side] || dominoOfKingdom.tile1.x - 1== x_tile[side] )))&&(dominoOfKingdom.tile1.y == y_tile[side])){
					System.out.println("checking bione validity of placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile1.biome);
					if (dominoOfKingdom.tile1.biome.equals(testedDomino_tile[side].biome) || dominoOfKingdom.tile1.biome.equals("king")) {
						System.out.println("placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile1.biome);
						return true;}	
				}
				//check for neighbour at a given x up and down of Tile0 and Tile1
				if((((dominoOfKingdom.tile0.y + 1== y_tile[side] || dominoOfKingdom.tile0.y - 1== y_tile[side] )))&&(dominoOfKingdom.tile0.x == x_tile[side])){
					System.out.println("checking bione validity of placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile1.biome);
					if (dominoOfKingdom.tile0.biome.equals(testedDomino_tile[side].biome) || dominoOfKingdom.tile1.biome.equals("king")) {
						System.out.println("placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile0.biome);
						return true;}
					
				}
				if((((dominoOfKingdom.tile1.y + 1== y_tile[side] || dominoOfKingdom.tile1.y - 1== y_tile[side] )))&&(dominoOfKingdom.tile1.x == x_tile[side])){
					System.out.println("checking bione validity of placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile1.biome);
					if (dominoOfKingdom.tile1.biome.equals(testedDomino_tile[side].biome) || dominoOfKingdom.tile1.biome.equals("king")) {
						System.out.println("placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile1.biome);
						return true;}
				}
			}
		}
		
		/*
		for (int side = 0;side<2;side++) {
		// check if one of the side of a domino is touching the kingTile if so , the domino can be place
		if((kingTile.x + 1 == x_tile[side] || kingTile.x - 1== x_tile[side] )&&(kingTile.y == y_tile[side])){
			System.out.println("y_tile : "+ y_tile[side]);
			System.out.println("horizontal king neighbor");
			return true;}
		
		if((kingTile.y + 1 == y_tile[side] || kingTile.y - 1== y_tile[side] )&&(kingTile.x == x_tile[side])){
			System.out.println("y_tile : "+ x_tile[side]);
			System.out.println("vertival king neighbor");
			return true;}
		}
		System.out.println("y_tile1 : "+ y_tile[1]);
		System.out.println("x_tile1 : "+ x_tile[1]);
		System.out.println("y_tile0 : "+ y_tile[0]);
		System.out.println("x_tile0 : "+ x_tile[0]);
		*/
		System.out.println("CheckPlacement : no neighbor found");
		
		return false;
	}
	public boolean placeLastSelectedInKingdomAsTile(int[] position,int orientation) {
		// TODO Auto-generated method stub
		
		///Domino Pick during Last round
		Domino domino = this.selectedDominoPile.get(0);
		
		//ini placement coordinates
		
			//Tile0
		int x_tile0 = position[0];
		System.out.println(x_tile0 +" was passed as x value for placement of tile0");
		int y_tile0 = position[1];
		System.out.println(y_tile0 +" was passed as y value for placement of tile0");
		
			//Tile1
		int x_tile1 = x_tile0 + Math.toIntExact(Math.round(Math.cos(Math.toRadians(this.desiredSelectedDominoRotation)))); // position relative to base Tile
		int y_tile1 = y_tile0 + Math.toIntExact(Math.round(Math.sin(Math.toRadians(this.desiredSelectedDominoRotation))));
		
		//check if placement coordinates are valid
		
		if(checkPlacementValidity(domino,x_tile0,y_tile0,x_tile1,y_tile1) == false) {System.out.println("non-valid placement");return false;}
		
		// set coordinates of tile 1 and tile 2 if check succeeded
		
			//set tile 1 coordinates
		domino.tile0.setPosition(x_tile0,y_tile0); 
		
			//set tile 2 coordinates
		domino.tile1.setPosition(x_tile1,y_tile1);
		
		processBiomeGroup(domino,x_tile0,y_tile0,x_tile1,y_tile1);
		
		System.out.println("placing domino baseTile : "+domino.tile0+" of biome "+domino.tile0.biome+" at x = "+domino.tile0.x+" at y = "+domino.tile0.y );
		System.out.println("placing domino orientedTile : "+domino.tile1+" of biome "+domino.tile1.biome+" at x = "+domino.tile1.x+" at y = "+domino.tile1.y );
		System.out.println(domino.tile1);
		
		this.kingdom.add(domino); // finaly add the domino to the kingdom
		
		this.selectedDominoPile.remove(0); // remove placed domino from selectedDominoPile a new domino need to be added to the pile to pusue next placement 
		System.out.print("removed placed domino from selectedDominoPile, a new domino need to be added to the pile to pursue next placement on next turn ");
		return true;
		
		
	}
	
	// process Groups of a given tile by depending of it's neighbor pos 
	public boolean processBiomeGroup(Domino testedDomino/* the domino that the player tries to check validity of*/,int x_tile0,int y_tile0,int x_tile1,int y_tile1) {
		// both tile need to be check at the same time if only one has manage to made a connection
		Tile[] testedDomino_tile = {testedDomino.tile0,testedDomino.tile1};
		
		int[] x_tile = new int[2];
		x_tile[0] = x_tile0;
		x_tile[1] = x_tile1;
		
		int[] y_tile = new int[2];
		y_tile[0] = y_tile0;
		y_tile[1] = y_tile1;
		
		
		System.out.println(".......1.......Geting GROUP");
		
		
		for (int side = 0;side<2;side++) { 
			for(int i = 0 ; i < this.kingdom.size();i++) {
				Domino dominoOfKingdom = kingdom.get(i);
				//check for neighbour at a given y on the left and right of Tile0 and Tile1
				if((((dominoOfKingdom.tile0.x + 1== x_tile[side] || dominoOfKingdom.tile0.x - 1== x_tile[side] )))&&(dominoOfKingdom.tile0.y == y_tile[side])){
					System.out.println("checking bione validity of placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile1.biome);
					if (dominoOfKingdom.tile0.biome.equals(testedDomino_tile[side].biome) ) {
						System.out.println("Group : placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile0.biome);
						testedDomino_tile[side].setGroupHandleOfTile(dominoOfKingdom.tile0.getGroupHandleOfTile()); // create/add or merge groups with tile current group
						}
					
				}
				if((((dominoOfKingdom.tile1.x + 1== x_tile[side] || dominoOfKingdom.tile1.x - 1== x_tile[side] )))&&(dominoOfKingdom.tile1.y == y_tile[side])){
					System.out.println("checking bione validity of placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile1.biome);
					if (dominoOfKingdom.tile1.biome.equals(testedDomino_tile[side].biome) ) {
						System.out.println("Group : placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile1.biome);
						testedDomino_tile[side].setGroupHandleOfTile(dominoOfKingdom.tile0.getGroupHandleOfTile()); // create/add or merge groups with tile current group	
					}	
				}
				//check for neighbour at a given x up and down of Tile0 and Tile1
				if((((dominoOfKingdom.tile0.y + 1== y_tile[side] || dominoOfKingdom.tile0.y - 1== y_tile[side] )))&&(dominoOfKingdom.tile0.x == x_tile[side])){
					System.out.println("checking bione validity of placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile1.biome);
					if (dominoOfKingdom.tile0.biome.equals(testedDomino_tile[side].biome) ) {
						System.out.println("Group : placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile0.biome);
						testedDomino_tile[side].setGroupHandleOfTile(dominoOfKingdom.tile0.getGroupHandleOfTile()); // create/add or merge groups with tile current group	
					}
					
				}
				if((((dominoOfKingdom.tile1.y + 1== y_tile[side] || dominoOfKingdom.tile1.y - 1== y_tile[side] )))&&(dominoOfKingdom.tile1.x == x_tile[side])){
					System.out.println("checking bione validity of placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile1.biome);
					if (dominoOfKingdom.tile1.biome.equals(testedDomino_tile[side].biome) ) {
						System.out.println("Group : placed Tile of biome "+ testedDomino_tile[side].biome +" of side " +side+ " is valid agains "+dominoOfKingdom.tile1.biome);
						testedDomino_tile[side].setGroupHandleOfTile(dominoOfKingdom.tile0.getGroupHandleOfTile()); // create/add or merge groups with tile current group	
					}
				}
			}
		}
		/*
		for (int side = 0;side<2;side++) { // pass on both tiles of a domino that was selected
			System.out.println("......2........Geting GROUP");
			// check that both tile of the same domino are not same else put them in the same group
			if(testedDomino.tile0.biome == testedDomino.tile1.biome) {
				tile[1].setGroupHandleOfTile(tile[0].getGroupHandleOfTile());
				}
			

			for(int i = 0 ; i < this.kingdom.size();i++) { // run on all domino placed in the kingdom
				Domino dominoOfKingdom = kingdom.get(i);
				
					//check for neighbor if there are pass to neighbor group on our soon to be placed domino
				if((((dominoOfKingdom.tile0.x + 1== x_tile[side] || dominoOfKingdom.tile0.x - 1== x_tile[side] )))&&(dominoOfKingdom.tile0.y == y_tile[side]&&(tile[side].biome == dominoOfKingdom.tile0.biome))){
					System.out.println("......3........Geting GROUP");
					tile[side].setGroupHandleOfTile(dominoOfKingdom.tile0.getGroupHandleOfTile()); // create/add or merge groups with tile current group
				}
				
				if((((dominoOfKingdom.tile1.x + 1== x_tile[side] || dominoOfKingdom.tile1.x - 1== x_tile[side] )))&&(dominoOfKingdom.tile0.y == y_tile[side]&&(tile[side].biome == dominoOfKingdom.tile0.biome))){
					System.out.println("......3........Geting GROUP");
					tile[side].setGroupHandleOfTile(dominoOfKingdom.tile1.getGroupHandleOfTile());
				}
			}
		}
		*/
		return false;
	}

	public int computePlayerScore() {
		int score = 0;
		for (Group i_group:biomeGroupList) {
			System.out.println("Computing score of "+i_group);
			int totalCrown = 0;
			for (Tile j_tile:i_group.biomeGroup) { // for every tiles in the group we check how many crowns they are in total
				totalCrown =+ j_tile.numberOfCrown;
			}
			score =+ totalCrown*i_group.biomeGroup.size();// multiply the number of crown by the size of the group and sum all for every group
		}
		
		
		return score;
		
	}
}


