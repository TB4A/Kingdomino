package KingdominoPackage;

import java.util.ArrayList;
import java.util.List;

public class Group {
	List<Tile> biomeGroup;// every Tile belonging to a given group will be stored here , the groups are dynamically defined and are recreated if multiple need to join
	
	Group(Tile tile){
		biomeGroup = new ArrayList<Tile>();
		biomeGroup.add(tile);
		// add this instance to the owning player GroupList
		
		
	}
	
	public void refreshTilesGroupOfCurrentGroup() {
		// TODO Auto-generated method stub
		for(Tile i : biomeGroup) {
			i.group = this;  
		};
	}
	
	public void addTileToGroup(Tile tileToAdd) {
		// TODO Auto-generated method stub
		biomeGroup.add(tileToAdd);
	}
}
