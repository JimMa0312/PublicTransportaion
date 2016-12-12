package publicTransportaion.model.en;

public enum eLineLayer {
	
	up(true, 1),
	down(false, 2);
	
	private boolean bool;
	private int index;
	
	private eLineLayer(boolean bool,int index){
		this.bool=bool;
		this.index=index;
	}

	public boolean getBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public static eLineLayer getBoLineLayer(int index) {
		switch (index) {
		case 1:
			return up;
		case 2:
			return down;
		default:
			throw new ArrayIndexOutOfBoundsException();
		}
	}
}
