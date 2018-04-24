package mancala;

/*
 * @author Papa Boakye, Dimitrius King, Nisha Bhatta
 *
 */

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import javax.swing.JOptionPane;

public class TieGame {

	public TieGame() {
		// TODO Auto-generated constructor stub
	}

	void tieGame(int P1, int P2){

		int noCapture = 25;
		final int tieCond = 30;

		int p1temp = 0;
		int p2temp = 0;

		int p1Score = P1;
		int p2Score = P2;

		if (noCapture != tieCond){
			if(p1Score == p1temp && p2Score == p2temp){
				noCapture++;				
			}else{
				noCapture = 0;
				p1temp = p1Score;
				p2temp = p2Score;

			}
		}else if (noCapture == tieCond){
			Game.endGame();
		}
	}

	void buttonTie() {
		JFrame alert = new JFrame();
		int a=JOptionPane.showConfirmDialog(alert,"Do you want to declare this game a tie/end the game");  
		if(a==JOptionPane.YES_OPTION){
			Game.endGame();
		}else if(a==JOptionPane.CANCEL_OPTION || a==JOptionPane.CANCEL_OPTION ){
			alert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// TODO Auto-generated method stub

		}

	}
}

