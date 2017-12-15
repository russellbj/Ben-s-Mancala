/**
 * 
 */
package mancala;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Dimitrius
 *
 */
public class TurnTest {

	/**
	 * Test method for {@link mancala.Turn#switchPlayer()}.
	 */
	@Test
	public void testSwitchPlayer() {
		Turn test = new Turn();
		test.switchPlayer();
		long currPlayer = test.getCurrPlayer();
		assertEquals(2, currPlayer);
	}

	/**
	 * Test method for {@link mancala.Turn#getCurrPlayer()}.
	 */
	@Test
	public void testGetCurrPlayer() {
		Turn test = new Turn();
		int currPlayer = test.getCurrPlayer();
		assertEquals(1, currPlayer);
	}

	/**
	 * Test method for {@link mancala.Turn#getCurrTurn()}.
	 */
	@Test
	public void testGetCurrTurn() {
		Turn test = new Turn();
		int currTurn = test.getCurrTurn();
		assertEquals(1, currTurn);
		test.switchPlayer();
		test.switchPlayer();
		currTurn = test.getCurrTurn();
		assertEquals(2, currTurn);
	}
	
	

	/**
	 * Test method for {@link mancala.Turn#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		Turn test = new Turn();
		Turn instance = test.getInstance();
		Turn instanceTwo = test.getInstance();
		assertEquals(instance, instanceTwo);
		
	}

}
