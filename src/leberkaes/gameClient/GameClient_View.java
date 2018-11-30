package leberkaes.gameClient;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import leberkaes.abstractClasses.View;
import leberkaes.commonClasses.Translator;
import leberkaes.jat2.ServiceLocator;

public class GameClient_View extends View<GameClient_Model> {
	protected Parent parent;

	
	
	public GameClient_View(Stage stage, GameClient_Model model) {
		super(stage, model);
		stage.setTitle("Majesty - FHNW Gruppe Leberkaes");
		ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");

	}

	
	protected Scene create_GUI() {
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Logger logger = sl.getLogger();
		Translator t = sl.getTranslator();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EnterGame.fxml"));
		loader.setController(new GameClient_Controller(model, this));
		try {
			parent = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Scene scene = new Scene(parent, 600, 400);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		return scene;
	}

	public void showEnterGame() {

	}

	public void showGameBoard() {
	}

	
}
