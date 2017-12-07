package mancala;

public class Seed {

	private static Rules rules = Game.rules;
	
	int startingValue;
	
	static int numberOfHoles = (rules.GetRowAmount() * rules.GetBinsPerRow()); //Taken from Rules class //Post-Wari: end-bins as holes/	

	public int getSeeds() { // get number of seeds
		return startingValue;
	}
}