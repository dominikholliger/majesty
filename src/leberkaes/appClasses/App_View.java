package leberkaes.appClasses;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;

import leberkaes.jat2.ServiceLocator;
import leberkaes.abstractClasses.View;
import leberkaes.commonClasses.Translator;
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
    Menu menuFile;
    Menu menuFileLanguage;
    
    Menu menuGame;
    Menu menuGameServer;
    
    MenuItem GameServerStart;
    MenuItem GameServerSettings; 

    Menu menuHelp;
    
    Label lblNumber;
    Button btnClick;

	public App_View(Stage stage, App_Model model) {
        super(stage, model);
        stage.setTitle("Majesty - FHNW Gruppe Leberkaes");
        ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");
    }

	@Override
	protected Scene create_GUI() {
	    ServiceLocator sl = ServiceLocator.getServiceLocator();  
	    Logger logger = sl.getLogger();
	    Translator t = sl.getTranslator();
	    
	    MenuBar menuBar = new MenuBar();
	    menuFile = new Menu(t.getString("program.menu.file"));
	    menuFileLanguage = new Menu(t.getString("program.menu.file.language"));
	    menuFile.getItems().add(menuFileLanguage);

	    menuGame = new Menu(t.getString("program.menu.game"));
	    menuGameServer = new Menu(t.getString("program.menu.game.server"));
	    GameServerStart = new MenuItem("program.menu.game.server.start");
	    GameServerSettings = new MenuItem("program.menu.game.server.settings");

	    menuGame.getItems().add(menuGameServer);
	    menuGameServer.getItems().add(GameServerStart);
	    menuGameServer.getItems().add(GameServerSettings);
	    
	    
       for (Locale locale : sl.getLocales()) {
           MenuItem language = new MenuItem(locale.getLanguage());
           menuFileLanguage.getItems().add(language);
           language.setOnAction( event -> {
				sl.getConfiguration().setLocalOption("Language", locale.getLanguage());
                sl.setTranslator(new Translator(locale.getLanguage()));
                updateTexts();
            });
        }
	    
        menuHelp = new Menu(t.getString("program.menu.help"));
	    menuBar.getMenus().addAll(menuFile,menuGame, menuHelp);
		
		GridPane root = new GridPane();
		root.add(menuBar, 0, 0);
		
		lblNumber = new Label();
        lblNumber.setText(Integer.toString(model.getValue()));
        lblNumber.setMinWidth(200);
        lblNumber.setAlignment(Pos.BASELINE_CENTER);
        root.add(lblNumber, 0, 1);
        
        btnClick = new Button();
        btnClick.setText(t.getString("button.clickme"));
        btnClick.setMinWidth(200);
        root.add(btnClick, 0, 2);
		
        Scene scene = new Scene(root, 500,500);
        scene.getStylesheets().add(
                getClass().getResource("app.css").toExternalForm());
        return scene;
	}
	
	   protected void updateTexts() {
	       Translator t = ServiceLocator.getServiceLocator().getTranslator();
	        
	        // The menu entries
	       menuFile.setText(t.getString("program.menu.file"));
	       menuFileLanguage.setText(t.getString("program.menu.file.language"));
	       menuGame.setText(t.getString("program.menu.game"));
	       menuGameServer.setText(t.getString("program.menu.game.server"));
           menuHelp.setText(t.getString("program.menu.help"));
           GameServerStart.setText(t.getString("program.menu.game.server.start"));
   	       GameServerSettings.setText(t.getString("program.menu.game.server.settings"));
           
           
	        
	        // Other controls
           btnClick.setText(t.getString("button.clickme"));
	    }

	public Scene showSettings() {
		GridPane root = new GridPane();
		
		lblNumber = new Label();
        lblNumber.setText(Integer.toString(model.getValue()));
        lblNumber.setMinWidth(200);
        lblNumber.setAlignment(Pos.BASELINE_CENTER);
        root.add(lblNumber, 0, 1);
        
     
        Scene scene = new Scene(root, 500,500);
        scene.getStylesheets().add(
                getClass().getResource("app.css").toExternalForm());
        return scene;
		
		
		
	}
		
}