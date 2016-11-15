package publicTransportaion.model.en;

public enum Jurisdtion {
	admin,
	manage,
	nomal;
	public static String printJur(int i){
		switch (i) {
		case 1:
			return admin.name();
		case 2:
			return manage.name();
		case 3:
			return nomal.name();
		default:
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	public static int getJurValue(Jurisdtion jurisdtion){
		switch (jurisdtion) {
		case admin:
			return 1;
		case manage:
			return 2;
		case nomal:
			return 3;
		default:
			throw new ArrayIndexOutOfBoundsException();
		}
	}
}
