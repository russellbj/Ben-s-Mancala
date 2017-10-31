package mancala;

public class GameBoard { // Basic template for gameboards, compatible now with rows and holes
	
	private Turn turn = Game.turn; 
	private Rules rules = Game.rules;
	
	int holesPerRow;
	Rows rowOne, rowTwo; // BEN: could possibly add any number of rows here, and then only actually instantiate the amount you need in the constructor depending on rules (how many rows there are)
	CollectingHole tallyOne, tallyTwo; 
	
	
	public GameBoard(){
		
		holesPerRow=rules.GetBinsPerRow();
		rowOne=new Rows(holesPerRow); // May need to add more rows based on game, currently built for Wari
		rowTwo=new Rows(holesPerRow);
		
		if(rules.CheckSideBins())
		{
			tallyOne=new CollectingHole();
			tallyTwo=new CollectingHole();
		}
	}
	public Rows getRowOne(){ // (Subject to change based on variations of game) returns the first row for moves
		return rowOne; // Check if fixed another commit try again
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
