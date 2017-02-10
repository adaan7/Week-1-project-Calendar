package calendar;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class CalendarApplication {

	// Coded by Adan Agic

	public static void main(String[] args) throws IOException {

		// kreiramo novi scanner objekat
		Scanner input = new Scanner(System.in);

		// kreiramo arraylist objekat
		ArrayList<Reminder> reminderList = new ArrayList<>();
		// kopiramo podatke sa fajla u arraylist
		copyFileToReminderList(reminderList);

		int userOption = 0;

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

		while (userOption != 3) {
			// pozivamo metodu koja ispisuje main menu
			printMainMenu();

			// deklarisemo varijablu boolean tipa
			boolean wrongUserOption = true;

			// petlja radi sve dok korisnik ne unese ispravan unos
			while (wrongUserOption) {
				try {
					// uzimamo unos od korisnika
					System.out.print("Choose your option: ");
					userOption = input.nextInt();

					// postavljamo boolean varijablu na false da bi izasli iz
					// petlje ako unos bude ispravan
					wrongUserOption = false;

					if (userOption < 1 || userOption > 3) {
						System.out
								.println("Wrong input. You have to enter a number from 1 to 3.");
						wrongUserOption = true;
					}
				} catch (Exception ex) {
					// ukoliko je uhvacen exception ispisujemo odgovarajucu
					// poruku
					System.out
							.println("Wrong input. You have to enter a number from 1 to 3.");
					input.nextLine();
				}
			}

			if (userOption == 1) {
				// DISPLAY CALENDAR

				int month = 0, year = 0;
				boolean wrongMonthInput = true, wrongYearInput = true;

				System.out.println(" ");

				while (wrongYearInput) {
					try {
						// uzimamo unos od korisnika
						System.out.print("Enter a year (YYYY format): ");
						year = input.nextInt();

						// postavljamo boolean varijablu na false da bi izasli
						// iz petlje ako unos bude ispravan
						wrongYearInput = false;

						if (year < 1970) {
							System.out
									.println("Wrong input. You have to enter a number from 1970.");
							wrongYearInput = true;
						}
					} catch (Exception ex) {
						System.out
								.println("Wrong input. You have to enter a number from 1970.");
						input.nextLine();
					}
				}

				while (wrongMonthInput) {
					try {
						// uzimamo unos od korisnika
						System.out
								.print("Enter a month (number from 1 to 12): ");
						month = input.nextInt();

						// postavljamo boolean varijablu na false da bi izasli
						// iz petlje ako unos bude ispravan
						wrongMonthInput = false;

						if (month < 1 || month > 12) {
							System.out
									.println("Wrong input. You have to enter a number from 1 to 12.");
							wrongMonthInput = true;
						}
					} catch (Exception ex) {
						// ukoliko je uhvacen exception ispisujemo odgovarajucu
						// poruku
						System.out
								.println("Wrong input. You have to enter a number from 1 to 12.");
						input.nextLine();
					}
				}

				// kreiramo novi gregoriancalendar objekat
				Calendar calendar = new GregorianCalendar(year, month - 1, 1);
				// deklarisemo int varijablu kojoj dodjelimo broj dana u
				// odabranom mjesecu
				int numberOfDaysInAMonth = calendar
						.getActualMaximum(Calendar.DAY_OF_MONTH);

				// pozivamo metodu koja ispisuje kalendar za odabrani mjesec
				displayCalendar(calendar);

				int userCalendarOption = 0;

				while (userCalendarOption != 2) {
					// pozivamo metodu koja ispisuje display calendar menu
					printDisplayCalendarMenu();

					boolean wrongUserCalendarOption = true;

					while (wrongUserCalendarOption) {
						try {
							// uzimamo unos od korisnika
							System.out.print("Choose your option: ");
							userCalendarOption = input.nextInt();

							wrongUserCalendarOption = false;

							if (userCalendarOption < 1
									|| userCalendarOption > 2) {
								System.out
										.println("Wrong input. You have to enter a number from 1 to 2.");
								wrongUserCalendarOption = true;
							}
						} catch (Exception ex) {
							// ako je uhvacen exception ispisujemo odgovarajucu
							// poruku
							System.out
									.println("Wrong input. You have to enter a number from 1 to 2.");
							input.nextLine();
						}
					}

					if (userCalendarOption == 1) {
						// ADD REMINDER

						int day = 0;
						String note = "";

						boolean wrongDayInput = true;

						System.out.println(" ");

						while (wrongDayInput) {
							try {
								// uzimamo unos od korisnika
								System.out.print("Enter a day (from 1 to "
										+ numberOfDaysInAMonth + "): ");
								day = input.nextInt();

								wrongDayInput = false;

								if (day < 1 || day > numberOfDaysInAMonth) {
									// ako je unesen dan manji od 1 ili veci od
									// broja dana mjeseca ispisujemo
									// odgovarajucu poruku
									System.out
											.println("Wrong input. You have to enter a number from 1 to "
													+ numberOfDaysInAMonth
													+ ".");
									wrongDayInput = true;
								}
							} catch (Exception ex) {
								// ako je uhvacen exception ispisujemo
								// odgovarajucu poruku
								System.out
										.println("Wrong input. You have to enter a number from 1 to "
												+ numberOfDaysInAMonth + ".");
								input.nextLine();
							}
						}

						input.nextLine();
						// uzimamo unos od korisnika
						System.out.print("Enter a note: ");
						note = input.nextLine();

						// pravimo novi objekat gregoriancalendar sa specificnom
						// godinom, mjesecom i danom
						Calendar newCalendar = new GregorianCalendar(year,
								month - 1, day);

						// pravimo novi reminder objekat sa datumom i note-om
						Reminder newReminder = new Reminder(note, newCalendar);
						// dodajemo novi reminder objekat u arraylist
						reminderList.add(newReminder);
						// pozivamo metodu koja ispisuje reminder list u fajl
						copyReminderListToFile(reminderList);

						System.out
								.println("\nReminder has been successfully added!");
					}
				}
			} else if (userOption == 2) {
				// REMINDER LIST

				if (reminderList.size() == 0) { // ako je reminder lista prazna
												// ispisujemo odgovarajucu
												// poruku
					System.out.println("\nReminder list is empty.");
				} else {
					// sortiramo reminder listu po datumu
					Collections.sort(reminderList);
					// pozivamo metodu koja ispisuje reminder listu
					printReminderList(reminderList);

					int userReminderOption = 0;

					while (userReminderOption != 2) {
						// pozivamo metodu koja ispisuje reminder list menu
						printReminderListMenu();

						boolean wrongReminderOption = true;

						while (wrongReminderOption) {
							try {
								// uzimamo unos od korisnika
								System.out.print("Choose your option: ");
								userReminderOption = input.nextInt();

								wrongReminderOption = false;

								if (userReminderOption < 1
										|| userReminderOption > 2) {
									System.out
											.println("Wrong input. You have to enter a number from 1 to 2.");
									wrongReminderOption = true;
								}
							} catch (Exception ex) {
								// ako je uhvacen exception ispisujemo
								// odgovarajucu
								// poruku
								System.out
										.println("Wrong input. You have to enter a number from 1 to 2.");
								input.nextLine();
							}
						}

						if (userReminderOption == 1) {
							// DELETE REMINDER

							int indexOfReminder = -1;
							boolean wrongReminderNumber = true;

							System.out.println(" ");

							while (wrongReminderNumber) {
								try {
									// uzimamo unos od korisnika
									System.out
											.print("Enter number of reminder you want to delete: ");
									indexOfReminder = input.nextInt();

									wrongReminderNumber = false;

									if (indexOfReminder < 1) {
										// ako je unesen broj manji od 1
										// ispisujemo
										// odgovarajucu poruku
										System.out
												.println("Wrong input. You have to enter a positive integer.");
										wrongReminderNumber = true;
									}
								} catch (Exception ex) {
									// ako je uhvacen exception ispisujemo
									// odgovarajucu poruku
									System.out
											.println("Wrong input. You have to enter an integer.");
									input.nextLine();
								}
							}

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
								copyReminderListToFile(reminderList);

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

		// deklarišemo varijablu int sa brojem dana u odabranom mjesecu
		int numberOfDaysInAMonth = calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH);
		// deklarišemo varijablu int sa prvim danom u mjesecu
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

	// metoda koja kopira podatke iz fajla u reminder list
	public static void copyFileToReminderList(ArrayList<Reminder> reminderList)
			throws IOException {
		Path path = Paths.get("src/calendar/reminder list.txt");
		// pravimo novi scanner objekat koji ce citati podatke sa fajla
		Scanner readFile = new Scanner(path);

		while (readFile.hasNextLine()) { // petlja radi sve dok ima linija u
											// fajlu
			// pravimo string sa linijom koja sadrzi datum
			String date = readFile.nextLine();
			// pravimo string sa linijom koja sadrzi note
			String note = readFile.nextLine();

			// string datum prebacujemo u array i uzimamo dan, mjesec i godinu
			String[] dateArray = date.split(" ");
			int day = Integer.parseInt(dateArray[0]);
			int month = Integer.parseInt(dateArray[1]);
			int year = Integer.parseInt(dateArray[2]);

			// pravimo novi objekat gregoriancalendar sa specificnom godinom,
			// mjesecom i datumom
			Calendar newCalendar = new GregorianCalendar(year, month, day);
			// pravimo novi objekat reminder sa note-om i datumom koji smo
			// ucitali sa fajla
			Reminder newReminder = new Reminder(note, newCalendar);

			// dodajemo novi objekat reminder u arraylist
			reminderList.add(newReminder);
		}

		// zatvaramo scanner
		readFile.close();
	}

	// metoda koja kopira reminder list u fajl
	public static void copyReminderListToFile(ArrayList<Reminder> reminderList)
			throws IOException {
		Path path = Paths.get("src/calendar/reminder list.txt");
		// pravimo novi bufferedwriter objekat
		BufferedWriter writer = Files.newBufferedWriter(path);

		for (int i = 0; i < reminderList.size(); i++) {
			// deklarišemo int varijable koje sadrze dan, mjesec i godinu
			int day = reminderList.get(i).getCalendar().get(Calendar.DATE);
			int month = reminderList.get(i).getCalendar().get(Calendar.MONTH);
			int year = reminderList.get(i).getCalendar().get(Calendar.YEAR);

			// ispisujemo datum u fajl
			writer.write(day + " ");
			writer.write(month + " ");
			writer.write(year + "");
			// prelazimo u novi red fajla
			writer.newLine();

			String note = reminderList.get(i).getNote();
			// ispisujemo note u fajl
			writer.write(note);
			// prelazimo u novi red fajla
			writer.newLine();
		}

		// zatvaramo bufferedwriter
		writer.close();
	}

}
