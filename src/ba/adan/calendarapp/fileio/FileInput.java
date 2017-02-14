package ba.adan.calendarapp.fileio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;

import ba.adan.calendarapp.reminder.Reminder;

public class FileInput {

	// metoda koja kopira reminder list u fajl
	public static void copyReminderListToFile(ArrayList<Reminder> reminderList)
			throws IOException {
		Path path = Paths.get("src/ba.adan.calendarapp.reminder.reminder files/reminder list.txt");

		// provjerimo da li postoji fajl, ako ne, kreiramo novi fajl
		if (!Files.exists(path)) {
			Files.createFile(path);
		}

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
