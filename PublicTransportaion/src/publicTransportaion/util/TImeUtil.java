package publicTransportaion.util;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TImeUtil {
	private static final String TIME_PATTER="HH:mm:ss";
	private static final DateTimeFormatter TIME_FORMATTER=DateTimeFormatter.ofPattern(TIME_PATTER);
	
	public static String format(LocalTime time) {
		if (time==null) {
			return null;
		}
		return TIME_FORMATTER.format(time);
	}
	
	public static LocalTime parse(String TimeString) {
		try {
			return TIME_FORMATTER.parse(TimeString,LocalTime::from);
		} catch (DateTimeException e) {
			// TODO: handle exception
			return null;
		}
	}

}
