package mancala;

import java.util.Scanner;

public class Game {

//	private static Scanner input = new Scanner(System.in);
	
	public static Rules rules = Rules.getInstance();
	public static Turn turn = Turn.getInstance();
//	private static int numOfHoles = 12; // Wari - specific
//	private static GameBoard board = GameBoard.getInstance();
	public static CollectingHole P1 = new CollectingHole();
	public static CollectingHole P2 = new CollectingHole();
	private static int pointsToWin = 25;
//	private static boolean possibleMoves = true;
	private static MainWindow mainWindow = MainWindow.getInstance();
	

	
	private static boolean playerOneDone = false;
	private static boolean playerTwoDone = false;
	
	private static Player playerOne = new Player();
	private static Player playerTwo = new Player();
	
	public static Player getPlayerOne() {
		return playerOne;
	}

	public static void setPlayerOne(Player playerOne) {
		Game.playerOne = playerOne;
	}

	public static Player getPlayerTwo() {
		return playerTwo;
	}

	public static void setPlayerTwo(Player playerTwo) {
		Game.playerTwo = playerTwo;
	}
	private static boolean isPlayerGame;
	private static boolean isComputerGame;
	
	static int holesPerRow = 6;
	private static Row rowOne=new Row(holesPerRow); // May need to add more rows based on game, currently built for Wari
	private static Row rowTwo=new Row(holesPerRow);
	static int clickedHoleIndex;
	static int totalPoints;

	private static boolean clickedZero;

	private static Game instance;
	
	static boolean clickHole(int holeIndex){
		
		//mainWindow.playGame();
		clickedZero = false;
		clickedHoleIndex = holeIndex;

		boolean goThrough = false;
		
		while(!goThrough)
		{
		try{
		if(clickedHoleIndex < 0 || clickedHoleIndex >= 6)
			throw new Exception();
		else
			goThrough = true;
		
		}
		catch(Exception E)
		{
			System.out.println("Please enter a value between 1 and 6.");
		}
		
	}
		// Add clicking functionality that returns the row you clicked, as well as index of the hole you have clicked on
		// Pass that index / row into the parameter for MoveSeeds()

		if(turn.getCurrPlayer() == 1)
		{
	
			//P1.addSeeds(playerOne.moveSeeds(rowOne, rowTwo, clickedHoleIndex)); // Player one can only click row one, I think (wari rules)
			
			if(playerOne.getClickedZero())
			{	
				System.out.println("Please enter the index of a hole that contains seeds.");
				clickedZero = true;
			}	
		//	playerOneDone = playerOne.playerOneOver();
			
		}
		
		else if(turn.getCurrPlayer() == 2)
		{
		//	P2.addSeeds(playerTwo.moveSeeds(rowTwo, rowOne, clickedHoleIndex)); // Player TWO can only click row two, I think (wari rules)
			
			if(playerTwo.getClickedZero())
			{
				System.out.println("Please enter the index of a hole that contains seeds.");
				clickedZero = true;
			}
		//	playerTwoDone = playerTwo.playerTwoOver();
		}
		
		return true;
	}
	
	public static String determineWinner(){ //Checks to see who has more seeds in their CollectingHole.
		if (P1.displayCount() > P2.displayCount()){
			return "Player 1 wins!";
		}else if (P1.displayCount() < P2.displayCount()){
			return "Player 2 wins!";
		}else{
			return "It's a tie!";
		}
	}

	public static void setup(){
		System.out.println("You are Player " + turn.getCurrPlayer());
		System.out.println("");
		System.out.print("{");
		for(int i = 0 ; i < 5 ; i++)
		{
			System.out.print(4 + ", ");
		}
		System.out.print(4);
		System.out.print("}");
		System.out.println();
		System.out.print("{");
		for(int i = 0 ; i < 5 ; i++)
		{
			System.out.print(4 + ", ");
		}
		System.out.print(4);
		System.out.print("}");
		System.out.println();
		System.out.println();
		
		playerOne.setPlayerNumber(1);
		playerTwo.setPlayerNumber(2);
  		rules.getGameRules("WARI");
		pointsToWin = 25;
	}

	public static void main(String[]args) throws InterruptedException{
		totalPoints = P1.displayCount() + P2.displayCount();
		setup();

		while(true){
			Thread.sleep(1); 
			if(playerOneDone || playerTwoDone ||
					totalPoints >= 47 || (P1.displayCount() >= pointsToWin) || (P2.displayCount() >= pointsToWin)){
				endGame();	
			}
			}
		}

	public static void endGame(){
		System.out.println("Game Over, " + determineWinner());
		System.exit(1);
	}
	
	public static Game getInstance()
	{
		if(instance == null)
		{
			instance = new Game();
		}
		return instance;
	}

	public static boolean getClickedZero() {
		// TODO Auto-generated method stub
		return clickedZero;
	}
	public void setClickedZero(boolean clickedZero) {
		// TODO Auto-generated method stub
		this.clickedZero = clickedZero;
	}
}