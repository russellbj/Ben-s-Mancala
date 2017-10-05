package mancala;

public class GameBoard {
	int holesPerRow;
	Row rowOne, rowTwo;
	CollectingHole tallyOne, tallyTwo;
	public Board(int holes){
		holesPerRow=holes;
		rowOne=new Row(holesPerRow);
		rowTwo=new Row(holesPerRow);
		tallyOne=new CollectingHole();
		tallyTwo=new CollectingHole();
	}
	public Row getRowOne(){
		return rowOne;
	}
	public Row getRowTwo(){
		return rowTwo;
	}
	public CollectingHole getPlayerOneBin(){
		return tallyOne;
	}
	public CollectingHole getPlayerTwoBin(){
		return tallyTwo;
	}
}
