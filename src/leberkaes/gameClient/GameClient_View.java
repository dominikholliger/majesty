package leberkaes.gameClient;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import leberkaes.abstractClasses.View;
import leberkaes.commonClasses.Translator;
import leberkaes.jat2.ServiceLocator;
import leberkaes.settingsWindows.GameSettings_Controller;

public class GameClient_View extends View <GameClient_Model>{
	Stage stage;
	private GameClient_Model model;
	private Parent parent;

	/*// Top controls
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
	Button btnSend = new Button("Send");*/
	
	public GameClient_View(Stage stage, GameClient_Model model) {
		super(stage, model);
		
		
		/*// Prevent labels and button from shrinking below their preferred size
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
*/		/**
		 * Für ein bisschen schnelleres debugging...
		 */
		/*Boolean debug = true;
		if(debug) {
			txtPort.setText("8082");
			txtIpAddress.setText("127.0.0.1");
			String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        while (salt.length() < 8) { // length of the random string.
	            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
		    txtName.setText(saltStr);
		}
		
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
		stage.setTitle("Game Client");*/
	}
	
	protected Scene create_GUI() {
	    ServiceLocator sl = ServiceLocator.getServiceLocator();  
	    Logger logger = sl.getLogger();
	    Translator t = sl.getTranslator();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
		loader.setController(new GameClient_Controller(model, this));
		try {
			parent = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Scene scene = new Scene(parent, 800, 1000);
		stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        return scene;
	}
	
	public void start() {
		stage.show();
		
		// Prevent resizing below initial size
		stage.setMinWidth(stage.getWidth());
		stage.setMinHeight(stage.getHeight());
	}
}
