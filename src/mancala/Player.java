package mancala;

public class Player 
{
	int playerNumber;
	
	private int[] topRow = new int[6];
	
	public int[] getTopRow() {
		return topRow;
	}

	public void setTopRow(int[] topRow) {
		this.topRow = topRow;
	}

	public int[] getBottomRow() {
		return bottomRow;
	}

	public void setBottomRow(int[] bottomRow) {
		this.bottomRow = bottomRow;
	}

	private int[] bottomRow = new int[6];;
	
	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	boolean flip = false;
	
	private boolean clickedZero;
	
	public int moveSeeds(Row clickedRow, Row secondaryRow, int currentHole) 
	{  //method for acting upon a bin being chosen for a move
		setClickedZero(false);
		int numOfSeeds = clickedRow.getSeedsAtIndex(currentHole);
		Holes[] clickedRowArray = clickedRow.getHoles();
		Holes[] secondaryRowArray = secondaryRow.getHoles();
		
		int pointsGain = 0;
		boolean droppedSeed = false;
		
		if(playerNumber == 1)
		{
		flip = false;
		
		if(clickedRowArray[currentHole].getNumOfSeeds() == 0)
		{
			setClickedZero(true);
		}
		
		else {
		clickedRowArray[currentHole].setNumOfSeeds(0);
		for( int i = numOfSeeds; i > 0; i--) 
		{
			droppedSeed = false;
			//System.out.println("Num of Seeds: " + numOfSeeds);
			if(!flip)
			{
				if(currentHole < 5)
				{
				currentHole++;
				//System.out.println(currentHole);
				clickedRowArray[currentHole].setNumOfSeeds(clickedRowArray[currentHole].getNumOfSeeds() + 1);
				droppedSeed = true;
				}
				if(currentHole >= 5)
				{
					flip = true;
				}
			}
			
			if(flip && !droppedSeed)
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
		}
		
		else if(playerNumber == 2)
		{
			if(clickedRowArray[currentHole].getNumOfSeeds() == 0)
			{
				setClickedZero(true);
			}
			else{
				clickedRowArray[currentHole].setNumOfSeeds(0);
			flip = false;
			for( int i = numOfSeeds; i > 0; i--) 
			{
				droppedSeed = false;
				//System.out.println("Num of Seeds: " + numOfSeeds);
				
				if(!flip)
				{
					if(currentHole > 0)
					{
					currentHole--;
					//System.out.println(currentHole);
					clickedRowArray[currentHole].setNumOfSeeds(clickedRowArray[currentHole].getNumOfSeeds() + 1);
					droppedSeed = true;
					}
					if(currentHole <= 0)
					{
						flip = true;
					}
				}
				
				if(flip && !droppedSeed)
				{	
					if(currentHole <= 5)
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
					if(currentHole > 5)
					{
						flip = false;
					}
				}
				numOfSeeds--;
			}
			
		}
		}
		
		
		
		if(!getClickedZero())
		{
			
		if(playerNumber == 1)
		{
			System.out.print("{");
			for(int i = 0 ; i < secondaryRowArray.length-1 ; i++)
			{
				System.out.print(secondaryRowArray[i].getNumOfSeeds() + ", ");
				topRow[i] = secondaryRowArray[i].getNumOfSeeds();
			}
			System.out.print(secondaryRowArray[clickedRowArray.length - 1].getNumOfSeeds());
			topRow[5] = secondaryRowArray[clickedRowArray.length - 1].getNumOfSeeds();
			System.out.print("}");
			System.out.println();
			System.out.print("{");
			for(int i = 0 ; i < clickedRowArray.length-1 ; i++)
			{
				System.out.print(clickedRowArray[i].getNumOfSeeds() + ", ");
				bottomRow[i] = clickedRowArray[i].getNumOfSeeds();
			}
			System.out.print(clickedRowArray[clickedRowArray.length - 1].getNumOfSeeds());
			bottomRow[5] = clickedRowArray[secondaryRowArray.length - 1].getNumOfSeeds();
			System.out.print("}");
			System.out.println();
			System.out.println();
			
			
	}
		if(playerNumber == 2)
		{
			System.out.print("{");
			for(int i = 0 ; i < clickedRowArray.length -1; i++)
			{
				System.out.print(clickedRowArray[i].getNumOfSeeds() + ", ");
				topRow[i] = clickedRowArray[i].getNumOfSeeds();
			}
			System.out.print(clickedRowArray[clickedRowArray.length - 1].getNumOfSeeds());
			topRow[5] = clickedRowArray[clickedRowArray.length - 1].getNumOfSeeds();
			System.out.print("}");
			System.out.println();
			System.out.print("{");
			for(int i = 0 ; i < secondaryRowArray.length -1; i++)
			{
				System.out.print(secondaryRowArray[i].getNumOfSeeds() + ", ");
				bottomRow[i] = secondaryRowArray[i].getNumOfSeeds();
			}
			System.out.print(secondaryRowArray[clickedRowArray.length - 1].getNumOfSeeds());
			bottomRow[5] = secondaryRowArray[secondaryRowArray.length - 1].getNumOfSeeds();
			System.out.print("}");
			System.out.println();
			System.out.println();
		}
		}
		return pointsGain;
	}

	public boolean playerOneOver()
	{
		int numOfZeroes = 0;
		for(int i = 0 ; i < bottomRow.length; i++)
		{
			if(bottomRow[i] == 0)
			{
				numOfZeroes++;
			}
		}
		if(numOfZeroes == 6)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean playerTwoOver()
	{
		int numOfZeroes = 0;
		for(int i = 0 ; i < topRow.length; i++)
		{
			if(topRow[i] == 0)
			{
				numOfZeroes++;
			}
		}
		if(numOfZeroes == 6)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	
	public boolean getClickedZero() {
		return clickedZero;
	}
	

	public void setClickedZero(boolean clickedZero) {
		this.clickedZero = clickedZero;
	}
}
