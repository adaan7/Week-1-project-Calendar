package ba.adan.calendarapp.control;

import java.io.FilterInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ba.adan.calendarapp.reminder.Reminder;

public class CalendarControl {

	// metoda pomocu koje pokrecemo calendar aplikaciju
	public static void calendarApplication() throws IOException {

		// novi scanner
		Scanner input = new Scanner(new FilterInputStream(System.in) {
			@Override
			public void close() throws IOException {
				// overrajdamo close() metodu i ostavimo je bez implementacije
			}
		});

		ArrayList<Reminder> reminderList = new ArrayList<>();
		// kopiramo podatke sa fajla u arraylist
		ba.adan.calendarapp.fileio.FileOutput
				.copyFileToReminderList(reminderList);

		int userOption = 0;

		// pozivamo metodu koja ispisuje header
		ba.adan.calendarapp.display.Display.printHeader();

		while (userOption != 3) {
			// pozivamo metodu koja ispisuje main menu
			ba.adan.calendarapp.display.Display.printMainMenu();

			// pozivamo metodu koja uzima unos integera od korisnika sa dva
			// uslova
			userOption = ba.adan.calendarapp.ui.IntUserInput
					.getIntUserInputWithTwoConditions(input, 1, 3,
							"Choose your option: ");

			if (userOption == 1) {
				// DISPLAY CALENDAR

				// pozivamo metodu display calendar option
				DisplayCalendarOption
						.displayCalendarOption(input, reminderList);

			} else if (userOption == 2) {
				// REMINDER LIST

				// pozivamo metodu reminder list option
				ReminderListOption.reminderListOption(input, reminderList);
			}
		}

		// zatvaramo scanner
		input.close();

		// ispisujemo pozdravnu poruku jer je korisnik izasao iz aplikacije
		System.out.println("\nSee you soon!");

	}

}
