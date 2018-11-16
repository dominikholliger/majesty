package leberkaes.appClasses;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;

import leberkaes.jat2.ServiceLocator;
import leberkaes.abstractClasses.View;
import leberkaes.commonClasses.Translator;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_View extends View<App_Model> {
    
	protected Parent parent;

	public App_View(Stage stage, App_Model model) {
        super(stage, model);
		try {
			parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        stage.setTitle("Majesty - FHNW Gruppe Leberkaes");
        ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");
    }

	@Override
	protected Scene create_GUI() {
	    ServiceLocator sl = ServiceLocator.getServiceLocator();  
	    Logger logger = sl.getLogger();
	    Translator t = sl.getTranslator();
	    
	   
	    
	    
      /* for (Locale locale : sl.getLocales()) {
           MenuItem language = new MenuItem(locale.getLanguage());
           menuFileLanguage.getItems().add(language);
           language.setOnAction( event -> {
				sl.getConfiguration().setLocalOption("Language", locale.getLanguage());
                sl.setTranslator(new Translator(locale.getLanguage()));
                updateTexts();
            });
        */
	    return scene;
	}
       
	
	
	   protected void updateTexts() {
	       Translator t = ServiceLocator.getServiceLocator().getTranslator();
	        // The menu entries
	       // Example menuFile.setText(t.getString("program.menu.file"));
	      
	    }

	public Scene showSettings() {
		
      
     
        Scene scene = new Scene(parent, 500,500);
        scene.getStylesheets().add(
        getClass().getResource("app.css").toExternalForm());
        return scene;
		
		
		
	}
		
}