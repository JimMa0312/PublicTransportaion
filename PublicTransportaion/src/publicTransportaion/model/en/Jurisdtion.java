package publicTransportaion.model.en;

import com.sun.org.apache.xpath.internal.operations.Equals;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum Jurisdtion {
	admin("超级管理员", 1), manage("管理员", 2), nomal("客户", 3);

	private String name;
	private int index;

	private Jurisdtion(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static Jurisdtion getJur(int i) {
		switch (i) {
		case 1:
			return admin;
		case 2:
			return manage;
		case 3:
			return nomal;
		default:
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	public static int getJurValue(Jurisdtion jurisdtion) {
		switch (jurisdtion) {
		case admin:
			return 1;
		case manage:
			return 2;
		case nomal:
			return 3;
		default:
			throw new ArrayIndexOutOfBoundsException();
		}
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

	public static ObservableList<String> getJurList() {
		ObservableList<String> jurisdtion = FXCollections.observableArrayList();
		jurisdtion.add("超级管理员");
		jurisdtion.add("管理员");
		jurisdtion.add("客户");

		return jurisdtion;
	}
}
