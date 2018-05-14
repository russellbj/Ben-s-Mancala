package mancala;

import java.awt.FlowLayout;
import java.util.*;
import java.awt.GridLayout;
import java.io.*;
import javax.swing.*;

/** Takes, sets, and enforces rules.
 * Holds all the game types.
 * Sets the current game type.
 * 
 * @author Joel, Robbie
 * 
 * Methods to make:
 * ReadGameList()
 */

public class Rules {
	
	// Local variables used for current game type
	private static Rules instance = null;
	private String name;
	private boolean hasSidebins, counterclockwise;
	private int pointsToWin, rowAmount, binsPerRow, seedsPerBin;
	
	private int gameListSize = 4;
	private GameType[] gameList;
	private GameType currentGamePlayed;
	
	String gamesText = "2 6 4 true WARI true;"
			+ "2 6 4 true OWARE true;"
			+ "5 6 4 true 5ROW true;"
			+ "2 6 10 true MANYSEED true;"
			+ "2 6 4 false BINLESS true;"
			+ "2 6 5 false VAI_LUNG_THLAN false;"
			+ "2 7 5 false SONGO false;"
			+ "2 5 10 false ADJI_BOTO true";
	
	/**
	 * Sets up the rules class.
	 */
	public Rules () {
		// open file
		// loop through file line-by-line
		String[] gamesTextLines = gamesText.split(";");
		gameListSize = gamesTextLines.length;
		gameList = new GameType[gameListSize];
		for (int i = 0; i< gamesTextLines.length; i++) {
			gameList[i] = TranslateLine(gamesTextLines[i]);
		}
	}
	
	public Rules(String gameName){
		String[] gamesTextLines = gamesText.split(";");
		gameListSize = gamesTextLines.length;
		gameList = new GameType[gameListSize];
		for (int i = 0; i< gamesTextLines.length; i++) {
			GameType candidateGame = TranslateLine(gamesTextLines[i]);
			if(candidateGame.GetName().equals(gameName)){
				currentGamePlayed=candidateGame;
				break;
			}
		}
	}
	
	/**
	 * @param toTranslate line of text to be turned into game
	 * @return the game that has been created from toTranslate
	 */
	public GameType TranslateLine(String toTranslate) {
		GameType game;
		int numRows, numBins, numSeeds, winPoints = 0;
		boolean sideBins= false;
		boolean counterclockwise = false;
		String name = "notgame";
		// rows, bins, seeds, sidebins, name, win
		
		String[] gameStuff = toTranslate.split(" ");
		// add tests to make sure is actually of proper type
		numRows = Integer.parseInt(gameStuff[0]);
		numBins = Integer.parseInt(gameStuff[1]);
		numSeeds = Integer.parseInt(gameStuff[2]);
		if (gameStuff[3].toUpperCase().equals("TRUE")) {
			sideBins = true;
		}
		if (gameStuff[5].toUpperCase().equals("TRUE")) {
			counterclockwise = true;
		}
		name = gameStuff[4];
		game = new GameType(numRows,numBins,numSeeds,sideBins,name,counterclockwise);
		return game;
	}
	
