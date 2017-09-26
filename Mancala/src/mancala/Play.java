package mancala;

public class Play {
	
	private Turn turn = new Turn();
	private boolean turnTaken;
	private int currPlayer = turn.getCurrPlayer();
	
	public void Move()
	{
		// Insert ability for a user to make a move here
		turn.switchTurn();
		currPlayer = turn.getCurrPlayer();
	}
}
