package publicTransportaion.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import publicTransportaion.model.Bus;
import publicTransportaion.model.Cars;

public class TransationManageBusDyanmic implements ControlledStage, Initializable {
	@SuppressWarnings("unused")
	private StageController myController;
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStageController(StageController stageController) {
		this.myController=stageController;
	}

}
