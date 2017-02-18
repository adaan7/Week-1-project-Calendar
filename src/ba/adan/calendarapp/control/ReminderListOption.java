package ba.adan.calendarapp.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import ba.adan.calendarapp.display.Display;
import ba.adan.calendarapp.reminder.Reminder;
import ba.adan.calendarapp.ui.IntUserInput;

public class ReminderListOption {

	public static void reminderListOption(Scanner input,
			ArrayList<Reminder> reminderList) throws IOException {

		// ako je reminder lista prazna ispisujemo odgovarajucu poruku
		if (reminderList.size() == 0) {
			System.out.println("\nReminder list is empty.");

		} else {
			// sortiramo reminder listu po datumu
			Collections.sort(reminderList);

			// pozivamo metodu koja ispisuje reminder listu
			Display.printReminderList(reminderList);

			int userReminderOption = 0;

			while (userReminderOption != 2) {
				// pozivamo metodu koja ispisuje reminder list menu
				Display.printReminderListMenu();

				userReminderOption = IntUserInput
						.getIntUserInputWithTwoConditions(input, 1, 2,
								"Choose your option: ");

				if (userReminderOption == 1) {
					// DELETE REMINDER

					// pozivamo metodu delete reminder option
					DeleteReminderOption.deleteReminderOption(input,
							reminderList);
				}
			}
		}
	}

}
