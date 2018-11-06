package leberkaes.appClasses;

import leberkaes.jat2.ServiceLocator;
import leberkaes.abstractClasses.Controller;
import leberkaes.gameServer.GameServer_Controller;
import leberkaes.gameServer.GameServer_Model;
import leberkaes.gameServer.GameServer_View;
import leberkaes.settingsWindows.WebValidator_Controller;
import leberkaes.settingsWindows.WebValidator_Model;
import leberkaes.settingsWindows.WebValidator_View;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

        // register ourselves to listen for our menu item clicks
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
        
        
        // register ourselves to listen for button clicks
        view.btnClick.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonClick();
            }
        });

        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
        
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application controller initialized");
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
