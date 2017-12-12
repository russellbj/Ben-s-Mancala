package mancala;

public class Rows {
	Seed newSeed = new Seed();
	
	public Rows(int numHoles) {
		Hole[] hole = new Hole[numHoles];
		for (int i = 0; i < numHoles; i++) {
			hole[i] = new Hole(newSeed.getSeedsAtIndex(i));
		}

	}
}