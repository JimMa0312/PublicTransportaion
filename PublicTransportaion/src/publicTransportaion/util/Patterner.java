package publicTransportaion.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import publicTransportaion.model.en.PatternEnum;

public class Patterner {
	public static boolean StringMatch(PatternEnum patternEnum,String string){
		Pattern pattern=Pattern.compile(PatternEnum.getPatternComplie(patternEnum));
		Matcher matcher=pattern.matcher(string);
		return matcher.matches();
	}
	
}