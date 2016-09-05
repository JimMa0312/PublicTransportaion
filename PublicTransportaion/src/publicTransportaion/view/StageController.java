package publicTransportaion.view;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageController {
	//建立一个专门储存Stage的Map，全部用于存放Stage对象
	private HashMap<String, Stage> stages=new HashMap<String,Stage>();
	
	/*
	 * 将加载好的Stage放到Map中进行管理
	 * 
	 * @param name 设定Stage的名字
	 * @param stage Stage的对象
	 */
	public void addStage(String name, Stage stage){
		stages.put(name, stage);
	}
	
	/*
	 * 通过Stage名获取Stage对象
	 * 
	 * @param name Stage的名称
	 * @return 对应的Stage对象
	 */
	public Stage getStage(String name){
		return stages.get(name);
	}
	
	/*
	 * 将主舞台的对象保存起来，这里只是为了以后可能需要用
	 * 
	 * @param primaryStageName 设置主舞台的名称
	 * @param primaryStage 主舞台对象，在Start（）方法中由javaFx的API建立
	 * 
	 */
	public void setPrimaryStage(String primaryStageName, Stage primaryStage) {
		this.addStage(primaryStageName, primaryStage);
	}
	
	/*
	 * 加载窗口地址，西药fxml资源文件属于独立的窗口并用Pane容器或其子类继承
	 * 
	 * @param name 注册好的fxml窗口的文件
	 * @param resources fxml资源地址
	 * @param styles 可变参数，init使用的初始化样式资源设置
	 * @return 是否加载成功
	 */
	public boolean loadStage(String name, String resoutces, StageStyle... style) {
		FXMLLoader loader=new FXMLLoader(getClass().getResource(resoutces));
		
		try {
			Pane tempPane=(Pane)loader.load();
			ControlledStage controlledStage=(ControlledStage)loader.getController();
			controlledStage.setStageController(this);
			
			Scene tempScene=new Scene(tempPane);
			Stage tempStage=new Stage();
			tempStage.setScene(tempScene);
			
			for (StageStyle stageStyle : style) {
				tempStage.initStyle(stageStyle);
			}
			
			this.addStage(name, tempStage);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean setStage(String name){
		this.getStage(name).show();
		return true;
	}
	
	public boolean setStage(String show, String close) {
		getStage(close).close();
		setStage(show);
		
		return true;
	}
	
	public boolean unloadStage(String name){
		if (stages.remove(name)==null) {
			System.out.println("窗口不存在，请检查名称");
			return false;
		}else{
			System.out.println("窗口移除成功");
			return true;
		}
	}
}
