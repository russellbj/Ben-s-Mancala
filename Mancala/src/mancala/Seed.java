package mancala;

public class Seed {
	

	private Rules rules = Game.rules;
	
	int numberOfHoles = (rules.GetRowAmount() * rules.GetBinsPerRow()); //Taken from Rules class //Post-Wari: end-bins as holes/	

	public int holes[] = new int[numberOfHoles];	//array of all holes on board 
		
	public void Populate() { //Populate initial board with starting seed values
		
		int startingValue = rules.GetSeedsPerBin(); //Taken from Rules class
		
		for(int i=0; 0 < numberOfHoles; i++) { 
			holes[i] = startingValue;
		}
	}
	
	public void setSeedsAtIndex(int numOfSeeds, int index) { //with parameter of index and value, set number of seeds in a bin
		holes[index] = numOfSeeds;
	}
	
	
	public int getSeedsAtIndex(int index) { //with parameter of index, get number of seeds in a bin
		return holes[index];
	}
	
	
	public void moveSeeds(int currentHole) {  //method for acting upon a bin being chosen for a move
		
		int numOfSeeds = getSeedsAtIndex(currentHole);
		
		for( int i = numOfSeeds; numOfSeeds > 0; i--) {
			currentHole++;
			holes[currentHole]++;
		}
	}
}
