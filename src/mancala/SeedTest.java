package mancala;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SeedTest {
 
	//Test the getSeeds method
	@Test
	public void testGetSeeds() {
		Seed test = new Seed();
		int initial = test.getSeeds(); //Set the value to be tested
		assertEquals(4, initial); //Check that we reutrn the desired value

}

}