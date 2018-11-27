package leberkaes.gameServer;

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

public class GameServer_View extends View<GameServer_Model>{
	protected Stage stage;
	private GameServer_Model model;
	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();
	
	/*// Top controls
	Label lblPort = new Label("Port: "+config.getOption("Port"));
	TextField txtPort = new TextField();
	Region topSpacer = new Region();
	
	// Client area
	TextArea txtClientArea = new TextArea();*/
	
	public GameServer_View(Stage stage, GameServer_Model model) {
		super(stage, model);
		stage.setTitle("Majesty - FHNW Gruppe Leberkaes");
		ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");

	}
	
	private dummyFXMLControllerNewGame _NewGameCtrl;
	public dummyFXMLControllerNewGame get_NewGameCtrl() {
		if(_NewGameCtrl == null) {
			_NewGameCtrl = new dummyFXMLControllerNewGame();
		}
		return _NewGameCtrl;    	
	}
	
	protected Parent parent;
	
	public void showNewGame() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("NewGame.fxml"));
		loader.setController(get_NewGameCtrl());
		
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
	
	protected void updateClients() {
		StringBuffer sb = new StringBuffer();
		for (Game_Client c : model.clients) {
			sb.append(c.toString());
			sb.append("\n");
		}
		// txtClientArea.setText(sb.toString());
	}
	
	protected Scene create_GUI() {
		ServiceLocator sl = ServiceLocator.getServiceLocator();  
		Logger logger = sl.getLogger();
		Translator t = sl.getTranslator();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("NewGame.fxml"));
		loader.setController(get_NewGameCtrl());
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