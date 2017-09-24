package mancala;

public class CollectingHole {
	int numSeeds = 0;
	
	private int count(hole){
		//use other method to get number of seeds and return it
		//make in hole class
		return numSeeds;
	}
	
	public void displayCount(hole){
		//print number of seeds in tally bin underneath it.
		System.out.println(count(hole));
	}
}
