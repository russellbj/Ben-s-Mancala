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
		
	}

	ArrayList<int[]> prevStates = new ArrayList<int[]>();
	boolean tieConditionMet = false;

	boolean tieGame(int [] gameBoard){
		final int limit = 25;
		int count = 0;

		for(int i = 0; i < prevStates.size(); i++) {
			if (prevStates.get(i).equals(gameBoard)){
				
				JFrame recurring = new JFrame();
				
				int b=JOptionPane.showConfirmDialog(recurring,"This state has ben reached before, you may be in a loop. Do you want to declare this game a tie and end the game");  
				if(b==JOptionPane.YES_OPTION){
					tieConditionMet = true;
				}else if(b==JOptionPane.NO_OPTION || b==JOptionPane.CANCEL_OPTION ){
					recurring.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					tieConditionMet = false;
				}
				break;
			}else if(i == prevStates.size()) {
				prevStates.add(gameBoard);
			}
		}
		return tieConditionMet;	
	}
		

	boolean buttonTie() {
		boolean tieChoice = false;
		JFrame alert = new JFrame();
		int a = JOptionPane.showConfirmDialog(alert,"Do you want to declare this game a tie/end the game");  
		if(a==JOptionPane.YES_OPTION){
			tieChoice = true;
		}else if(a==JOptionPane.NO_OPTION || a==JOptionPane.CANCEL_OPTION ){
			alert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			tieChoice = false;
		}
		return tieChoice;
	}

	public ArrayList<int[]> getPrevStates() {
		return prevStates;
	}

	public void setPrevStates(ArrayList<int[]> prevStates) {
		this.prevStates = prevStates;
	}
	
}

