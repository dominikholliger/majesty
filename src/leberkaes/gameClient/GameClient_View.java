package leberkaes.gameClient;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class GameClient_View {
	protected Stage stage;
	private GameClient_Model model;


	
	public GameClient_View(Stage stage, GameClient_Model model) {
		this.stage = stage;
		this.model = model;
			
		
		//Scene scene = new Scene(root);
		//stage.setScene(scene);
        //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setTitle("SimpleChat Client");
	}
	
	public void start() {
		stage.show();
		
		// Prevent resizing below initial size
		stage.setMinWidth(stage.getWidth());
		stage.setMinHeight(stage.getHeight());
	}
}
