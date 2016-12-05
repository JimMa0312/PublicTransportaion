package publicTransportaion.util;


public class TImeUtil {
	private static final String PANE=":";
	
	public static String[] parseStringList(String stationText) {
		String[] tempString=stationText.split(PANE);
		return tempString;
	}
	
	public static String parseString(String[] stations) {
		String TimeString="";
		for(int i=0;i<stations.length;i++){
			TimeString+=stations[i];
			if (i!=stations.length-1) {
				TimeString+=PANE;
			}
		}
		
		return TimeString;
	}
}
