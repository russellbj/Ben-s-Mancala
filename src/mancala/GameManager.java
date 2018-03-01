package mancala;


public class GameManager 
{
	public static int[] boardArray;
	private static GameManager instance;
	private static BoardTypes boardType;
	private static int numOfColumns;
	private static int numOfRows;
	private static String originCountry;
	private static String name;
	private static int numOfHoles;
	private static int initialSeedsPerBin;
	
	
	public static void setup(GameEnum gameEnum)
	{
		boardType = gameEnum.getBoardType();
		numOfColumns = gameEnum.getColumns();
		numOfRows = gameEnum.getNumOfRows();
		name = gameEnum.getName();
		originCountry = gameEnum.getOriginCountry();
		numOfHoles = numOfColumns * numOfRows;
		initialSeedsPerBin = gameEnum.getInitialSeedsPerBin();
		boardArray = new int[numOfHoles];
		setupBoardArray();
		printBoard();
	}
	
	public static void printBoard() 
	{
		System.out.println(numOfHoles);
		int rowsLeft = numOfRows;
		int i = 0;
		int loopCounter = 1;
		while(rowsLeft > 0)
		{
			System.out.print("{");
			
		while(i < (numOfColumns * loopCounter))
		{
			if(i != (numOfColumns * loopCounter) - 1)
			{
			System.out.print(boardArray[i] + ", ");
			}
			else
			{
				System.out.print(boardArray[i]);
			}
			i++;
			
		}
		loopCounter++;
		System.out.println("}");
		rowsLeft--;
		}
	}

	public static void setupBoardArray()
	{
		for(int x = 0 ; x < numOfHoles ; x++)
		{
			boardArray[x] = initialSeedsPerBin;
		}
	}
	
	public static void moveSeeds(int x, int y)
	{
		int[] boardArrayCopy = boardArray;
		int rowsToAdd = (numOfColumns) * (x-1);
		int index = 0;
			
				index = (rowsToAdd + y) - 1;
			
			
		int seedsLeftToMove = boardArray[index];
		boardArray[index] = 0;
		
		int currRow = x;
		
		System.out.println("Index: " + index);
		while(seedsLeftToMove >0)
		{		
			
			if(currRow % 2 == 1)
			{
				if(index != numOfColumns - 1)
				{
				index++;
				int newVal = boardArray[index];
				newVal++;
				boardArray[index] = newVal;
				seedsLeftToMove--;
				}
				
				else if(index == numOfColumns - 1)
				{
					if(currRow != numOfRows)
						currRow++;
					else
						currRow = 1;
					
					index = (currRow * numOfColumns);
					
				}
				boardArrayCopy = boardArray;
				System.out.println("Index: " + index);
			} 
			else if(currRow % 2 == 0)
			{
				
			
				if(index != (currRow-1) * numOfColumns)
				{
					index--;
					System.out.println("Switch CAse: "+ (currRow-1) * numOfColumns);
					int newVal = boardArray[index];
					newVal++;
					boardArray[index] = newVal;
					seedsLeftToMove--;
				}
				
				
				else if(index == (currRow-1) * numOfColumns)
				{
					if(currRow != numOfRows)
						currRow++;
					else
					{
						currRow = 1;
						System.out.println("Switching To Beginning");
					}
					
					index = ((currRow - 1) * numOfColumns) - 1;
					
				}
				
				boardArrayCopy = boardArray;
				System.out.println("Index: " + index);
			} 
		}
		printBoard();
	}
	
	public static boolean validMove(int index)
	{
		if(boardArray[index] <= 0) 
		{
			return false;
		}
		else
		{
		return true;
		}
	}

	public static GameManager getInstance() {
		if(instance == null)
		{
			instance = new GameManager();
		}
		return instance;
	}
}
