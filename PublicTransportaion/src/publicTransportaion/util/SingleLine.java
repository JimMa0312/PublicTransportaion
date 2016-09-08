package publicTransportaion.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleLine {
	private static List<String> line;
	
	
	public static List<String> parse(String lienString){
		line=new ArrayList<String>();
		String[] strings=lienString.split("|");
		line= Arrays.asList(strings);
		return line;
	}
}
