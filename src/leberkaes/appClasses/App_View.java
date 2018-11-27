package leberkaes.appClasses;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.logging.Logger;

import leberkaes.jat2.ServiceLocator;
import leberkaes.abstractClasses.View;
import leberkaes.commonClasses.Translator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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
	
	@FXML private Button newGame;
	@FXML private Button settings;
	@FXML private Button highscore;
	@FXML private Button enterGame;
	@FXML private Button back;
	@FXML private ToggleButton germanoption;
	@FXML private ToggleButton englishoption;

	public App_View(Stage stage, App_Model model) {
		super(stage, model);
		stage.setTitle("Majesty - FHNW Gruppe Leberkaes");
		ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");

	}
	
	
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

		Scene scene = new Scene(parent, 600,400);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		return scene;
	}

	public void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		
		// The menu entries
		newGame.setText(t.getString("newGame"));
		settings.setText(t.getString("settings"));
		highscore.setText(t.getString("highscore"));
		enterGame.setText(t.getString("enterGame"));
		back.setText(t.getString("back"));
		
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
		
		Scene scene = new Scene(parent, 600,400);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		super.getStage().setScene(scene);	
		
		ToggleGroup toggle = new ToggleGroup();
		germanoption.setToggleGroup(toggle);
		englishoption.setToggleGroup(toggle);
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

		Scene scene = new Scene(parent, 600,400);
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

		Scene scene = new Scene(parent, 600, 400);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		super.getStage().setScene(scene);	
	}
}