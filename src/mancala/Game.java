package mancala;

import java.util.Scanner;

public class Game {

	private static Scanner input = new Scanner(System.in);
	
	public static Rules rules = Rules.getInstance();
	public static Turn turn = new Turn();
	private static int numOfHoles = 12; // Wari - specific
	private static GameBoard board = GameBoard.getInstance();
	public static CollectingHole P1 = new CollectingHole();
	public static CollectingHole P2 = new CollectingHole();
	private static int pointsToWin = 25;
	private static boolean possibleMoves = true;
	
	private static Player playerOne = new Player();
	private static Player playerTwo = new Player();
	
	static int holesPerRow = 6;
	private static Row rowOne=new Row(holesPerRow); // May need to add more rows based on game, currently built for Wari
	private static Row rowTwo=new Row(holesPerRow);
	static int clickedHoleIndex;
	
	static boolean clickHole(){

			
		
		System.out.println("Player 1 Points: " + P1.displayCount());
		System.out.println("Player 2 Points: " + P2.displayCount());
		System.out.println();
		System.out.println("You are Player " + turn.getCurrPlayer());
		System.out.println("You can choose from Holes 1-6 in row " + turn.getCurrPlayer());

		boolean goThrough = false;
		
		while(!goThrough)
		{
		try{
		System.out.print("Enter the index # of the hole you want to click: ");
		clickedHoleIndex = input.nextInt() - 1;
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
			P1.addSeeds(playerOne.moveSeeds(rowOne, rowTwo, clickedHoleIndex)); // Player one can only click row one, I think (wari rules)
		}
		if(turn.getCurrPlayer() == 2)
		{
			P2.addSeeds(playerTwo.moveSeeds(rowTwo, rowOne, clickedHoleIndex)); // Player one can only click row one, I think (wari rules)
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
		
		System.out.print("{");
		for(int i = 0 ; i < 6 ; i++)
		{
			System.out.print(4 + ", ");
		}
		System.out.print("}");
		System.out.println();
		System.out.print("{");
		for(int i = 0 ; i < 6 ; i++)
		{
			System.out.print(4 + ", ");
		}
		System.out.print("}");
		System.out.println();
		System.out.println();
		
		new MainWindow();
		playerOne.setPlayerNumber(1);
		playerTwo.setPlayerNumber(2);
  		rules.getGameRules("WARI");
		pointsToWin = 25;
	}

	public static void main(String[]args){
	
		setup();
		while(true){
			clickHole();
			turn.switchTurn();
			if(possibleMoves == false || (P1.displayCount() >= pointsToWin) || (P2.displayCount() >= pointsToWin)){
				endGame();
			}
			}
		}

	public static void endGame(){
		System.out.println("Game Over, " + determineWinner());
		System.exit(1);
	}
}