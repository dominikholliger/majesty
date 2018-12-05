package leberkaes.gameClient;

import java.io.File;
import java.util.Random;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import leberkaes.abstractClasses.Controller;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;
import leberkaes.jat2.ServiceLocator;

public class GameClient_Controller extends Controller<GameClient_Model, GameClient_View> {

	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();

	// Top controls (Connection)
	@FXML
	public TextField txtName;
	@FXML
	public TextField txtChatPort;
	@FXML
	public TextField txtGamePort;
	@FXML
	public TextField txtIpAddress;
	@FXML
	public Button btnConnect;

	// Center Controls (GameBoard)
	// Character Deck

	@FXML
	public ImageView openDeck5;
	@FXML
	public ImageView openDeck4;
	@FXML
	public ImageView openDeck3;
	@FXML
	public ImageView openDeck2;
	@FXML
	public ImageView openDeck1;
	@FXML
	public ImageView openDeck0;
	@FXML
	public ImageView deckBack;


	//Locations
	@FXML
	public ImageView loc1;
	@FXML
	public ImageView loc2;
	@FXML
	public ImageView loc3;
	@FXML
	public ImageView loc4;
	@FXML
	public ImageView loc5;
	@FXML
	public ImageView loc6;
	@FXML
	public ImageView loc7;
	@FXML
	public ImageView loc8;




	// Bottom Controls (Chat)
	@FXML
	public TextArea txtChatMessage;
	@FXML
	public Button btnSend;

	// Right controls (Chatlog)
	@FXML
	public TextArea txtChatArea;



	public GameClient_Controller(GameClient_Model model, GameClient_View view) {
		super(model, view);
		view.getStage().setOnCloseRequest(event -> model.disconnect());
		
	/*	model.newestMessage.addListener((o, oldValue, newValue) -> {
			if (!newValue.isEmpty()) // Ignore empty messages
				System.out.println("Event");
				//this.txtChatArea.appendText(newValue + "\n");
		});*/
	}

	@FXML
	public void initialize() {
		
		//debug initialization
		this.txtChatPort.setText(config.getOption("ChatPort"));
		this.txtGamePort.setText(config.getOption("GamePort"));
		
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
	    this.txtName.setText(saltStr);
	    
	    this.txtIpAddress.setText("127.0.0.1");
		
	    this.model.createDummyBoard(Integer.parseInt(this.config.getOption("PlayerCount")));
		
		
 		}
	
	@FXML
	protected void handleSendClicked(ActionEvent event) throws Exception {

		model.sendMessage(this.txtChatMessage.getText());

	}

	@FXML
	protected void handleConnectClicked(ActionEvent event) throws Exception {
		// Daten aus GUI auslesen
		String name = this.txtName.getText();
		int chatPort = Integer.parseInt(this.txtChatPort.getText());
		int gamePort = Integer.parseInt(this.txtGamePort.getText());
		String ipAddress = this.txtIpAddress.getText();

		// Connect for Chat Com
		model.connect(ipAddress, chatPort, name);
		// Connect for Object Com
		model.connectObjectCom(ipAddress, gamePort, name);
	}

}
