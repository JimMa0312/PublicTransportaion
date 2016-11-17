package publicTransportaion.model.en;

public enum GenderEnum {
	sir,
	lady;
	
	public static String printGender(GenderEnum genderEnum) {
		switch (genderEnum) {
		case sir:
			return "男";
		case lady:
			return "女";
		default:
			throw new ArrayIndexOutOfBoundsException();
		}
	}
}
