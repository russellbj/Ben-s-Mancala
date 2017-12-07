package mancala;

public class Row {
	//private static int seeds = rules.GetSeedsPerBin();
	private  int seeds = 4; // four seed per hole in Wari
	private Holes[] holes;

	public Holes[] getHoles() {
		return holes;
	}

	public void setHoles(Holes[] holes) {
		this.holes = holes;
	}

	public Row(int numHoles) 
	{
		holes = new Holes[numHoles];
		
		for (int i = 0; i < numHoles; i++) {
			holes[i] = new Holes(seeds);
		}

	}
	
	public int getSeedsAtIndex(int index)
	{
		return holes[index].getNumOfSeeds();
	}
}