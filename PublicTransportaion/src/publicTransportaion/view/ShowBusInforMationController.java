package publicTransportaion.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import publicTransportaion.MainApp;

public class ShowBusInforMationController {
	@FXML
	private SplitPane left_page;
	@FXML
	private SplitPane right_page;
	@FXML
	private Label bus_no;
	@FXML
	private Label position_information;
	@FXML
	private Label last_station;
	@FXML
	private Label next_station;
	@FXML
	private Label how_many_station;
	//
	@FXML
	private TextField neirong;

	@SuppressWarnings("unused")
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void initialize() {
		ShowBusInfor();
	}

	private void ShowBusInfor() {
		bus_no.setText(null);
		position_information.setText(null);
		last_station.setText(null);
		next_station.setText(null);
		how_many_station.setText(null);
	}
}
