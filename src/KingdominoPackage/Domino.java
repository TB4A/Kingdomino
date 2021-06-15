import java.io.IOException;

public class Domino {
	String[] ImportedDominoData;// prompt only
	Integer ownerID;
	Player owner;
	Integer dominoID;
	Tile tile0;
	Tile tile1;
	///////////////////////////Constructor///////////////////////////
	public Domino(String[] importData_splitedLine){
		///// import redeable data for prompt only
		this.ImportedDominoData = importData_splitedLine ;
		
		///// initialize the owner of the domino "ownerID and ownerref"
		this.ownerID = null;
		
		///// initialize the number of the domino "dominoID"
		try {
			this.dominoID = Integer.parseInt(importData_splitedLine[4]) ;
		}
		
		catch (Exception e) {this.dominoID = null; }// the fist line of csv contain a characters and a such will cause an error when expecting an integer durring the pick phase
	
		///// initialize Tile 0 "the origin"
		try {
		tile0 = new Tile(Integer.parseInt(importData_splitedLine[0]),importData_splitedLine[1],this);// create an object tile containing the biome and the number of crowns 
		///// initialize Tile 1 "the orientation"
		tile1 = new Tile(Integer.parseInt(importData_splitedLine[2]),importData_splitedLine[3],this);
		}
		catch(Exception e) {}
	}

	public void setDominoID(Integer dominoID) {
		// TODO Auto-generated method stub
		this.dominoID = dominoID;
	}

	public Integer getDominoID() {
		// TODO Auto-generated method stub
		return this.dominoID;
	}

	public void setOwner(Player player) {
		// TODO Auto-generated method stub
		this.owner = player;
		this.ownerID = player.playerID;
	}
	
	public Player getOwner() {
		// TODO Auto-generated method stub
		return this.owner;
	}
	
	public void setTileCoordinate() {
		
		
	}
	
	public Tile getTile(int i) {	//use to gather Tile Data of the Domino
		// TODO Auto-generated method stub
		switch(i) {
		case 0:
			return this.tile0;
		case 1:
			return this.tile1;
		}
		return null;
	}
	
}
