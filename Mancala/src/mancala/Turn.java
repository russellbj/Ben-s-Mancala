package mancala;

public class Turn {
	private Play game = new Play();
	private int currTurn = 1;
	private int currPlayer = 1;
	
	public void switchTurn()
	{
			if(currPlayer == 1)
			{
				currPlayer = 2;
			}
			else if(currPlayer == 2)
			{
				currPlayer = 1;
				currTurn++;
			}
	}
	
	public int getCurrPlayer()
	{
		return currPlayer;
	}
	
	public int getCurrTurn()
	{
		return currTurn();
	}
}