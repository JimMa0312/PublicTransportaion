package publicTransportaion.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleLine {
	@SuppressWarnings("unused")
	private List<String> line;

	public SingleLine() {
		// TODO Auto-generated constructor stub
		line=new ArrayList<String>();
	}
	
	public SingleLine(String lienString){
		String[] strings=lienString.split("|");
		line= Arrays.asList(strings);
	}
	
	public List<String> getLine(){
		return line;
	}
}
