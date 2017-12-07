package mancala;

/**
 * Created by Darrah Chavey, Nov. 7, 2017.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


/**
 * MainWindow is where most objects in this program live. This creates the program,
 * initializes the menus and GUI, and then initiates the play of the game.
 */
public class MainWindow extends JFrame implements WindowListener, ActionListener {			// For an application
	
/* Class & object data, other than the GUI elements */
	
	/**
	 * Serial ID to allow implementation of Serializable. We don't actually
	 * implement saving the file this way, but it is expected by "JFrame".
	 */
	protected static final long serialVersionUID = 8095078020386417776L;
		
	
/* Declarations of other classes within the system. MainWindow acts as the primary
 * mode of initial communication between these classes.
 */	

    /**
     * Where we draw the game being played.
     */
	protected DrawingCanvas drawingCanvas;
	
 	/** Which game is currently being played. */
	protected GameVariations gameBeingPlayed;
	

	/* 
	 * Menu choices available in the "Help" menu.
	 */
	
	/** The menu choices available from the Help menu. Stored this way, because they are the most
	 *  likely to change on a semi-regular basis as the program develops.	 */
	protected static final String[] helpOptions = {"About the Authors", "Mancala Family of Games", "Mancala Program Help"};


    /*
     * Menu items (AD section 14), implemented with JMenu. This includes the
     * menu bar, menus within it, and menu items, including hierarchical menus.
     * See http://java.sun.com/docs/books/tutorial/uiswing/components/menu.html
     * for tutorials on assembling JMenuBars from JMenus and JMenuItems
     */
    
    /**
     * The object menuBar contains the interface structure for the menu bar at the
     * top of the main window. This object should contain the JMenu objects
     * gameMenu, speedMenu, opponentMenu, and helpMenu.
     */
    protected JMenuBar menuBar;
    
     /**
     * The menu gameMenu contains the user to select which game they wish
     * to play. There are a variety of ways for them to select the game.
     */
    protected JMenu gameMenu;
    
    
    /**
     * The menu speedMenu allows the user to select how fast they want
     * the play of the game (distribution & captures) to be animated.
     */
    protected JMenu speedMenu;
    
    /**
     * The menu opponentMenu allows the user to select whether they are
     * playing against a human or the computer. If they choose the computer,
     * they can also choose how strong an opponent to play against.
     */
    protected JMenu opponentMenu;
    
    /**
     * The menu helpMenu contains the interface structure for the contents
     * of the "Help" section of the menu bar. This object should contain
     * the JMenuItem objects help and about.
     */
    protected JMenu helpMenu;
 
    
    /* Menu items within the fileMenu */
    
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
    

   /* Menu items within the editMenu */
    
    /**
     * The cut, copy and paste menu items reside in the "Edit" menu
     * and handle the menu commands to perform standard cut, copy
     * and paste operations on text fields.
     */
    protected JMenuItem editCut, editCopy, editPaste, editUndo;
    
    
    /* Menu items within the gameMenu */
    
    /** Select games alphabetically. Divided into submenus organized alphabetically.     */
    protected JMenu gameAlphabetical;
    
    /** Subset of the menu choices in the Alphabetical selection of games. */
    protected JMenu gameAlphaAE, gameAlphaFL, gameAlphaMO, gameAlphaPZ;
    
    /** Select games organized by the number of rows in the board, or other "board shapes". */
    protected JMenu gameOneRow, gameTwoRow, gameThreeRow, gameFourRow;
    
    /** Select games that are played on two rows, but where the end bins are played into as you
     *  sow, so that you can't separate the "collection" of won stones from the play of the game. */
    protected JMenu gameTwoRowEnds;
    
    /** There are so many 2-row Mancala games, that we group them into alphabetically
     *  organized submenus. */
    protected JMenu gameAlphaAL, gameAlphaMZ;
    
       
    /**
     * Select games by the country in which they were historically played.
     */
    protected JMenu gameByCountry;
    
