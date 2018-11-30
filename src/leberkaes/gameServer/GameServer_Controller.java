package leberkaes.gameServer;
import leberkaes.abstractClasses.Controller;
import leberkaes.commonClasses.Configuration;
import leberkaes.jat2.ServiceLocator;
import leberkaes.commonClasses.Translator;
import leberkaes.gameClient.GameClient_Model;
import leberkaes.gameClient.GameClient_View;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameServer_Controller extends Controller<GameServer_Model, GameServer_View>{
	
	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();
	private int port;
	
	public GameServer_Controller(GameServer_Model model, GameServer_View view) {
		super(model, view);
        // D.Holliger:
        // Vorbereiten für die Kommunikation Controller vs DummyController FXML
        port = Integer.parseInt(config.getOption("Port"));
		
		
		
		
		view.getStage().setOnCloseRequest(event -> model.stopServer());
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
	
        @FXML
        public void handleStartGameClicked() {
        	model.startServer(port);
        	model.createGameboard();
        	view.getStage().hide(); //eventuell auch close
        	this.joinAsClient();
        }
        
        @FXML
        public void handleTwoPlayersClicked() {
        	model.setNumberOfPLayers(2);
        	logger.info("2 Players set");
        }
        @FXML
        public void handleFourPlayersClicked() {
        	model.setNumberOfPLayers(4);
        	logger.info("4 Players set");
        }
            
        public void joinAsClient() {
        	Stage appStage = new Stage();
           	GameClient_Model model = new GameClient_Model();
            GameClient_View view = new GameClient_View(appStage, model);
            view.start();
        }
        
	}
	
	

