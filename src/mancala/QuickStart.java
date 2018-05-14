package quickStartMenu;

/**
 * 
 * @author Eric Salvi
 *
 * This class opens and changes the pages of the Quick Start Menu.
 * It is designed to be able to be opened by itself or called from another class.
 * This class also includes the methods openInBrowser and version1, which are alternate
 * ways to use the Quick Start Menu.
 * 
 */

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class QuickStart {

	static int pageNumber = 1;
	static boolean keepRunning = true;

	// Allows the Quick Start Menu to run on its own.
	// If you want to use openInBrowser or version1, change the commented code accordingly.
	public static void main(String[] args) throws InterruptedException {
		run();
//		openInBrowser();
//		version1();
		System.out.println("Finished Running");
	}

	// Runs the Quick Start Menu
	public static void run() throws InterruptedException {
		while (keepRunning == true) {
			// Display the page the user is on then automatically go to the next page after 7 seconds.
			HTML_DisplayPage.displayURL("files/page" + pageNumber + ".html");
			Thread.sleep(7000);
			next();
		}
	}

	// Increases the page number, or if the page number is currently 9, opens the exit page.
	public static void next() {
		if (pageNumber == 9) {
			HTML_DisplayPage.displayURL("files/Exit.html");
			keepRunning = false;
		} else {
			pageNumber++;
		}
	}

	// Decreases the page number, or if the page number is currently 0, opens the exit page.
	public static void back() {
		if (pageNumber == 0) {
			HTML_DisplayPage.displayURL("files/Exit.html");
			keepRunning = false;
		} else {
			pageNumber--;
		}
	}

	// Use this method if you want to open the Quick Start Menu in the Computer's default web browser.
	// Works on Windows, whether it works on Mac is unknown.
	public static void openInBrowser() throws IOException, URISyntaxException {
		if (Desktop.isDesktopSupported()) {
			File htmlFile = new File("C://Users//Q//workspace//Quick Start Menu//src//quickStartMenu//files/QSM.html");
			Desktop.getDesktop().browse(htmlFile.toURI());
		}
	}

	// A previous version of this code that includes next and back options via typing into the console.
	// Although currently unused, I wanted to include it.
	public static void version1() {
		Scanner sc = new Scanner(System.in);
		int input = 0;
		pageNumber = 0;
		while (keepRunning == true) {

			// Display the page the user is on.
			HTML_DisplayPage.displayURL("files/page" + pageNumber + ".html");
			System.out.println("Continue: 1 Back: 2");
			input = sc.nextInt();

			// User selects next.
			if (input == 1) {
				next();

				// User selects back.
			} else if (input == 2) {
				back();

				// An invalid option is selected.
			} else {
				System.out.println("Invalid option");
			}
		}
		sc.close();
	}
}