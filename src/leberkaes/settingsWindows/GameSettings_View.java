package leberkaes.settingsWindows;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import leberkaes.abstractClasses.View;
import leberkaes.appClasses.App_Controller;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;
import leberkaes.jat2.ServiceLocator;
/** @author Sebrina Pedrossi*/
public class GameSettings_View extends View<GameSettings_Model>{
	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();
    protected Parent parent;

   private App_Controller appControlInstance;

    public App_Controller getAppControlInstance() {
	return appControlInstance;
}

	public void setAppControlInstance(App_Controller appControlInstance) {
	this.appControlInstance = appControlInstance;
}
	public GameSettings_View(Stage stage, GameSettings_Model model) {
        super (stage, model);
        stage.setTitle(t.getString("settings.windowTitle"));
        // Get current values
    }

    protected Scene create_GUI() {

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
