 /*
 * Created by Darrah Chavey, April 4, 2006.
 * Extended by Software Engineering students, Spring 2006.
 * Continued development by Darrah Chavey
 * v. 0.8.7 , September 5, 2017.
 */

package mancala;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaName;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

// The following is not yet available with Java 1.8
//import com.apple.eawt.AppEvent;
//import com.apple.eawt.Application;
//import com.apple.eawt.QuitHandler;
//import com.apple.eawt.QuitResponse;

import beloit.sona.DrawingCanvas.Modes;
import beloit.sona.DrawingCanvas.Spacing;

/*
 * Sequence of events as the program starts up:
 * 		MainWindow theProgram = new MainWindow();
 *          Does nothing except to create the main window into which other stuff goes.
 * 		theProgram.initPanesAndGUI();
 *          Create all the drawing panes used in the main window, and place them inside
 *          that window. Create all menus and menu items. We do this before 
 *          initProgramInternals, so that we can pass these items into the program 
 *          internals as we create them. 
 *		theProgram.initProgramInternals();
 *          Create the program objects that correspond to these various GUI 
 *          elements. This includes: thePreferenceList; mainDrawingCanvas; 
 *          and theFileHandler.  
 *      The event handling thread then takes over all further activity.
 */

/**
 * MainWindow is where most objects in this program live. The MenuBar
 * and ToolBar live inside MainWindow. We use JMenuBar and JToolBar to
 * implement these classes. The other main classes also live inside MainWindow:
 * a DrawingCanvas called mainDrawingCanvas, the PreferenceList, and the FileHandler.
 * All the ToolBar and MenuBar functionalities are implemented by this Class.
 * <P>
 * When initializing the various objects, this class is responsible for 
 * passing in references from certain objects to others, so that each object
 * is able to communicate with objects necessary to it in order for it to
 * carry out its tasks.
 * 
 * @author Darrah Chavey
 * @author and contributions from most of his 2007 Software Engineering class.
 * 
 */
