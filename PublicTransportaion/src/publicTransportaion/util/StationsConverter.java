package publicTransportaion.util;

import javafx.collections.ObservableList;
import publicTransportaion.model.Station;

public class StationsConverter {
	private static final String PANE = " ";

	public static String[] parseStringList(String stationText) {
		String[] tempString = stationText.split(PANE);
		return tempString;
	}

	public static String parseString(ObservableList<Station> stations) {
		String stationsString = "";
		for (int i = 0; i < stations.size(); i++) {
			stationsString += stations.get(i).getStationID();
			if (i != stations.size() - 1) {
				stationsString += PANE;
			}
		}

		return stationsString;
	}
}
