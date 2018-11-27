package leberkaes.appClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import leberkaes.gameClient.GameClient_Controller;
import leberkaes.gameClient.GameClient_Model;
import leberkaes.gameClient.GameClient_View;
import leberkaes.gameClient.dummyFXMLControllerEnterGame;

public class dummyFXMLControllerHome {
	
	/** 
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


	@FXML protected void handleNewGameButtonClicked(ActionEvent event) throws Exception {
		// Nur ein Beispiel einer Mapping Methode
		//TODO
	}
	
	
	@FXML protected void handleSettingsButtonClicked(ActionEvent event) throws Exception{
		//TODO was passiert wenn der knopf gedrueckt wird 
		//settings-Fenster oeffnet sich, nichts uebergeben
		get_MvcCtrl().serverSettings();
	}
	
	@FXML protected void handleHighscoreButtonClicked(ActionEvent event) throws Exception{
		// Highscore-Fenster oeffnet sich, nichts wird uebergeben
		get_MvcCtrl().goToHighscore();
		
	}
	@FXML protected void handleEnterGameButtonclicked(ActionEvent event) throws Exception{
		// Hier hackt es: Es soll ein GameMvc-Controller angesprochen werden (get_GMvcCtrl)
		// ABER: Diesen gibt es gar noch nicht, weil es das gesamte GameClient-MVC noch nicht gibt!
		// Wie kann ich diesen ansprechen ud zuvor das GameClientMVC aufbauen?
		leberkaes.gameClient.dummyFXMLControllerEnterGame.get_GMvcCtrl().goToEnterGame();
	}	
	
	public String Hallo() {
		return "Welt";
	}
	
}