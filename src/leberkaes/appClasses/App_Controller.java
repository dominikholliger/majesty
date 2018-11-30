package leberkaes.appClasses;

import leberkaes.jat2.ServiceLocator;
import leberkaes.abstractClasses.Controller;
import leberkaes.gameClient.GameClient_Model;
import leberkaes.gameClient.GameClient_View;
import leberkaes.gameServer.GameServer_Model;
import leberkaes.gameServer.GameServer_View;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_Controller extends Controller<App_Model, App_View> {
    ServiceLocator serviceLocator;

    public App_Controller(App_Model model, App_View view) {
        super(model, view);
        // D.Holliger:
        // Vorbereiten f�r die Kommunikation Controller vs DummyController FXML
//        view.get_Ctrl().set_MvcCtrl(this);
        
        
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
    }
    
	public void Juhu(String msg) {
		System.out.println(msg);
	}    
    
    @FXML
    public void handleNewGameButtonClicked(){
    System.out.println("new game button");
   	Stage appStage = new Stage();
   	GameServer_Model model = new GameServer_Model();
    GameServer_View view = new GameServer_View(appStage, model);
    view.start();
   	 
   	 
     
    }
    
    @FXML
    public void handleSettingsButtonClicked(){
    	System.out.println("settings button clicked");
    }
    
    @FXML
    public void handleHighscoreButtonClicked(){
    	System.out.println("highscore button clicked");
    }
    
    @FXML
    public void handleEnterGameButtonclicked(){
    	System.out.println("game button clicked");
    	Stage appStage = new Stage();
       	GameClient_Model model = new GameClient_Model();
        GameClient_View view = new GameClient_View(appStage, model);
        view.start();
    }
    
    public void serverSettings() {
    	//
    	// Server Settings 
    	view.showSettings();
    	
    	
    }
}
