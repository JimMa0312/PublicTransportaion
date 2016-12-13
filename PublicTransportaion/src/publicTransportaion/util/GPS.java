package publicTransportaion.util;

public class GPS {
	private static final String PANE = " ";

	public static double[] parseFloatList(String GPSString) {
		String[] tempValue = GPSString.split(PANE);
		double[] TrueVale = new double[tempValue.length];

		for (int i = 0; i < tempValue.length; i++) {
			TrueVale[i] = Double.parseDouble(tempValue[i]);
		}

		return TrueVale;
	}

	public static String parseString(double[] GPSVale) {
		String outString = "";
		for (int i = 0; i < GPSVale.length; i++) {
			outString += Double.toString(GPSVale[i]);
			if (i != GPSVale.length - 1) {
				outString += PANE;
			}
		}
		return outString;
	}
}