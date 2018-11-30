package leberkaes.appClasses;

import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import leberkaes.commonClasses.Translator;
import leberkaes.jat2.ServiceLocator;

public class dummyFXMLControllerSettings {
	private App_Controller _MvcCtrl;
	
	@FXML private Button back;
	@FXML private ToggleButton germanoption;
	@FXML private ToggleButton englishoption;
	
	public App_Controller get_MvcCtrl() {
		return _MvcCtrl;
	}
	public void set_MvcCtrl(App_Controller _MvcCtrl) {
		this._MvcCtrl = _MvcCtrl;
	}
	
	@FXML protected void handleBackClicked(ActionEvent event) throws Exception {
	}
	
	@FXML private Button newGame;
	@FXML private Button settings;
	@FXML private Button highscore;
	@FXML private Button enterGame;
	
	@FXML protected void handleGermanClicked(ActionEvent event) throws Exception {
		// TODO
		
		/*Locale.setDefault(Locale.GERMAN);
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		
		// The menu entries
		newGame.setText(t.getString("newGame"));
		settings.setText(t.getString("settings"));
		highscore.setText(t.getString("highscore"));
		enterGame.setText(t.getString("enterGame"));
		back.setText(t.getString("back"));*/
		
		
		// Via plain text, without locale and translator
	/*	newGame.setText("Neues Spiel");
		settings.setText("Einstellungen");
		highscore.setText("Bestenliste");
		enterGame.setText("Spiel beitreten");
		back.setText("Zurück");*/
	}
	
	@FXML protected void handleEnglishClicked(ActionEvent event) throws Exception {
		// TODO
		
		// Via Locale
		//Locale.setDefault(Locale.ENGLISH);
		//Translator t = ServiceLocator.getServiceLocator().getTranslator();
		
		// The menu entries
		/*newGame.setText(t.getString("newGame"));
		settings.setText(t.getString("settings"));
		highscore.setText(t.getString("highscore"));
		enterGame.setText(t.getString("enterGame"));
		back.setText(t.getString("back"));*/
		
		
		// Via plain text, without locale and translator
		/*newGame.setText("New Game");
		settings.setText("Settings");
		highscore.setText("Highscore");
		enterGame.setText("Enter Game");
		back.setText("Back");*/
		
	}
}
