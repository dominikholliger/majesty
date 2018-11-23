package leberkaes.appClasses;

import java.io.IOException;
import java.net.URL;
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

	/**
	 * Per Lazy Loading die ein DummyKontroller Objekt erstellen und per Getter zur verfügung Stellen
	 * wird für den FXML Loader gebraucht.
	 */
	private dummyFXMLControllerHome _Ctrl;
	public dummyFXMLControllerHome get_Ctrl() {
		if(_Ctrl == null) {
			_Ctrl = new dummyFXMLControllerHome();
		}
		return _Ctrl;    	
	}

	private dummyFXMLControllerSettings _SettingsCtrl;
	public dummyFXMLControllerSettings get_SettingsCtrl() {
		if(_SettingsCtrl == null) {
			_SettingsCtrl = new dummyFXMLControllerSettings();
		}
		return _SettingsCtrl; 
	}
	
	private dummyFXMLControllerHighscore _HighscoreCtrl;
	public dummyFXMLControllerHighscore get_HighscoreCtrl() {
		if(_HighscoreCtrl == null) {
			_HighscoreCtrl = new dummyFXMLControllerHighscore();
		}
		return _HighscoreCtrl; 
	}

	protected Parent parent;

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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		loader.setController(get_Ctrl());
		try {
			parent = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Scene scene = new Scene(parent, 500,500);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		return scene;
	}

	protected void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		// The menu entries
		/*	       menuFile.setText(t.getString("program.menu.file"));
	       menuFileLanguage.setText(t.getString("program.menu.file.language"));
	       menuGame.setText(t.getString("program.menu.game"));
	       menuGameServer.setText(t.getString("program.menu.game.server"));
           menuHelp.setText(t.getString("program.menu.help"));
           GameServerStart.setText(t.getString("program.menu.game.server.start"));
   	       GameServerSettings.setText(t.getString("program.menu.game.server.settings"));
	        // Other controls
           btnClick.setText(t.getString("button.clickme"));*/
	}

	public void showSettings() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
		loader.setController(get_SettingsCtrl());
		try {
			parent = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Scene scene = new Scene(parent, 500,500);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		super.getStage().setScene(scene);		
	}	

	public void showHome() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		loader.setController(get_Ctrl());
		try {
			parent = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Scene scene = new Scene(parent, 500,500);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		super.getStage().setScene(scene);	
	}
	
	public void showHighscore() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Highscore.fxml"));
		loader.setController(get_HighscoreCtrl());
		try {
			parent = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Scene scene = new Scene(parent, 500,500);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		super.getStage().setScene(scene);
	}
}