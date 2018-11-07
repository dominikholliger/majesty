package leberkaes.gameServer;
import leberkaes.commonClasses.Configuration;
import leberkaes.jat2.ServiceLocator;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;

public class GameServer_Controller {
	private GameServer_Model model;
	private GameServer_View view;
	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();

	public GameServer_Controller(GameServer_Model model, GameServer_View view) {
		this.model = model;
		this.view = view;
		int port = Integer.parseInt(config.getOption("Port"));
		model.startServer(port);
		view.stage.setOnCloseRequest(event -> model.stopServer());
		model.clients.addListener((ListChangeListener) (event -> view.updateClients()));
	}
}
