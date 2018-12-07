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
	private boolean gamePortValid = false;
	private boolean chatPortValid = false;
	
	
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
        
        
        // Listeners
		model.newestMessage.addListener((o, oldValue, newValue) -> { 
			if(!newValue.isEmpty()){
				view.get_Ctrl().chatTextArea.appendText(newValue + "\n"); 
			}
		});
		model.moveCounter.addListener((o, oldValue, newValue) -> { 
			if(!newValue.isEmpty()){
				view.get_Ctrl().setGameBoard(model.getActGameBoard()); 
			}
		});
		view.get_Ctrl().txtGamePort.textProperty().addListener((observable, oldValue, newValue) -> {
			validateGamePortNumber(newValue, "txtGamePort");
		});
		view.get_Ctrl().txtChatPort.textProperty().addListener((observable, oldValue, newValue) -> {
			validateChatPortNumber(newValue, "txtChatPort");
		});
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


	public void validateGamePortNumber(String newValue, String obsElement) {
		// TODO Auto-generated method stub
		boolean valid = model.isValidPortNumber(newValue);
		// Change text color
		if (valid) {
			view.get_Ctrl().txtGamePort.setStyle("-fx-text-inner-color: green;");
		} else {
			view.get_Ctrl().txtGamePort.setStyle("-fx-text-inner-color: red;");
		}
		// Save result
		gamePortValid = valid;
		// Enable or disable button, as appropriate
		enableDisableButton();
		
	}
	public void validateChatPortNumber(String newValue, String obsElement) {
		// TODO Auto-generated method stub
		boolean valid = model.isValidPortNumber(newValue);
		// Change text color
		if (valid) {
			view.get_Ctrl().txtChatPort.setStyle("-fx-text-inner-color: green;");
		} else {
			view.get_Ctrl().txtChatPort.setStyle("-fx-text-inner-color: red;");
		}
		// Save result
		chatPortValid = valid;
		// Enable or disable button, as appropriate
		enableDisableButton();
		
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

	public void sendMessage(String messageText) {
		model.sendMessage(messageText);
	}
	
	/**
	 * Enable or disable the Connect button, based on the validity of the two text
	 * controls
	 */
	private void enableDisableButton() {
		boolean valid = gamePortValid;
		boolean valid2 = chatPortValid;
		view.get_Ctrl().btnConnect.setDisable(!(valid || valid2));
	}

}
