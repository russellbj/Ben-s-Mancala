package mancala;

public class CollectingHole {
	int numberSeeds;
	
	/**
	 * constructor, instanciates tally bin with 0 seeds in it
	 */
	public CollectingHole(){
		numberSeeds = 0;
	}
	
	/**
	 * prints the number of seeds in the tally bin.
	 */
	public void displayCount(){
		System.out.println(numberSeeds);
	}
	
	/**
	 * method takes in the number of seeds being added to the tally bin and 
	 * calcuates the new number of seeds in the bin.
	 */
	public void addSeeds(int numSeeds){
		//adds seeds to bin
		numberSeeds += numSeeds;
	}
}

