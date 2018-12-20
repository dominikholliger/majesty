package leberkaes.appClasses;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import leberkaes.abstractClasses.Controller;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;
import leberkaes.gameClient.GameClient_Controller;
import leberkaes.gameClient.GameClient_Model;
import leberkaes.gameClient.GameClient_View;
import leberkaes.gameServer.GameServer_Controller;
import leberkaes.gameServer.GameServer_Model;
import leberkaes.gameServer.GameServer_View;
import leberkaes.jat2.ServiceLocator;
import leberkaes.settingsWindows.GameSettings_Model;
import leberkaes.settingsWindows.GameSettings_View;
/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 *
 * @author Brad Richards
 */
public class App_Controller extends Controller<App_Model, App_View> {
    ServiceLocator serviceLocator;

	@FXML public Button ButtonMusicOn;
    @FXML public Button ButtonMusicOff;
    public boolean musicPlaying = false;
    private boolean serverrunning = false;


    public App_Controller(App_Model model, App_View view) {
        super(model, view);
        // D.Holliger:
        // Vorbereiten für die Kommunikation Controller vs DummyController FXML
        view.get_Ctrl().set_MvcCtrl(this);
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            	System.out.println("Spiel wird komplett beendet.");
            	Platform.exit();
            }
        });
        serviceLocator = ServiceLocator.getServiceLocator();
        serviceLocator.getLogger().info("Application controller initialized");
        Translator t = serviceLocator.getTranslator();
        view.get_Ctrl().newGame.setText(t.getString("home.btnServerStart"));
        view.get_Ctrl().enterGame.setText(t.getString("home.btnClientStart"));
        view.get_Ctrl().highscore.setText(t.getString("home.btnHighScore"));
      
    }
    /**
     * Die einzelnen Events werden an dieser Stelle aus dem Dummy Controller verarbeitet
     */
	public void startNewServerProcess() {
		// Aufrufendes Fenster: Button auf Home-Screen
		// Diese Methode startet das Server Window
		if(!serverrunning) {
			Stage optionsStage = new Stage();
		   	GameServer_Model serverModel = new GameServer_Model();
		   	GameServer_View serverView = new GameServer_View(optionsStage, serverModel);
			new GameServer_Controller(serverModel, serverView, this);
			serverView.start();
			serverrunning = true;
		}
	}
	public void startNewClientProcess() {
		// Aufrufendes Fenster: Button auf Home-Screen
		// Diese Methode startet das Client Window
		Stage optionsStage = new Stage();
	  	GameClient_Model clientModel = new GameClient_Model();
	  	GameClient_View clientView = new GameClient_View(optionsStage, clientModel);
	  	new GameClient_Controller(clientModel, clientView);
		clientView.start();
	}

	/** @author Sebrina Pedrossi */
	public void openSettingWindow() {
		// Aufrufendes Fenster: Button auf Home-Screen
		// Diese Methode startet das Setting Window
		Stage optionsStage = new Stage();
	   	GameSettings_Model settingsModel = new GameSettings_Model();
	   	GameSettings_View settingsView = new GameSettings_View(optionsStage, settingsModel);
	   	settingsView.setAppControlInstance(this);
		settingsView.start();
	}

	protected Parent parent;

	/**
	 *  Aufrufendes Fenster: Button auf Home-Screen
	 *  Diese Methode startet das Highscore Window
	 *  @author D.Holliger
	 */
	public void openHighscoreWindow(){
		Stage optionsStage = new Stage();
		HighScore_Model highScoreModel = new HighScore_Model();
		HighScore_View highScoreView = new HighScore_View(optionsStage, highScoreModel);
		new HighScore_Controller(highScoreModel, highScoreView);
		highScoreView.start();
	}

	public void showHomeWindow(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		loader.setController(this);
		try {
			parent = loader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Scene scene = new Scene(parent, 600,400);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		view.getStage().setScene(scene);
	}

	public void setMusicPlaying(boolean musicPlaying) {
		this.musicPlaying = musicPlaying;
	}
	public boolean isMusicPlaying() {
		// TODO Auto-generated method stub
		return musicPlaying;
	}
	public void setNewGameServerActive() {
		view.get_Ctrl().enableServerButton();
		this.serverrunning=false;
	}

	public void updateTexts() {
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Translator t = sl.getServiceLocator().getTranslator();
		Configuration config = sl.getConfiguration();
		t.setLocale(config.getOption("Language"));
		System.out.println(t.getCurrentLocale());
		// update Homescreen Buttons.....
       view.get_Ctrl().highscore.setText(t.getString("home.btnHighScore"));
       view.get_Ctrl().newGame.setText(t.getString("home.btnServerStart"));
       view.get_Ctrl().enterGame.setText(t.getString("home.btnClientStart"));
       view.getStage().setTitle(t.getString("home.windowTitle"));

	}
}
