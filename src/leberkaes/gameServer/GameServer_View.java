package leberkaes.gameServer;

import java.util.logging.Logger;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;
import leberkaes.jat2.ServiceLocator;
/** @author Dominik Holliger */
public class GameServer_View {
	protected Stage stage;
	private GameServer_Model model;
	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();
	
	// Top controls
	Label lblPort = new Label("Port: "+config.getOption("Port"));
	TextField txtPort = new TextField();
	Region topSpacer = new Region();
	
	// Client area
	TextArea txtClientArea = new TextArea();
	
	public GameServer_View(Stage stage, GameServer_Model model) {
		this.stage = stage;
		this.model = model;
		
		HBox topBox = new HBox(topSpacer);
		topBox.getStyleClass().add("hbox"); // Class for styling
		HBox.setHgrow(topSpacer,  Priority.ALWAYS);
		
		BorderPane root = new BorderPane();
		root.getStyleClass().add("root"); // Class for styling
		
		root.setTop(topBox);
		root.setCenter(txtClientArea);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setTitle("Majesty Game Server");
	}
	
	public void start() {
		stage.show();
		
		// Prevent resizing below initial size
		stage.setMinWidth(stage.getWidth());
		stage.setMinHeight(stage.getHeight());
	}
	
	protected void updateClients() {
		StringBuffer sb = new StringBuffer();
		for (Game_Client c : model.clients) {
			sb.append(c.toString());
			sb.append("\n");
		}
		txtClientArea.setText(sb.toString());
	}
}