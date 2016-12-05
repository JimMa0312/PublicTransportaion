package publicTransportaion.model.en;

public enum eSqlCommand {
	
	SelectStation("select * from Station_information order by Station_ID",1);
	
	private String name;
	private int index;
	private eSqlCommand(String name,int index){
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
