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
 * @author MancalaTeam
 * 
 * Methods to make:
 * ReadGameList()
 */

public class Rules {
	
	// Local variables used for current game type
	private String name;
	private boolean hasSidebins;
	private int pointsToWin, rowAmount, binsPerRow, seedsPerBin;
	
	private int gameListSize = 4;
	private GameType[] gameList;
	
	/**
	 * Sets up the rules class.
	 */
	public Rules () {
		// open file
		// loop through file line-by-line
		String gamesText = "2 6 4 true WARI 25;"
				+ "5 6 4 true 5ROW 25;"
				+ "2 6 10 true MANYSEED 25;"
				+ "2 6 4 false BINLESS 25";
		String[] gamesTextLines = gamesText.split(";");
		gameListSize = gamesTextLines.length;
		gameList = new GameType[gameListSize];
		for (int i = 0; i< gamesTextLines.length; i++) {
			gameList[i] = TranslateLine(gamesTextLines[i]);
		}
		
		/*
		gameList[0] = TranslateLine("2 6 4 true WARI 25");
		gameList[1] = TranslateLine("5 6 4 true 5ROW 25");
		gameList[2] = TranslateLine("2 6 10 true MANYSEED 25");
		gameList[3] = TranslateLine("2 6 4 false BINLESS 25");
		*/
		
		/*
		gameList[0] = new GameType(2,6,4,true,"WARI",25);
		gameList[1] = new GameType(5,6,4,true,"5ROW",25);
		gameList[2] = new GameType(2,6,10,true,"MANYSEED",25);
		gameList[3] = new GameType(2,6,4,false,"BINLESS",25);
		*/
		// Will set array of game types from text file
	}
	
	/**
	 * @param toTranslate line of text to be turned into game
	 * @return the game that has been created from toTranslate
	 */
	public GameType TranslateLine(String toTranslate) {
		GameType game;
		int numRows, numBins, numSeeds, winPoints = 0;
		boolean sideBins = false;
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
		name = gameStuff[4];
		if (gameStuff.length < 6) {
			game = new GameType(numRows,numBins,numSeeds,sideBins,name);
		} else {
			winPoints = Integer.parseInt(gameStuff[5]);
			game = new GameType(numRows,numBins,numSeeds,sideBins,name,winPoints);
		}
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
		pointsToWin = gameList[gameSelected].GetPointsToWin();
	}
	
	/**
	 * Sets up rules for game based on given parameters.
	 * @param rows number of rows
	 * @param columns number of columns
	 * @param startBeans number of beans per hole (at start)
	 * @param sideBins whether has side bins or not
	 */
	public void setCustomRules (int rows, int columns, int startBeans, boolean sideBins, int winPoints) {
		rowAmount = rows;
		binsPerRow = columns;
		seedsPerBin = startBeans;
		hasSidebins = sideBins;
		pointsToWin = winPoints;
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
	 * @return The name of the game
	 */
	public String GetName() {
		return name;
	}
	
	/**
	 * @return points required for a player to win
	 */
	public int GetPointsToWin() {
		return pointsToWin;
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
	
	/**
	 * give parameters for a game
	 * @param rows number of rows
	 * @param columns number of columns
	 * @param startBeans number of beans per hole
	 * @param sideBins whether has sidebins or not
	 */
	public GameType (int rows, int columns, int startBeans, boolean sideBins, String gameName) {
		SetUp (rows,columns,startBeans,sideBins,gameName,10000);
	}
	
	/**
	 * give parameters for game
	 * @param rows number of rows
	 * @param columns number of columns
	 * @param startBeans number of beans per hole
	 * @param sideBins whether has sidebins or not
	 */
	public GameType (int rows, int columns, int startBeans, boolean sideBins, String gameName, int winPoints) {
		SetUp (rows,columns,startBeans,sideBins,gameName,winPoints);
	}
	
	/**
	 * Sets the game rules from given parameters
	 * @param rows number of rows
	 * @param columns number of columns
	 * @param startBeans number of beans per hole
	 * @param sideBins whether has sidebins or not
	 */
	private void SetUp (int rows, int columns, int startBeans, boolean sideBins, String gameName, int winPoints) {
		name = gameName;
		rowAmount = rows;
		binsPerRow = columns;
		seedsPerBin = startBeans;
		hasSidebins = sideBins;
		pointsToWin = winPoints;
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
	 * @return points required for a player to win
	 */
	public int GetPointsToWin() {
		return pointsToWin;
	}
}
