package mancala;


public class GameManager 
{
	private static int[] boardArray;
	private static GameManager instance;
	private static BoardTypes boardType;
	private static int numOfColumns;
	private static int numOfRows;
	private static String originCountry;
	private static String name;
	private static int numOfHoles;
	private static int initialSeedsPerBin;
	private static boolean hasEndBins;
	private static boolean counterclockwise;
	public static Rules rules;

	public static void setup(GameEnum gameEnum)
	{
		rules=new Rules(name);
		boardType = gameEnum.getBoardType();
		numOfColumns = gameEnum.getColumns();
		numOfRows = gameEnum.getNumOfRows();
		name = gameEnum.getName();

		if(gameEnum.getBoardType() == BoardTypes.TWO_ROW_WITH_ENDS)
			hasEndBins = true;
		else
			hasEndBins = false;

		originCountry = gameEnum.getOriginCountry();
		numOfHoles = numOfColumns * numOfRows;
		initialSeedsPerBin = gameEnum.getInitialSeedsPerBin();
		counterclockwise=rules.getCounterclockwise();
		System.out.println(counterclockwise);
		boardArray = new int[numOfHoles];
		setupBoardArray();
		printBoard();
	}

	public static void printBoard() 
	{	
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
		System.out.println(index);

		int seedsLeftToMove = boardArray[index];
		boardArray[index] = 0;

		int currRow = x;

		while(seedsLeftToMove >0)
		{		
			if(counterclockwise){
				if(currRow % 2 == 1)
				{
					if(index != 0)
					{
						index--;
						int newVal = boardArray[index];
						newVal++;
						boardArray[index] = newVal;
						seedsLeftToMove--;
					}

					else if(index == 0)
					{
						if(currRow != numOfRows)
							currRow++;
						else
							currRow = 1;

						index = numOfColumns - 1;

					}

				}
				else if(currRow % 2 == 0)
				{
					if(index < numOfHoles - 1)
					{
						index++;

						int newVal = boardArray[index];
						newVal++;
						boardArray[index] = newVal;
						seedsLeftToMove--;
					}


					else if(index >= numOfHoles - 1)
					{
						if(currRow != numOfRows)
							currRow++;
						else
						{
							currRow = 1;

						}

						index = (currRow * numOfColumns);;

					}
				}
			}
			else if(counterclockwise==false){
				System.out.println("CLOCKWISE");
				if(currRow % 2 == 1){
					if(index < numOfColumns -1){
						index++;
						int newVal = boardArray[index];
						newVal++;
						boardArray[index] = newVal;
						seedsLeftToMove--;
					}
					else if(index >= numOfColumns -1){
						if(currRow != numOfRows){
							currRow++;
						}
						else
							currRow = 1;

						index = (numOfRows-1)*numOfColumns+numOfColumns;

					}

				}
				else if(currRow % 2 == 0)
				{
					if(index >(currRow-1)*numOfColumns)
					{
						index--;

						int newVal = boardArray[index];
						newVal++;
						boardArray[index] = newVal;
						seedsLeftToMove--;
					}


					else{
						System.out.println("Switch");
						if(currRow != numOfRows)
							currRow++;
						else
						{
							currRow = 1;

						}

						index = -1;

					}
				}
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
