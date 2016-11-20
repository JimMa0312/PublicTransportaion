package publicTransportaion.model;

import javafx.beans.property.StringProperty;

public class User {
	
	private StringProperty UserId;
	private StringProperty FirstName;
	private StringProperty SecondName;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public StringProperty getUserIdProperty() {
		return UserId;
	}

	public void setUserIdProperty(StringProperty userId) {
		UserId = userId;
	}

	public StringProperty getFirstNameProperty() {
		return FirstName;
	}

	public void setFirstNameProperty(StringProperty firstName) {
		FirstName = firstName;
	}

	public StringProperty getSecondNameProperty() {
		return SecondName;
	}

	public void setSecondNameProperty(StringProperty secondName) {
		SecondName = secondName;
	}
	
	public String getUserId() {
		return UserId.get();
	}
	
	public void setUserId(String userId) {
		this.UserId.set(userId);
	}
	
	public String getFirstName() {
		return FirstName.get();
	}
	
	public void setFirstName(String firstName) {
		this.FirstName.set(firstName);
	}
	
	
}
