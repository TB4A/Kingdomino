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
	List<List<String>> dominoPile;
	List<List<String>> currentDraw = new ArrayList<>() ;
	List<Player> player = new ArrayList<Player>();// initialize player list "containing the objects of the class Player"
	int[] playerOrder;
	
	
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
	}
	
	///////////////////////////////////////// methodes /////////////////////////////////////////////////////
	
	public void setCurrentDraw(List<List<String>> arg_setCurrentDraw){
		
		this.currentDraw = arg_setCurrentDraw;
	}

	public void setDominoPile(List<List<String>> arg_setDominoPile){
		
		this.dominoPile = arg_setDominoPile;
	}

	
	
	public static List<List<String>> createDrawPile() {
		// TODO Auto-generated method stub
			/// load csv and add domino status in the pile 
			List<List<String>> dominoPile = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader("dominos.csv"))) {
			    String line; // buffer reader next line
			    while ((line = br.readLine()) != null) { //while there is data to read
			    	String[] splitedLine = line.split(","); // chunk the line into segment of data "colones" separated in the csv by ","
			    	
			    	///// add missing data to the gathered csv line per line at runtime
			    	int n = splitedLine.length;
			        String[] values = new String[n+1] ; // if need to add one more col change with -- > new String[n+1]
			        for(int i = 0 ; i < 5; i++)       
			        	{
				        values[i] = splitedLine[i];
				        }
			        //[was used if we add additional columns]
			        //if (dominoPile.size() == 0) {values[5] = "PlayerID";} //if the pile is empty (so the first entry is still running) we use "Etat" as an header for the column
			        //else {values[5] = "available";}
			        
			        ///// print the pile line per line
			        dominoPile.add(Arrays.asList(values));// add the those chunks contained into an array of string "the chunks are of type strings" to the variable dominoPile which is an array list / a table
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
			return sufflePile(dominoPile);
			
		}
		
	
	
	
	
		public static List<List<String>> sufflePile(List<List<String>> dominoPile) { // shuffle the domino Pile while keeping on the header for clarity 
			//List<String> header = dominoPile.get(0);
			//System.out.println((header));
			dominoPile.remove(0);
			Collections.shuffle(dominoPile);
			//dominoPile.add(0, header); actually the header is useless so we just remove it 
			return dominoPile;
			}

		
		
		
		

		public void drawFromDrawPile() {
			// TODO Auto-generated method stub
			
			for(int i = 0 ; i< numberOfPlayer;i++) {
				currentDraw.add(i, dominoPile.get(0));
				dominoPile.remove(0);
				
			}
			
			setCurrentDraw(sortDraw(currentDraw)); // sort drafted dominos by their number using the sortDraw methode , then assign the sortedDraw to the currentDraw attribute 
		}


		public List<List<String>> sortDraw(List<List<String>> arg_currentDraw) { // order the current draw in domino ascending order
		// complexité algo 
		//Meilleur cas : O(n)
		//Pire cas :O(n^2)
			
		System.out.print("Debut du triage insersion de la liste --- > ");
		System.out.println(arg_currentDraw);
		Instant start = Instant.now();
		///// (arg_currentDraw.get(0)).get(4);
		for (int i = 1; i < arg_currentDraw.size(); i++) {
			
				List<String> ligneATrier = arg_currentDraw.get(i);
				int elementATrier = Integer.parseInt(ligneATrier.get(4));
				
				int j = i;
				while (j > 0 && Integer.parseInt((arg_currentDraw.get(j-1)).get(4))> elementATrier) 
					{
					arg_currentDraw.remove(j);
					arg_currentDraw.add(j,arg_currentDraw.get(j-1));
					j--;
					}
				arg_currentDraw.remove(j);
				arg_currentDraw.add(j,ligneATrier);
				

			}
		Instant end = Instant.now();
		System.out.println("Fin du triage insersion");
		long duration = Duration.between(start, end).toMillis();
		System.out.println("triage insersion a pris " + duration + " ms");
		return arg_currentDraw;
		}
		
		
		
		
		public void setRoundSelection(Object drawFromDrawPile) {
			// TODO Auto-generated method stub
			
		}
		
		public void setPlayerOrder() {
			// TODO Auto-generated method stub
			for(int i = 0 ; i<currentDraw.size();i++) {
				playerOrder[i] = Integer.parseInt(currentDraw.get(i).get(5));
			}	
							
		}
		
		public int[] getPlayerOrder() {
			return playerOrder;
		}
		
////////////////////////// interface //////////////////////////////////
		
		
		public void playerPick(int playerID) {
			//////////this method add the name of the player to the picked card and prevent it to be picked by an other player, before that it check if the picked card had been previously been picked if so it re asked 
			// TODO Auto-generated method stub
			int int_playerPick = 0;
			while (true) {
				System.out.println("player "+playerID+" :select a Domino between 0 and "+ (numberOfPlayer-1));
				try {
					@SuppressWarnings("resource")
					Scanner sc_playerPick= new Scanner(System.in); //System.in is a standard input stream.
					int_playerPick = sc_playerPick.nextInt(); //
					if(currentDraw.get(int_playerPick).get(5) == null) {
						break;
					}
				}
				catch(Exception e) {
					  //  Block of code to handle errors
					}
				/// check if valid

				System.out.println("invalid domino");
			}

			/// add the PlayerID of the player to the selected domino
			List<String> selectedDomino = currentDraw.get(int_playerPick);
			//selectedDomino.remove(5);// 5 is the index of the playerID column
			//System.out.println("colum removed");
			selectedDomino.set(5,Integer.toString(playerID));// 5 is the index of the playerID column
			//currentDraw.remove(int_playerPick);
			currentDraw.set(int_playerPick, selectedDomino);
			
			// send a copy of the domino to the player ""Inventory" DominoSelectionPile" to be placed the next round if he can place it
			player.get(playerID).addToSelectionPile(selectedDomino);
			System.out.println("finnishing Player "+playerID+" Pick....");
		}

		public void kingdomCheck(int i) {
			// TODO Auto-generated method stub
			
		}

		public void playerKingdomPlace(int playerID) {
			// TODO Auto-generated method stub
			int[] position = new int[2];
			int orientation;
			String orientationCardinal;
			
			while(true) {

				try{ // get and convert string cardinal location to int {1,2,3 or 4}
				@SuppressWarnings("resource")	
				Scanner sc_positionX= new Scanner(System.in);
				@SuppressWarnings("resource")
				Scanner sc_positionY= new Scanner(System.in);
				position[0] = sc_positionX.nextInt();
				position[1] = sc_positionY.nextInt();
				@SuppressWarnings("resource")
				Scanner sc_orientationCardinal= new Scanner(System.in);
				orientationCardinal = sc_orientationCardinal.nextLine();
				System.out.println("enter cardinal orientation centered on the given position (north , east ,south , west");
				if(orientationCardinal=="north") {orientation = 1;break;}
				else if(orientationCardinal=="east") {orientation = 2;break;}
				else if(orientationCardinal=="south") {orientation = 3;break;}
				else if(orientationCardinal=="west") {orientation = 4;break;}
				else{System.out.println("invalide check the cardinal direction is in lower case");}
				}
				catch(Exception e) {System.out.println("invalide enter a cardinal direction");} // if error do 	
			}
				player.get(playerID).placeLastSelectedInKingdom(position,orientation);
			
			
			//
			
			
		}


	}
