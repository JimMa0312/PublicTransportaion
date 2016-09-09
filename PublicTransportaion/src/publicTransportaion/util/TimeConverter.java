package publicTransportaion.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeConverter {
	
	private static final String PARSESTRING="HH:mm";
	private static final SimpleDateFormat SDF=new SimpleDateFormat(PARSESTRING);
	
	public static String format(Time time) {
		if (time==null) {
			return null;
		}
		return SDF.format(time);
	}
	public static Time parse(String TimeString){
		try {
			return (Time)SDF.parse(TimeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
