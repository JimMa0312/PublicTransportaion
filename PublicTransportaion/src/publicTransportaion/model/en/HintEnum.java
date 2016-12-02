package publicTransportaion.model.en;

public enum HintEnum {
	UserNameEmpty,
	ErrorStringLegth,
	PwdEmpty,
	ChPwdEmpty,
	PwdUnEqual,
	CommeBetweenUserNameAndPWD;
	
	public static String printHint(HintEnum hintEnum) {
		switch (hintEnum) {
		case UserNameEmpty:
			return "用户不能为空";
		case ErrorStringLegth:
			return "用户长度不得小于6，不得大于16";
		case PwdEmpty:
			return "密码不能为空";
		case ChPwdEmpty:
			return "密码不一致";
		case PwdUnEqual:
			return "用户或密码错误";
		case CommeBetweenUserNameAndPWD:
			return "密码不能与用户名相同";
		default:
			throw new ArrayIndexOutOfBoundsException();
		}
	}
}