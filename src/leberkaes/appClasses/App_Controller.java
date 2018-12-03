package leberkaes.appClasses;

import leberkaes.jat2.ServiceLocator;
import leberkaes.abstractClasses.Controller;
import leberkaes.gameClient.GameClient_Controller;
import leberkaes.gameClient.GameClient_Model;
import leberkaes.gameClient.GameClient_View;
import leberkaes.gameServer.GameServer_Controller;
import leberkaes.gameServer.GameServer_Model;
import leberkaes.gameServer.GameServer_View;
import leberkaes.settingsWindows.GameSettings_Controller;
import leberkaes.settingsWindows.GameSettings_Model;
import leberkaes.settingsWindows.GameSettings_View;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private GameServer_View _GameServerViewInstance;
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
    /**
     * Die einzelnen Events werden an dieser Stelle aus dem Dummy Controller verarbeitet
     */
	public void startNewServerProcess() {
		// Aufrufendes Fenster: Button auf Home-Screen
		// Diese Methode startet das Server Windows
		Stage optionsStage = new Stage();
	   	GameServer_Model serverModel = new GameServer_Model();
	   	GameServer_View serverView = new GameServer_View(optionsStage, serverModel);
		new GameServer_Controller(serverModel, serverView);
		serverView.start();
	}    
	public void startNewClientProcess() {
		// Aufrufendes Fenster: Button auf Home-Screen
		// Diese Methode startet das Client Windows
		Stage optionsStage = new Stage();
	   	GameClient_Model clientModel = new GameClient_Model();
	   	GameClient_View clientView = new GameClient_View(optionsStage, clientModel);
		clientView.start();
	}    
	public void openSettingWindow() {
		// Aufrufendes Fenster: Button auf Home-Screen
		// Diese Methode startet das Setting Windows
		Stage optionsStage = new Stage();
	   	GameSettings_Model settingsModel = new GameSettings_Model();
	   	GameSettings_View settingsView = new GameSettings_View(optionsStage, settingsModel);
		settingsView.start();
	}    
	
	protected Parent parent;
	
	public void openHighscoreWindow(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Highscore.fxml"));
		loader.setController(this);
		try {
			parent = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Scene scene = new Scene(parent, 600,400);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		view.getStage().setScene(scene);
	}
	
	public void showHomeWindow(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		loader.setController(this);
		try {
			parent = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Scene scene = new Scene(parent, 600,400);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		view.getStage().setScene(scene);
	}
	
	@FXML protected void handleNewGameButtonClicked(ActionEvent event) throws Exception {
		// Server Prozess starten
		startNewServerProcess();
	}
	@FXML protected void handleSettingsButtonClicked(ActionEvent event) throws Exception{
		// Einstellungen öffnen
		openSettingWindow();
	}
	@FXML protected void handleHighscoreButtonClicked(ActionEvent event) throws Exception{
		// Highscore-Fenster oeffnet sich, nichts wird uebergeben
		openHighscoreWindow();
		
	}
	@FXML protected void handleBackClicked(ActionEvent event) throws Exception{
		// Client Prozess starten
		showHomeWindow();
	}
	
	@FXML protected void handleEnterGameButtonClicked(ActionEvent event) throws Exception{
		// Client Prozess starten
		startNewClientProcess();
	}
	
	
	
	
}