    /**
     * Select games by type (chapter) from "Mancala Games"
     */
    protected JMenu gameByType;
    
    
 
    /* Menu items within the helpMenu */
    
    /**
     * The menu item helpAboutProgram handles the menu command "About Sona Program" to open a dialog
     * box with information about the program. It resides in the "Help"
     * menu.
     */
    protected JMenuItem helpAboutProgram;

    /**
     * The menu item helpMancalaGames handles the menu command "About Mancala Games" to open the help file.
     * It resides in the "Help" menu.
     */
    protected JMenuItem helpMancalaGames;

    /**
     * The menu item helpMancala handles the menu command "Mancala Help" to open the help file.
     * This provides help with how to use the program.
     * It resides in the "Help" menu.
     */
    protected JMenuItem helpMancala;

    
    /*
     * The GUI panes and elements, other than menus, in the interface
     */
	
    /** A top level pane for holding the menus */
	protected JPanel menuBarPane;
	
    /** A 2nd level pane for holding the menus */
	protected JPanel menus;
	
    /** A 2nd level pane for holding the other options for the game */
	protected JPanel gameOptions;

   /** A top level pane for holding the drawing canvas */
	protected JScrollPane drawingPane;
	
	/** A top level pane for holding the rules of the game */
	protected JScrollPane infoPane;
		
	protected Image[] beans, hand;
	
    /** Forces all required images to be loaded and available. */
    public void loadImages() {
		MediaTracker mt;
		mt = new MediaTracker(this);
		 
   		// Images used in the game play and animation
		beans = new Image[10];
		hand = new Image[2];

		// We've put all of the image files directly into the Eclipse "src" and "bin"
		// folders so we don't need to fiddle with image/filename on most platforms
		// and image\\filename on Windows. 
		// BUG: This should be corrected using the platform-specific delimiter
//		BUG: For an applet, you need to getCodeBase( ) and use that to construct the file URL for the graphics.
		Toolkit toGetImages = Toolkit.getDefaultToolkit();
		for (int index=1; index<=9; index++) {
			beans[index-1] = toGetImages.getImage("Bean-0" + index +".gif");
			mt.addImage(beans[index-1],index-1);
		}
		
		beans[9] = toGetImages.getImage("Bean-10.gif");
		hand[0] = toGetImages.getImage("Hand_1.gif");
		hand[1] = toGetImages.getImage("Hand_2.gif");
		mt.addImage(beans[9],9);
		mt.addImage(hand[0],10);
		mt.addImage(hand[1],11);

		try {	// Wait until all images have been successfully uploaded
			mt.waitForAll();
		} catch (InterruptedException  e) { 
			System.err.println("Unable to load applet.");
			System.exit(ABORT);
		} catch (NullPointerException e) {
			System.err.println("Unable to load image files.");
			System.exit(ABORT);
		} catch (Exception e) {
			System.err.println("Unable to load image files.");
			System.exit(ABORT);
		}
    }
	
	/** Create the main program window(s). Keep track of the number of such
	 *  windows that are open within the program. The constructor does nothing 
	 *  but initialize a window with an appropriate title. The calling structure 
	 *  should be: you call the constructor; you then call "initComponents()", 
	 *  which creates the actual GUI elements and places them within this window;
	 *  then you call "initProgramInternals()" which sets up the other objects
	 *  used by the program, many of which need to be able to communicate with 
	 *  these GUI elements. 
	 *  
	 *  @param beans An array of images used to draw the beans (for sowing) on the screen.
	 *  @param hand  An array of the two images used to draw a hand, holding beans, on the screen.
	 */
	public MainWindow( ) {
		super( "Mancala Family Games" );	// For an application
		loadImages();
		theProgram = this;
		theProgram.initPanesAndGui( beans, hand );
	}


	/** The menu choices available for how quickly to make the animation. */
	protected static final String[] speeds = {"Very Slow", "Slow", "Normal", "Fast", "Very Fast"};

