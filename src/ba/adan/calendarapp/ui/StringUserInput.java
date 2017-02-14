package ba.adan.calendarapp.ui;

import java.util.Scanner;

public class StringUserInput {

	// metoda koja uzima unos stringa od korisnika
	public static String getStringUserInput(Scanner input) {

		String userInput = "";
		boolean wrongUserInput = true;

		input.nextLine();

		while (wrongUserInput) {
			// uzimamo unos od korisnika
			System.out.print("Enter a note (max 80 characters): ");
			userInput = input.nextLine();

			wrongUserInput = false;

			// ogranicimo unos stringa na 80 karaktera
			if (userInput.length() > 80) {
				System.out
						.println("Wrong input. Max characters allowed is 100.");
				wrongUserInput = true;
			}
		}

		return userInput;
	}

}
