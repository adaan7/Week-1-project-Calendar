package ba.adan.calendarapp.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import ba.adan.calendarapp.reminder.Reminder;

public class AddReminderOption {

	public static void addReminderOption(Scanner input,
			int numberOfDaysInAMonth, int month, int year,
			ArrayList<Reminder> reminderList) throws IOException {

		int day = 0;
		String note = "";
		String text = "Enter a day (from 1 to " + numberOfDaysInAMonth + "): ";

		System.out.println(" ");

		// pozivamo metodu koja uzima unos integera od korisnika
		// sa dva uslova
		day = ba.adan.calendarapp.ui.IntUserInput
				.getIntUserInputWithTwoConditions(input, 1,
						numberOfDaysInAMonth, text);

		// pozivamo metodu koja uzima unos stringa od korisnika
		note = ba.adan.calendarapp.ui.StringUserInput.getStringUserInput(input);

		// pravimo novi objekat gregoriancalendar sa specificnom
		// godinom, mjesecom i danom
		Calendar newCalendar = new GregorianCalendar(year, month - 1, day);

		// pravimo novi reminder objekat sa datumom i note-om
		Reminder newReminder = new Reminder(note, newCalendar);
		// dodajemo novi reminder objekat u arraylist
		reminderList.add(newReminder);

		// pozivamo metodu koja ispisuje reminder list u fajl
		ba.adan.calendarapp.fileio.FileInput
				.copyReminderListToFile(reminderList);

		System.out.println("\nReminder has been successfully added!");
	}

}
