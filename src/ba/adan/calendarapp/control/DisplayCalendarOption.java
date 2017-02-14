package ba.adan.calendarapp.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import ba.adan.calendarapp.reminder.Reminder;

public class DisplayCalendarOption {

	public static void displayCalendarOption(Scanner input,
			ArrayList<Reminder> reminderList) throws IOException {
		System.out.println(" ");

		// pozivamo metodu koja uzima unos integera od korisnika sa
		// jednim uslovom
		int year = ba.adan.calendarapp.ui.IntUserInput
				.getIntUserInputWithOneCondition(input, 1,
						"Enter a year (YYYY format): ");
		// pozivamo metodu koja uzima unos integera od korisnika sa dva
		// uslova
		int month = ba.adan.calendarapp.ui.IntUserInput
				.getIntUserInputWithTwoConditions(input, 1, 12,
						"Enter a month (number from 1 to 12): ");

		// kreiramo novi gregoriancalendar objekat
		Calendar calendar = new GregorianCalendar(year, month - 1, 1);

		// deklarisemo int varijablu kojoj dodjelimo broj dana u
		// odabranom mjesecu
		int numberOfDaysInAMonth = calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH);

		// pozivamo metodu koja ispisuje kalendar za odabrani mjesec
		ba.adan.calendarapp.display.Display.displayCalendar(calendar);

		int userCalendarOption = 0;

		while (userCalendarOption != 2) {
			// pozivamo metodu koja ispisuje display calendar menu
			ba.adan.calendarapp.display.Display.printDisplayCalendarMenu();

			// pozivamo metodu koja uzima unos integera od korisnika sa
			// dva uslova
			userCalendarOption = ba.adan.calendarapp.ui.IntUserInput
					.getIntUserInputWithTwoConditions(input, 1, 2,
							"Choose your option: ");

			if (userCalendarOption == 1) {
				// ADD REMINDER

				// pozivamo metodu add reminder option
				AddReminderOption.addReminderOption(input,
						numberOfDaysInAMonth, month, year, reminderList);
			}
		}
	}

}
