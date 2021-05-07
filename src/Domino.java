import java.io.IOException;

public class Domino {
	String[] ImportedDominoData;// prompt only
	Integer ownerID;
	Integer dominoID;
	///////////////////////////Constructor///////////////////////////
	public Domino(String[] importData_splitedLine){
		///// import redeable data for prompt only
		this.ImportedDominoData = importData_splitedLine ;
		
		///// initialize the owner of the domino "ownerID"
		this.ownerID = null;
		
		///// initialize the number of the domino "dominoID"
		try {
			this.dominoID = Integer.parseInt(importData_splitedLine[4]) ;
		}
		
		catch (Exception e) {this.dominoID = null; }// the fist line of csv contain a characters and a such will cause an error when expecting an integer durring the pick phase
	
		///// initialize Tile 0 "the origin"
		try {
		Tile tile0 = new Tile(Integer.parseInt(importData_splitedLine[0]),importData_splitedLine[1]);// create an object tile containing the biome and the number of crowns 
		///// initialize Tile 1 "the orientation"
		Tile tile1 = new Tile(Integer.parseInt(importData_splitedLine[2]),importData_splitedLine[3]);
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

	public void setOwner(Integer playerID) {
		// TODO Auto-generated method stub
		this.ownerID = playerID;
	}
	
	public Integer getOwner() {
		// TODO Auto-generated method stub
		return this.ownerID;
	}
	
}
