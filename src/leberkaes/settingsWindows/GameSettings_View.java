package leberkaes.settingsWindows;

import java.io.IOException;
import java.util.logging.Logger;

import leberkaes.jat2.ServiceLocator;
import leberkaes.abstractClasses.View;
import leberkaes.appClasses.App_Model;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class GameSettings_View extends View<GameSettings_Model>{
	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();
	
    private GameSettings_Model model;
    private Stage stage;
    protected Parent parent;

    protected Label lblPort = new Label(t.getString("options.port"));
    protected TextField txtPort = new TextField();
    protected Label lblPlayer = new Label(t.getString("options.playercount"));
    protected TextField txtPlayer = new TextField();
    protected Button btnCancel = new Button(t.getString("options.cancel"));
    protected Button btnSave = new Button(t.getString("options.save"));

    public GameSettings_View(Stage stage, GameSettings_Model model) {
        super (stage, model);
        stage.setTitle(t.getString("options.title"));
        // Get current values
        txtPort.setText(config.getOption("Port"));
        txtPlayer.setText(config.getOption("PlayerCount"));
        
    }
    
 
    
    protected Scene create_GUI() {
	    ServiceLocator sl = ServiceLocator.getServiceLocator();  
	    Logger logger = sl.getLogger();
	    Translator t = sl.getTranslator();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
		loader.setController(new GameSettings_Controller(model, this));
		try {
			parent = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Scene scene = new Scene(parent, 600,400);
        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        return scene;
	}
    
 
}
