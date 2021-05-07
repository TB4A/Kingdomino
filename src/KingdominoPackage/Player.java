package KingdominoPackage;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	///////////////////////////////////////// attributes /////////////////////////////////////////////////////
		
	int playerID;
	String playerName;
	String playerKingColor;
	List<List<String>> kingdom;
	List<List<String>> selectedDominoPile;
	
	///////////////////////////////////////// constructor /////////////////////////////////////////////////////
	public Player(int entry_playerID,String entry_playerName,String entry_playerKingColor) {
		// TODO Auto-generated constructor stub
	
		this.playerID = entry_playerID;
		this.playerName = entry_playerName;
		this.playerKingColor = entry_playerKingColor;
		this.selectedDominoPile = new ArrayList<>();
		this.kingdom = new ArrayList<>();
	}
	public void pick(int arg_playerPick) {
		
		
		
		
		
	}
	public void addToSelectionPile(List<String> Domino) {
		// TODO Auto-generated method stub
		System.out.println(playerName+" picked "+Domino);
		this.selectedDominoPile.add(Domino);
	}
	
	public void placeLastSelectedInKingdom(int[] position, int orientation) {
		// TODO Auto-generated method stub
		
		
	}
}
