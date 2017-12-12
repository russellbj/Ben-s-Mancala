package mancala;

/*
 * Created on Mar 13, 2006
 *
 * Software Engineering
 * Team 2 method stubs
 * Maintained By: Miles Evenson
 * Other Team Members: Russell Stringer
 * 
 * Coded & tested by Darrah Chavey, May 4.
 * 
 * Design changed: Instead of creating a new ErrorHandler object, with
 * no initialization, and then calling a "newError( String theError )"
 * message, you specify the error message in the constructor, and only
 * create an ErrorHandler object if it's needed.
 */

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class is in charge of displaying errors to the user.
 * This applies to errors not serious enough to throw an exception,
 * but worth warning the user about.
 * 
 * @author Miles Evenson, Russell Stringer
 */
public class ErrorHandler {
	
	/**
	 * ErrorHandler receives the newError message with a warning string.
	 * It then opens a dialog box displaying that string, and waits
	 * for the user to dismiss it.
	 * 
	 * @param theError is a String that contains the error message.
	 */
	public ErrorHandler (String theError) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, theError, "Error!", JOptionPane.ERROR_MESSAGE);
	}
}