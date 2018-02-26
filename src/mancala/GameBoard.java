package mancala;

public class GameBoard { // Basic template for gameboards, compatible now with rows and holes
	private static String gameName;
	public static String getGameName() {
		return gameName;
	}

	public static void setGameName(String gameName) {
		GameBoard.gameName = gameName;
	}

	private static GameBoard instance = null;
	private Turn turn = GameDriver.turn;
	private Rules rules = GameDriver.rules;

	private String originCountry;
	private int initialSeedsForBin;
	public int getInitialSeedsForBin() {
		return initialSeedsForBin;
	}

	public void setInitialSeedsForBin(int initialSeedsForBin) {
		this.initialSeedsForBin = initialSeedsForBin;
	}

	private int numOfColumns;
	public int getNumOfColumns() {
		return numOfColumns;
	}

	public void setNumOfColumns(int numOfColumns) {
		this.numOfColumns = numOfColumns;
	}

	private BoardTypes boardType;
	private int numOfRows;

	public int getNumOfRows() {
		return numOfRows;
	}

	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}

	private static int holesPerRow;
	public static int getHolesPerRow() {
		return holesPerRow;
	}

	public static void setHolesPerRow(int holesPerRow) {
		GameBoard.holesPerRow = holesPerRow;
	}

	//public static Row rowOne, rowTwo; // BEN: could possibly add any number of rows here, and then only actually instantiate the amount you need in the constructor depending on rules (how many rows there are)
	CollectingHole scoreOne, scoreTwo;

	private static int[] board;

	public int[] getBoard()
	{
		return board;
	}

	public void setBoard(int[] board)
	{
		this.board = board;
	}

	public GameBoard(GameEnum variation){
		boardType = variation.getBoardType();
		originCountry = variation.getOriginCountry();
		initialSeedsForBin = variation.initialSeedsPerBin();
		numOfColumns = variation.getColumns();
		numOfRows = boardType.numberOfRows();
		gameName = variation.getName();

		board = new int[numOfRows + numOfColumns];
		for(int x = 0 ; x < board.length; x++)
		{
				board[x] = initialSeedsForBin;

		}

		//rowOne=new Row(holesPerRow); // May need to add more rows based on game, currently built for Wari
		//rowTwo=new Row(holesPerRow);

		if(rules.CheckSideBins())
		{
			scoreOne=new CollectingHole();
			scoreTwo=new CollectingHole();
		}
	}
//	public Row getRowOne(){ // (Subject to change based on variations of game) returns the first row for moves
//		return rowOne; // Check if fixed another commit try again
//	}
//	public Row getRowTwo(){ // Returns second row (both to be called by other classes when moving or collecting
//		return rowTwo;
//	}
	public CollectingHole getPlayerOneScore(){ // return value in tally bin one
		return scoreOne;
	}
	public CollectingHole getPlayerTwoScore(){ // return value in tally bin two (once again, subject to change per game)
		return scoreTwo;
	}

	public static GameBoard getInstance(GameEnum theVariation)
	{
		if(instance == null)
		{
			instance = new GameBoard(theVariation);
		}
		return instance;
	}
}

