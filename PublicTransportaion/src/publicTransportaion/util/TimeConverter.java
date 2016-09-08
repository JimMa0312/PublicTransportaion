package publicTransportaion.util;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class TimeConverter {
	
	private static final String PARSESTRING="HH:mm:ss";
	private static final SimpleDateFormat SDF=new SimpleDateFormat(PARSESTRING);
	
	public static String format(Time time) {
		if (time==null) {
			return null;
		}
		return SDF.format(time);
	}
}
