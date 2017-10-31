
package mancala;

import java.io.*;

/** Takes, sets, and enforces rules.
 * 
 * @author MancalaTeam
 * 
 * Methods to make:
 * ReadGameList()
 */

public class Rules {
	
	private String name;
	private int rowAmount;
	private int binsPerRow;
	private int seedsPerBin;
	private boolean hasSidebins;
	
	private int gameListSize = 3;
	private GameType[] gameList;
	
	/**
	 * Sets up the rules class.
	 */
	public Rules () {
		gameList = new GameType[gameListSize];
		gameList[0] = new GameType(2,6,4,true,"WARI");
		gameList[1] = new GameType(5,6,4,true,"5ROW");
		gameList[2] = new GameType(2,6,4,true,"MANYSEED");
		// Will set array of game types from text file
	}
	
	/**
	 * Searches through the games listed for one of a given name
	 * @param gameName name of game to find rules for
	 * @return ruleSet
	 */
	public void getGameRules (String gameName) {
		// search through array of game types for one with given name
		int gameSelected = 0;
		gameName = gameName.toUpperCase();
		for (int i=0; i<gameListSize; i++) {
			
			if (gameName.equals(gameList[i].GetName())) {
				gameSelected = i;
				break;
			}
		}
		rowAmount = gameList[gameSelected].GetRowAmount();
		binsPerRow = gameList[gameSelected].GetBinsPerRow();
		seedsPerBin = gameList[gameSelected].GetSeedsPerBin();
		hasSidebins = gameList[gameSelected].CheckSideBins();
		name = gameList[gameSelected].GetName();
	}
	
	/**
	 * Sets up rules for game based on given parameters.
	 * @param rows number of rows
	 * @param columns number of columns
	 * @param startBeans number of beans per hole (at start)
	 * @param sideBins whether has side bins or not
	 */
	public void setCustomRules (int rows, int columns, int startBeans, boolean sideBins) {
		rowAmount = rows;
		binsPerRow = columns;
		seedsPerBin = startBeans;
		hasSidebins = sideBins;
	}
	
	/**
	 * Displays tutorial?
	 */
	public void ShowTutorial () {
		// put work here
	}
	
	/**
	 * @return amount of rows for current game
	 */
	public int GetRowAmount() {
		return rowAmount;
	}
	
	/**
	 * @return amount of bins per row for current game
	 */
	public int GetBinsPerRow() {
		return binsPerRow;
	}
	
	/**
	 * @return number of seeds per bin for current game
	 */
	public int GetSeedsPerBin() {
		return seedsPerBin;
	}
	
	/**
	 * @return True if has sideBins, false if not
	 */
	public boolean CheckSideBins() {
		return hasSidebins;
	}
	
	
	/**
	 * @return The name of the game
	 */
	public String GetName() {
		return name;
	}
	
	private static final String FILENAME = "assets/GameList.txt";
	
	/**
	 * Under Construction
	 */
	public void ReadGameList() {
		BufferedReader br = null;
		FileReader fr = null;
		
		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			
			String CurrentLine;
			
			while((CurrentLine = br.readLine()) != null) {
				System.out.println(CurrentLine);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
			try {
				if(br != null) {
					br.close();
				}
				if(fr != null) {
					fr.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}
}

/**
 * Class to be used only by Rules. 
 * Organizes rules for given games.
 * 
 * @author MancalaTeam
 *
 */

class GameType {
	
	private String name;
	private int rowAmount;
	private int binsPerRow;
	private int seedsPerBin;
	private boolean hasSidebins;
	
	/**
	 * @param rows number of rows
	 * @param columns number of columns
	 * @param startBeans number of beans per hole
	 * @param sideBins whether has sidebins or not
	 */
	public GameType (int rows, int columns, int startBeans, boolean sideBins, String gameName) {
		name = gameName;
		rowAmount = rows;
		binsPerRow = columns;
		seedsPerBin = startBeans;
		hasSidebins = sideBins;
	}
	
	/**
	 * @return amount of rows for current game
	 */
	public int GetRowAmount() {
		return rowAmount;
	}
	
	/**
	 * @return amount of bins per row for current game
	 */
	public int GetBinsPerRow() {
		return binsPerRow;
	}
	
	/**
	 * @return number of seeds per bin for current game
	 */
	public int GetSeedsPerBin() {
		return seedsPerBin;
	}
	
	/**
	 * @return whether game has side bins
	 */
	public boolean CheckSideBins() {
		return hasSidebins;
	}
	
	/**
	 * @return name of the game
	 */
	public String GetName() {
		return name;
	}
}