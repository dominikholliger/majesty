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

	// Top controls
	Label lblIpAddress = new Label("IP Address");
	TextField txtIpAddress = new TextField();
	Label lblPort = new Label("Port");
	TextField txtPort = new TextField();
	Label lblName = new Label("Name");
	TextField txtName = new TextField();
	Button btnConnect = new Button("Connect");
	
	// Chat area
	TextArea txtChatArea = new TextArea();
		
	// Bottom controls
	TextField txtChatMessage = new TextField();
	Button btnSend = new Button("Send");
	
	public GameClient_View(Stage stage, GameClient_Model model) {
		this.stage = stage;
		this.model = model;
		
		// Prevent labels and button from shrinking below their preferred size
		lblIpAddress.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		lblPort.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		lblName.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		btnConnect.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		btnSend.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		
		// Set sizes for top TextFields
		txtIpAddress.setMinWidth(150); txtIpAddress.setPrefWidth(150);
		txtPort.setMinWidth(60); txtPort.setPrefWidth(60);
		txtName.setMinWidth(150); txtName.setPrefWidth(150);
		
		HBox topBox = new HBox(lblIpAddress, txtIpAddress, lblPort, txtPort, lblName, txtName, btnConnect);
		topBox.getStyleClass().add("hbox"); // Class for styling
		
		HBox bottomBox = new HBox(txtChatMessage, btnSend);
		bottomBox.getStyleClass().add("hbox"); // Class for styling
		HBox.setHgrow(txtChatMessage, Priority.ALWAYS);	
		
		BorderPane root = new BorderPane();
		root.getStyleClass().add("root"); // Class for styling
		
		root.setTop(topBox);
		root.setBottom(bottomBox);
		root.setCenter(txtChatArea);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setTitle("SimpleChat Client");
	}
	
	public void start() {
		stage.show();
		
		// Prevent resizing below initial size
		stage.setMinWidth(stage.getWidth());
		stage.setMinHeight(stage.getHeight());
	}
}
