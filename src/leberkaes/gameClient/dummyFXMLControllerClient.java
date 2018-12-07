package leberkaes.gameClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import leberkaes.appClasses.App_Controller;

public class dummyFXMLControllerClient {

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

	
	private GameClient_Controller _MvcCtrl;
	
	public GameClient_Controller get_MvcCtrl() {
		return _MvcCtrl;
	}
	
	public void set_MvcCtrl(GameClient_Controller _MvcCtrl) {
		this._MvcCtrl = _MvcCtrl;
	}

	// Top controls (Connection)
	@FXML
	public TextField txtName;
	@FXML
	public TextField txtChatPort;
	@FXML
	public TextField txtGamePort;
	@FXML
	public TextField txtIpAddress;
	@FXML
	public Button btnConnect;
	
	
	
	
	
	private void validateGamePortNumber(String newValue, String obsElement) {
		
		get_MvcCtrl().validateGamePortNumber();
		
	}

	
	private void validateChatPortNumber(String newValue, String obsElement) {
//		boolean valid = model.isValidPortNumber(newValue);
//		// Change text color
//		if (valid) {
//			this.txtChatPort.setStyle("-fx-text-inner-color: green;");
//		} else {
//			this.txtChatPort.setStyle("-fx-text-inner-color: red;");
//		}
//		// Save result
//		portValid = valid;
//		// Enable or disable button, as appropriate
//		enableDisableButton();
	}

	private void validatePlayerCountNumber(String newValue, String obsElement) {
//		boolean valid = model.isValidPlayerCountNumber(newValue);
//
//		// Change text color
//		if (valid) {
//			this.txtPlayerCount.setStyle("-fx-text-inner-color: green;");
//		} else {
//			this.txtPlayerCount.setStyle("-fx-text-inner-color: red;");
//		}
//		// Save result
//		playerCountValid = valid;
//		// Enable or disable button, as appropriate
//		enableDisableButton();
	}

	/**
	 * Enable or disable the Connect button, based on the validity of the two text
	 * controls
	 */
	private void enableDisableButton() {
//		boolean valid = portValid;
//		boolean valid2 = playerCountValid;
//		this.btnSave.setDisable(!(valid || valid2));
	}

	@FXML
	protected void handleSendClicked(ActionEvent event) throws Exception {

	//	model.sendMessage(this.txtChatMessage.getText());

	}

	@FXML
	protected void handleConnectClicked(ActionEvent event) throws Exception {
		get_MvcCtrl().connectToServer();
	}


}
