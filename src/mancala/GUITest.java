package mancala;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import org.junit.Test;
/*
 * @author Nisha
 */
public class GUITest{
	
	static MainWindow instance = new MainWindow();
	
	public static void main (String[]args){
		testAllPanes();
	}	
	
	@Test
	public static void testAllPanes(){
		
		JFrame test = new JFrame();
		test.add(instance.topPane);
		
		instance.topPane.getHeight();
		instance.topPane.getWidth();
		instance.sidePane.getHeight();
		instance.sidePane.getWidth();
		
		System.out.println("Expected:1, Result:" + test.getComponentCount());
		System.out.println("Expected:2, Result:" +instance.topPane.getComponentCount());
		System.out.println("Expected:1, Result:" +instance.sidePane.getComponentCount());
		
		System.out.println("Expected:BorderLayout, Result:" + instance.topPane.getLayout());
		System.out.println("Expected:BorderLayout, Result:" + instance.sidePane.getLayout());
		
		System.out.println("Expected: White, Result:" +instance.topPane.getBackground());
		
		System.out.println("Expected: True, Result:" + instance.tie.isEnabled());
	}
	
}