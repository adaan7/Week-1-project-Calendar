package ba.adan.calendarapp.control;

import java.io.FilterInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ba.adan.calendarapp.display.Display;
import ba.adan.calendarapp.fileio.FileOutput;
import ba.adan.calendarapp.reminder.Reminder;
import ba.adan.calendarapp.ui.IntUserInput;

public class CalendarControl {

	// metoda pomocu koje pokrecemo calendar aplikaciju
	public static void calendarApplication() throws IOException {

		Scanner input = new Scanner(new FilterInputStream(System.in) {
			@Override
			public void close() throws IOException {
				// overrajdamo close() metodu i ostavimo je bez implementacije
			}
		});

		ArrayList<Reminder> reminderList = new ArrayList<>();
		// kopiramo podatke sa fajla u arraylist
		FileOutput.copyFileToReminderList(reminderList);

		int userOption = 0;

		// pozivamo metodu koja ispisuje header
		Display.printHeader();

		while (userOption != 3) {
			// pozivamo metodu koja ispisuje main menu
			Display.printMainMenu();

			// pozivamo metodu koja uzima unos integera od korisnika
			userOption = IntUserInput.getIntUserInputWithTwoConditions(input,
					1, 3, "Choose your option: ");

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

		input.close();

		// ispisujemo pozdravnu poruku jer je korisnik izasao iz aplikacije
		System.out.println("\nSee you soon!");

	}

}
