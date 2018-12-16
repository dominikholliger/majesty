package leberkaes.appClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.WindowEvent;


public class dummyFXMLControllerHome {

	/**
	 * @author Dominik Holliger
	 * Anmerkung D.Holliger:
	 * Vorsicht: FXML Controller versus MVC Controller!
	 * Um den generierten Kontroller von FXML an das MVC Framework von Brad anzudocken habe ich einige Anpassungen vorgenommen Ausgangslage:
	 * - Der FXMLoader lädt die fxml bei der Instanzierung des Views des MVC Modelles. Zu diesem Zeitpunkt existiert noch kein MVC Controller.
	 * - Der FMXLoader will den angegeben Kontrollerklasse instanzieren und erwartert eine statische Klasse ohne Konstruktor
	 *
	 * Implementation:
	 * - Im FXML keine Kontroller Klasse angeben
	 * - Kontrollerklasse (generierte Datei) von FXML mit dem Getter / Setter für ein APP_Controller Objekt (Controller MVC Brad) erstellen
	 * - Im View des MVC Brad den FXML Loader setzen, inkl. definition der ControllerKlasse -> Diese Dumy-Kontroller Klasse angeben (welche vom Scene Builder generiert wird).
	 * - Im View per Lazy Loading eine Instanz des Dummy-Controllers dem FXML Loader übergeben.
	 *
	 * Damit arbeiten:
	 * - In diesem Dummy-Kontroller können nun per this._MvcCtrl-> Methoden auf Methoden im MVC Kontroller von Brad gemappt werden.
	 *
	 * Ausbau:
	 * - Theoretisch könnte man dies noch mit einer Abstrakten Klasse vereinfachen, aber ich glaube wir lassen es aus Zeitgründen.
	 */

	private App_Controller _MvcCtrl;
	public App_Controller get_MvcCtrl() {
		return _MvcCtrl;
	}
	public void set_MvcCtrl(App_Controller _MvcCtrl) {
		this._MvcCtrl = _MvcCtrl;
	}

	@FXML
	private Button newGame;

	@FXML public Button ButtonMusicOn;
	@FXML public Button ButtonMusicOff;
	


	@FXML protected void handleNewGameButtonClicked(ActionEvent event) throws Exception {
		// Server Prozess starten
		get_MvcCtrl().startNewServerProcess();
	}
	
	@FXML protected void handleSettingsButtonClicked(ActionEvent event) throws Exception{
		// Einstellungen öffnen
		get_MvcCtrl().openSettingWindow();
	}
	
	@FXML protected void handleHighscoreButtonClicked(ActionEvent event) throws Exception{
		// Highscore-Fenster oeffnet sich, nichts wird uebergeben
		get_MvcCtrl().openHighscoreWindow();

	}
	@FXML protected void handleEnterGameButtonClicked(ActionEvent event) throws Exception{
		// Client Prozess starten
		get_MvcCtrl().startNewClientProcess();
	}

	@FXML protected void handleBackClicked(ActionEvent event) throws Exception{
		get_MvcCtrl().showHomeWindow();

		if (get_MvcCtrl().isMusicPlaying() == true){
			ButtonMusicOn.setVisible(true);
			ButtonMusicOff.setVisible(false);
		} else {
			ButtonMusicOn.setVisible(false);
			ButtonMusicOff.setVisible(true);	
		}
	}

	@FXML protected void handleMusicOffClicked(ActionEvent event) throws Exception{
		// stop Music
		App_View.stopmusic();
		ButtonMusicOn.setVisible(false);
		ButtonMusicOff.setVisible(true);
		get_MvcCtrl().setMusicPlaying(false);
	}

	@FXML protected void handleMusicOnClicked(ActionEvent event) throws Exception{
		// start Music
		App_View.music();
		ButtonMusicOn.setVisible(true);
		ButtonMusicOff.setVisible(false);
		get_MvcCtrl().setMusicPlaying(true);
	}

	public void writeToHighScore(String line){
		// Schreibt die Highscore
		highScoreField.appendText("1");
	}


}
