package publicTransportaion.view;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import publicTransportaion.MainApp;
import publicTransportaion.model.Bus;
import publicTransportaion.model.Cars;

public class CreateBusPlanController {
	@FXML
	private Label carPosIdLabel;
	@FXML
	private Label busIdLabel;
	@FXML
	private ChoiceBox<String> typeOfLineChoiceBox;
	@FXML
	private DatePicker WorkeDatePicker;
	@FXML
	private TextField hourTextField;
	@FXML
	private TextField minuteTextField;
	@FXML
	private TableView<Bus> busTableView;
	@FXML
	private TableColumn<Bus, String> BusTableBusIdColumn;
	@FXML
	private TableView<Cars> CarsTabelView;
	@FXML
	private TableColumn<Cars, String> CarsTableIdColumn;
	@FXML
	private TableColumn<Cars, Integer> carsTableChairColun;
	
	@SuppressWarnings("unused")
	private MainApp mainApp;
	
	@FXML
	private void initialize(){
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp=mainApp;
	}

}
