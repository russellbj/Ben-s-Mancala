package mancala;

public class GameBoard {
    private String gameName;
    private BoardTypes thisBoardType;
    private int numColumns;
    private String originCountry;
    private int initialSeedsPerBin;
    private GameEnum gameEnum;

    public GameBoard(GameEnum gameEnum) {
    	this.gameEnum = gameEnum;
        gameName = gameEnum.getName();
        thisBoardType = gameEnum.getBoardType();
        numColumns = gameEnum.getColumns();
        originCountry = gameEnum.getOriginCountry();
        initialSeedsPerBin = gameEnum.getInitialSeedsPerBin();
    }
    
    public int getInitialSeedsPerBin() {
    	return initialSeedsPerBin;
    }
    
    public GameEnum getGameEnum()
    {
    	return gameEnum;
    }

    public String getGameName() {
        return gameName;
    }

    public int getNumRows() {
        return thisBoardType.numberOfRows();
    }

    public int getNumColumns() {
        return numColumns;
    }

    public String getOriginCountry() {
        return originCountry;
    }
}
