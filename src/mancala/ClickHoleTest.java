package mancala;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.junit.Test;
/**
 * 
 * @author Joel
 *
 */
public class ClickHoleTest {

	@Test
	public void test() {
		MainWindow junit=new MainWindow();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int vOffset=(int)(5*screenSize.getHeight()/385);
		int hOffset=(int)(10*screenSize.getWidth()/594);
		int vBorder=(int)(100*screenSize.getHeight()/385);
		int hBorder=(int)(40*screenSize.getWidth()/594);
		int holeWidth=(int)((screenSize.getWidth()-hBorder*2-hOffset*5)/6);
		int holeHeight=(int)((screenSize.getHeight()-vOffset-vBorder*2)/2);
		int[] topLeftCorner=junit.clickHole(0, 0, screenSize, vOffset, hOffset, vBorder, hBorder, false, 2, 6);
		int[] bottomLeftCorner=junit.clickHole(0, screenSize.height-5, screenSize, vOffset, hOffset, vBorder, hBorder, false, 2, 6);
		int[] topRightCorner=junit.clickHole(screenSize.width-5, 0, screenSize, vOffset, hOffset, vBorder, hBorder, false, 2, 6);
		int[] bottomRightCorner=junit.clickHole(screenSize.width-5, screenSize.height-5, screenSize, vOffset, hOffset, vBorder, hBorder, false, 2, 6);
		assertNull(topLeftCorner);
		assertNull(bottomLeftCorner);
		assertNull(topRightCorner);
		assertNull(bottomRightCorner);
		int[] middleHorizontal=junit.clickHole(hBorder+holeWidth+hOffset/2, vBorder+holeHeight/2, screenSize, vOffset, hOffset, vBorder, hBorder, false, 2, 6);
		assertNull(middleHorizontal);
		int[] middleVertical=junit.clickHole(hBorder+holeWidth/2, vBorder+holeHeight+vOffset/2, screenSize, vOffset, hOffset, vBorder, hBorder, false, 2, 6);
		assertNull(middleVertical);
		for(int cols=0;cols<6;cols++){
			for(int rows=0;rows<2;rows++){
				int[] expected=new int[2];
				expected[0]=rows+1;
				expected[1]=cols+1;
				int[] inHole=junit.clickHole(hBorder+cols*hOffset+cols*holeWidth+holeWidth/2, vBorder+rows*vOffset+rows*holeHeight+holeHeight/2, screenSize, vOffset, hOffset, vBorder, hBorder, false, 2, 6);
				assertEquals(inHole[0], expected[0]);
				assertEquals(inHole[1], expected[1]);
			}
		}
	}

}
