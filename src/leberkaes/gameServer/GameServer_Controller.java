package leberkaes.gameServer;
import leberkaes.commonClasses.Configuration;
import leberkaes.jat2.ServiceLocator;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class GameServer_Controller {
	private GameServer_Model model;
	private GameServer_View view;
	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();

	public GameServer_Controller(GameServer_Model model, GameServer_View view) {
		super(model, view);
        // D.Holliger:
        // Vorbereiten für die Kommunikation Controller vs DummyController FXML
        view.get_NewGameCtrl().set_NMvcCtrl(this);
        
        int port = Integer.parseInt(config.getOption("Port"));
		model.startServer(port);
		view.stage.setOnCloseRequest(event -> model.stopServer());
		model.clients.addListener((ListChangeListener) (event -> view.updateClients()));
        
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                
            	System.out.println();
            	
            	Platform.exit();
            }
        });
		
	}
}
