package leberkaes.appClasses;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import leberkaes.abstractClasses.View;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;
import leberkaes.jat2.ServiceLocator;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 *
 * @author Brad Richards
 */
public class App_View extends View<App_Model> {

	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();
	
	// Audio Controls
	static AudioPlayer MGP = AudioPlayer.player;
	static AudioStream BGM;
	static AudioData MD;

	/**
	 * Per Lazy Loading die ein DummyKontroller Objekt erstellen und per Getter zur
	 * verfügung Stellen wird für den FXML Loader gebraucht.
	 * 
	 * @author Dominik Holliger
	 */
	private dummyFXMLControllerHome _Ctrl;

	public dummyFXMLControllerHome get_Ctrl() {
		if (_Ctrl == null) {
			_Ctrl = new dummyFXMLControllerHome();
		}
		return _Ctrl;
	}

	protected Parent parent;

	public App_View(Stage stage, App_Model model) {
		super(stage, model);
		stage.setTitle(t.getString("home.windowTitle"));
		ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");
		music();
	}

	@Override
	protected Scene create_GUI() {
		
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
		return scene;
	}

	/** @author Sebrina Pedrossi */
	// Play music from audioStream, file in project explorer
	public static InputStream stream;

	//Try with File for Music
	/*public static void music() {
		try {
			InputStream in = new FileInputStream(new File("./src/leberkaes.GUIsources/MedievalMusicShort.wav"));
			audios = new AudioStream(in);
			AudioPlayer.player.start(audios);
		} catch (Exception e) {
			System.out.println(e);
		}
	}*/
	
	// Try with Stream so it works with jar-file
	public static void music() {
		try {
	        stream = App_View.class.getResourceAsStream("/leberkaes.GUIsources/MedievalMusicShort.wav");
	        AudioPlayer.player.start(stream);
	    } catch (Exception e) {
	    	System.out.println("Problem playing file MedievalMusicShort.wav");
	        System.out.println(e);
	    }
	}

	// stop Music that is played from audioStream
	public static void stopmusic() {
		AudioPlayer.player.stop(stream);
	}

}