	/** The menu choices available for what levels of computer opponent to use. */
	protected static final String[] opponent = {"Random Play", "Beginner", "Intermediate", "Very Good", "Genius"};
	
	/**
	 * Create the menu pane. Add all the menus, then add the menu items,
	 * including the hierarchical menus with their menu items.<BR><BR>
	 * This is declared as public only for purposes of the testing modules.
	 */
	protected void initMenuBarPane() {
		
		menuBarPane = new JPanel();
		
		// Create the menuBar to contain the menus
		menuBar = new JMenuBar();
		
		// Set up the various game menus:
		
	    // Get all of the known games, sort them alphabetically.
	    // We keep that list of "allGames" unchanged for later uses.
		String[] allGames = new String[ GameVariations.values().length ];
		int index=0;
		for (GameVariations gameVariations : GameVariations.values() ) {
			allGames[index++] = gameVariations.getName();
		}
		//		Sort the array of games, handling diacritical marks
	    Collator ignoreDiacriticals = Collator.getInstance(Locale.US);
	    ignoreDiacriticals.setStrength(Collator.SECONDARY);
		Arrays.sort(allGames, ignoreDiacriticals);

		
		// Game Alphabetical menus:
	    gameAlphaAE = new JMenu("A-E");
	    gameAlphaFL = new JMenu("F-L");
	    gameAlphaMO = new JMenu("M-O");
	    gameAlphaPZ = new JMenu("P-Z");
		String gameName;
		JMenuItem gameMenuItem;
		for (index=0; index<allGames.length; index++) {
			gameName = allGames[index];
			gameMenuItem = new JMenuItem(gameName);
			gameMenuItem.addActionListener(this);
			if ( ignoreDiacriticals.compare(gameName,"F" ) < 0) {
				gameAlphaAE.add( gameMenuItem );
			} else if ( ignoreDiacriticals.compare(gameName,"M" ) < 0) {
				gameAlphaFL.add( gameMenuItem );
			} else if ( ignoreDiacriticals.compare(gameName,"P" ) < 0) {
				gameAlphaMO.add( gameMenuItem );
			} else {
				gameAlphaPZ.add( gameMenuItem );
			}
		}
		gameAlphabetical = new JMenu("Alphabetical");
		gameAlphabetical.add(gameAlphaAE);
		gameAlphabetical.add(gameAlphaFL);
		gameAlphabetical.add(gameAlphaMO);
		gameAlphabetical.add(gameAlphaPZ);


		// Games by Board Type menus:
		gameByType = new JMenu("By Type");
		JMenu thisBoardType;
		for (BoardTypes boardType : BoardTypes.values() ) {
			ArrayList<String> gamesOfOneType = new ArrayList<String>( );
			for (GameVariations gameVariation : GameVariations.values() ) {
				if (gameVariation.getBoardType() == boardType) {
					gamesOfOneType.add( gameVariation.getName() );
				}
			}
			String[] allGamesOfOneType = new String[ gamesOfOneType.size() ];
			for (index=0; index < gamesOfOneType.size(); index++ ) {
				allGamesOfOneType[index] = gamesOfOneType.get(index);
			}
			Arrays.sort(allGamesOfOneType);
			gameOneRow = new JMenu("One Row Games:");
			gameTwoRow = new JMenu("Two Row Games:");
			gameAlphaAL = new JMenu("Two Row, A-L");
			gameAlphaMZ = new JMenu("Two Row, M-Z");
			gameTwoRowEnds = new JMenu("Two Row Games, end bins:");
			gameThreeRow = new JMenu("Three Row Games:");
			gameFourRow = new JMenu("Four Row Games:");

			switch ( boardType ) {
				case ONE_ROW: thisBoardType = gameOneRow; break;
				case TWO_ROW: thisBoardType = gameAlphaAL; break;
				case TWO_ROW_WITH_ENDS: thisBoardType = gameTwoRowEnds; break;
				case THREE_ROW: thisBoardType = gameThreeRow; break;
				case FOUR_ROW: thisBoardType = gameFourRow; break;
				default: thisBoardType = null;
			}
			// There are too many TWO_ROW games to all fit in one menu, so we
			// handle them via a special case.
			for (index=0; index<allGamesOfOneType.length; index++) {
				gameName = allGamesOfOneType[index];
				gameMenuItem = new JMenuItem(gameName);
				gameMenuItem.addActionListener(this);
				if ((boardType == BoardTypes.TWO_ROW) && (gameName.charAt(0) >= 'M')) {
					gameAlphaMZ.add( gameMenuItem );
				} else {
					thisBoardType.add( gameMenuItem );					
				}
			}
			if (boardType == BoardTypes.TWO_ROW) {
				gameTwoRow.add( gameAlphaAL );
				gameTwoRow.add( gameAlphaMZ );
				thisBoardType = gameTwoRow;
			}
			gameByType.add( thisBoardType );
		}


		// Games by Country of Origin menus:
		/** BUG: We don't store the information about the country or people group. */
		gameByCountry = new JMenu("By Country");
		ArrayList<String> countries = new ArrayList<String>( );
		String newCountry;
		for (GameVariations gameVariations : GameVariations.values() ) {
			newCountry = gameVariations.getOriginCountry( );
			if (!countries.contains(newCountry)) {
				countries.add(newCountry);
			}
		}
		String[] countriesOfOrigin = new String[countries.size()];
		for (int i=0; i<countries.size(); i++) {
			countriesOfOrigin[i] = countries.get(i);
		}
		Arrays.sort(countriesOfOrigin, ignoreDiacriticals);
		JMenu gamesOneCountry;
		String countryName;
		GameVariations gameVariations;
		for (int whichCountry=0; whichCountry<countriesOfOrigin.length; whichCountry++) {
			countryName = countriesOfOrigin[whichCountry];
			gamesOneCountry = new JMenu(countryName);
			for (index=0; index<allGames.length; index++) {
				gameName = allGames[index];
				gameVariations = GameVariations.getGameVariation(gameName);
				if (gameVariations.getOriginCountry().equals(countryName)) {
					gameMenuItem = new JMenuItem( gameVariations.getName() );
					gameMenuItem.addActionListener(this);
					gamesOneCountry.add(gameMenuItem);
				}
			}
			gameByCountry.add(gamesOneCountry);
		}


		// Game menus:
		gameMenu = new JMenu("Games");		
		gameMenu.add(gameAlphabetical);
		gameMenu.add(gameByCountry);
		gameMenu.add(gameByType);
		menuBar.add(gameMenu);

		// Set up the game animation speed menu
		speedMenu = new JMenu("Animation");	
		// A group of radio button menu items for the speed control
		ButtonGroup speedGroup = new ButtonGroup();
		JMenuItem speedMenuItem;
		for (int i=0; i<5; i++) {
			speedMenuItem = new JRadioButtonMenuItem(speeds[i]);
			speedMenuItem.addActionListener(this);
			speedGroup.add(speedMenuItem);
			speedMenu.add(speedMenuItem);
		}
		((JMenuItem) speedMenu.getMenuComponent(2)).setSelected(true);
		menuBar.add(speedMenu);

		// Set up the opponent menu. BUG: Menu items should all be disabled, for quite a while
		opponentMenu = new JMenu("Opponent");	
		JMenu computerOpponentMenu = new JMenu("Opponent");	
		// A group of radio button menu items for the opponent
		ButtonGroup opponentGroup = new ButtonGroup();
		JMenuItem opponentMenuItem;
		opponentMenuItem = new JRadioButtonMenuItem("Human");
		opponentMenuItem.addActionListener(this);
		opponentGroup.add(opponentMenuItem);
		opponentMenu.add(opponentMenuItem);
		for (int i=0; i<5; i++) {
			opponentMenuItem = new JRadioButtonMenuItem(opponent[i]);
			opponentMenuItem.addActionListener(this);
			opponentGroup.add(opponentMenuItem);
			computerOpponentMenu.add(opponentMenuItem);
		}
		opponentMenu.add(computerOpponentMenu);
		((JMenuItem) opponentMenu.getMenuComponent(0)).setSelected(true);
		menuBar.add(opponentMenu);

		// Set up the help menu
		// Help menu items:
		helpAboutProgram = new JMenuItem(helpOptions[0]);
		helpAboutProgram.addActionListener(this);
		helpMancalaGames = new JMenuItem(helpOptions[1]);
		helpMancalaGames.addActionListener(this);
		helpMancala = new JMenuItem(helpOptions[2]);
		helpMancala.addActionListener(this);
		helpAboutProgram.setEnabled(false);
		helpMancalaGames.setEnabled(false);
		helpMancala.setEnabled(false);
		//Help menu:
		helpMenu = new JMenu("Help");
		helpMenu.add(helpAboutProgram);
		helpMenu.add(helpMancalaGames);
		helpMenu.add(helpMancala);
		menuBar.add(helpMenu);
		
		// Add the menu bar to the appropriate pane
		menuBarPane.setLayout(new BorderLayout());
		menuBarPane.add(menuBar, "West");
		
		gameOptions = new JPanel();
		gameOptions.setLayout(new FlowLayout()) ;
		
		gameOptions.add(new JLabel("You can put buttons, fields, etc. in this panel:") );
		gameOptions.setPreferredSize(new Dimension(200,24));
		
		menuBarPane.add(gameOptions, "Center");
		
		JPanel buttonPanel = new JPanel( new FlowLayout() );
		

		menuBarPane.add( buttonPanel, "East");
	}
		
	
	/**
	 *  Create the drawing pane, containing the main canvas for drawing, along with
	 *  the various slots for the components. We also initialize theComponentBar here.
	 *  
	 *  @param beans An array of images used to draw the beans (for sowing) on the screen.
	 *  @param hand  An array of the two images used to draw a hand, holding beans, on the screen.
	 */
	protected void initDrawingPane( Image beans[], Image hand[] ) {
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
		drawingCanvas = new DrawingCanvas( beans, hand );
		drawingCanvas.setBackground(Color.WHITE);
		borderForCanvas.add(drawingCanvas, "Center");
		
		// Then put them into the drawingPane
		drawingPane = new JScrollPane( borderForCanvas );
		drawingPane.setLayout( new ScrollPaneLayout() );
		drawingPane.setBackground(Color.WHITE);
		drawingPane.setBorder(new EmptyBorder(0,0,1,1));
		
		//add buttons for wari
		JButton button1 = new JButton("Hole 1 \n 4 seeds");
		JButton button2 = new JButton("Hole 2 \n 4 seeds");
		drawingPane.setViewportView(button1);
		drawingPane.setViewportView(button2);
		//borderForCanvas.add(button2);
	}

