package publicTransportaion.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Line {
	private final StringProperty line;
	
	public Line()  {
		this(null);
	}

	public Line(String line) {
		super();
		this.line = new SimpleStringProperty(line);
	}

	
	
	public void setLine(String line) {
		this.line.set(line);
	}
	
	
	public String getLine() {
		return line.get();
	}

}
