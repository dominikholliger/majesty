package leberkaes.appClasses;

import leberkaes.jat2.ServiceLocator;
import leberkaes.abstractClasses.Controller;
import leberkaes.commonClasses.Highscore;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 *
 * @author Brad Richards
 */
public class HighScore_Controller extends Controller<HighScore_Model, HighScore_View> {
    ServiceLocator serviceLocator;

    public HighScore_Controller(HighScore_Model model, HighScore_View view) {
        super(model, view);
        // D.Holliger:
        // Vorbereiten für die Kommunikation Controller vs DummyController FXML
        view.get_Ctrl().set_MvcCtrl(this);
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            	System.out.println("HighScore Screen schliessen");
            	view.stop();
            }
        });
        serviceLocator = ServiceLocator.getServiceLocator();
        serviceLocator.getLogger().info("Application controller initialized");
        //
        writeHighScoreListFromDB();
    }
    /**
     * Die einzelnen Events werden an dieser Stelle aus dem Dummy Controller verarbeitet
     */
	public void writeHighScoreListFromDB() {
		ArrayList list = Highscore.getInstance().getHighscore();
		Iterator<String> listIterator = list.iterator();
		while (listIterator.hasNext()) {
			view.get_Ctrl().writeToHighScore(listIterator.next()+"\n");
		}
	}






}