	/** A scrolling text field in which we can hold the field with instructions for playing a particular game. */
	protected JScrollPane scrollPane;
	/** The text area in which we hold the instructions for playing a particular game. */
	protected JTextArea instructions;
	
	/**
	 *  Create the drawing pane, containing the main canvas for drawing, along with
	 *  the various slots for the components. We also initialize theComponentBar here.
	 */
	protected void initInstructionsPane( ) {		
		instructions = new JTextArea(5, 30);
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		instructions.setText("Game instructions could appear here");
		scrollPane = new JScrollPane(instructions);
		scrollPane.setPreferredSize(new Dimension(250, 300));
	}


	/**
	 *  Create the drawing panes and other GUI elements used in the main 
	 *  window; place them inside the main window. 
	 *  
	 *  @param beans An array of images used to draw the beans (for sowing) on the screen.
	 *  @param hand  An array of the two images used to draw a hand, holding beans, on the screen.
	 */
	public void initPanesAndGui( Image beans[], Image hand[] ) {
		// Create and initialize the main panes:
		initMenuBarPane();
		initDrawingPane( beans, hand );
		initInstructionsPane();
		
		// Then add all the highest level panes to the main window
		getContentPane().setLayout(new BorderLayout());		// For applications
		getContentPane().add(menuBarPane, "North");			// For applications
		getContentPane().add(drawingPane, "Center");		// For applications
		pack();												// For applications
		this.add(menuBarPane, BorderLayout.NORTH);
		this.add(drawingPane, BorderLayout.CENTER);
		this.add(scrollPane, BorderLayout.EAST);

		setPreferredSize( new Dimension(800,600) );   		// Has to happen after "pack()"
//		setLocation(32*numOfWindows-8,32*numOfWindows-8);	// For applicaitons, support staggering with multiple windows
		setVisible(true);
		
		addComponentListener( new ComponentAdapter( ) 
				{	    public void componentResized(ComponentEvent e) {
							theProgram.setSize(
									Math.max(theProgram.getWidth(),696),
									Math.max(theProgram.getHeight(),600)
							);
				 		}
				}
		);
	}

