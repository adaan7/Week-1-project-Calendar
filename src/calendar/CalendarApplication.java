package calendar;

import java.io.FilterInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Scanner;

import reminder.Reminder;

public class CalendarApplication {

	// Coded by Adan Agic

	public static void main(String[] args) throws IOException {

		// pozivamo metodu koja pokrece calendar aplikaciju
		calendarApplication();

	}

	// metoda pomocu koje pokrecemo calendar aplikaciju
	public static void calendarApplication() throws IOException {
		// novi scanner
		Scanner input = new Scanner(new FilterInputStream(System.in) {
			@Override
			public void close() throws IOException {
				// overrajdamo close() metodu i ostavimo je bez implementacije
			}
		});

		// kreiramo arraylist objekat
		ArrayList<Reminder> reminderList = new ArrayList<>();
		// kopiramo podatke sa fajla u arraylist
		fileinputoutput.FileOutput.copyFileToReminderList(reminderList);

		int userOption = 0;

		// pozivamo metodu koja ispisuje header
		display.Display.printHeader();

		while (userOption != 3) {
			// pozivamo metodu koja ispisuje main menu
			display.Display.printMainMenu();

			// pozivamo metodu koja uzima unos integera od korisnika sa dva
			// uslova
			userOption = userinput.IntUserInput
					.getIntUserInputWithTwoConditions(input, 1, 3,
							"Choose your option: ");

			if (userOption == 1) {
				// DISPLAY CALENDAR

				System.out.println(" ");

				// pozivamo metodu koja uzima unos integera od korisnika sa
				// jednim uslovom
				int year = userinput.IntUserInput
						.getIntUserInputWithOneCondition(input, 1,
								"Enter a year (YYYY format): ");
				// pozivamo metodu koja uzima unos integera od korisnika sa dva
				// uslova
				int month = userinput.IntUserInput
						.getIntUserInputWithTwoConditions(input, 1, 12,
								"Enter a month (number from 1 to 12): ");

				// kreiramo novi gregoriancalendar objekat
				Calendar calendar = new GregorianCalendar(year, month - 1, 1);
				// deklarisemo int varijablu kojoj dodjelimo broj dana u
				// odabranom mjesecu
				int numberOfDaysInAMonth = calendar
						.getActualMaximum(Calendar.DAY_OF_MONTH);

				// pozivamo metodu koja ispisuje kalendar za odabrani mjesec
				display.Display.displayCalendar(calendar);

				int userCalendarOption = 0;

				while (userCalendarOption != 2) {
					// pozivamo metodu koja ispisuje display calendar menu
					display.Display.printDisplayCalendarMenu();

					// pozivamo metodu koja uzima unos integera od korisnika sa
					// dva uslova
					userCalendarOption = userinput.IntUserInput
							.getIntUserInputWithTwoConditions(input, 1, 2,
									"Choose your option: ");

					if (userCalendarOption == 1) {
						// ADD REMINDER

						int day = 0;
						String note = "";
						String text = "Enter a day (from 1 to "
								+ numberOfDaysInAMonth + "): ";

						System.out.println(" ");

						// pozivamo metodu koja uzima unos integera od korisnika
						// sa dva uslova
						day = userinput.IntUserInput
								.getIntUserInputWithTwoConditions(input, 1,
										numberOfDaysInAMonth, text);

						note = userinput.StringUserInput
								.getStringUserInput(input);

						// pravimo novi objekat gregoriancalendar sa specificnom
						// godinom, mjesecom i danom
						Calendar newCalendar = new GregorianCalendar(year,
								month - 1, day);

						// pravimo novi reminder objekat sa datumom i note-om
						Reminder newReminder = new Reminder(note, newCalendar);
						// dodajemo novi reminder objekat u arraylist
						reminderList.add(newReminder);
						// pozivamo metodu koja ispisuje reminder list u fajl
						fileinputoutput.FileInput
								.copyReminderListToFile(reminderList);

						System.out
								.println("\nReminder has been successfully added!");
					}
				}
			} else if (userOption == 2) {
				// REMINDER LIST

				// ako je reminder lista prazna ispisujemo odgovarajucu poruku
				if (reminderList.size() == 0) {
					System.out.println("\nReminder list is empty.");
				} else {
					// sortiramo reminder listu po datumu
					Collections.sort(reminderList);
					// pozivamo metodu koja ispisuje reminder listu
					display.Display.printReminderList(reminderList);

					int userReminderOption = 0;

					while (userReminderOption != 2) {
						// pozivamo metodu koja ispisuje reminder list menu
						display.Display.printReminderListMenu();

						userReminderOption = userinput.IntUserInput
								.getIntUserInputWithTwoConditions(input, 1, 2,
										"Choose your option: ");

						if (userReminderOption == 1) {
							// DELETE REMINDER

							System.out.println(" ");

							// pozivamo metodu koja uzima unos integera od
							// korisnika sa jednim uslovom
							int indexOfReminder = userinput.IntUserInput
									.getIntUserInputWithOneCondition(input, 1,
											"Enter number of reminder you want to delete: ");

							// ako je uneseni index veci od posljednjeg indexa u
							// reminder listi ispisujemo odgovarajucu poruku
							if ((indexOfReminder - 1) > reminderList.size() - 1) {
								System.out
										.println("\nReminder with number '"
												+ indexOfReminder
												+ "' does not exist.");
							} else { // ako je unesen index postojeci u reminder
								// listi, uklanjamo odabrani reminder iz
								// reminder liste
								reminderList.remove(indexOfReminder - 1);
								// ispisujemo reminder listu u fajl
								fileinputoutput.FileInput
										.copyReminderListToFile(reminderList);

								System.out
										.println("\nReminder has been successfully deleted.");
							}
						}
					}
				}
			}
		}

		// zatvaramo scanner
		input.close();

		// ispisujemo pozdravnu poruku jer je korisnik izasao iz aplikacije
		System.out.println("\nSee you soon!");

	}

}
