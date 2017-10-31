package mancala;

public class Game {

	private static Seed seeds = new Seed();
	public static Rules rules = new Rules();
	public static Turn turn = new Turn();
	private static GameBoard board = new GameBoard();
	public static CollectingHole P1 = new CollectingHole();
	public static CollectingHole P2 = new CollectingHole();
	private static int pointsToWin;
	private static boolean possibleMoves;
	static int clickedHoleIndex;
	
	static boolean clickHole(){
		
		// Add clicking functionality that returns the index of the hole you have clicked on
		// Pass that index into the parameter for MoveSeeds()
		// the current parameter value of 5 is ARBITRARY AND SHOULD BE CHANGED ONCE WE CAN IDENTIFY THE CLICKED INDEX
		
		clickedHoleIndex = 5; // Set the value of this integer to the index that was clicked. 5 is placeholder
		seeds.moveSeeds(clickedHoleIndex); // Pass the index of the clicked hole into MoveSeeds()
		return true;
	}
	
	public static string determineWinner(){
		if (P1.displayCount > P2.displayCount){
			return "Player 1 wins!"
		}else if (P1.displayCount < P2.displayCount){
			return "Player 2 wins!"
		}else{
			return "It's a tie!"
		}
	}

	public static void setup(){
  		rules.getGameRules("WARI");
	}

	public static void main(){
		setup();
		while(true){
			if(clickHole()){
				seeds.moveSeeds(clickedHoleIndex);
				turn.switchTurn();
				if(possibleMoves == false || (P1.displayCount || P2.displayCound >= pointsToWin)){
					endGame();
				}
			}
		}
	}

	public static void endGame(){
		System.out.println("Game Over, " + determineWinner());
		System.exit(1);
	}
}
