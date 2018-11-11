package leberkaes.gui.GameBoard;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameBoardView {
	protected Stage stage;
	protected Scene scene;
	protected Parent parent;
	
	public void start(Stage stage) throws Exception {
		parent = FXMLLoader.load(getClass().getResource("GameBoard.fxml"));
		
		scene = new Scene(parent, 300, 275);
		this.stage = stage;
		this.stage.setScene(scene);
		this.stage.show();
	}
	
	public void stop() {
        stage.hide();
    }
}
