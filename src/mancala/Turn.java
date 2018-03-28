package mancala;

public class Turn {
	private static Turn instance;
	private int currTurn = 1;
	private int currPlayer = 1;
	
	public void switchPlayer()
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
		return currTurn;
	}
	
	public void resetTurn(){
		currPlayer=1;
		currTurn=1;
	}

	public static Turn getInstance() {
		if(instance == null)
		{
			instance = new Turn();
		}
		return instance;
	}
}
