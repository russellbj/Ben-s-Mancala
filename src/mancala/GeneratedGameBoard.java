package mancala;

public class GeneratedGameBoard {
    private String gameName;
    private BoardTypes thisBoardType;
    private int numColumns;
    private String originCountry;

    public GeneratedGameBoard(GameEnum gameEnum) {
        gameName = gameEnum.getName();
        thisBoardType = gameEnum.getBoardType();
        numColumns = gameEnum.getColumns();
        originCountry = gameEnum.getOriginCountry();
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
