package mancala;

public class Scoring 
{
	static GameManager gameManager = GameManager.getInstance();
	
	
	public static int wariScore(int[] boardState, int index)
	{
	
		if (boardState[index] == 2)
		{
			System.out.println("Score");
			boardState[index] = 0;
			gameManager.setBoardArray(boardState);
			return 2;
		}
		else if (boardState[index] == 3)
		{
			System.out.println("Score");
			boardState[index] = 0;
			gameManager.setBoardArray(boardState);
			return 3;
		}
		else
		{
			return 0;
		}
		
	}
}
