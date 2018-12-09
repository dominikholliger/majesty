package leberkaes.gameClient;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

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
import leberkaes.appClasses.dummyFXMLControllerHome;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;
import leberkaes.jat2.ServiceLocator;
import leberkaes.settingsWindows.GameSettings_Controller;

public class GameClient_View extends View <GameClient_Model>{
	
	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();
	
	
	/**
     * Per Lazy Loading die ein DummyKontroller Objekt erstellen und per Getter zur verfügung Stellen
     * wird für den FXML Loader gebraucht.
     */
    private dummyFXMLControllerClient _Ctrl;
	public dummyFXMLControllerClient get_Ctrl() {
    		if(_Ctrl == null) {
    			_Ctrl = new dummyFXMLControllerClient();
    		}
    		return _Ctrl;    	
    }
    
    protected Parent parent;
	private Stage stage;
	
	//private GameClient_Model model;
		
	public GameClient_View(Stage stage, GameClient_Model model) {
		super (stage, model);
        stage.setTitle(t.getString("program.menu.game"));
     
	}
	
	protected Scene create_GUI() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
		loader.setController(get_Ctrl());
		try {
			parent = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Scene scene = new Scene(parent, 1100, 800);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		

        return scene;
	}
	

}
