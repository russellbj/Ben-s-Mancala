package mancala;

import java.util.Scanner;

public class GameDriver {

//	private static Scanner input = new Scanner(System.in);
	
	public static Rules rules = Rules.getInstance();
//	private static int numOfHoles = 12; // Wari - specific
//	private static GameBoard board = GameBoard.getInstance();

	private static int pointsToWin = 25;
//	private static boolean possibleMoves = true;
	private static MainWindow gui;
	static int holesPerRow = 6;

	static int clickedHoleIndex;
	static int totalPoints;

	private static boolean clickedZero;

	private static GameDriver instance;
	private static boolean gameChosen = false;



	public static void main(String[]args) throws InterruptedException{
		//totalPoints = P1.displayCount() + P2.displayCount();
		gui = new MainWindow();
		while(true){
			Thread.sleep(1); 
			
			}
		}

	public static GameDriver getInstance()
	{
		if(instance == null)
		{
			instance = new GameDriver();
		}
		return instance;
	}

	public static boolean getClickedZero() {
		// TODO Auto-generated method stub
		return clickedZero;
	}
	public void setClickedZero(boolean clickedZero) {
		// TODO Auto-generated method stub
		this.clickedZero = clickedZero;
	}
}