package mancala;

import static org.junit.Assert.*;


import org.junit.Test;

/**
 * @author melissa & robbie
 *
 */
public class CollectingHoleTest {
	/**
	 * tests that when initialized the number of seeds is 0
	 */
	@Test
	public void testDisplayCount() {
		CollectingHole test = new CollectingHole();
		int seedNum = test.displayCount();
		assertEquals(0, seedNum);
	}

	/**
	 * tests adding seeds to a collecting hole one time
	 */
	@Test
	public void testAddSeeds() {
		CollectingHole test = new CollectingHole();
		int seedNum = test.addSeeds(5);
		assertEquals(5, seedNum);
	}
	
	/**
	 * tests adding seeds to the collecting holes multiple times to make sure they are actually getting added
	 */
	@Test
	public void testAddSeedsMultipleTimes(){
		CollectingHole test = new CollectingHole();
		int seedNum = test.addSeeds(3);
		seedNum = test.addSeeds(4);
		seedNum = test.addSeeds(3);
		assertEquals(10, seedNum);
	}

}
