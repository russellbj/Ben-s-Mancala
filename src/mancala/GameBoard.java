package mancala;

import java.util.Arrays;


/**
 *
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



    /**
     * This prints the array to console (debug)
     */
    private void printBoardStateArray(Integer[] boardStateArray) {
        System.out.println(boardStateArray.toString());
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
