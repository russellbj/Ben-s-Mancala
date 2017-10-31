package mancala;

public class CollectingHole {
	int numberSeeds;
	
	/**
	 * constructor, instantiates tally bin with 0 seeds in it
	 */
	public CollectingHole(){
		numberSeeds = 0;
	}
	
	/**
	 * prints the number of seeds in the tally bin.
	 */
	public int displayCount(){
		return numberSeeds;
	}
	
	/**
	 * method takes in the number of seeds being added to the tally bin and 
	 * calcuates the new number of seeds in the bin.
	 */
	public int addSeeds(int numSeeds){
		//adds seeds to bin
		numberSeeds += numSeeds;
		return numberSeeds;
	}
}
