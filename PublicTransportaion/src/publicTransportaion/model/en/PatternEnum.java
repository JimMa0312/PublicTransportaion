package publicTransportaion.model.en;

public enum PatternEnum {
	vehicleNoStyle;
	
	public static String getPatternComplie(PatternEnum patternEnum) {
		switch (patternEnum) {
		case vehicleNoStyle:
			return 	"^[\u4e00-\u9fa5]{1}[A-Z0-9]{6}$";

		default:
			throw new ArrayIndexOutOfBoundsException();
		}
	}
}
