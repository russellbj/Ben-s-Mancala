package mancala;

/*
 * @author Papa Boakye, Dimitrius King, Nisha Bhatta
 *
 */
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TieGame {

	public TieGame() {
		// TODO Auto-generated constructor stub
	}

	boolean tieGame(int [] gameBoard){
		final int limit = 24;
		int count = 0;

		boolean tieConditionMet = false;
		ArrayList<int[]> prevStates = new ArrayList<int[]>();

		for(int i = 0; i < prevStates.size(); i++) {
			if (prevStates.get(i).equals(gameBoard)){
				count = 0;
				break;
			}else {
				//
				prevStates.add(gameBoard);
				count++;
			}
		}

		if (count == limit) {
			tieConditionMet = true;
		}else if(count < limit){
			tieConditionMet = false;
		}
		return tieConditionMet;
	}

	boolean buttonTie() {
		boolean tieChoice = false;
		JFrame alert = new JFrame();
		int a=JOptionPane.showConfirmDialog(alert,"Do you want to declare this game a tie/end the game");  
		if(a==JOptionPane.YES_OPTION){
			tieChoice = true;
		}else if(a==JOptionPane.NO_OPTION || a==JOptionPane.CANCEL_OPTION ){
			alert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			tieChoice = false;
		}
		return tieChoice;
	}
}

