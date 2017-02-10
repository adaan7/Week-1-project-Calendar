package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Reminder implements Comparable<Reminder> { // implementiramo comparable interface da bi mogli sortirati remindere
	
	private String note;
	private Calendar calendar;

	public Reminder() { // napravimo konstruktor koji ne prima argumente
		note = "";
		calendar = new GregorianCalendar();
	}

	public Reminder(String note, Calendar calendar) { // napravimo konstruktor sa argumentima note i calendar
		this.note = note;
		this.calendar = calendar;
	}

	public String getNote() { // pravimo getter za note
		return note;
	}

	public void setNote(String note) { // pravimo setter za note
		this.note = note;
	}

	public Calendar getCalendar() { // pravimo getter za calendar
		return calendar;
	}

	@Override
	public String toString() { // overrajdamo i implementiramo metodu toString
		return calendar.get(Calendar.DATE) + "." + (calendar.get(Calendar.MONTH) + 1) + "."
				+ calendar.get(Calendar.YEAR) + ". - " + note;
	}

	@Override
	public int compareTo(Reminder reminder) { // overrajdamo i implementiramo metodu compareTo da poredi objekat po datumu
		if (calendar.compareTo(reminder.getCalendar()) > 0) {
			return 1;
		} else if (calendar.compareTo(reminder.getCalendar()) == 0) {
			return 0;
		} else {
			return -1;
		}
	}

}
