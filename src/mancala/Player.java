package mancala;

public class Player 
{
	private static GameBoard board = GameBoard.getInstance();
	int playerNumber;
	
	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	boolean flip = false;
	
	public int moveSeeds(Row clickedRow, Row secondaryRow, int currentHole) 
	{  //method for acting upon a bin being chosen for a move
		
		int numOfSeeds = clickedRow.getSeedsAtIndex(currentHole);
		Holes[] clickedRowArray = clickedRow.getHoles();
		Holes[] secondaryRowArray = secondaryRow.getHoles();
		clickedRowArray[currentHole].setNumOfSeeds(0);
		int pointsGain = 0;
		
		if(playerNumber == 1)
		{
			flip = false;
		for( int i = numOfSeeds; i > 0; i--) 
		{
			//System.out.println("Num of Seeds: " + numOfSeeds);
			
			if(!flip)
			{
				if(currentHole < 5)
				{
				currentHole++;
			//	System.out.println(currentHole);
				clickedRowArray[currentHole].setNumOfSeeds(clickedRowArray[currentHole].getNumOfSeeds() + 1);
				}
				if(currentHole >= 5)
				{
					flip = true;
				}
			}
			
			if(flip)
			{	
				if(currentHole >= 0)
				{
				secondaryRowArray[currentHole].setNumOfSeeds(secondaryRowArray[currentHole].getNumOfSeeds() + 1);
					if(i == 1)
					{
						try{
						while(secondaryRowArray[currentHole].getNumOfSeeds() == 2 || secondaryRowArray[currentHole].getNumOfSeeds() == 3 && currentHole < 6)
						{
							pointsGain = pointsGain + secondaryRowArray[currentHole].getNumOfSeeds();
							secondaryRowArray[currentHole].setNumOfSeeds(0);
							currentHole++;
						}
						}
						catch(Exception E)
						{
							
						}
					}
				currentHole--;
				}
				if(currentHole < 0)
				{
					flip = false;
				}
			}
			numOfSeeds--;
		}
		
		}
		
		else if(playerNumber == 2)
		{
			flip = false;
			for( int i = numOfSeeds; i > 0; i--) 
			{
			//	System.out.println("Num of Seeds: " + numOfSeeds);
				
				if(!flip)
				{
					if(currentHole > 0)
					{
					currentHole--;
				//	System.out.println(currentHole);
					clickedRowArray[currentHole].setNumOfSeeds(clickedRowArray[currentHole].getNumOfSeeds() + 1);
					}
					if(currentHole <= 0)
					{
						flip = true;
					}
				}
				
				if(flip)
				{	
					if(currentHole < 5)
					{
					secondaryRowArray[currentHole].setNumOfSeeds(secondaryRowArray[currentHole].getNumOfSeeds() + 1);
					if(i == 1)
					{
						//int iterator = currentHole;
						try
						{
							while(secondaryRowArray[currentHole].getNumOfSeeds() == 2 || secondaryRowArray[currentHole].getNumOfSeeds() == 3)
							{
								pointsGain = pointsGain + secondaryRowArray[currentHole].getNumOfSeeds();
								secondaryRowArray[currentHole].setNumOfSeeds(0);
								currentHole--;
							}
						}
						
						catch(Exception E)
						{
							
						}
						
					}
					currentHole++;
					}
					if(currentHole >= 5)
					{
						flip = false;
					}
				}
				numOfSeeds--;
			}
			
		}
		
		
		
		
		if(playerNumber == 1)
		{
			System.out.print("{");
			for(int i = 0 ; i < secondaryRowArray.length ; i++)
			{
				System.out.print(secondaryRowArray[i].getNumOfSeeds() + ", ");
			}
			System.out.print("}");
			System.out.println();
			System.out.print("{");
			for(int i = 0 ; i < clickedRowArray.length ; i++)
			{
				System.out.print(clickedRowArray[i].getNumOfSeeds() + ", ");
			}
			System.out.print("}");
			System.out.println();
			System.out.println();
	}
		if(playerNumber == 2)
		{
			System.out.print("{");
			for(int i = 0 ; i < clickedRowArray.length ; i++)
			{
				System.out.print(clickedRowArray[i].getNumOfSeeds() + ", ");
			}
			System.out.print("}");
			System.out.println();
			System.out.print("{");
			for(int i = 0 ; i < secondaryRowArray.length ; i++)
			{
				System.out.print(secondaryRowArray[i].getNumOfSeeds() + ", ");
			}
			System.out.print("}");
			System.out.println();
			System.out.println();
		}
		return pointsGain;
	}
}
