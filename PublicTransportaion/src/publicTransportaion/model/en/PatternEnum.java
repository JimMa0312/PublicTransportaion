package publicTransportaion.model.en;

public enum PatternEnum {
	vehicleNoStyle("^[\u4e00-\u9fa5]{1}[A-Z0-9]{6}$",0),
	ChinsesTelStyle("^((13[0-9])|(15[^4,\\D])|(18[0,2,5-9]))\\d{8}$",1);
	
	private String name;
	private int index;
	
	private PatternEnum(String name, int index){
		this.name=name;
		this.index=index;
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
