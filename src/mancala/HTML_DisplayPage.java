package mancala;

/**
 * 
 * @author Darrah Chavey with minor adjustments by Eric Salvi
 *
 * This class opens an HTML page in a frame.
 * 
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

import javax.swing.*;


public class HTML_DisplayPage extends JFrame implements KeyListener {
	
	/**
	 * Serial ID to allow implementation of Serializable. We don't actually
	 * implement saving the file this way, but it is expected by "JFrame".
	 */
	private static final long serialVersionUID = 0;
	
	/** Keep a singleton copy of this object, available to other methods. */
	private static HTML_DisplayPage theHelpScreen;
	
	/** The Pane into which different HTML pages would be placed. */
	private static JEditorPane HTML_Pane;

     /** Set the link to the web page which should be displayed, then 
     *  show the appropriate frame.
     *  
     * @param currentURL The filename or URL to be displayed in this frame.
     */
    public static void displayURL(String currentURL) {
    	if (theHelpScreen == null) {
    		theHelpScreen = new HTML_DisplayPage( );
    	}
    	theHelpScreen.setVisible(false);
    	URL pageToDisplay = HTML_DisplayPage.class.getResource(currentURL);
        if (pageToDisplay != null) {
            try {
            	HTML_Pane.setPage(pageToDisplay);
            } catch (IOException e) {
                System.out.println("Attempted to read a bad URL: " + pageToDisplay);
                return;
            }
        } else {
        	System.out.println("Couldn't find file: " + pageToDisplay);
            return;
        }

    	theHelpScreen.setVisible(true);
	}

    /**
     *  Create a single display page the first time someone asks for a Help screen.
	 *  If someone asks for another page, we simply replace the contents of this page.
	 *  (Singleton design pattern)
     */
	private HTML_DisplayPage(  ) {
		super("Quick Start Menu");	// Set the main panel's name
	   	
		// I don't really know what this does, but it was recommended:
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		
		// Try to build the HTML page:
        HTML_Pane = new JEditorPane();
        HTML_Pane.setEditable(false);
        HTML_Pane.addKeyListener(this);

        // Now put it inside the appropriate nesting of panels:
        JScrollPane helpScreen = new JScrollPane(HTML_Pane);
		getContentPane().add(helpScreen, BorderLayout.CENTER);
		
		// Set the location and size of the displayed page:
		helpScreen.setPreferredSize(new Dimension(1000, 600));
		helpScreen.setLocation(20,20);
		this.pack();
		this.setVisible(true);
    }
	
	
// Key Listener methods
	
	/** 
	 * The key listener is installed to listen to a command to close the window (cmd-W or ctrl-W).
	 */
	public void keyPressed(KeyEvent e) { 
		// Required for Key Listener. We ignore it.
	}
	public void keyReleased(KeyEvent e) {
		keyTyped(e);
	}
	public void keyTyped(KeyEvent e)  {
		char keyTyped = e.getKeyChar();
		if (keyTyped == 'w') {
			if (e.isMetaDown() || e.isControlDown() || e.isAltDown()) {
				this.setVisible(false);
			}
		}
	}
	    
 }
