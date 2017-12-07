package mancala;

import java.awt.*;
import java.applet.Applet;

public class Grid extends Applet{
	Button p1collecting = new Button("Player 1 Collecting hole");
	Button p1h1 = new Button("<html>Player 1 Hole 1 <br/>Number of seeds<html>"); //could add in here the number of seeds from call
	Button p1h2 = new Button("Player 1 Hole 2");
	Button p1h3 = new Button("Player 1 Hole 3");
	Button p1h4 = new Button("Player 1 Hole 4");
	Button p1h5 = new Button("Player 1 Hole 5");
	Button p1h6 = new Button("Player 1 Hole 6");
	Button p2h6 = new Button("Player 2 Hole 6");
	Button p2h5 = new Button("Player 2 Hole 5");
	Button p2h4 = new Button("Player 2 Hole 4");
	Button p2h3 = new Button("Player 2 Hole 3");
	Button p2h2 = new Button("Player 2 Hole 2");
	Button p2h1 = new Button("Player 2 Hole 1");
	Button p2collecting = new Button("Player 2 Collecting Hole");
	

	/*public Grid(){
		init();
	}
	*/
	
	public void init(){
		setLayout(new GridLayout(2,6));
		add(p1collecting).setEnabled(false);
		add(p1h1); //could add in here the number of seeds from call
		add(p1h2);
		add(p1h3);
		add(p1h4);
		add(p1h5);
		add(p1h6);
		add(p2h6);
		add(p2h5);
		add(p2h4);
		add(p2h3);
		add(p2h2);
		add(p2h1);
		add(p2collecting).setEnabled(false);
	}

	
}