@SuppressWarnings("serial") // We don't use the serialVersionUID for version control
public class MainWindow extends JFrame 
           implements WindowListener, ActionListener {  // BUG: and QuitHandler?
	
	
/* MAJOR SECTIONS OF THIS FILE ARE SEPARATED BY ALL CAPS COMMENTS SUCH AS THIS */

	
	
	/* SHARED CLASS DATA, OTHER THAN THE GUI ELEMENTS */
	
	/** Are we currently showing the Celtic walls tool? For development purposes.
	 *  This is not yet fully implemented, hence this is set to false.
	 */
	final public static boolean SHOW_CELTIC_TOOLS = false;
	
	/**
	 * If we have several windows open, keep track of the number of windows
	 * we have EVER opened, so we can give a new name to every windows.
	 */
	protected static int numOfWindows = 0;
	
	/**
	 * If we have several windows open, keep track of the number of windows
	 * that are STILL open, so we can quit when closing the last one.
	 */
	protected static int numOfOpenWindows = 0;
	
	
	
/* DECLARATIONS OF OTHER OBJECTS WITHIN THE SYSTEM. MainWindow ACTS AS THE PRIMARY
 * MODE OF INITIAL COMMUNICATION BETWEEN THESE OBJECTS.                           */

    /**
     * mainDrawingCanvas is the center of the whole program. 
     */
	protected DrawingCanvas mainDrawingCanvas;
    
    /**
     * thePreferenceList stores many program settings, and the program
     * queries preference list to do, or draw things in accordance with
     * the preference settings the user has set up.
     * For specifics on the capabilities of PreferenceList, see AD section 12.
     */
	protected PreferencesList thePreferences;
    
    /**
     * Handles writing out program state to disc, and loading program state from the
     * hard drive.
     */
	protected FileHandler theFileHandler;
    	
	
	
	/*  CHOICES USED BY VARIOUS MENUS.  */

    /** The menu choices available for pen width used in making the drawing. */
	protected static final String[] widths = {"1", "2", "3", "4", "5", "6", "7", "8"};

    /** The menu choices available for pen width used in making the drawing. */
	protected static final String[] sizes = {"1", "2", "3", "4", "5", "6", "7", "8", "9",
		"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" };

	/** The menu choices available for how quickly to make the drawing. */
	protected static final String[] speeds = {"Slower", "Slow", "Normal", "Fast", "Faster"};
	
	/** The menu choices available from the Help menu. Stored this way, because they are the most
	 *  likely to change on a semi-regular basis as the program develops.	 */
	protected static final String[] helpOptions = {"About the Authors", "Introduction to Sona", "Program Tutorials"};
	protected static final String[] tutorialOptions = {"Basic Skills", "Using Walls", "Making One-Line Sona"};

	

    /* THE MENUS AVAILABLE WITHIN THE PROGRAM */
    
    /**
     * The object menuBar contains the interface structure for the menu bar at the
     * top of the main window. This object should contain the JMenu objects
     * fileMenu, editMenu, optionsMenu, componentsMenu, and helpMenu.
     */
    protected JMenuBar menuBar;
    
    /**
     * The menu fileMenu contains the interface structure for the
     * contents of the "File" section of the menu bar. This object
     * should contain the JMenuItem objects saveState, saveStateAs,
     * print, and exit.
     */
    protected JMenu fileMenu;
    
    /**
     * The menu editMenu contains the interface structure for the contents
     * of the "Edit" section of the menu bar. This object should contain
     * the JMenuItem objects select, cut, copy, paste, and clear.
     */
    protected JMenu editMenu;
    
    /**
     * The menu clearMenu handles the "Clear" submenu options. This menu 
     * should contain the JMenuItem objects clearLines, clearWalls, clearDots,
     * and clearAll.
     */
    protected JMenu clearMenu;

    /**
     * The menu dotsLayoutMenu handles various options associated with the dots.
     * In particular, you can turn show or hide the dots, and set the dot spacing
     * to fine, medium, or coarse.
     */
    protected JMenu dotsLayoutMenu;
    

    /**
     * The "Walls" menu allows you to: show/hide the walls; specify whether 
     * bounces will be straight or curved; limit yourself to Sona or Celtic
     * walls, or allow a mix of Sona & Celtic walls.
     */
    protected JMenu wallsMenu;

    /**
     * The menu helpMenu contains the interface structure for the contents
     * of the "Help" section of the menu bar. This object should contain
     * the JMenuItem objects help and about.
     */
    protected JMenu helpMenu;
    
    

    /* THE MENU COMMANDS AVAILABLE INSIDE THOSE MENUS */
    
    /* MENU ITEMS INSIDE THE _FILE_ MENU */
    
    /**
     * The menu item fileNew handles the menu command "New" to create a
     * new drawing window. It resides in the "File" menu.
     */
    protected JMenuItem fileNew;
    
    /**
     * The menu item fileOpen handles the menu command "Open" to open a
     * previously saved session. It resides in the "File" menu.
     */
    protected JMenuItem fileOpen;
    
    /**
     * The menu item fileClose handles the menu command "Close" in the "
     * File" menu. It closes the current window and, if that's the last
     * window, it exits the program.
     */
    protected JMenuItem fileClose;
   
    /**
     * The menu item fileSave handles the menu command "Save" to save the
     * current state of the program. It resides in the "File" menu.
     */
    protected JMenuItem fileSave;
   
    /**
     * The menu item saveStateAs handles the menu command "Save As..." to
     * save the current state of the program based on a user's input for the
     * file path and name. It resides in the "File" menu.
     */
    protected JMenuItem fileSaveAs;
    
    /**
      * The menu item print handles the menu command "Print" to start the printing
      * routine. It resides in the "File" menu.
      */
     protected JMenuItem filePrint; 

    /**
     * The menu item fileExit handles the menu command "Exit" to quit the
     * program. It resides in the "File" menu.
     */
    protected JMenuItem fileExit;
    

    
    /* MENU ITEMS INSIDE THE _EDIT_ MENU */
    
    /**
     * The cut, copy and paste menu items reside in the "Edit" menu
     * and handle the menu commands to perform standard cut, copy
     * and paste operations on text fields.
     */
    protected JMenuItem editSelect, editCut, editCopy, editPaste, editClear, editUndo;
   
    /**
     * The menu item editPreferences resides in the "Edit" menu
     * and handles a request to update the preferences for the
     * program.
     */
    protected JMenuItem editPreferences;
    

    
    /* MENU ITEMS INSIDE THE _CLEAR_ MENU */
    
    /**
     * The menu item clearAll handles the menu command "Clear All" to
     * clear dots, walls, and sona curves. It lives in the "Clear" menu.
     */
    protected JMenuItem clearAll;
    
    /**
     * The menu item clearLines erases the lines of the drawing, but leaves
     * the dots and the walls in place.
     */
    protected JMenuItem clearLines;
    
    /**
     * The menu item clearWalls erases the walls of the drawing, but leaves
     * the dots and the sona curves in place.
     */
    protected JMenuItem clearWalls;
    
    /**
     * The menu item clearDots erases the existing dots of the drawing, but leaves
     * the walls and the sona curves in place.
     */
    protected JMenuItem clearDots;
    

    
    /* MENU ITEMS INSIDE THE _DOTS_ MENU */
    
    /** Group the dot spacing options as a collection of Radio Buttons. */
    protected ButtonGroup dotSpacing;
    
    /**
     * The menu item "fineSpacing" sets the dot spacing to fine, currently 
     * set to be a 32 pixel grid.
     */
    protected JRadioButtonMenuItem fineSpacing;
    
    /**
     * The menu item "mediumSpacing" sets the dot spacing to medium, currently 
     * set to be a 48 pixel grid.
     */
    protected JRadioButtonMenuItem mediumSpacing;
    
    /**
     * The menu item "coarseSpacing" sets the dot spacing to coarse, currently 
     * set to be a 96 pixel grid.
     */
    protected JRadioButtonMenuItem coarseSpacing;

    /**
     * The menu "showDots" is used both to show and to hide the sona dots, 
     * and/or the background dots.
     * Three options in this menu: Hide all dots, Show Sona dots, Show all dots.
     */
    protected JMenu showDots;

    /** Group the options for showing dots as a collection of Radio Buttons. */
    protected ButtonGroup dotsShowing;

    /**
     * This menu choice hides all of the dots on the screen. It's over-ridden
     * if you add any rectangles of dots to the screen.
     */
    protected JRadioButtonMenuItem noDots;
    
    /**
     * This menu choice requests that the sona grid dots be shown, but that
     * the background dots are hidden.
     */
    protected JRadioButtonMenuItem showSonaDots;
    
    /**
     * This menu choice requests that both sona dots and background dots be 
     * shown. The background dots will be shown lighter..
     */
    protected JRadioButtonMenuItem showAllDots;
    

    
    /* MENU ITEMS INSIDE THE _WALLS_ MENU */
    
    /**
     * The menu item "showWalls" allows the user to show the walls they've 
     * placed, or else to hide them. Showing is forced when you begin to add
     * or delete additional walls; hiding is often useful to get the best pics.
     */
    protected JMenuItem showWalls;
    
    /**
     * The menu item "curvedBounces" allows you to specify curved lines when
     * bouncing off a wall, which is the norm, or straight bounces to get the
     * Bushong style sharp corners. Sharp corners rarely give a good appearance
     * when applied to internal walls. However, there are some multi-line 
     * kolam where using straight edges for some lines and curved for others
     * is appropriate.
     */
    protected JMenuItem curvedBounces;
    
    /**
     * The "wallsAllowed" sub-menu allows you to specify whether you are limited
     * to Sona or Celtic walls, or can have a mix of Sona & Celtic walls.
     */
    protected JMenu wallsAllowed;

    /** Group the options for placing walls as a collection of Radio Buttons. */
    protected ButtonGroup wallChoices;

    /**
     * The menu item "sonaWalls" allows you to limit the drawing to only using
     * Sona style walls. If selected when there are Celtic walls in the layout,
     * you will be prompted if you really want to, then all Celtic walls will
     * be removed.
     */
    protected JRadioButtonMenuItem sonaWalls;
    
    /**
     * The menu item "celticWalls" allows you to limit the drawing to only using
     * Celtic style walls. If selected when there are Sona walls in the layout,
     * you will be prompted if you really want to, then all Sona walls will
     * be removed.
     */
    protected JRadioButtonMenuItem celticWalls;  
    
    /**
     * The menu item "bothWalls" allows you to include both Celtic walls and
     * Sona walls in the layout.
     */
    protected JRadioButtonMenuItem bothWalls;  
    

    
    /* MENU ITEMS INSIDE THE _HELP_ MENU */
    
    /**
     * The menu item helpAboutProgram handles the menu command "About Sona Program" to open a dialog
     * box with information about the program. It resides in the "Help"
     * menu.
     */
    protected JMenuItem helpAboutProgram;

    /**
     * The menu item helpSonaDrawings handles the menu command "About Sona Drawings" to open the help file.
     * It resides in the "Help" menu.
     */
    protected JMenuItem helpSonaIntroduction;

    /**
     * The menu item helpSona handles the menu command "Sona Help" to open the help file.
     * This provides help with how to use the program.
     * It resides in the "Help" menu.
     */
    protected JMenuItem helpTutorials;
 
    
    
    /* GUI PANES AND ELEMENTS, OTHER THAN THE MENUS */
    
    /** A top level pane for holding both the menus and the tools */
	protected JPanel menuAndTools;
	
    /** A 2nd level pane for holding the menus */
	protected JPanel menuBarPane;
	
    /** A 2nd level pane for holding the tool buttons */
	protected JPanel toolbarPane;
	public void changeFocus() {
		if (toolbarPane != null)
			toolbarPane.grabFocus();
	}

    /** A top level pane for holding the drawing canvas */
	protected JScrollPane drawingPane;
	
    /** A top level pane for holding the sona name field & draw/stop buttons */
	protected JPanel bottomPane;
	
    /** A 2nd level pane for the labels associated with the sona name.  */
	protected JPanel sonaNameLabel;
	
    /** A 2nd level pane for the sona name. */
	protected JPanel sonaNamePane;
	
    /** A 2nd level pane for holding the "Draw" and "Stop" buttons   */
	protected JPanel drawButtons;
	
	/** Holds the sona name assigned by the user.	 */
	protected JTextField sonaName;
	
	/** The icons used for the drawCanvas "mode" radio buttons. */
	protected ImageIcon[] modeIcons;
	
	/** The group of radio buttons for selecting the drawing mode. */
	protected ButtonGroup modeGroup;
	
    

    
    /* AND NOW, ON TO THE METHODS FOR THIS CLASS... */
    
	
	/** Create the main program window(s). Keep track of the number of such
	 *  windows that are open within the program. The constructor does nothing 
	 *  but initialize a window with an appropriate title. The calling structure 
	 *  should be: you call the constructor; you then call "initComponents()", 
	 *  which creates the actual GUI elements and places them within this window;
	 *  then you call "initProgramInternals()" which sets up the other objects
	 *  used by the program, many of which need to be able to communicate with 
	 *  these GUI elements. 
	 */
	public MainWindow( ) {
		super( "Sona Drawing Program" );
		addWindowListener(this);
		numOfWindows++;
		numOfOpenWindows++;
		if (numOfWindows>1) {
			setTitle( "Sona Drawing Program #" + numOfWindows );
		}
	}

	
	
	/**
	 * When the preferences have been changed by the user, we need to update
	 * the values displayed by various menus, toolbars, etc. We set the toolbar
	 * displayed values, and set checks on the appropriate menu items.
	 */
	public void updatePreferencesDisplayed() { 		
		// Set check marks on appropriate menu options choices:	
		int selectedMenuItem = thePreferences.getPrefValue(PrefSettings.PEN_WIDTH);
		// Our numbers are 1..N; Java stores 0..N-1, so it's natural to translate
		penWidth.setSelectedIndex( selectedMenuItem-1 );
		selectedMenuItem = thePreferences.getPrefValue(PrefSettings.DRAW_SPEED);
		drawingSpeed.setSelectedIndex( selectedMenuItem );
		selectedMenuItem = thePreferences.getPrefValue(PrefSettings.DOT_RECTANGLE_WIDTH);
		rectangleWidth.setSelectedIndex( selectedMenuItem );
		selectedMenuItem = thePreferences.getPrefValue(PrefSettings.DOT_RECTANGLE_HEIGHT);
		rectangleHeight.setSelectedIndex( selectedMenuItem );
		selectedMenuItem = thePreferences.getPrefValue(PrefSettings.PEN_WIDTH);
		penWidth.setSelectedIndex( selectedMenuItem-1 );
		
		// Make the current screen bigger, if Preferences require it, but no smaller.
		if (thePreferences.getPrefValue(PrefSettings.SCREEN_WIDTH) > getWidth()) {
			this.setSize(thePreferences.getPrefValue(PrefSettings.SCREEN_WIDTH),getHeight());	
		}
		if (thePreferences.getPrefValue(PrefSettings.SCREEN_HEIGHT) > getHeight()) {
			this.setSize(getWidth(), thePreferences.getPrefValue(PrefSettings.SCREEN_HEIGHT));	
		}
		if (thePreferences.getPrefValue(PrefSettings.SHOW_WALLS) == 0) {
			showWalls.setText("Show Walls");
		} else {
			showWalls.setText("Hide Walls");
		}
		
		// This is slightly inefficient, but simple to validate.
		selectedMenuItem = thePreferences.getPrefValue(PrefSettings.SHOW_DOTS);
		noDots.setSelected( selectedMenuItem==0 );
		showSonaDots.setSelected( selectedMenuItem==1 );
		showAllDots.setSelected( selectedMenuItem==2 );
		
		selectedMenuItem = thePreferences.getPrefValue(PrefSettings.DOT_SPACING);
		fineSpacing.setSelected( selectedMenuItem==0 );
		mediumSpacing.setSelected( selectedMenuItem==1 );
		coarseSpacing.setSelected( selectedMenuItem==2 );
		
		selectedMenuItem = thePreferences.getPrefValue(PrefSettings.WHICH_WALLS);
		sonaWalls.setSelected( selectedMenuItem==0 );
		celticWalls.setSelected( selectedMenuItem==1 );
		bothWalls.setSelected( selectedMenuItem==2 );
	}
	
	
	/* METHODS TO INITIALIZE THE VARIOUS PANES USED IN THE GUI */
	
	/**
	 * This sets up the components inside the bottom pane of the GUI interface.
	 * It creates the required label and text field for the sona Name; puts in
	 * "Draw" and "Stop" buttons; then packs everything into the JPanel.
	 */
	protected void initBottomPane() {
		bottomPane = new JPanel();
		bottomPane.setLayout( new BorderLayout( ) );

		// Build the sub-pane containing the label fields
		sonaNameLabel = new JPanel();
		sonaNameLabel.setLayout( new GridLayout(1,1) );
		sonaNameLabel.setBorder( new EmptyBorder(0, 5, 0, 8));		// Padding on left & right
		JLabel nameLabel = new JLabel("Sona Name:");
		nameLabel.setHorizontalAlignment(JTextField.RIGHT);
		sonaNameLabel.add(nameLabel);
		
		// Build the sub-pane containing the text field for the sona name entry
		sonaNamePane = new JPanel();
		sonaName = new JTextField("");
		sonaNamePane.setLayout( new GridLayout(1, 1) );
		sonaNamePane.add(sonaName);

		// Build the sub-pane for the name of the drawing
		drawButtons = new JPanel();
		drawButtons.setLayout( new BoxLayout(drawButtons, BoxLayout.X_AXIS) );
		drawButtons.setBorder( new EmptyBorder(0, 8, 0, 12));		// Padding on left & right
		
		// Now add these sub-panes to the bottom pane
		bottomPane.add(sonaNameLabel, "West");
		bottomPane.add(sonaNamePane, "Center");
		bottomPane.add(drawButtons, "East");
	}
	
	/**
	 * Create the menu pane. Add all the menus, then add the menu items,
	 * including the hierarchical menus with their menu items.<BR><BR>
	 * This is declared as public only for purposes of the testing modules.
	 */
	protected void initMenuBarPane() {
		menuBarPane = new JPanel();
		
		// File menu items
		fileNew = new JMenuItem("New");
		fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.META_MASK));
		fileNew.addActionListener(this);
		fileOpen = new JMenuItem("Open");
		fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.META_MASK));
		fileOpen.addActionListener( this);		
		fileClose = new JMenuItem("Close");
		fileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.META_MASK));
		fileClose.addActionListener(this);		
		fileSave = new JMenuItem("Save");
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.META_MASK));
		fileSave.addActionListener(this);
		fileSaveAs = new JMenuItem("Save As");
		fileSaveAs.addActionListener(this);
		filePrint = new JMenuItem("Print");
		filePrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.META_MASK));
		filePrint.addActionListener(this);
		fileExit = new JMenuItem("Exit");
		fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.META_MASK));
		fileExit.addActionListener(this);
		
		// Edit menu items
	    editSelect = new JMenuItem("Select");
	    editSelect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.META_MASK));
	    editSelect.addActionListener(this);	
	    editCopy = new JMenuItem("Copy");
		editCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.META_MASK));
		editCopy.addActionListener(this);
		editCut = new JMenuItem("Cut");
		editCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.META_MASK));
		editCut.addActionListener(this);
		editPaste = new JMenuItem("Paste");
		editPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.META_MASK));
		editPaste.addActionListener(this);
	    editClear = new JMenuItem("Clear Selection");
