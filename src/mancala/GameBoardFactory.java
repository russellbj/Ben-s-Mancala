package mancala;

import java.util.Vector;

/**
 * @author Dhanvanthri
 */
public class GameBoardFactory {

    private Vector<GameBoard> gameBoardVector = new Vector<>();

    public GameBoardFactory(GameEnum gameToPlay) {
        GameBoard thisGameBoard = new GameBoard(gameToPlay);
        gameBoardVector.add(thisGameBoard);
    }

    public GameBoard GameBoardFactory(int vectorIndex) {
            return gameBoardVector.get(vectorIndex);
        }

    }
