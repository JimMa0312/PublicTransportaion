package publicTransportaion.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateConverter {
	private static final String PARSESTRING = "YYYY-mm-dd";
	private static final SimpleDateFormat SDF = new SimpleDateFormat(PARSESTRING);

	public static String format(Date date) {
		if (date == null) {
			return null;
		}
		return SDF.format(date);
	}
}
