package leberkaes.gameServer;
import leberkaes.appClasses.App_Controller;
import leberkaes.commonClasses.Configuration;
import leberkaes.jat2.ServiceLocator;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;

public class GameServer_Controller {
	private GameServer_Model model;
	private GameServer_View view;
	private App_Controller appControllerInstance;



	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();

	public GameServer_Controller(GameServer_Model model, GameServer_View view, App_Controller appControl) {
		this.appControllerInstance = appControl;
		this.model = model;
		this.view = view;
		// Settings laden
		int chatPort = Integer.parseInt(config.getOption("ChatPort"));
		int gamePort = Integer.parseInt(config.getOption("GamePort"));
		int playerCount = Integer.parseInt(config.getOption("PlayerCount"));
		boolean bside = Boolean.parseBoolean(config.getOption("BSide"));
		model.startServer(chatPort,playerCount,bside);
		// Muss noch aus der Konfig den Port nehmen
		model.startServerObjectCom(gamePort);
		view.stage.setOnCloseRequest(event -> {
			appControllerInstance.setNewGameServerActive();
			model.stopServer();

		});
		model.clients.addListener((ListChangeListener) (event -> view.updateClients()));
	}
}
