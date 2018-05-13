package mancala;

import static org.junit.Assert.*;

/**
 * 
 * @author Dakotah, Aaron, & Tallulah
 *
 */

import java.awt.Toolkit;

import javax.swing.JFrame;

import org.junit.Test;

public class MainWindowTest {
	MainWindow mw = new MainWindow();
	Toolkit tk = Toolkit.getDefaultToolkit();  
	
	@Test
	public void testInitScorePane() {
		
		JFrame testFrame = new JFrame();
		
		testFrame.add(mw.scorePane);
		testFrame.setVisible(true);
		testFrame.pack();

	}

	@Test
	public void testGetWariCapture() {
		String expected = "\n    A capture is made when the last bean sown lands in an opponents pit that has one or two beans in it, so the resulting number would be two or three. You then “capture” all seeds in that bin, as well as all opponent bins in a row counting back from the current pit, if they also now contain two or three beans.";
		String result = mw.getWariCapture();
		assertEquals(expected, result);
	}

	@Test
	public void testGameWidth() {
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int expected = (int) (Math.round(xSize * 0.90));
		int result = mw.gameWidth();
		assertEquals(expected,result);
	}

	@Test
	public void testGameHeight() {
		int ySize = ((int) tk.getScreenSize().getHeight());
		int expected = (int) (Math.round(ySize * 0.90));
		int result = mw.gameHeight();
		assertEquals(expected,result);
	}

}
