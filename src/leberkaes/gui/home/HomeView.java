package leberkaes.gui.home;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

public class HomeView {
	protected Stage stage;
	protected Scene scene;
	protected Parent parent;
	
	public void start(Stage stage) throws Exception {
		parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
		
		scene = new Scene(parent, 800, 600);
		this.stage = stage;
		this.stage.setScene(scene);
		this.stage.show();
	}
	
	public void stop() {
        stage.hide();
    }
	
	public Stage getStage() {
		return stage;
	}
}
