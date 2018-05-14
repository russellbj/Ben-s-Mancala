package mancala;

import java.util.Arrays;

/**
 * @author Dhanvanthri
 */
public class GameBoard {
    private String gameName;
    private BoardTypes numRows;
    private String originCountry;
    private Integer numColumns;
    private Integer initialSeedsPerBin;
    private GameEnum gameEnum;
    private Integer[] boardStateArray;


    /**
     * @param gameEnum The game enumeration to use
     */
                                                public GameBoard(GameEnum gameEnum) {
        this.gameEnum = gameEnum;
        gameName = gameEnum.getName();
        numRows = gameEnum.getBoardType();
        numColumns = gameEnum.getColumns();
        originCountry = gameEnum.getOriginCountry();
        initialSeedsPerBin = gameEnum.getInitialSeedsPerBin();
        {
            // Init and populate the board
            boardStateArray = new Integer[getNumColumns() * getNumRows()];
            populateBoardStateArray();
        }
    }

    private Integer transformXYtoIndex(Integer xCoordinate, Integer yCoordinate) {
        if (yCoordinate > getNumRows() - 1 || xCoordinate > getNumColumns() - 1) {
            System.err.println("You selected an index out of bounds for the game; Error " + 666);
            return 666;
        } else {
            Integer boardIndex = ((yCoordinate * (getNumColumns())) - 1) + xCoordinate;
            System.out.println(boardIndex.toString());
            return boardIndex;
        }
    }


    public void realSetBoardState(Integer boardPosition, Integer newValue) {
        Integer[] board = boardStateArray.clone();
        System.out.println(board.toString());
        board[boardPosition] = newValue;
        boardStateArray = board.clone();
        System.out.println(boardStateArray.toString());
    }

    private Integer[] internalBoardState(Integer[] board, Integer boardPosition, Integer newValue) {
        board = boardStateArray.clone();
        System.out.println(board.toString());
        board[boardPosition] = newValue;
        System.out.println(boardStateArray.toString());
        return board;
    }

    /**
     * Populates boardStateArray with the appropriate number of initial seeds
     */
    // TODO Deal with games with end bins
    private void populateBoardStateArray() {
        Arrays.fill(boardStateArray, initialSeedsPerBin);
    }

    /**
     * @return The array that holds the game state
     */
    public Integer[] getBoardStateArray() {
            return boardStateArray;
    }


    /**
     * @return The number of seeds that are supposed to be in the starting position for this game
     */
    public int getInitialSeedsPerBin() {
        return initialSeedsPerBin;
    }

    /**
     * @return The enumerated value of this game from the enumeration
     */
    public GameEnum getGameEnum() {
        return gameEnum;
    }

    /**
     * @return The name of the game
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * @return The number of rows in the game
     */
    public int getNumRows() {
        return numRows.numberOfRows();
    }

    /**
     * @return The number of columns in the game
     */
    public int getNumColumns() {
        return numColumns;
    }

    /**
     * @return The country that this game is from
     */
    public String getOriginCountry() {
        return originCountry;
    }
}
