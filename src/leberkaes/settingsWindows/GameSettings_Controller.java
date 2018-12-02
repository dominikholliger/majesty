package leberkaes.settingsWindows;

import java.util.logging.Logger;

import leberkaes.jat2.ServiceLocator;
import leberkaes.abstractClasses.Controller;
import leberkaes.appClasses.App_Model;
import leberkaes.appClasses.App_View;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GameSettings_Controller extends Controller<GameSettings_Model, GameSettings_View>{
	
	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();
	// Validity checks for the two text fields
	private boolean portValid = false;
	private boolean playerCountValid = false;
	
	@FXML public TextField txtPlayer;
	@FXML public TextField txtPort;
	@FXML public Button btnCancel;
	@FXML public Button btnSave;

	
	
	public GameSettings_Controller(GameSettings_Model model,GameSettings_View view) {
		super(model, view);
		// ChangeListener for the text-property of the port number
		/*txtPort.textProperty().addListener(
				// Parameters of any PropertyChangeListener
				(observable, oldValue, newValue) -> {
					validatePortNumber(newValue,"txtPort");
				});
		
		txtPlayer.textProperty().addListener(
				// Parameters of any PropertyChangeListener
				(observable, oldValue, newValue) -> {
					validatePlayerCountNumber(newValue,"txt");
				});
		
		btnCancel.setOnAction((event) -> {
			view.stop();
		});
		
		btnSave.setOnAction((event) -> {
			config.setLocalOption("Port", view.txtPort.getText());
			config.setLocalOption("PlayerCount", view.txtPlayer.getText());
			view.stop();
		});*/
	}
	/**
	 * Validierung der beiden Settingseingaben (muss jeweils eine gültige Zahl sein,
	 * ansonsten kann nicht gespeichert werden)
	 */
	private void validatePortNumber(String newValue , String obsElement) {
		boolean valid = model.isValidPortNumber(newValue);
		// Change text color
		if (valid) {
			view.txtPort.setStyle("-fx-text-inner-color: green;");
		} else {
			view.txtPort.setStyle("-fx-text-inner-color: red;");
		}
		// Save result
		portValid = valid;
		// Enable or disable button, as appropriate
		enableDisableButton();
	}
	private void validatePlayerCountNumber(String newValue , String obsElement) {
		boolean valid = model.isValidPortNumber(newValue);
		// Change text color
		if (valid) {
			view.txtPlayer.setStyle("-fx-text-inner-color: green;");
		} else {
			view.txtPlayer.setStyle("-fx-text-inner-color: red;");
		}
		// Save result
		playerCountValid = valid;
		// Enable or disable button, as appropriate
		enableDisableButton();
	}
	
	/**
	 * Enable or disable the Connect button, based on the validity of the two
	 * text controls
	 */
	private void enableDisableButton() {
		boolean valid = portValid;
		boolean valid2 = playerCountValid;
		view.btnSave.setDisable(!(valid || valid2));
	}
	
	@FXML private void handleMeepleOption(){
		
	}
	
	@FXML private void handleSplitCardOption(){
		
	}
	
	@FXML private void handleChangetxtPort(){
		
	}
	
	@FXML private void handleStartGameClicked(){
		
	}
	
	
	
	
	
}
