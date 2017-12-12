package mancala;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;

/*
 * @author Nisha and Papa
 */
public class RowHolesTest {
	
	@Test
	public void testGetSeeds() {
		// Test the getSeedsAt
		Row test = new Row(6);
		int numSeed = test.getSeedsAtIndex(0);
		System.out.println("4:" + numSeed);

	}
	
	private Holes[] holes;
	int seeds = 4;

	@Test
	public void testRowPositive() {
		//Tests if the the Row Constructor Creates an array of holes and places the correct number elements(seeds)
		// into the array
		int testHoles = 2;
		holes = new Holes[testHoles];

		for (int i = 0; i < testHoles; i++) {
			holes[i] = new Holes(seeds);

			int m = holes[i].getNumOfSeeds();

			System.out.println(m);
			//Prints out after every entry.
		}
	}
}



