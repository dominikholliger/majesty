package leberkaes.gameClient;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import leberkaes.abstractClasses.View;
import leberkaes.appClasses.dummyFXMLControllerHighscore;
import leberkaes.appClasses.dummyFXMLControllerHome;
import leberkaes.commonClasses.Translator;
import leberkaes.jat2.ServiceLocator;

public class GameClient_View extends View<GameClient_Model>{
	protected Stage stage;
	protected Scene scene;
	private GameClient_Model model;


	public GameClient_View(Stage stage, GameClient_Model model) {
		super(stage, model);
		stage.setTitle("Majesty - FHNW Gruppe Leberkaes");
		ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");

	}
	private dummyFXMLControllerEnterGame _EnterGameCtrl;
	public dummyFXMLControllerEnterGame get_EnterGameCtrl() {
		if(_EnterGameCtrl == null) {
			_EnterGameCtrl = new dummyFXMLControllerEnterGame();
		}
		return _EnterGameCtrl;    	
	}
	
	private dummyFXMLControllerGameBoard _GameBoardCtrl;
	public dummyFXMLControllerGameBoard get_GameBoardCtrl() {
		if(_GameBoardCtrl == null) {
			_GameBoardCtrl = new dummyFXMLControllerGameBoard();
		}
		return _GameBoardCtrl; 
	}

	public void showEnterGame() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EnterGame.fxml"));
		loader.setController(get_EnterGameCtrl());
		try {
			parent = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Scene scene = new Scene(parent, 600,400);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		super.getStage().setScene(scene);	

	}
	
	public void showGameBoard() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
		loader.setController(get_GameBoardCtrl());
		try {
			parent = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Scene scene = new Scene(parent, 600,400);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		super.getStage().setScene(scene);	

	}


	public void start() {
		stage.show();

		// Prevent resizing below initial size
		stage.setMinWidth(stage.getWidth());
		stage.setMinHeight(stage.getHeight());
	}

	protected Parent parent;

	@Override
	protected Scene create_GUI() {
		ServiceLocator sl = ServiceLocator.getServiceLocator();  
		Logger logger = sl.getLogger();
		Translator t = sl.getTranslator();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EnterGame.fxml"));
		loader.setController(get_EnterGameCtrl());
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
