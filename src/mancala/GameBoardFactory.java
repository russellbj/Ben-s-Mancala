package mancala;

import java.util.Vector;

public class GameBoardFactory {

    private Vector<GeneratedGameBoard> gameBoardVector = new Vector<>();

    public GameBoardFactory(GameEnum gameToPlay) {
        GeneratedGameBoard thisGameBoard = new GeneratedGameBoard(gameToPlay);
        gameBoardVector.add(thisGameBoard);
    }

    public GeneratedGameBoard GameBoardFactory(int vectorIndex) {
            return gameBoardVector.get(vectorIndex);
        }

    }
