package publicTransportaion.model.en;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum GenderEnum {
	sir("男", 1), lady("女", 2);

	private String name;
	private int index;

	private GenderEnum(String name, int index) {
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

	public static GenderEnum valueOf(int index) {
		switch (index) {
		case 1:
			return GenderEnum.sir;
		case 2:
			return GenderEnum.lady;
		default:
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	public static ObservableList<String> getGenderList() {
		ObservableList<String> genderList = FXCollections.observableArrayList();
		genderList.add(sir.getName());
		genderList.add(lady.getName());

		return genderList;
	}
}
