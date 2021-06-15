package KingdominoPackage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Gamedata {
	
	///////////////////////////////////////// attributes /////////////////////////////////////////////////////
	
	static int numberOfPlayer;
	List<Domino> dominoPile;
	List<Domino> currentDraw = new ArrayList<>() ;
	List<Player> player = new ArrayList<Player>();// initialize player list "containing the objects of the class Player"
	int[] playerOrder;
	int currentPlayer;
	public boolean pick;
	
	
	///////////////////////////////////////// constructor /////////////////////////////////////////////////////
	public Gamedata(int entry_numberOfPlayer) {
		// TODO Auto-generated constructor stub
		
		Gamedata.numberOfPlayer = entry_numberOfPlayer ; // initialize the static attribute returning the number of player playing given by the players in the gameloop
		this.dominoPile = createDrawPile(); //initialize the dominopile by removing useless information inside the .csv file and by shuffling it 
		
		//ini the player order array
		this.playerOrder = new int[entry_numberOfPlayer];
		for (int i = 0 ;i<entry_numberOfPlayer;i++) {
			playerOrder[i] = i; /// as such by default playerOrder = {1,2,3,4... number of player}
		}
		// create player profiles
		 		/// create a list that will hold every new player object
		for (int i = 0 ;i<entry_numberOfPlayer;i++) {
			int playerID =  i;
			String playerName = "player"+i ;
			String playerKingColor = "Color"+i;
			player.add(new Player(playerID,playerName,playerKingColor));//add player data to the player object list to be able to access them using game.player("playerID"). 
			
		}
		currentPlayer = 0;
		pick = true;
	}
	
	///////////////////////////////////////// methodes /////////////////////////////////////////////////////
	
	public void setCurrentDraw(List<Domino> arg_setCurrentDraw){
		
		this.currentDraw = arg_setCurrentDraw;
	}

	public void setDominoPile(List<Domino> arg_setDominoPile){
		
		this.dominoPile = arg_setDominoPile;
	}

	
	
	public static List<Domino> createDrawPile() {
		// TODO Auto-generated method stub
			/// load csv and add domino status in the pile 
			List<Domino> dominoPile = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader("dominos.csv"))) {
			    String line; // buffer reader next line
			    while ((line = br.readLine()) != null) { //while there is data to read
			    	String[] splitedLine = line.split(","); // chunk the line into segment of data "colones" separated in the csv by ","
			    	
			    	Domino domino = new Domino(splitedLine);
			    	///// add missing data to the gathered csv line per line at runtime
			    	//int n = splitedLine.length;
			    	//Domino values = new Domino;//String[] values = new String[n+1] ; // if need to add one more col change with -- > new String[n+1]
			    	//for(int i = 0 ; i < 5; i++)       
			    	//  	{
			    	//     values[i] = splitedLine[i];
			    	//      }
			        //[was used if we add additional columns]
			        //if (dominoPile.size() == 0) {values[5] = "PlayerID";} //if the pile is empty (so the first entry is still running) we use "Etat" as an header for the column
			        //else {values[5] = "available";}
			        
			        ///// print the pile line per line
			        dominoPile.add(domino);// add the those chunks contained into an array of string "the chunks are of type strings" to the variable dominoPile which is an array list / a table
			    	//System.out.println(Arrays.toString(values));

			    }
			   //System.out.println(());
			    
			/////// just check for error /////////////
			} catch (FileNotFoundException e) {		//
				// TODO Auto-generated catch block	//
				e.printStackTrace();				//
			} catch (IOException e) {				//
				// TODO Auto-generated catch block	//
				e.printStackTrace();				//
			}										//
			//////////////////////////////////////////
	        System.out.println(numberOfPlayer);
			return shufflePile(dominoPile);
			
		}
		
	
	
	
	
		public static List<Domino> shufflePile(List<Domino> dominoPile) { // shuffle the domino Pile while keeping on the header for clarity
			//List<String> header = dominoPile.get(0);
			//System.out.println((header));
			dominoPile.remove(0);
			Collections.shuffle(dominoPile);
			//dominoPile.add(0, header); actually the header is useless so we just remove it 
			return dominoPile;
			}

		
		
		
		

		public void drawFromDrawPile() {
			// TODO Auto-generated method stub
			currentDraw.clear();// make sure the current draw pile is empty for the incoming draw
			for(int i = 0 ; i< numberOfPlayer;i++) {
				currentDraw.add(i, dominoPile.get(0));
				dominoPile.remove(0);
				
			}
			
			setCurrentDraw(sortDraw(currentDraw)); // sort drafted dominos by their number using the sortDraw methode , then assign the sortedDraw to the currentDraw attribute 
		}


		public List<Domino> sortDraw(List<Domino> arg_currentDraw) { // order the current draw in domino ascending order
		// complexité algo
		//Meilleur cas : O(n)
		//Pire cas :O(n^2)
			
		System.out.print("Debut du triage insertion de la liste --- > ");
		System.out.println(arg_currentDraw);
		Instant start = Instant.now();
		///// (arg_currentDraw.get(0)).get(4);
		for (int i = 1; i < arg_currentDraw.size(); i++) {
			
				Domino dominoATrier = arg_currentDraw.get(i);
				int elementATrier = (dominoATrier.getDominoID()); //int elementATrier = Integer.parseInt(ligneATrier.get(4));
				
				int j = i;
				while (j > 0 && ((arg_currentDraw.get(j-1)).getDominoID())> elementATrier)
					{
					arg_currentDraw.remove(j);
					arg_currentDraw.add(j,arg_currentDraw.get(j-1));
					j--;
					}
				arg_currentDraw.set(j,dominoATrier);
				

			}
		Instant end = Instant.now();
		System.out.println("Fin du triage insertion");
		long duration = Duration.between(start, end).toMillis();
		System.out.println("triage insertion a pris " + duration + " ms");
		return arg_currentDraw;
		}
		
		
		
		public void setPlayerOrder() {
			// TODO Auto-generated method stub
			System.out.println("setting Player Order as : ");
			for(int i = 0 ; i<currentDraw.size();i++) {
				this.playerOrder[i] = currentDraw.get(i).getOwner().playerID;
				System.out.println(this.playerOrder[i]+" - ");
			}

		}
		
		public int[] getPlayerOrder() {
			return playerOrder;
		}

		public void changePlayer() {
			currentPlayer++;
			if (currentPlayer >= numberOfPlayer) {
				currentPlayer = 0;
				drawFromDrawPile();
				setPlayerOrder();
			}
		}

		public int getCurrentPlayer() { return playerOrder[currentPlayer]; }
		
