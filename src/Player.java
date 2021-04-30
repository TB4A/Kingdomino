import java.util.ArrayList;
import java.util.List;
public class Player {
	
	///////////////////////////////////////// attributes /////////////////////////////////////////////////////
		
	int playerID;
	String playerName;
	String playerKingColor;
	List<Domino> kingdom;
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
		this.desiredSelectedDominoRotation = 0;
		this.desiredSelectedDominoPosition = new int[2];
	}
	public void pick(int arg_playerPick) {
		
		
		
		
		
	}
	
	public void rotateSelectedDominoRight() {
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
	/*
	public void placeLastSelectedInKingdomAsTile(int[] position,int orientation) {
		// TODO Auto-generated method stub
		///tile 1
		Domino domino = this.selectedDominoPile.get(0);
		Domino tile1 = new ArrayList<>();

		tile1.add(domino.get(0));
		tile1.add(domino.get(1));
		tile1.add(Integer.toString(position[0]));
		tile1.add(Integer.toString(position[1]));
		
		//tile 2
		Domino tile2 = new ArrayList<>();

		tile2.add(domino.get(2));
		tile2.add(domino.get(3));
		tile2.add(Integer.toString(Math.toIntExact((position[0]+Math.round(Math.cos(Math.toRadians(this.desiredSelectedDominoRotation)))))));
		tile2.add(Integer.toString(Math.toIntExact(position[0]+Math.round(Math.sin(Math.toRadians(this.desiredSelectedDominoRotation))))));
		
		System.out.println(tile1);
		System.out.println(tile2);
		
		
	}
	*/
		
}
