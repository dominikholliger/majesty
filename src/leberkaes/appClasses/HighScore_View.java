package leberkaes.appClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import leberkaes.jat2.ServiceLocator;
import leberkaes.abstractClasses.View;
import leberkaes.commonClasses.Translator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 *
 * @author Brad Richards
 */
public class HighScore_View extends View<HighScore_Model> {


	/**
	 * @author D.Holliger
	 * Per Lazy Loading die ein DummyKontroller Objekt erstellen und per Getter zur verfügung Stellen
	 * wird für den FXML Loader gebraucht.
	 */
	private dummyFXMLControllerHighScore _Ctrl;
	public dummyFXMLControllerHighScore get_Ctrl() {
		if(_Ctrl == null) {
			_Ctrl = new dummyFXMLControllerHighScore();
		}
		return _Ctrl;
	}

	protected Parent parent;
	public HighScore_View(Stage stage, HighScore_Model model) {
		super(stage, model);
		stage.setTitle("Majesty - Highscore");
		ServiceLocator.getServiceLocator().getLogger().info("HighScore view initialized");
	}

	@Override
	protected Scene create_GUI() {
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Logger logger = sl.getLogger();
		Translator t = sl.getTranslator();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Highscore.fxml"));
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

	protected void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
	}


}