	/** 
	 * This action event listener is installed with all menu items and with
	 * all of the main toolbar buttons and popup menus.
	 * We determine which action was selected by the user, then take the 
	 * appropriate action -- usually by calling some other object with the
	 * appropriate request.
	 * @param e The event that has requested a user interface generated action
	 */
	public void actionPerformed(ActionEvent e) {	
		String theCommand = e.getActionCommand();
		
		// Look for "Speed" menu commands:
		if (theCommand.equals("Very Slow")) {
			new ErrorHandler(theCommand + " is not implemented yet." );
		} else if (theCommand.equals("Slow")) {
			new ErrorHandler(theCommand + " is not implemented yet." );
		} else if (theCommand.equals("Normal")) {
			new ErrorHandler(theCommand + " is not implemented yet." );
		} else if (theCommand.equals("Fast")) {
			new ErrorHandler(theCommand + " is not implemented yet." );
		} else if (theCommand.equals("Very Fast")) {
			new ErrorHandler(theCommand + " is not implemented yet." );

		// Look for "Help" menu commands:
		} else if (theCommand.equals(helpOptions[0])) {
			new ErrorHandler(theCommand + " is not implemented yet." );
		} else if (theCommand.equals(helpOptions[1])) {
			new ErrorHandler(theCommand + " is not implemented yet." );
		} else if (theCommand.equals(helpOptions[2])) {
			new ErrorHandler(theCommand + " is not implemented yet." );
			
			// It must be a game menu command:
		} else if (theCommand.equals("New Game")) {
			new ErrorHandler(theCommand + " is not implemented yet." );
		} else if (theCommand.equals("Declare Game Over")) {
			new ErrorHandler(theCommand + " is not implemented yet." );
		} else {
			//THIS IS WHERE WE WANT TO START THE GAME
			//new ErrorHandler(theCommand + " is not implemented yet." );
		}
	}
	

	 /**
	  *  We keep a count on the number of drawing windows that the user has 
	  *  open, e.g. those created via the "New" command. If they've closed 
	  *  all such windows, we treat that as a "Quit" command and terminate
	  *  the program.
	  */
	 public void windowClosing(WindowEvent e) {
		 dispose();
		 System.exit(0);
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
	 public void windowActivated(WindowEvent e) { }
	 /** No action taken for this event; this is included only to satisfy the 
	  *  requirements of a WindowListener.									  */
	 public void windowDeactivated(WindowEvent e) { }
	 
	 // This object is declared here so some inner classes can get access to it
	 private static MainWindow theProgram;

	 /** Intended primarily for the JUnit testing of the program, to allow
	  *  access to this window, e.g. to send direct events imitating user events.
	  * @return the single applet window being used by the program.
	  */
	public static MainWindow getTheProgram() {
		return theProgram;
	}
		
	/** The starting point for this program. Creates the main window, then calls
	 *  the other methods needed to initialize the rest of the program.
	 *  
	 *  @param args Not used; only here to satisfy signature requirements.
	 */ 
	public static void main(String[] args) {
		new MainWindow();
	}

}