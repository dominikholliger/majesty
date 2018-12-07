package leberkaes.gameClient;

import java.io.File;
import java.util.Random;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;
import leberkaes.abstractClasses.Controller;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;
import leberkaes.gameServer.GameServer_View;
import leberkaes.jat2.ServiceLocator;

public class GameClient_Controller extends Controller<GameClient_Model, GameClient_View> {
	 ServiceLocator serviceLocator;
	
	
	
	//Logger logger = serviceLocator.getLogger();
	//Translator t = serviceLocator.getTranslator();
	//Configuration config = serviceLocator.getConfiguration();

   private GameClient_View _GameClientViewInstance;
   public GameClient_Controller(GameClient_Model model, GameClient_View view) {
		super(model, view);
		view.getStage().setOnCloseRequest(event -> model.disconnect());
		// D.Holliger:
        // Vorbereiten für die Kommunikation Controller vs DummyController FXML
        view.get_Ctrl().set_MvcCtrl(this);
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            	System.out.println();
            	Platform.exit();
            }
        });
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application controller initialized");
		/*
		 * model.newestMessage.addListener((o, oldValue, newValue) -> { if
		 * (!newValue.isEmpty()) // Ignore empty messages System.out.println("Event");
		 * //this.txtChatArea.appendText(newValue + "\n"); });
		 */
	}

	@FXML
	public void initialize() {

//		// debug initialization
//		this.txtChatPort.setText(config.getOption("ChatPort"));
//		this.txtGamePort.setText(config.getOption("GamePort"));
//
//		model.newestMessage.addListener((observable, oldValue, newValue) -> {
//			this.txtChatArea.appendText(newValue + "\n");
//		});
//		
//		
//		String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//		StringBuilder salt = new StringBuilder();
//		Random rnd = new Random();
//		while (salt.length() < 8) { // length of the random string.
//			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
//			salt.append(SALTCHARS.charAt(index));
//		}
//		String saltStr = salt.toString();
//		this.txtName.setText(saltStr);
//		this.txtIpAddress.setText("127.0.0.1");
//		this.model.createDummyBoard(Integer.parseInt(this.config.getOption("PlayerCount")));

	}


	public void validateGamePortNumber() {
//		// TODO Auto-generated method stub
//		boolean valid = model.isValidPortNumber(newValue);
//		// Change text color
//		if (valid) {
//			this.txtGamePort.setStyle("-fx-text-inner-color: green;");
//		} else {
//			this.txtGamePort.setStyle("-fx-text-inner-color: red;");
//		}
//		// Save result
//		portValid = valid;
//		// Enable or disable button, as appropriate
//		enableDisableButton();
		
	}

	public void connectToServer() {
		// Daten aus GUI auslesen
		String name = view.get_Ctrl().txtName.getText();
		int chatPort = Integer.parseInt(view.get_Ctrl().txtChatPort.getText());
		int gamePort = Integer.parseInt(view.get_Ctrl().txtGamePort.getText());
		String ipAddress = 	view.get_Ctrl().txtIpAddress.getText();
		// Connect for Chat Com
		model.connect(ipAddress, chatPort, name);
		// Connect for Object Com
		model.connectObjectCom(ipAddress, gamePort, name);
	}

}
