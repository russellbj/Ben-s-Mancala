package mancala;

public class GameBoard { // Basic template for gameboards
	int holesPerRow;
	Rows rowOne, rowTwo;
	CollectingHole tallyOne, tallyTwo;
	public GameBoard(int holes){
		holesPerRow=holes;
		rowOne=new Rows(holesPerRow); // May need to add more rows based on game, currently built for Wari
		rowTwo=new Rows(holesPerRow);
		tallyOne=new CollectingHole();
		tallyTwo=new CollectingHole();
	}
	public Rows getRowOne(){ // (Subject to change based on variations of game) returns the first row for moves
		return rowOne;
	}
	public Rows getRowTwo(){ // Returns second row (both to be called by other classes when moving or collecting
		return rowTwo;
	}
	public CollectingHole getPlayerOneBin(){ // return value in tally bin one 
		return tallyOne;
	}
	public CollectingHole getPlayerTwoBin(){ // return value in tally bin two (once again, subject to change per game)
		return tallyTwo;
	}
}
