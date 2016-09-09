package publicTransportaion.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import publicTransportaion.model.Station;

public class SingleLine {
	private static final String PARSE=",";
	private static List<String> line;
	//以下方法可以将把String转List<String>类型
	public static List<String> parse(String lineString){
		line=new ArrayList<String>();
		String[] strings=lineString.split(PARSE);
		line= Arrays.asList(strings);
		return line;
	}
	//以下方法可以将List<String>类型转为String类型
	public static String ltStr(List<String> list)
	{
		String str="";
		for(int i=0;i<list.size();i++)
		{
			str+=list.get(i);
			if (i!=list.size()-1) {
				str+=PARSE;
			}
		}
		return str;
	}
}
