package mancala;

public class Rows {
	Seed newSeed = new Seed();
	int seeds = newSeed.getSeeds();

	public void rows(int numHoles) {
		Holes[] hole = new Holes[numHoles];
		for (int i = 0; i < numHoles; i++) {
			hole[i] = new Holes(seeds);

		}

	}
}
