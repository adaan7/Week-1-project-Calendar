package ba.adan.calendarapp.reminder;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Reminder implements Comparable<Reminder> {
	// implementiramo comparable interface da bi mogli sortirati remindere

	private String note;
	private Calendar calendar;

	// napravimo konstruktor koji ne prima argumente
	public Reminder() {
		note = "";
		calendar = new GregorianCalendar();
	}

	// napravimo konstruktor sa argumentima note i calendar
	public Reminder(String note, Calendar calendar) {
		this.note = note;
		this.calendar = calendar;
	}

	// pravimo getter za note
	public String getNote() {
		return note;
	}

	// pravimo setter za note
	public void setNote(String note) {
		this.note = note;
	}

	// pravimo getter za calendar
	public Calendar getCalendar() {
		return calendar;
	}

	// overrajdamo i implementiramo metodu toString
	@Override
	public String toString() {
		return calendar.get(Calendar.DATE) + "."
				+ (calendar.get(Calendar.MONTH) + 1) + "."
				+ calendar.get(Calendar.YEAR) + ". - " + note;
	}

	// overrajdamo i implementiramo metodu compareTo da poredi objekat po datumu
	@Override
	public int compareTo(Reminder reminder) {
		if (calendar.compareTo(reminder.getCalendar()) > 0) {
			return 1;
		} else if (calendar.compareTo(reminder.getCalendar()) == 0) {
			return 0;
		} else {
			return -1;
		}
	}

}
