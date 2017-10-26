package mancala;

public class Game {

	moveSeeds(){
		Seeds.move(Row(Turn.getCurrPlayer)[clickedHole]);
//		int numOfSeeds = Row(Turn.get)[clickedHole].getAmount;
//		int nextHole = clickedHole + 1;
//		Row(Turn.get)[clickedHole].removeAll;
//		while(numOfSeeds > 0){
//			RowX[nextHole].add;
//			nextHole++;
//		}
	}

	public static void setup(){
  		rules();
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