//	    editClear.setAccelerator(KeyStroke.getKeyStroke('\b') ); using KeyReleased
	    editClear.addActionListener(this);
		editUndo = new JMenuItem("Can't Undo");
		editUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.META_MASK));
		editUndo.addActionListener(this);
		editPreferences = new JMenuItem("Preferences...");
		editPreferences.addActionListener(this);
	    editSelect.setEnabled(true);
		editCopy.setEnabled(false);
		editCut.setEnabled(false);
		editPaste.setEnabled(false);
	    editClear.setEnabled(true);
		editUndo.setEnabled(false);
		editPreferences.setEnabled(false);
		
		// Clear menu items		
		clearLines = new JMenuItem("Clear Lines");
		clearLines.addActionListener(this);
		clearWalls = new JMenuItem("Clear Walls");
		clearWalls.addActionListener(this);
		clearDots = new JMenuItem("Clear Dots");
		clearDots.addActionListener(this);
		clearAll = new JMenuItem("Clear All");
		clearAll.addActionListener(this);
	
		// Dot spacing menu items
		fineSpacing = new JRadioButtonMenuItem("Fine Spacing",true);
		fineSpacing.addActionListener(this);
		mediumSpacing = new JRadioButtonMenuItem("Medium Spacing",false);
		mediumSpacing.addActionListener(this);
		coarseSpacing = new JRadioButtonMenuItem("Coarse Spacing",false);
		coarseSpacing.addActionListener(this);
		dotSpacing = new ButtonGroup();
		dotSpacing.add(fineSpacing);
		dotSpacing.add(mediumSpacing);
		dotSpacing.add(coarseSpacing);
		
		// Show Dots is a hierarchical sub-menu of the dot spacing menu.
		showDots = new JMenu("Show Which Dots");	// Default value: Dots is on
		showDots.addActionListener(this);
		noDots = new JRadioButtonMenuItem("Hide All Dots",false);
		noDots.addActionListener(this);
		showSonaDots = new JRadioButtonMenuItem("Show Design Dots",true);
		showSonaDots.addActionListener(this);
		showAllDots = new JRadioButtonMenuItem("Show Design & Background Dots",false);
		showAllDots.addActionListener(this);
		dotsShowing = new ButtonGroup();
		dotsShowing.add(noDots);
		dotsShowing.add(showSonaDots);
		dotsShowing.add(showAllDots);

		// Walls menu items
		showWalls = new JMenuItem("Hide Walls");
		showWalls.addActionListener(this);
		curvedBounces = new JMenuItem("Straight Bounces");
		curvedBounces.setEnabled(false);
		curvedBounces.addActionListener(this);
		wallsAllowed = new JMenu("Walls Allowed");
		sonaWalls = new JRadioButtonMenuItem("Sona Walls",true);
		sonaWalls.addActionListener(this);
		celticWalls = new JRadioButtonMenuItem("Celtic Walls",false);
		celticWalls.setEnabled(SHOW_CELTIC_TOOLS);
		celticWalls.addActionListener(this);
		bothWalls = new JRadioButtonMenuItem("Sona or Celtic Walls",false);
		bothWalls.setEnabled(false);
		bothWalls.addActionListener(this);
		wallChoices = new ButtonGroup();
		wallChoices.add(sonaWalls);
		wallChoices.add(celticWalls);
		wallChoices.add(bothWalls);

		// Help menu items
		helpAboutProgram = new JMenuItem(helpOptions[0]);
		helpAboutProgram.addActionListener(this);
		helpSonaIntroduction = new JMenuItem(helpOptions[1]);
		helpSonaIntroduction.addActionListener(this);
		helpTutorials = new JMenu(helpOptions[2]);
		JMenuItem tutorialMenu;
		for (int tutorial = 0; tutorial < tutorialOptions.length; tutorial++) {
			tutorialMenu = new JMenuItem(tutorialOptions[tutorial]);
			tutorialMenu.setEnabled(true);
			tutorialMenu.addActionListener(this);
			helpTutorials.add(tutorialMenu);
		}		
		helpAboutProgram.setEnabled(false);
		helpSonaIntroduction.setEnabled(true);
		helpTutorials.setEnabled(true);

		// Set up the file menu
		fileMenu = new JMenu("File");
		fileMenu.add(fileNew);
		fileMenu.add(fileOpen);
		fileMenu.add(fileClose);
		fileMenu.add(fileSave);
		fileMenu.add(fileSaveAs);
		fileMenu.add(filePrint);
		fileMenu.add(fileExit);
		
		// Set up the edit menu
		editMenu = new JMenu("Edit");
		editMenu.add(editSelect);
		editMenu.add(editCut);
		editMenu.add(editCopy);
		editMenu.add(editPaste);
		editMenu.add(editClear);
		editMenu.add(editUndo);
		editMenu.add( new JSeparator( ) );
		editMenu.add(editPreferences);
		
		// Set up the clear menu
		clearMenu = new JMenu("Clear");
		clearMenu.add(clearLines);
		clearMenu.add(clearWalls);
		clearMenu.add(clearDots);
		clearMenu.add(clearAll);

		// Set up the Dots menu
		dotsLayoutMenu = new JMenu("Dots");
		dotsLayoutMenu.add(fineSpacing);
		dotsLayoutMenu.add(mediumSpacing);
		dotsLayoutMenu.add(coarseSpacing);
		showDots.add(noDots);
		showDots.add(showSonaDots);
		showDots.add(showAllDots);
		dotsLayoutMenu.add(showDots);

		// Set up the Walls menu
		wallsMenu = new JMenu("Walls");
		wallsMenu.add(showWalls);
		wallsMenu.add(curvedBounces);
		wallsAllowed.add(sonaWalls);
		wallsAllowed.add(celticWalls);
		wallsAllowed.add(bothWalls);
		wallsMenu.add(wallsAllowed);

		// Set up the help menu
		helpMenu = new JMenu("Help");
		helpMenu.add(helpAboutProgram);
		helpMenu.add(helpSonaIntroduction);
		helpMenu.add(helpTutorials);
		
		// Create the menuBar to contain the menus
		menuBar = new JMenuBar();
		
		// Add the menus to the bar
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(clearMenu);
		menuBar.add(dotsLayoutMenu);
		menuBar.add(wallsMenu);
		menuBar.add(helpMenu);
		
		// Add the menu bar to the appropriate pane
		menuBarPane.setLayout(new GridLayout(1,1));
		menuBarPane.add(menuBar);
	}
	
	/** 
	 * A pop-up list of choices for the width of the pen to use for drawing the
	 * pattern.
	 */
	private JComboBox<String> penWidth;
	
	/** 
	 * A pop-up list of choices for the width of the rectangle of dots to add
	 * to the drawing screen.
	 */
	private JComboBox<String> rectangleWidth;

	/** 
	 * A pop-up list of choices for the height of the rectangle of dots to add
	 * to the drawing screen.
	 */
	private JComboBox<String> rectangleHeight;
	
	/** 
	 * A pop-up list of choices for the speed at which to draw the pattern.
	 */
	private JComboBox<String> drawingSpeed;
	
	/**
	 * The mode buttons that the user clicks to determine the current mode,
	 * i.e. how to interpret a mouse click.
	 */
	private JRadioButton dotsMode, lineMode, eraserMode, sonaMode, celticMode, selectionMode;
		
	/** 
	 *  Create the toolbar pane, with the various tools and buttons
	 */
	protected void initToolbarPane() {
		toolbarPane = new JPanel();
//		toolbarPane.setBackground(new Color(255,0,0,100));
		
		// Build the sub-pane containing the selection options
		JPanel selectOptions = new JPanel();
		selectOptions.setLayout( new BoxLayout(selectOptions, BoxLayout.X_AXIS) );
		selectOptions.add( new JLabel("  Mode: "));
		
		// Create the icons that are used like buttons for the tools
		modeIcons = new ImageIcon[10];
		modeIcons[0] = createImageIcon("images/dots.gif","Dot rectangle tool");
		modeIcons[1] = createImageIcon("images/line.gif","Draw a line");
		modeIcons[2] = createImageIcon("images/eraserOff.gif","Eraser tool");
		modeIcons[3] = createImageIcon("images/sonaWall.gif","Sona walls");
		modeIcons[4] = createImageIcon("images/celticWall.gif","Celtic walls");
		modeIcons[5] = createImageIcon("images/dotsOn.gif","Dot rectangle tool on");
		modeIcons[6] = createImageIcon("images/lineOn.gif","Draw line on");
		modeIcons[7] = createImageIcon("images/eraserOn.gif","Eraser tool on");
		modeIcons[8] = createImageIcon("images/sonaWallOn.gif","Sona walls on");
		modeIcons[9] = createImageIcon("images/celticWallOn.gif","Celtic walls on");
		
		// The tools act like radio buttons: Only one may be selected at a time.
		dotsMode = new JRadioButton(modeIcons[0], true);
		dotsMode.setSelectedIcon (modeIcons[5]);
	    dotsMode.setActionCommand("Rectangle tool");
	    dotsMode.setToolTipText("Add Rectangle of Dots");
		dotsMode.addActionListener(this);
		selectOptions.add(dotsMode);
		lineMode = new JRadioButton(modeIcons[1], true);
		lineMode.setSelectedIcon (modeIcons[6]);
		lineMode.setActionCommand("Line tool");
		lineMode.setToolTipText("Draw a line");
	    lineMode.addActionListener(this);
		selectOptions.add(lineMode);
		eraserMode = new JRadioButton(modeIcons[2], true);
		eraserMode.setSelectedIcon (modeIcons[7]);
		eraserMode.setActionCommand("Eraser tool");
		eraserMode.setToolTipText("Erase dots or lines");
		eraserMode.addActionListener(this);
		selectOptions.add(eraserMode);
		sonaMode = new JRadioButton(modeIcons[3], false);
		sonaMode.setSelectedIcon (modeIcons[8]);
		sonaMode.setActionCommand("Sona tool");
		sonaMode.setToolTipText("Add/Delete Sona Walls");
		sonaMode.addActionListener(this);
		selectOptions.add(sonaMode);
		if (SHOW_CELTIC_TOOLS) {
			celticMode = new JRadioButton(modeIcons[4], false);
			celticMode.setSelectedIcon (modeIcons[9]);
			celticMode.setActionCommand("Celtic tool");
			celticMode.setToolTipText("Add/Delete Celtic Walls");
			celticMode.addActionListener(this);
			selectOptions.add(celticMode);
		}
		selectionMode = new JRadioButton( );
//		selectionMode.setSelectedIcon (modeIcons[8]);
		selectionMode.setActionCommand("Select tool");
		selectionMode.setToolTipText("Select portions of the sona");
		selectionMode.addActionListener(this);
//		selectOptions.add(selectionMode);
		
		// Put all of those radio buttons into one group:
		modeGroup = new ButtonGroup();
	    modeGroup.add(dotsMode);
	    modeGroup.add(lineMode);
	    modeGroup.add(eraserMode);
	    modeGroup.add(sonaMode);
	    modeGroup.add(selectionMode);
	    if (SHOW_CELTIC_TOOLS) {
	    	modeGroup.add(celticMode);
	    }
		
		// For a tutorial on how to use combo boxes, see:
	    // <http://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html>
		penWidth = new JComboBox<String>(widths);
		penWidth.setName("Pen Width");
		penWidth.setToolTipText("The drawing width for the sona curves.");
		penWidth.addActionListener(this);
		
		rectangleHeight = new JComboBox<String>(sizes);
		rectangleHeight.setName("Rectangle Height");
		rectangleHeight.setToolTipText("Add a rectangle of dots of this height.");
		rectangleHeight.addActionListener(this);

		rectangleWidth = new JComboBox<String>(sizes);
		rectangleWidth.setName("Rectangle Width");
		rectangleWidth.setToolTipText("Add a rectangle of dots of this width.");
		rectangleWidth.addActionListener(this);
	
		drawingSpeed = new JComboBox<String>(speeds);
		drawingSpeed.setName("Drawing Speed");
		drawingSpeed.setToolTipText("The speed at which a sona is drawn.");	
		drawingSpeed.addActionListener(this);
		
		// Set the layout of the toolBar
		toolbarPane.setLayout(new FlowLayout());
		
		// Add the buttons to the toolBar
		toolbarPane.add(new JLabel("Height:"));
		toolbarPane.add(rectangleHeight);
		toolbarPane.add(new JLabel("Width:"));
		toolbarPane.add(rectangleWidth);
		toolbarPane.add(selectOptions);
		toolbarPane.add(new JLabel(" Pen Width:"));
		toolbarPane.add(penWidth);
		toolbarPane.add( new JLabel(" Draw Speed:"));
		toolbarPane.add(drawingSpeed);
	}
	
	/**
	 *  Create the drawing pane, containing the main canvas for drawing. This includes
	 *  a scroll bar to allow a drawing pane larger than the window or the monitor.
	 */
	protected void initDrawingPane() {
		// Create some borders that we will use for the components
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border canvasBorder =  BorderFactory.createCompoundBorder( raisedbevel, loweredbevel);
		
		// Now create the sub-elements of this pane

		// Create the actual drawing panel. We put it inside a bordered panel, then put that panel
		// inside the pane. This allows the border panel, which we never need reference again, to
		// always redraw the beveled edges, while the actual canvas inside it is bound to the width
		// WITHOUT the border, extends to take up all available space, but is properly cropped.
		JPanel borderForCanvas = new JPanel( new BorderLayout() );
		borderForCanvas.setBorder(canvasBorder);
		borderForCanvas.setBackground(Color.WHITE);
		mainDrawingCanvas = new DrawingCanvas( this );
		mainDrawingCanvas.setBackground(Color.WHITE);
		borderForCanvas.add(mainDrawingCanvas, "Center");
		
		// Then put them into the drawingPane
		drawingPane = new JScrollPane( borderForCanvas );
		drawingPane.setLayout( new ScrollPaneLayout() );
		drawingPane.setBackground(Color.WHITE);
		drawingPane.setBorder(new EmptyBorder(0,0,1,1));
	}

	/**
	 *  Create the drawing panes and other GUI elements used in the main 
	 *  window; place them inside the main window. 
	 */
	public void initPanesAndGui() {
		// Create and initialize the main panes:
		initMenuBarPane();
		initDrawingPane();
		initToolbarPane();
		initBottomPane();
		
		// Add the sub-panes to the menuAndTools pane
		menuAndTools = new JPanel();
		menuAndTools.setLayout(new BorderLayout());
		menuAndTools.add(menuBarPane, "North");
		menuAndTools.add(toolbarPane, "South");
		
		// Then add all the highest level panes to the main window
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(menuAndTools, "North");
		getContentPane().add(drawingPane, "Center");
		getContentPane().add(bottomPane, "South");
		pack();
		setSize(800,645);   // Has to happen after "pack()"
		setVisible(true);
	}
	
	
	/* THE ACTION PERFORMED METHOD, I.E. THE EVENT LISTENER HANDLER.  */

	/** 
	 * This action event listener is installed with all menu items and with
	 * all of the main toolbar buttons and popup menus.
	 * We determine which action was selected by the user, then take the 
	 * appropriate action -- usually by calling some other object with the
	 * appropriate request.
	 */
	public void actionPerformed(ActionEvent e) {	
		String theCommand = e.getActionCommand();
		String theName = e.getSource().getClass().getName();
		if (e.getSource().getClass().getName().contains("JMenuItem")) {
			switch (theCommand) {

			// Look for "File" menu commands:
			case "New": { MainWindow nextSona = new MainWindow();
					nextSona.initPanesAndGui();
					nextSona.initProgramInternals( thePreferences );	// Must happen after initComponents();
					break;
			}
			case "Open": {
				File toRead = theFileHandler.openFile();
				if (toRead != null) {	// Otherwise, they clicked "Cancel"
					setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR));
					String windowName = theFileHandler.readFile(toRead);
					setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR));
					this.setTitle(windowName);
					updatePreferencesDisplayed();
				}
				break;
			} 
			case "Close": {
				if (mainDrawingCanvas.isCanvasDirty()) {
					JFrame frame = new JFrame();
					int choice = JOptionPane.showOptionDialog(frame, "There are unsaved changes. Do you want to save this drawing?", "Warning!", 
							JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
					if (choice==JOptionPane.YES_OPTION) {
						actionPerformed( new ActionEvent(this, e.getID(), "Save") );
					}
				}
				dispose();
				numOfOpenWindows--;
				if (numOfOpenWindows == 0) System.exit(0);
				break;
			}
			case "Save As": case "Save": {
				boolean replaceFile = theCommand.equals("Save");
				if ( theFileHandler.createOutputFile(replaceFile) ) {
					try {
						setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR));
						theFileHandler.writeOutputFile( );
						setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR));
						mainDrawingCanvas.setCanvasDirty(false);
					} catch (FileException fe) {
						// Error dialog. Not shown if the user clicked "Cancel"
						JOptionPane.showMessageDialog(null, fe.getMessage(), "Error writing file.", JOptionPane.ERROR_MESSAGE);
					}
				}
				break;
			} 
			case "Print": {
				PrinterJob printJob = PrinterJob.getPrinterJob();
				PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
				aset.add(OrientationRequested.LANDSCAPE);
				aset.add(MediaName.NA_LETTER_WHITE);
				aset.add(MediaSizeName.NA_LETTER);
				aset.add( new MediaPrintableArea(0.68F,0.75F, 7.14F, 9.5F, MediaPrintableArea.INCH));
				if (printJob.printDialog(aset)) {
					try {
						printJob.setPrintable(mainDrawingCanvas);
						printJob.print(aset);
					} catch (PrinterException pe) {
						new ErrorHandler("Error printing: " + pe);
					}
				}
				break;
// 				BUG: It seems that this should work instead
//			    printJob = PrinterJob.getPrinterJob();
//			    printJob.setPrintable( mainDrawingCanvas );
//			    if (printJob.printDialog()) {
//			      try { 
//			        printJob.print();
//			      } catch (PrinterException pe) {
//			        new ErrorHandler("Error printing: " + pe);
//			      }
//			    }
			}
			case "Exit": {
				if (mainDrawingCanvas.isCanvasDirty()) {
					JFrame frame = new JFrame();
					int choice = JOptionPane.showOptionDialog(frame, "There are unsaved changes. Do you want to save this drawing?", "Warning!", 
							JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
					if (choice==JOptionPane.YES_OPTION) {
						actionPerformed( new ActionEvent(this, e.getID(), "Save") );
					}
				}
				dispose(); System.exit(0);
				
			// Look for "Edit" menu commands:
			} 
			case "Select": {
				mainDrawingCanvas.setCanvasSelectionMode();
				selectionMode.setSelected(true);
				break;
			} 
			case "Cut": {
				// Can't just Copy then Clear, 'cuz you've lost the marchingAnts
				mainDrawingCanvas.cutSelection();
				break;
			} 
			case "Copy": {
				mainDrawingCanvas.copySelection();
				break;
			} 
			case "Paste": {
				Point mouseLoc = this.getMousePosition();
				// Have to adjust this for location of the Canvas relative to the Window.
				Point adjust = this.getLocationOnScreen();
				if ((mouseLoc == null) || (adjust == null)) { 
					return; // Can happen if you move the mouse fast.
				}
				mouseLoc.translate(adjust.x, adjust.y);
				adjust = mainDrawingCanvas.getLocationOnScreen();
				mouseLoc.translate(-adjust.x, -adjust.y);
				MouseEvent evt = new MouseEvent(mainDrawingCanvas, MouseEvent.MOUSE_RELEASED, 
						e.getWhen(), MouseEvent.BUTTON1_MASK, mouseLoc.x, mouseLoc.y, 1, false);
				for (MouseListener m : mainDrawingCanvas.getMouseListeners() ) {
					m.mouseReleased(evt);
				}
				break;
			} 
			case "Preferences...": {
				new ErrorHandler(theCommand + " is not implemented yet." );
				break;
			} 
			case "Clear Selection": {
				mainDrawingCanvas.clearSelection();
				break;
			} 
			case "Undo": {
				mainDrawingCanvas.undo();
				break;
			} 
			case "Redo": 
				mainDrawingCanvas.redo(); break;

				// Look for "Clear" menu commands
			case "Clear Lines": {
				mainDrawingCanvas.clearLines( );
				break;
			} 
			case "Clear Walls": {
				mainDrawingCanvas.clearWalls( );
				break;
			} 
			case "Clear Dots": {
				mainDrawingCanvas.clearDots( );
				break;
			} 
			case "Clear All": {
				mainDrawingCanvas.clearAll( );
				updatePreferencesDisplayed();
				break;
			}
				// Look for "Walls" menu commands:
			case "Hide Walls": {
				mainDrawingCanvas.setShowWalls(false);
				showWalls.setText("Show Walls");
				break;
			} 
			case "Show Walls": {
				mainDrawingCanvas.setShowWalls(true);
				showWalls.setText("Hide Walls");
				break;
			}
			case "Sona Walls": 
			case "Sona or Celtic Walls": {
				if ( mainDrawingCanvas.isAnimationInEffect()) {
					lineMode.setSelected(true);
					return;	// Can't use this tool while animation is in effect.
				}
				// Check whether there are any Celtic walls. If so, give the user the 
				// chance to delete all Celtic walls or cancel the switch to Sona mode
				if (mainDrawingCanvas.thereAreCelticWalls()) {
					actionPerformed( new ActionEvent(this, e.getID(), "Show Walls") );
					JFrame frame = new JFrame();
					int choice = JOptionPane.showOptionDialog(frame, "Should I delete all Celtic walls so you can switch to Sona mode?", "Warning!", 
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,null,null);
					if (choice==JOptionPane.CANCEL_OPTION) {
						return;	// So we don't fall through to the next chunks of code.
					}
					mainDrawingCanvas.setUndoPoint( );
					mainDrawingCanvas.clearWalls();
				}
				thePreferences.setPrefValue(PrefSettings.WHICH_WALLS, 0);
				mainDrawingCanvas.setDrawingType(SonaLayout.DrawingType.SONA);
				updatePreferencesDisplayed();
				break;
			}
			case "Celtic Walls": {
					if ( mainDrawingCanvas.isAnimationInEffect()) {
					lineMode.setSelected(true);
					return;	// Can't use this tool while animation is in effect.
				}
				// Check whether there are any Sona walls. If so, give the user the 
				// chance to delete all Sona walls or cancel the switch to Celtic mode
				if (mainDrawingCanvas.thereAreSonaWalls()) {
					actionPerformed( new ActionEvent(this, e.getID(), "Show Walls") );
					JFrame frame = new JFrame();
					int choice = JOptionPane.showOptionDialog(frame, "Should I delete all Sona walls so you can switch to Celtic mode?", "Warning!", 
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,null,null);
					if (choice==JOptionPane.CANCEL_OPTION) {
						return;	// So we don't fall through to the next chunks of code.
					}
					mainDrawingCanvas.setUndoPoint( );
					mainDrawingCanvas.clearWalls();
				}
				mainDrawingCanvas.setDrawingType(SonaLayout.DrawingType.CELTIC);
				thePreferences.setPrefValue(PrefSettings.WHICH_WALLS, 1);
				if (theCommand.endsWith("tool")) {
					mainDrawingCanvas.setCanvasCelticWallMode();
				}
				updatePreferencesDisplayed();
				break;
			}
				
				// Look for "Dots" menu commands:
			case "Hide All Dots": {
					thePreferences.setPrefValue(PrefSettings.SHOW_DOTS, 0);
					mainDrawingCanvas.invalidate();
					repaint();
					break;
				} 
			case "Show Design Dots": {
					thePreferences.setPrefValue(PrefSettings.SHOW_DOTS, 1);
					mainDrawingCanvas.invalidate();
					repaint();
					break;
				} 
			case "Show Design & Background Dots": {
					thePreferences.setPrefValue(PrefSettings.SHOW_DOTS, 2);
					mainDrawingCanvas.invalidate();
					repaint();
					break;
				}
			case "Fine Spacing": case "Medium Spacing": case "Coarse Spacing": {
				if (mainDrawingCanvas.isAnimationInEffect()) { 
					return;	// You can't change spacing while drawing.
				} else if (theCommand.equals("Fine Spacing")) {
					mainDrawingCanvas.resetDotSpacing( Spacing.FINE_DOT_SPACING );
				} else if (theCommand.equals("Medium Spacing")) {
					mainDrawingCanvas.resetDotSpacing( Spacing.MEDIUM_DOT_SPACING );
				} else if (theCommand.equals("Coarse Spacing")) {
					mainDrawingCanvas.resetDotSpacing( Spacing.COARSE_DOT_SPACING );
				} else {
					new ErrorHandler("Program error: Unknown dots spacing." );					
				}
				break;
			}
			} // End of switch
			return;
		} // End of "if JMenuItem
			
		// Look for "Toolbar::Mode" commands. 
		// Celtic walls and Sona walls tools were handled with the related menu items.
		if (theCommand.endsWith("tool") ) {	
			if ( mainDrawingCanvas.isAnimationInEffect()) {
				lineMode.setSelected(true);
				return;	// Can't use these tools while animation is in effect.
			}
			// Clicked the "mode" radio buttons. Ignore "tool on", cause it's an active tool
			if (theCommand.startsWith("Rectangle")) {
				mainDrawingCanvas.setCanvasDotsMode();
				// Sometimes this is a created event, in which case we need to emulate clicking the button
				dotsMode.setSelected(true);
				if (thePreferences.getPrefValue(PrefSettings.SHOW_DOTS) == 0) {
					thePreferences.setPrefValue(PrefSettings.SHOW_DOTS, 1);
				}
			} else if (theCommand.startsWith("Line")) {
				mainDrawingCanvas.setCanvasLineMode();
			} else if (theCommand.startsWith("Eraser")) {
				mainDrawingCanvas.setCanvasEraserMode();
			} else if (theCommand.startsWith("Sona")) {
					mainDrawingCanvas.setCanvasSonaWallMode();
			} else {
				new ErrorHandler("Illegal canvas mode change." );		
			}
			
		// Look for Toolbar commands:
		} else if (e.getSource().getClass().getName().startsWith("javax.swing.JComboBox")) {
			JComboBox<String> whichComboBox = (JComboBox<String>) e.getSource();
			if (whichComboBox.getName().equals("Pen Width")) {
				// Our numbers are 1..N; Java stores 0..N-1, so it's natural to translate
				thePreferences.setPrefValue(PrefSettings.PEN_WIDTH, whichComboBox.getSelectedIndex()+1);
				if (!mainDrawingCanvas.isAnimationInEffect()) {
					mainDrawingCanvas.invalidate();
					mainDrawingCanvas.repaint();
				}
			} else if (whichComboBox.getName().equals("Drawing Speed")) {
				thePreferences.setPrefValue(PrefSettings.DRAW_SPEED, whichComboBox.getSelectedIndex());				
			} else if (whichComboBox.getName().equals("Rectangle Width")) {
				thePreferences.setPrefValue(PrefSettings.DOT_RECTANGLE_WIDTH, whichComboBox.getSelectedIndex());
				if (mainDrawingCanvas.getClickMode() == Modes.CANVAS_DOTS_MODE) {
					// Force it to re-draw the boxes:
					mainDrawingCanvas.setCanvasDotsMode();	// Re-make the new size
				}
			} else if (whichComboBox.getName().equals("Rectangle Height")) {
				thePreferences.setPrefValue(PrefSettings.DOT_RECTANGLE_HEIGHT, whichComboBox.getSelectedIndex());				
				if (mainDrawingCanvas.getClickMode() == Modes.CANVAS_DOTS_MODE) {
					// Force it to re-draw the boxes:
					mainDrawingCanvas.setCanvasDotsMode();	// Re-make the new size
				}
			} else {
				new ErrorHandler("Bad Name for the Combo Box selected.");
			}
		}
	}
	 
	 
	 /* METHODS NECESSARY FOR THIS TO BE A WINDOW LISTENER */

	 /**
	  *  We keep a count on the number of drawing windows that the user has 
	  *  open, e.g. those created via the "New" command. If they've closed 
	  *  all such windows, we treat that as a "Quit" command and terminate
	  *  the program.
	  */
	 public void windowClosing(WindowEvent e) {
		 dispose();
		 numOfOpenWindows--;
		 if (numOfOpenWindows == 0) {
			 System.exit(0);
		 }
	 }
	 
	 /** No action taken for this event; this is included only to satisfy the 
	  *  requirements of a WindowListener.									  */
	 public void windowClosed(WindowEvent e) {  }
	 /** No action taken for this event; this is included only to satisfy the 
	  *  requirements of a WindowListener.									  */
	 public void windowOpened(WindowEvent e) {  }
	 /** No action taken for this event; this is included only to satisfy the 
	  *  requirements of a WindowListener.									  */
	 public void windowIconified(WindowEvent e) { }
	 /** No action taken for this event; this is included only to satisfy the 
	  *  requirements of a WindowListener.									  */
	 public void windowDeiconified(WindowEvent e) { }
	 /** No action taken for this event; this is included only to satisfy the 
	  *  requirements of a WindowListener.									  */
	 public void windowActivated(WindowEvent e) {
		 mainDrawingCanvas.resetCurrentCursor();
	 }
	 /** No action taken for this event; this is included only to satisfy the 
	  *  requirements of a WindowListener.									  */
	 public void windowDeactivated(WindowEvent e) { }
	 
	 
	 
	 /* METHOD REQUIRED FOR THIS TO BE A QUIT HANDLER */

		
		/** Intended for a Macintosh implementation. */
		/**
		 *  See <http://developer.apple.com/library/mac/documentation/Java/Reference/JavaSE6_AppleExtensionsRef/api/com/apple/eawt/ApplicationAdapter.html>
		 *  for links to the interfaces available for interacting with the Mac Application menu.
		 *  
		 *	// BUG: Should we add some of the other menu action handlers as well?
		 */
	 // This has been commented out because a Java 1.8 release is not yet available.
