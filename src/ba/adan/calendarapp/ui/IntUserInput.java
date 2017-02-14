package ba.adan.calendarapp.ui;

import java.util.Scanner;

public class IntUserInput {

	// metoda koja uzima unos integera od korisnika sa jednim uslovom
	public static int getIntUserInputWithOneCondition(Scanner input,
			int condition, String text) {

		int userInput = 0;
		boolean wrongUserInput = true;

		// petlja radi dok je unos korisnika pogresan
		while (wrongUserInput) {
			try {
				// uzimamo unos od korisnika
				System.out.print(text);
				userInput = input.nextInt();

				// postavljamo boolean varijablu na false da bi izasli
				// iz petlje ako unos bude ispravan
				wrongUserInput = false;

				// ako je uslov ispunjen ispisujemo odgovarajucu poruku i
				// nastavljamo rad petlje
				if (userInput < condition) {
					System.out.println("Wrong input. You have to enter a "
							+ "number bigger or equals to " + condition + ".");
					wrongUserInput = true;
				}
			} catch (Exception ex) {
				System.out.println("Wrong input. You have to enter a "
						+ "number bigger or equals to " + condition + ".");
				input.nextLine();
			}
		}

		return userInput;
	}

	// metoda koja uzima unos integera od korisnika sa dva uslova
	public static int getIntUserInputWithTwoConditions(Scanner input,
			int firstCondition, int lastCondition, String text) {

		int userInput = 0;
		boolean wrongUserInput = true;

		// petlja radi dok je unos korisnika pogresan
		while (wrongUserInput) {
			try {
				// uzimamo unos od korisnika
				System.out.print(text);
				userInput = input.nextInt();

				// postavljamo boolean na false da bi izasli iz petlje
				wrongUserInput = false;

				// ako je uslov ispunjen ispisujemo odgovarajucu poruku i
				// nastavljamo rad petlje
				if (userInput < firstCondition || userInput > lastCondition) {
					System.out
							.println("Wrong input. You have to enter a number from "
									+ firstCondition
									+ " to "
									+ lastCondition
									+ ".");
					wrongUserInput = true;
				}
			} catch (Exception ex) {
				// ako je uhvacen exception ispisujemo odgovarajucu
				// poruku
				System.out
						.println("Wrong input. You have to enter a number from "
								+ firstCondition + " to " + lastCondition + ".");
				input.nextLine();
			}
		}

		return userInput;
	}

}
