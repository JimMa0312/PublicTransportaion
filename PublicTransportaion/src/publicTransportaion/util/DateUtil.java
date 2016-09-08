package publicTransportaion.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	private static final String TIME_PATTER="YYYY-mm-dd";
	private static final DateTimeFormatter TIME_FORMATTER=DateTimeFormatter.ofPattern(TIME_PATTER);
	
	public static String format(LocalDate date) {
		if (date==null) {
			return null;
		}
		return TIME_FORMATTER.format(date);
	}
	
	public static LocalDate parse(String TimeString) {
		try {
			return TIME_FORMATTER.parse(TimeString,LocalDate::from);
		} catch (DateTimeException e) {
			// TODO: handle exception
			return null;
		}
	}

}
