package fileinputoutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import reminder.Reminder;

public class FileOutput {

	// metoda koja kopira podatke iz fajla u reminder list
	public static void copyFileToReminderList(ArrayList<Reminder> reminderList)
			throws IOException {
		Path path = Paths.get("src/reminder files/reminder list.txt");

		// provjerimo da li postoji fajl, ako ne, kreiramo novi fajl
		if (!Files.exists(path)) {
			Files.createFile(path);
		}

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

}
