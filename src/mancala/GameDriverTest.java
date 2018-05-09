package mancala;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author melissamclean
 *
 */
public class GameDriverTest {

	@Test
	public void testGetInstance() {
		//ARRANGE
		//ACT 
		GameDriver result = GameDriver.getInstance();
		//ASSERT
		assertNotNull(result);
	}

	@Test
	public void testGetClickedZeroFalse() {
		//ARRANGE
		GameDriver test = new GameDriver();
		test.setClickedZero(false);
		//ACT 
		boolean result = test.getClickedZero();
		//ASSERT
		assertFalse(result);
	}
	
	@Test
	public void testGetClickedZeroTrue() {
		//ARRANGE
		GameDriver test = new GameDriver();
		test.setClickedZero(true);
		//ACT 
		boolean result = test.getClickedZero();
		//ASSERT
		assertTrue(result);
	}

	@Test
	public void testSetClickedZeroTrue() {
		//ARRANGE
		GameDriver test = new GameDriver();
		//ACT
		test.setClickedZero(true);
		boolean result = test.getClickedZero();
		//ASSERT
		assertTrue(result);
	}
	
	@Test
	public void testSetClickedZeroFalse() {
		//ARRANGE
		GameDriver test = new GameDriver();
		//ACT
		test.setClickedZero(false);
		boolean result = test.getClickedZero();
		//ASSERT
		assertFalse(result);
	}

}
