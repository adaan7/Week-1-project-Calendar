package ba.adan.calendarapp.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ba.adan.calendarapp.fileio.FileInput;
import ba.adan.calendarapp.reminder.Reminder;
import ba.adan.calendarapp.ui.IntUserInput;

public class DeleteReminderOption {

	public static void deleteReminderOption(Scanner input,
			ArrayList<Reminder> reminderList) throws IOException {

		System.out.println(" ");

		// pozivamo metodu koja uzima unos integera od korisnika
		int indexOfReminder = IntUserInput.getIntUserInputWithOneCondition(
				input, 1, "Enter number of reminder you want to delete: ");

		// ako je uneseni index veci od posljednjeg indexa u
		// reminder listi ispisujemo odgovarajucu poruku
		if ((indexOfReminder - 1) > reminderList.size() - 1) {
			System.out.println("\nReminder with number '" + indexOfReminder
					+ "' does not exist.");
		} else {
			// ako je unesen index postojeci u reminder listi, uklanjamo
			// odabrani reminder iz reminder liste
			reminderList.remove(indexOfReminder - 1);

			// ispisujemo reminder listu u fajl
			FileInput.copyReminderListToFile(reminderList);

			System.out.println("\nReminder has been successfully deleted.");
		}
	}

}
