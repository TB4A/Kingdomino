package KingdominoPackage;

public class Tile{
	
///////////////////////////////////////// attributes /////////////////////////////////////////////////////
	
	int x;
	int y;
	int numberOfCrown; // is taking the higher multiplicator of neighboring biomes
	Domino parentDomino;
	String biome;
	Group group;
	
///////////////////////////////////////// constructor ////////////////////////////////////////////////////
	
	public Tile(Integer numberOfCrown,String biome,Domino parentDomino) {
		// TODO Auto-generated constructor stub
		this.numberOfCrown = numberOfCrown;
		this.biome = biome;
		this.parentDomino = parentDomino;
	}
	
///////////////////////////////////////// methodes ///////////////////////////////////////////////////////
	
	public void setPosition(int x, int y) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;
	}

	public Group getGroupHandleOfTile() {
		// TODO Auto-generated method stub
		//used to get the group of a valid neighboring Tiles
		
		if(group == null) {// if the neighboring tile as no group make one
			group = new Group(this);
			parentDomino.getOwner().biomeGroupList.add(group);// add group to player biomegrouplist
			}
		
		return group;
	}
	
	public void setGroupHandleOfTile(Group groupHandle) {// set a new grouphandle for the tile or set a new merged group of multiple tiles
		// TODO Auto-generated method stub
		// this methode set the group of the tile in the Tile class , and add the reference of tile to the assosiated KingdominoPackage.Group object
		if(group == null) {// The only case of a Tile being null is when it hasnt found any neighbor yet ,else it mean that it might have multiple valid neighbor 
			groupHandle.addTileToGroup(this);
			this.group = groupHandle;
			}
		else {
			mergeGroupHandleOfTile(this.group , groupHandle);
		}

	}
	
	public Group mergeGroupHandleOfTile(Group group1 , Group group2) {// merge group of Tile object with given KingdominoPackage.Group
		// 1.update groups inside used Tile Object 2.remove legacy KingdominoPackage.Group in the player group list
		Group newGroup = new Group(this);
		parentDomino.getOwner().biomeGroupList.add(newGroup);// add newgroup to player's biomegrouplist
		//1.update groups inside used Tile Object
		for(int i=0;i<group1.biomeGroup.size();i++) {
			newGroup.biomeGroup.add(group1.biomeGroup.get(i));//add tiles to the new KingdominoPackage.Group Object
			group1.biomeGroup.get(i).group = newGroup;//update all group arguments of old Tile object of old groups with new group
			}
		for(int i=0;i<group2.biomeGroup.size();i++) {
			newGroup.biomeGroup.add(group2.biomeGroup.get(i));//add tiles to the new KingdominoPackage.Group Object
			group1.biomeGroup.get(i).group = newGroup;//update all group arguments of old Tile object of old groups with new group
			}
		//2. unfinished remove legacy KingdominoPackage.Group in the player group list
		newGroup.refreshTilesGroupOfCurrentGroup(); // set group of every Tile belonging to the new group as the new group
		
		parentDomino.getOwner().biomeGroupList.remove(group1);// remove no longeur used group from player's biomegrouplist
		group1 = null;//remove ref and allow to group 1 and 2 to be collected by the garbage collector
		parentDomino.getOwner().biomeGroupList.remove(group2);
		group2 = null;

		return newGroup;
	}
}
