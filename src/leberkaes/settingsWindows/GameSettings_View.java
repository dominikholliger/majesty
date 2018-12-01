package leberkaes.settingsWindows;

import java.util.logging.Logger;

import leberkaes.jat2.ServiceLocator;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class GameSettings_View {
	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();
	
    private GameSettings_Model model;
    private Stage stage;

    protected Label lblPort = new Label(t.getString("options.port"));
    protected TextField txtPort = new TextField();
    protected Label lblPlayer = new Label(t.getString("options.playercount"));
    protected TextField txtPlayer = new TextField();
    protected Button btnCancel = new Button(t.getString("options.cancel"));
    protected Button btnSave = new Button(t.getString("options.save"));

    public GameSettings_View(Stage stage, GameSettings_Model model) {
        this.stage = stage;
        this.model = model;
        stage.setTitle(t.getString("options.title"));
        HBox root = new HBox();
        Region spacer1 = new Region();
        Region spacer2 = new Region();
        root.getChildren().addAll(lblPort, txtPort, spacer2,lblPlayer, txtPlayer, spacer1, btnCancel, btnSave);
        // Get current values
        txtPort.setText(config.getOption("Port"));
        txtPlayer.setText(config.getOption("PlayerCount"));
        Scene scene = new Scene(root);
        stage.setScene(scene);;
    }
    
    public void start() {
        stage.show();
    }
    
    /**
     * Stopping the view - just make it invisible
     */
    public void stop() {
        stage.hide();
    }
    
    /**
     * Getter for the stage, so that the controller can access window events
     */
    public Stage getStage() {
        return stage;
    }
}
