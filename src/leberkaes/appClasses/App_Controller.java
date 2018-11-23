package leberkaes.appClasses;

import leberkaes.jat2.ServiceLocator;
import leberkaes.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.event.EventHandler;
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
    }
 
    public void serverSettings() {
    	view.get_SettingsCtrl().set_MvcCtrl(this);
    	view.showSettings();  	
    }
    
    public void goToHome() {
    	view.get_Ctrl().set_MvcCtrl(this);
    	view.showHome();
    }
    
    public void goToHighscore() {
    	view.get_HighscoreCtrl().set_MvcCtrl(this);
    	view.showHighscore();
    }
    
}