////////////////////////// interface //////////////////////////////////
		
		
		public boolean playerPick(int playerID,int playerPick/*one of the N of the CurrentDraw's card going from 0 to N*/) {
//////////this method add the name of the player to the picked card and prevent it to be picked by an other player, before that it check if the picked card had been previously been picked if so it re asked 
			// TODO Auto-generated method stub
				System.out.println("player "+playerID+" :select a Domino between 0 and "+ (numberOfPlayer-1));
				try {
					if(currentDraw.get(playerPick).getOwner() != null) {
						return false;
					}
				}
				catch(Exception e) {
					  //  Block of code to handle errors
					}

			/// add the PlayerID of the player to the selected domino
			Domino selectedDomino = currentDraw.get(playerPick);
			//selectedDomino.remove(5);// 5 is the index of the playerID column
			//System.out.println("colum removed");
			selectedDomino.setOwner(player.get(playerID));//selectedDomino.set(5,Integer.toString(playerID));// 5 is the index of the playerID column
			//currentDraw.remove(int_playerPick);
			currentDraw.set(playerPick, selectedDomino);
			
			// send a copy of the domino to the player ""Inventory" DominoSelectionPile" to be placed the next round if he can place it
			player.get(playerID).addToSelectionPile(selectedDomino);
			System.out.println("finnishing Player "+playerID+" Pick....");
			return true;
		}

		public Boolean kingdomCheck(int PlayerID) {
			// TODO Auto-generated method stub
			return false;
		}

		public void playerKingdomPlace(int playerID) {
			// TODO Auto-generated method stub
			int[] position = new int[2];
			int orientation = 90;
			String orientationCardinal;

			//// event update orientation "console" need to be replace with gui
			while(true) {
				try{
					// orientation
					System.out.println("enter cardinal orientation centered on the given position (north , east ,south , west)");
					@SuppressWarnings("resource")
					Scanner sc_orientationCardinal= new Scanner(System.in);
					orientationCardinal = sc_orientationCardinal.nextLine();
					System.out.println("player entered "+orientationCardinal);
					// cardinal to integer
					if(orientationCardinal.equals("west")) {orientation = 0;break;}
					else if(orientationCardinal.equals("north")){orientation = 90;break;}
					else if(orientationCardinal.equals("east")) {orientation = 180;break;}
					else if(orientationCardinal.equals("south")) {orientation = 270;break;}

					else{System.out.println("invalid check the cardinal direction is in lower case");}
				}
				catch(Exception e) {System.out.println("invalid enter a cardinal direction");} // if error do
			}

			try{ // get and convert string cardinal location to int {1,2,3 or 4}
				
				// x pos
				System.out.println("enter x pos");
				@SuppressWarnings("resource")	
				Scanner sc_positionX= new Scanner(System.in);
				position[0] = sc_positionX.nextInt();
				
				// y pos
				System.out.println("enter y pos");
				@SuppressWarnings("resource")
				Scanner sc_positionY= new Scanner(System.in);
				position[1] = sc_positionY.nextInt();
			}
			catch(Exception e) {}
				

				//player.get(playerID).placeLastSelectedInKingdomAsTile(position,orientation);
			
		
			//
			
			
		}


	}
