package publicTransportaion.model.en;

public enum HintEnum {
	UserNameEmpty("用户不能为空", 1), ErrorStringLegth("用户长度不得小于6，不得大于16", 2), PwdEmpty("密码不能为空", 3), ChPwdEmpty("密码不一致",
			4), PwdUnEqual("用户或密码错误", 5), CommeBetweenUserNameAndPWD("密码不能与用户名相同", 6), EmptyFirstName("姓氏不能为空",
					7), EmptySecondName("名不能为空", 8), EmptyTel("电话不能为空", 9), UnCatchTel("请输入正确电话", 10),ErrorPWD("密码错误请重试或退出!",11);

	private String name;
	private int index;

	private HintEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}