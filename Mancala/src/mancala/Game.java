package mancala;

public class Game {

	private static Seed seeds = new Seed();
	public static Rules rules = new Rules();
	
	clickHole(){
		
		// Add clicking functionality that returns the index of the hole you have clicked on
		// Pass that index into the parameter for MoveSeeds()
		// the current parameter value of 5 is ARBITRARY AND SHOULD BE CHANGED ONCE WE CAN IDENTIFY THE CLICKED INDEX
		
		seeds.moveSeeds(5);
//		int numOfSeeds = Row(Turn.get)[clickedHole].getAmount;
//		int nextHole = clickedHole + 1;
//		Row(Turn.get)[clickedHole].removeAll;
//		while(numOfSeeds > 0){
//			RowX[nextHole].add;
//			nextHole++;
//		}
	}

	public static void setup(){
  		rules.getGameRules("WARI");
		board();
	}

	public static void main(){
		setup();
		loop{
			moveSeeds;
			Turn.switchTurn();
			if(possibleMoves = false || pointsToWin){
				endGame();
			}
		}
	}

	public static void gameOver(){
		shutdown;
	}
}