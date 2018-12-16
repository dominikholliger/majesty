package leberkaes.gameClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.WindowEvent;
import leberkaes.abstractClasses.Controller;
import leberkaes.commonClasses.CharacterCard;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;
import leberkaes.jat2.ServiceLocator;

/** author: Dominik Holliger */

public class GameClient_Controller extends Controller<GameClient_Model, GameClient_View> {
	ServiceLocator serviceLocator;
	Configuration config;
	Logger logger;
	Translator t;

	private boolean gamePortValid = false;
	private boolean chatPortValid = false;

	private GameClient_View _GameClientViewInstance;

	public GameClient_Controller(GameClient_Model model, GameClient_View view) {
		super(model, view);

		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application controller initialized");
		config = serviceLocator.getConfiguration();
		logger = serviceLocator.getLogger();
		t = serviceLocator.getTranslator();

		view.getStage().setOnCloseRequest(event -> model.disconnect());
		// D.Holliger:
		// Vorbereiten f√ºr die Kommunikation Controller vs DummyController FXML
		view.get_Ctrl().set_MvcCtrl(this);

		// register ourselves to handle window-closing event
		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.out.println();
				Platform.exit();
			}
		});

		// Listeners
		model.newestMessage.addListener((o, oldValue, newValue) -> {
			if (!newValue.isEmpty()) {
				view.get_Ctrl().chatTextArea.appendText(newValue + "\n");
			}
		});
		model.moveCounter.addListener((o, oldValue, newValue) -> {
			if (!newValue.isEmpty()) {
				view.get_Ctrl().setGameBoard(model.getActGameBoard());
			}
		});
		view.get_Ctrl().txtGamePort.textProperty().addListener((observable, oldValue, newValue) -> {
			validateGamePortNumber(newValue, "txtGamePort");
		});
		view.get_Ctrl().txtChatPort.textProperty().addListener((observable, oldValue, newValue) -> {
			validateChatPortNumber(newValue, "txtChatPort");
		});

		// Random-Name Generator
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 8) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();

		// Set auto Values for Connecting
		view.get_Ctrl().txtChatPort.setText(config.getOption("ChatPort"));
		view.get_Ctrl().txtGamePort.setText(config.getOption("GamePort"));
		view.get_Ctrl().txtChatPort.setText(config.getOption("ChatPort"));
		view.get_Ctrl().txtName.setText(saltStr);
		view.get_Ctrl().txtIpAddress.setText("127.0.0.1");

	}

	@FXML
	public void initialize() {

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
		String ipAddress = view.get_Ctrl().txtIpAddress.getText();
		// Connect for Chat Com
		model.connect(ipAddress, chatPort, name);
		// Connect for Object Com
		model.connectObjectCom(ipAddress, gamePort, name);
	}

	public void sendMessage(String messageText) {
		model.sendMessage(messageText);
	}

	/**
	 * Enable or disable the Connect button, based on the validity of the two
	 * text controls
	 */
	private void enableDisableButton() {
		boolean valid = gamePortValid;
		boolean valid2 = chatPortValid;
		view.get_Ctrl().btnConnect.setDisable(!(valid || valid2));
	}

	public void takeCard(int pos, double x, double y) {
		CharacterCard c = this.model.getActGameBoard().takeCard(pos);
		if (c.getCardType2() != null) {
			// Splitt-Card
			int choosenType = this.helperSlopePosition(x, y);
			if (choosenType == 1) {
				c.setChoosenCardType(c.getCardType1());
			} else {
				c.setChoosenCardType(c.getCardType2());
			}

		} else {
			// Normale Karte
			c.setChoosenCardType(c.getCardType1());
		}

		int l = this.model.getActGameBoard().getActivePlayer().getLocation(c);
		this.model.getActGameBoard().playCard(c, l);

		// Message mit get‰tigten Spielz¸gen an Server schicken
		String gameMessage = this.model.getActGameBoard().getActivePlayer().getPlayerGameMessage();
		String gameBoardMessage = this.model.getActGameBoard().getGameMessage();
		this.model.sendGameMessage(gameMessage);
		this.model.sendGameMessage(gameBoardMessage);

		this.model.getActGameBoard().setNextPlayerIndex();
		if (this.model.getActGameBoard().isGameEnd()) {
			
			ArrayList<String> finalScoreMessage = this.model.getActGameBoard().getFinalScoreMessage();
			System.out.println(finalScoreMessage.toString());
			Iterator<String> it = finalScoreMessage.iterator();
			
				while (it.hasNext()) {
					String msg = it.next();
					System.out.println(msg);
					this.model.sendGameMessage(msg);
				    try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}

		this.model.sendGameBoardToServer(this.model.getActGameBoard());
		view.get_Ctrl().setGameBoard(this.model.getActGameBoard());

	}

	private int helperSlopePosition(double x, double y) {
		// Steigung
		final double m = -0.5;
		// Achsenabschnitt
		final double c = 97;
		int result = 0;
		if (x * m + c > y) {
			result = 1;
		} else {
			result = 2;
		}
		return result;
	}

	public void closeView() {
		view.stop();
		model.disconnect();
	}

}
