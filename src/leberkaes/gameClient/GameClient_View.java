package leberkaes.gameClient;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import leberkaes.abstractClasses.View;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;
import leberkaes.jat2.ServiceLocator;

/** author: Sebrina Pedrossi*/

public class GameClient_View extends View <GameClient_Model>{

	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();


	/**
	 * Per Lazy Loading die ein DummyKontroller Objekt erstellen und per Getter zur Verfügung stellen
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

	public GameClient_View(Stage stage, GameClient_Model model) {
		super (stage, model);
		stage.setTitle(t.getString("client.windowTitle"));
	}

	protected Scene create_GUI() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
		loader.setController(get_Ctrl());
		try {
			parent = loader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Scene scene = new Scene(parent, 1100, 800);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		return scene;
	}


}
