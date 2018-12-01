package leberkaes.settingsWindows;

import java.util.logging.Logger;

import leberkaes.jat2.ServiceLocator;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class GameSettings_Controller {
	final private GameSettings_Model model;
	final private GameSettings_View view;

	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();
	
	// Validity checks for the two text fields
	private boolean portValid = false;

	public GameSettings_Controller(GameSettings_Model model,
			GameSettings_View view) {
		this.model = model;
		this.view = view;

		// ChangeListener for the text-property of the port number
		view.txtPort.textProperty().addListener(
				// Parameters of any PropertyChangeListener
				(observable, oldValue, newValue) -> {
					validatePortNumber(newValue);
				});
		
		view.btnCancel.setOnAction((event) -> {
			view.stop();
		});
		
		view.btnSave.setOnAction((event) -> {
			config.setLocalOption("Port", view.txtPort.getText());
			view.stop();
		});
	}


	/**
	 * Must be an integer 1-65535
	 */
	private void validatePortNumber(String newValue) {
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
	
	/**
	 * Enable or disable the Connect button, based on the validity of the two
	 * text controls
	 */
	private void enableDisableButton() {
		boolean valid = portValid;
		view.btnSave.setDisable(!valid);
	}
}
