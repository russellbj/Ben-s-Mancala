package mancala;

/** The various physical layouts of board games which we can draw.
 *  The JavaDocs should have pictures of these types.
 * 
 * @author Darrah Chavey
 */
 
public enum BoardTypes { 

 	/** Games that have only one row of bins for seeds. */
	ONE_ROW,
	
	/** Games that have two rows of bins for seeds. */
	TWO_ROW,

	/** Games that have two rows of bins for seeds, and sow into end bins where seeds are collected. */
	TWO_ROW_WITH_ENDS,
	
	/** Games that have three rows of bins for seeds. */
	THREE_ROW,
	
	/** Games that have four rows of bins for seeds. */
	FOUR_ROW;
	
	/** For each enumerated value, determine the number of rows
	 *  used in that particular game.
	 * 
	 *  @return The number of rows used by the specified game.
	 */
	public int numberOfRows() {
		switch (this) {
			case ONE_ROW: return 1;
			case TWO_ROW: case TWO_ROW_WITH_ENDS: return 2;
			case THREE_ROW: return 3;
			case FOUR_ROW: return 4;
			default: return 0;	// To force an error elsewhere
		}
	}
}