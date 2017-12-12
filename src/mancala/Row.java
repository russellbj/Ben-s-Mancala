package mancala;
public class Row {
	/*
	 * @author Nisha and Papa
	 */

	// private static int seeds = rules.GetSeedsPerBin();
	private int seeds = 4; // four seed per hole in Wari
	private Holes[] holes;

	// getter method to return holes
	public Holes[] getHoles() {
		return holes;
	}
 
	
	// setter method for number of holes
	public void setHoles(Holes[] holes) {
		this.holes = holes;
	}

	// constructor
	public Row(int numHoles) {
		holes = new Holes[numHoles];

		for (int i = 0; i < numHoles; i++) {
			holes[i] = new Holes(seeds);
		}
	}

	// returns the number of seeds at a given hole
	public int getSeedsAtIndex(int index) {
		return holes[index].getNumOfSeeds();
	}
}