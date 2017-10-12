package mancala;

public class Seeds {
	
	int numberOfHoles = (getRowAmount() * getBinsPerRow()); //Taken from Rules class //Post-Wari: end-bins as holes/	

	public int holes[] = new int[numberOfHoles];	//array of all holes on board 
		
	public void Populate() { //Populate initial board with starting seed values
		
		int startingValue = getSeedsPerBin(); //Taken from Rules class
		
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
	
	
	public void moveSeeds() {  //method for acting upon a bin being chosen for a move
		
		
		int holeIndex = holes[currentHole];  //currentHole 
		int numOfSeeds = currentHole.getSeeds();
		
		for( int i = numOfSeeds; numOfSeeds > 0; i--) {
			holes[holeIndex]++;
			
		}
	}
	
	
	
	
	
	
	
	
	

}
