package leberkaes.gui.StartNewGame;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

public class StartNewGameView {

	protected Stage stage;
	protected Scene scene;
	protected Parent parent;
	
	public void start(Stage stage) throws Exception {
		parent = FXMLLoader.load(getClass().getResource("StartNewGame.fxml"));
		
		scene = new Scene(parent, 300, 275);
		this.stage = stage;
		this.stage.setScene(scene);
		this.stage.show();
	}
	
	public void stop() {
        stage.hide();
    }
}