/*		public void handleQuitRequestWith(AppEvent.QuitEvent e, QuitResponse response)  {
		    actionPerformed( new ActionEvent(this, 0, "Exit") );
		}
*/
	
	/* OTHER HELPER METHODS */
	
	/**
	 * This method tried to customize the "Edit" menu commands, to they could 
	 * say things like "Copy Dots" or "Copy Walls", but it doesn't work.
	 */
	public void updateEditCommands( ) {
		if ( mainDrawingCanvas == null ) {
			editUndo.setEnabled(false);
			editUndo.setText("Can't Undo");
		} else if ( mainDrawingCanvas.canUndo() ) {
			editUndo.setEnabled(true);
			editUndo.setText("Undo");
		} else if ( mainDrawingCanvas.canRedo() ) {
			editUndo.setEnabled(true);
			editUndo.setText("Redo");
		} else {
			editUndo.setEnabled(false);
			editUndo.setText("Can't Undo");
		}
		
		if ( mainDrawingCanvas != null ) {
			editCopy.setEnabled(  mainDrawingCanvas.hasMarchingAnts() );
			editClear.setEnabled( mainDrawingCanvas.hasMarchingAnts() );
			editCut.setEnabled(   mainDrawingCanvas.hasMarchingAnts() );
			editPaste.setEnabled( mainDrawingCanvas.hasDotRectangle() );
		}
	}


	 /** 
	  * Returns an ImageIcon, or null if the path was invalid. 
	  * Used internally for creating icons for some of the GUI buttons.
	  */
	 protected static ImageIcon createImageIcon(String path, String description) {
	     java.net.URL imgURL = MainWindow.class.getResource(path);
	     if (imgURL != null) {
	         return new ImageIcon(imgURL, description);
	     } else {
	         System.err.println("Couldn't find file: " + path);
	         return null;
	     }
	 }
	 
	 /**
	  *  Create and initialize the various non-GUI objects used by the program.
	  */
	 public void initProgramInternals( PreferencesList oldPreferences ) {
		if (oldPreferences == null) {
			thePreferences = new PreferencesList();
		} else {
			thePreferences = new PreferencesList( oldPreferences );
			updatePreferencesDisplayed();
		}
	 	SonaLayout theSonaLayout = new SonaLayout( );
	 	mainDrawingCanvas.register(thePreferences, theSonaLayout );
	 	theFileHandler = new FileHandler( thePreferences, sonaName, theSonaLayout, mainDrawingCanvas );
		mainDrawingCanvas.setCanvasDotsMode();
	 }
	 // This object is declared here so some inner classes can get access to it
	 private static MainWindow theProgram;
		
	/** The starting point for this program. Creates the main window, then calls
	 *  the other methods needed to initialize the rest of the program.
	 *  
	 *  @param args Not used; only here to satisfy signature requirements.
	 */ 
	public static void main(String[] args) {
		theProgram = new MainWindow();
		theProgram.initPanesAndGui();
		theProgram.initProgramInternals( null );	// Must happen after initComponents();
		theProgram.updatePreferencesDisplayed();	// Being called too soon??
		String version = System.getProperty("java.version");
		int baseVersion = Integer.parseInt(""+ version.charAt(2));
		if (baseVersion < 5) {
			new ErrorHandler("This program has not been tested with versions of Java prior to 1.5.");
		}
		String osName = System.getProperty("os.name"); // E.g. "Mac OS X"
		// BUG: The following has been commented out because a Java 1.8 version is not yet available.
//		if (osName.equals("Mac OS X")) {
//			Application.getApplication().setQuitHandler( theProgram );
//			// Now a Mac Menu "Quit", or CMD-Q, will be handled here by handleQuitRequestWith()
//		}


	}
	
	/**
	 * This is used only for testing, and should not be accessed by
	 * any other part of the program.
	 */
	public static MainWindow getTheProgram() {
		return theProgram;
	}

	/**
	 * This is used only for testing, and should not be accessed elsewhere in the program.
	 */
	public DrawingCanvas getTheCanvas() {
		return mainDrawingCanvas;
	}

}
