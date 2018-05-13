package mancala;

public class Scoring 
{
	MainWindow window = new MainWindow().getInstance();
	GameBoard board = window.getGameBoard();
	int numRows = board.getNumRows();
	int numColumns = board.getNumColumns();
	Integer[] boardState = new Integer[numColumns * numRows];
	
	public static int wariScore()
	{
		
		return 0;
	}
}
