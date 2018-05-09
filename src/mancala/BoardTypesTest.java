package mancala;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author melissamclean
 *
 */
public class BoardTypesTest {

	@Test
	public void numberOfRowsTestOne() {
		//ARRANGE
		BoardTypes test = BoardTypes.ONE_ROW;
		int expected = 1;
		//ACT
		int result = test.numberOfRows();
		//ASSERT
		assertEquals(expected, result);
	}
	
	@Test
	public void numberOfRowsTestTwo() {
		//ARRANGE
		BoardTypes test = BoardTypes.TWO_ROW;
		int expected = 2;
		//ACT
		int result = test.numberOfRows();
		//ASSERT
		assertEquals(expected, result);
	}
	
	@Test
	public void numberOfRowsTestTwoEnds() {
		//ARRANGE
		BoardTypes test = BoardTypes.TWO_ROW_WITH_ENDS;
		int expected = 2;
		//ACT
		int result = test.numberOfRows();
		//ASSERT
		assertEquals(expected, result);
	}
	
	@Test
	public void numberOfRowsTestThree() {
		//ARRANGE
		BoardTypes test = BoardTypes.THREE_ROW;
		int expected = 3;
		//ACT
		int result = test.numberOfRows();
		//ASSERT
		assertEquals(expected, result);
	}
	
	@Test
	public void numberOfRowsTestFour() {
		//ARRANGE
		BoardTypes test = BoardTypes.FOUR_ROW;
		int expected = 4;
		//ACT
		int result = test.numberOfRows();
		//ASSERT
		assertEquals(expected, result);
	}	

}
