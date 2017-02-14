package ba.adan.calendarapp.display;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import ba.adan.calendarapp.reminder.Reminder;

public class Display {

	// metoda koja ispisuje header
	public static void printHeader() {
		System.out.println("============================"
				+ "============================");
		System.out.println("||                          "
				+ "                          ||");
		System.out.println("||                Calendar"
				+ " Application                ||");
		System.out.println("||                          "
				+ "                          ||");
		System.out.println("============================"
				+ "============================");
	}

	// metoda koja ispisuje main menu
	public static void printMainMenu() {
		System.out.println(" ");
		System.out.println("- MAIN MENU -");
		System.out.println("-----------------------------"
				+ "---------------------------");
		System.out.println("1. Display calendar");
		System.out.println("2. Reminder list");
		System.out.println("3. Exit application");
		System.out.println("-----------------------------"
				+ "---------------------------");
	}

	// metoda koja ispisuje display calendar menu
	public static void printDisplayCalendarMenu() {
		System.out.println(" ");
		System.out.println("- DISPLAY CALENDAR MENU -");
		System.out.println("-----------------------------"
				+ "---------------------------");
		System.out.println("1. Add reminder");
		System.out.println("2. Back to main menu");
		System.out.println("-----------------------------"
				+ "---------------------------");
	}

	// metoda koja ispisuje reminder list menu
	public static void printReminderListMenu() {
		System.out.println(" ");
		System.out.println("- REMINDER LIST MENU -");
		System.out.println("-----------------------------"
				+ "---------------------------");
		System.out.println("1. Delete reminder");
		System.out.println("2. Back to main menu");
		System.out.println("-----------------------------"
				+ "---------------------------");
	}

	// metoda koja ispisuje kalendar
	public static void displayCalendar(Calendar calendar) {
		// pravimo string sa nazivom odrabranog mjeseca
		String monthName = calendar.getDisplayName(Calendar.MONTH,
				Calendar.LONG, Locale.getDefault());
		// pravimo string sa nazivom odabrane godine
		String sYear = calendar.get(Calendar.YEAR) + "";
		String dashes = "--------------------------"
				+ "--------------------------";

		System.out.println(" ");
		// centriramo ispis odrabranog mjeseca i godine
		System.out
				.printf("%"
						+ (dashes.length() / 2 - (monthName.length() + 5) / 2 + monthName
								.length()) + "s %-4s", monthName, sYear);
		System.out.print("\n" + dashes);
		System.out.printf("\n %-3s\t%-3s\t%-3s\t%-3s\t%-3s\t%-3s\t%-3s\t\n ",
				"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");

		// deklarisemo varijablu int sa brojem dana u odabranom mjesecu
		int numberOfDaysInAMonth = calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH);
		// deklarisemo varijablu int sa prvim danom u mjesecu
		int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		int counter = 0;

		// ispisujemo prazna mjesta u zavisnosti od prvog dana mjeseca
		for (int i = 0; i < firstDayOfMonth; i++) {
			System.out.print("   \t");
			counter++;
		}

		// ispisujemo dane u mjesecu
		for (int i = 1; i <= numberOfDaysInAMonth; i++) {
			System.out.printf(" %2d\t", i);
			counter++;

			if (counter == 7) {
				System.out.print("\n ");
				counter = 0;
			}
		}

		System.out.println();
	}

	// metoda koja ispisuje reminder list
	public static void printReminderList(ArrayList<Reminder> reminderList) {
		System.out.println(" ");
		System.out.printf(" %-3s \t %-11s \t %-5s", "Num", "Date", "Note");
		System.out.print("\n----------------------------"
				+ "----------------------------\n");

		for (int i = 0; i < reminderList.size(); i++) {
			// pravimo novi objekat calendar koji uzima trenutni calendar
			Calendar currentCalendar = reminderList.get(i).getCalendar();
			// pravimo string sa trenutnim datumom
			String currentDate = currentCalendar.get(Calendar.DATE) + "."
					+ (currentCalendar.get(Calendar.MONTH) + 1) + "."
					+ currentCalendar.get(Calendar.YEAR) + ".";
			String sIndex = i + 1 + ".";

			// ispisujemo listu remindera
			System.out.printf(" %-3s \t %-11s \t %-5s\n", sIndex, currentDate,
					reminderList.get(i).getNote());
		}
	}

}