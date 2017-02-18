package ba.adan.calendarapp.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import ba.adan.calendarapp.display.Display;
import ba.adan.calendarapp.reminder.Reminder;
import ba.adan.calendarapp.ui.IntUserInput;

public class DisplayCalendarOption {

	public static void displayCalendarOption(Scanner input,
			ArrayList<Reminder> reminderList) throws IOException {
		System.out.println(" ");

		// pozivamo metode koja uzimaju unos integera od korisnika
		int year = IntUserInput.getIntUserInputWithOneCondition(input, 1,
				"Enter a year (YYYY format): ");
		int month = IntUserInput.getIntUserInputWithTwoConditions(input, 1, 12,
				"Enter a month (number from 1 to 12): ");

		// kreiramo novi gregoriancalendar objekat
		Calendar calendar = new GregorianCalendar(year, month - 1, 1);

		int numberOfDaysInAMonth = calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH);

		// pozivamo metodu koja ispisuje kalendar za odabrani mjesec
		Display.displayCalendar(calendar);

		int userCalendarOption = 0;

		while (userCalendarOption != 2) {
			// pozivamo metodu koja ispisuje display calendar menu
			Display.printDisplayCalendarMenu();

			userCalendarOption = IntUserInput.getIntUserInputWithTwoConditions(
					input, 1, 2, "Choose your option: ");

			if (userCalendarOption == 1) {
				// ADD REMINDER

				// pozivamo metodu add reminder option
				AddReminderOption.addReminderOption(input,
						numberOfDaysInAMonth, month, year, reminderList);
			}
		}
	}

}
