package mancala;

/**
 * 
 * @author Eric Salvi
 *
 * This class opens and changes the pages of the Quick Start Menu.
 * It is designed to be able to be opened by itself or called from another class.
 * 
 */

public class QuickStart {

	static int pageNumber = 0;

	// Allows the Quick Start Menu to run on its own.
	public static void main(String[] args) throws InterruptedException {
		run();
	}

	// Opens the first page.
	public static void run() {
		HTML_DisplayPage.displayURL("files/page" + pageNumber + ".html");
	}

	// Opens the next page, or if the page number is currently 9, opens the exit page.
	public static void next() {
		if (pageNumber == 9) {
			HTML_DisplayPage.displayURL("files/Exit.html");
		} else {
			pageNumber++;
			HTML_DisplayPage.displayURL("files/page" + pageNumber + ".html");
		}
	}

	// Opens the previous page, or if the page number is currently 0, opens the exit page.
	public static void back() {
		if (pageNumber == 0) {
			HTML_DisplayPage.displayURL("files/Exit.html");
		} else {
			pageNumber--;
			HTML_DisplayPage.displayURL("files/page" + pageNumber + ".html");
		}
	}
}
