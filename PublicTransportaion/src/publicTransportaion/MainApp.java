package publicTransportaion;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import publicTransportaion.sql.SqlDeloy;
import publicTransportaion.view.OutLayerControl;
import publicTransportaion.view.ShowBusInforMationController;

public class MainApp extends Application {
	
	private Stage PrimaryStage;
	private BorderPane rootLayout;
	

	@Override
	public void start(Stage primaryStage) {
		this.PrimaryStage=primaryStage;
		this.PrimaryStage.setTitle("公交车查询系统");
		
		initRootLayout();
		ShowBusInformationOverView();
	}
	
	private void initRootLayout() {
		try {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/OutLayer.fxml"));
			rootLayout=(BorderPane) loader.load();
			
			OutLayerControl control=new OutLayerControl();
			control.setMainApp(this);
			
			Scene scene=new Scene(rootLayout);
			PrimaryStage.setScene(scene);
			PrimaryStage.show();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void ShowBusInformationOverView(){
		try {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ShowBusInforMation.fxml"));
			AnchorPane busInformationDialog=(AnchorPane) loader.load();
			
			ShowBusInforMationController controller=new ShowBusInforMationController();
			controller.setMainApp(this);
			
			rootLayout.setCenter(busInformationDialog);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		SqlDeloy testsqlDeloy=new SqlDeloy();
		testsqlDeloy.shotDownCon();
		
		launch(args);
	}
}
