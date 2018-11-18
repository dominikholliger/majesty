package leberkaes.gui.ShowSettings;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShowSettingsView {
	
	protected Stage stage;
	protected Scene scene;
	protected Parent parent;
	
	public void start(Stage stage) throws Exception {
		parent = FXMLLoader.load(getClass().getResource("settings.fxml"));
		
		scene = new Scene(parent, 300, 275);
		this.stage = stage;
		this.stage.setScene(scene);
		this.stage.show();
	}
	
	public void stop() {
        stage.hide();
    }
}
