package leberkaes.appClasses;

import leberkaes.jat2.ServiceLocator;
import leberkaes.abstractClasses.Controller;
import leberkaes.gameClient.GameClient_Controller;
import leberkaes.gameClient.GameClient_Model;
import leberkaes.gameClient.GameClient_View;
import leberkaes.gameServer.GameServer_Controller;
import leberkaes.gameServer.GameServer_Model;
import leberkaes.gameServer.GameServer_View;
import leberkaes.settingsWindows.WebValidator_Controller;
import leberkaes.settingsWindows.WebValidator_Model;
import leberkaes.settingsWindows.WebValidator_View;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
        
        view.get_Ctrl().set_MvcCtrl(this);
        
        
        /*// register ourselves to listen for our menu item clicks
        view.GameServerStart.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	serviceLocator.getLogger().info("GameServer - start requested");
            	 Stage optionsStage = new Stage();
        	     // Initialize the option MVC components
            	GameServer_Model serverModel = new GameServer_Model();
            	GameServer_View serverView = new GameServer_View(optionsStage, serverModel);
        	    new GameServer_Controller(serverModel, serverView);
       	        // Display the options window
        	    serverView.start();
            }
        });
        view.GameServerSettings.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	 serviceLocator.getLogger().info("GameServer - settings clicked");
        		 Stage optionsStage = new Stage();
        	     // Initialize the option MVC components
        		WebValidator_Model oModel = new WebValidator_Model();
        		WebValidator_View oView = new WebValidator_View(optionsStage, oModel);
        	    new WebValidator_Controller(oModel, oView);
       	        // Display the options window
       	        oView.start();
            }
        });
        view.GameClientStart.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	 serviceLocator.getLogger().info("GameClient - started");
        		 Stage optionsStage = new Stage();
        	     // Initialize the option MVC components
        		GameClient_Model gModel = new GameClient_Model();
        		GameClient_View gView = new GameClient_View(optionsStage, gModel);
        	    new GameClient_Controller(gModel, gView);
       	        // Display the options window
       	        gView.start();
            }
        });
        
        // register ourselves to listen for button clicks
        view.btnClick.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonClick();
            }
        });
*/
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
    
	@FXML protected void handleNewGameButtonClicked(ActionEvent event) throws Exception {
		//StartNewGameView newgame = new StartNewGameView();
		//Stage s = new Stage();
		//newgame.start(s);
	}
	
	
	@FXML protected void handleSettingsButtonClicked(ActionEvent event) throws Exception{
		//TODO was passiert wenn der knopf gedrueckt wird 
		//settings-Fenster oeffnet sich, nichts uebergeben
		//ShowSettingsView settings = new ShowSettingsView();
		//Stage s = new Stage();
		//settings.start(s);
		
	}
	@FXML protected void handleHighscoreButtonClicked(ActionEvent event) throws Exception{
		// Highscore-Fenster oeffnet sich, nichts wird uebergeben
		//ShowHighscoresView highscores = new ShowHighscoresView();
		//Stage s = new Stage();
		//highscores.start(s);
		
	}
	@FXML protected void handleEnterGameButtonclicked(ActionEvent event) throws Exception{
		//EnterGameView entergame = new EnterGameView();
		//Stage s = new Stage();
		//entergame.start(s);
		
	}
    
    
    public void buttonClick() {
        model.incrementValue();
        String newText = Integer.toString(model.getValue());        

        view.lblNumber.setText(newText);        
    }
    
    public void serverSettings() {
    	//
    	// Server Settings 
    	view.showSettings();
    	
    	
    }
}
