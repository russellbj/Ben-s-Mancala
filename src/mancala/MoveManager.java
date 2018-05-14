package mancala;

/**
 * 
 * @author Joel
 *
 */
public class MoveManager{
	private int numColumns, numRows;
	public MoveManager(int numColumns, int numRows){
		this.numColumns=numColumns;
		this.numRows=numRows;
	}
	public int[] move(int x, int y, GameBoard gb, int[] score){
		int endingIndex=counterClockwiseMove(x,y,gb);
		score=capture(x,endingIndex,gb,score);
		return score;
	}
	public int counterClockwiseMove(int x, int y, GameBoard gb){
		Integer[] board=gb.getBoardStateArray();
		int rowsToAdd = (numColumns) * (x-1);
		int index = (rowsToAdd + y) - 1;
		int seedsLeftToMove = pickUpSeeds(board[index]);
		board[index]-=seedsLeftToMove;
		int currRow=x;
		while(seedsLeftToMove>0){
			if(x%2==1){
				if(index!=0){
					index--;
					int newVal = board[index];
					newVal++;
					board[index] = newVal;
					seedsLeftToMove--;
				}
				else if(index == 0){
					if(currRow != numRows)
						currRow++;
					else
						currRow = 1;
					index = numColumns - 1;
				}
			}
			else if(currRow % 2 == 0){
				if(index < numColumns*numRows - 1){
					index++;
					int newVal = board[index];
					newVal++;
					board[index] = newVal;
					seedsLeftToMove--;
				}
				else if(index >= numColumns*numRows - 1){
					if(currRow != numRows)
						currRow++;
					else{
						currRow = 1;
					}
					index = (currRow * numColumns);
				}
			}
		}
		for(int i=0;i<board.length;i++){
			gb.realSetBoardState(i, board[i]);
		}
		return index;
	}
	public int pickUpSeeds(int seedsInHole){
		return seedsInHole;
	}
	public int[] capture(int row, int endingIndex, GameBoard gb, int[] score){
		Integer[] board=gb.getBoardStateArray();
		if(board[endingIndex]<2||board[endingIndex]>3)
			return score;
		int endOfOpponentSide;
		if(row==1)
			endOfOpponentSide=0;
		else
			endOfOpponentSide=numRows*numColumns-1;
		int currIndex=endingIndex;
		boolean endOfStreak=false;
		while(currIndex!=endOfOpponentSide&&!endOfStreak){
			if(board[endingIndex]==2||board[endingIndex]==3){
				score[row-1]+=board[endingIndex];
				gb.realSetBoardState(endingIndex, 0);
			}
			else{
				endOfStreak=true;
			}
			if(row==1)
				currIndex--;
			else
				currIndex++;
		}
		return score;
	}
}

class VaiLungThlanMoveManager extends MoveManager {
	private int numColumns, numRows;
	public VaiLungThlanMoveManager(int numColumns, int numRows) {
		super(numColumns, numRows);
		// TODO Auto-generated constructor stub
	}
	
	public void move(int x, int y, GameBoard gb){
		clockwiseMove(x,y,gb);
	}
	public void clockwiseMove(int x, int y, GameBoard gb){
		Integer[] board=gb.getBoardStateArray();
		int rowsToAdd = (numColumns) * (x-1);
		int index = (rowsToAdd + y) - 1;
		int startingIndex=index;
		int seedsLeftToMove = pickUpSeeds(board[index]);
		board[index]=0;
		int currRow=x;
		while(seedsLeftToMove>0){
			if(currRow % 2 == 1){
				if(index < numColumns -1){
					index++;
					int newVal = board[index];
					if(checkCanScore(index,startingIndex))
						newVal++;
					board[index] = newVal;
					seedsLeftToMove--;
				}
				else if(index >= numColumns -1){
					if(currRow != numRows){
						currRow++;
					}
					else
						currRow = 1;
					index = (numRows-1)*numColumns+numColumns;
	
				}
	
			}
			else if(currRow % 2 == 0){
				if(index >(currRow-1)*numColumns){
					index--;
					int newVal = board[index];
					if(checkCanScore(index,startingIndex))
						newVal++;
					board[index] = newVal;
					seedsLeftToMove--;
				}
				else{
					if(currRow != numRows)
						currRow++;
					else
						currRow = 1;
					index = -1;
				}
			}
		}
		for(int i=0;i<board.length;i++){
			gb.realSetBoardState(i, board[i]);
		}
	}
	public boolean checkCanScore(int currIndex, int startIndex){
		return true;
	}
}

class SongoMoveManager extends VaiLungThlanMoveManager{
	private int numColumns, numRows;
	public SongoMoveManager(int numColumns, int numRows) {
		super(numColumns, numRows);
		// TODO Auto-generated constructor stub
	}
	public boolean checkCanScore(int currIndex, int startIndex){
		return currIndex!=startIndex;
	}
}

class AdjiBotoMoveManager extends MoveManager{
	private int numColumns, numRows;
	public AdjiBotoMoveManager(int numColumns, int numRows) {
		super(numColumns, numRows);
		// TODO Auto-generated constructor stub
	}
	public int pickUpSeeds(int seedsInHole){
		return seedsInHole-1;
	}	
}

class QelatMoveManager extends VaiLungThlanMoveManager{
	private int numColumns, numRows;
	public QelatMoveManager(int numColumns, int numRows) {
		super(numColumns, numRows);
		// TODO Auto-generated constructor stub
	}
	public void move(int x, int y, GameBoard gb){
		Integer[] board=gb.getBoardStateArray();
		int rowsToAdd = (numColumns) * (x-1);
		int index = (rowsToAdd + y) - 1;
		if((index<numColumns/2)||(index>=(numColumns*numRows)-2))
			counterClockwiseMove(x,y,gb);
		else
			clockwiseMove(x,y,gb);
	}
}

class EsonXorgulMoveManager extends VaiLungThlanMoveManager{
	private int numColumns, numRows;
	public EsonXorgulMoveManager(int numColumns, int numRows) {
		super(numColumns, numRows);
		// TODO Auto-generated constructor stub
	}
}

class TorguzXorgolMoveManager extends VaiLungThlanMoveManager{
	private int numColumns, numRows;
	public TorguzXorgolMoveManager(int numColumns, int numRows) {
		super(numColumns, numRows);
		// TODO Auto-generated constructor stub
	}
	public int pickUpSeeds(int seedsInHole){
		return seedsInHole-1;
	}
}