	/**
	 * Searches through the games listed for one of a given name
	 * and sets local variable values based on found game.
	 * If no game is found, load default "Wari".
	 * @param gameName name of game to find rules for
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
		pointsToWin = GetPointsToWin(rowAmount,binsPerRow,seedsPerBin);
		counterclockwise=gameList[gameSelected].IsCounterClockwise();
	}
	
	/**
	 * Sets up rules for game based on given parameters.
	 * @param rows number of rows
	 * @param columns number of columns
	 * @param startBeans number of beans per hole (at start)
	 * @param sideBins whether has side bins or not
	 * @param ccw whether sowing is done in counterclockwise direction
	 */
	public void setCustomRules (int rows, int columns, int startBeans, boolean sideBins, int winPoints, boolean ccw) {
		rowAmount = rows;
		binsPerRow = columns;
		seedsPerBin = startBeans;
		hasSidebins = sideBins;
		pointsToWin = (rowAmount*binsPerRow*seedsPerBin)/2 +1;
		counterclockwise = ccw;
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
	 * @return True when has sideBins, false if not
	 */
	public boolean CheckSideBins() {
		return hasSidebins;
	}
	
	/**
	 * 
	 * @return True when sowing done in counterclockwise direction, false if done clockwise
	 */
	public boolean getCounterclockwise() {
		return counterclockwise;
	}
	
	
	/**
	 * @return The name of the game
	 */
	public String GetName() {
		return name;
	}
	
	/**
	 * @return points required for a player to win, given the board type
	 */
	public int GetPointsToWin(int rows, int bins, int seeds) {
		return (rows*bins*seeds)/2 +1;
	}
	
	public String[] BreakText(String text) {
		String[] gameStuff = text.split(";");
		return gameStuff;
	}
	
	/**
	 * @param fileName the name of the file to be read
	 * @return an ArrayList of the lines of text from the file
	 */
	public ArrayList<String> ReadTextFile(String fileName) {
		ArrayList<String> textLines = null;
		BufferedReader br = null;
		FileReader fr = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			String CurrentLine;
			
			while((CurrentLine = br.readLine()) != null) {
				textLines.add(CurrentLine);
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
		return textLines;
	}
	
	/**
	 * Does important stuff regarding the Rules() class
	 */
	public void important() {
		System.out.println("\nWhy would you call this method?\nIt doesn't do anything except print this.");
	}
	
	public static Rules getInstance(){
		if(instance == null) {
			instance = new Rules();
		}
		return instance;
	}
}

class LegalMoveChecker {
	public LegalMoveChecker getLegalMoveChecker(String gameName){
		switch (gameName) {
			case "Wari": 
				return new WariLegalMoveChecker();
			default: 
				return new LegalMoveChecker(); 
		}
	}
}

class WariLegalMoveChecker extends LegalMoveChecker {
	public WariLegalMoveChecker() {
		int[][] gameBoard = new int[2][6];
	}
	// checks if move is legal 
	public boolean checkMove(int[][] board, int[] hole, boolean player1) {
		int sum = 0; 
		int newSum = 0;  
		if(hole[0] == 0 && player1 == false) {
			return false;
		}
		if(hole[0] == 1 && player1 == true) {
			return false; 
		}
		if(player1 == true) {
			for(int i = 0; i < 6; i++) {
				sum += board[1][i];
			}
		}
		if(player1 == false) {
			for(int i = 0; i < 6; i++) {
				sum += board[0][i];
			}
		}
		//we will make the move here 
		if(player1 == true) {
			for(int i = 0; i < 6; i++) {
				newSum += board[1][i];
			}
		}
		if(player1 == false) {
			for(int i = 0; i < 6; i++) {
				newSum += board[0][i];
			}
		}
		if (newSum != sum) {
			return true; 
		}else {
			return false; 
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
	private int pointsToWin;
	private boolean counterclockwise;
	
	
	/**
	 * give parameters for game
	 * @param rows number of rows
	 * @param columns number of columns
	 * @param startBeans number of beans per hole
	 * @param sideBins whether has sidebins or not
	 * @param ccw whether beans are sowed in counterclockwise direction
	 */
	public GameType (int rows, int columns, int startBeans, boolean sideBins, String gameName, boolean ccw) {
		SetUp (rows,columns,startBeans,sideBins,gameName,ccw);
	}

	/**
	 * Sets the game rules from given parameters
	 * @param rows number of rows
	 * @param columns number of columns
	 * @param startBeans number of beans per hole
	 * @param sideBins whether has sidebins or not
	 */
	private void SetUp (int rows, int columns, int startBeans, boolean sideBins, String gameName, boolean ccw) {
		name = gameName;
		rowAmount = rows;
		binsPerRow = columns;
		seedsPerBin = startBeans;
		hasSidebins = sideBins;
		pointsToWin=GetPointsToWin(rowAmount,binsPerRow,seedsPerBin);
		counterclockwise=ccw;
	}
	
	/**
	 * Takes a line of text and creates a game type out of it.
	 * @param textLine
	 */
	public GameType (String textLine) {
		
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
	/**
	 * 
	 * @return points required for a player to win, given the board type
	 */
	public int GetPointsToWin(int rows, int bins, int seeds) {
		return (rows*bins*seeds)/2 +1;
	}
	
	/**
	 * 
	 * @return whether direction of sowing is counterclockwise
	 */
	public boolean IsCounterClockwise() {
		return counterclockwise;
	}
	
	
}
