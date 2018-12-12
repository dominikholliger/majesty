package leberkaes.settingsWindows;

import java.util.logging.Logger;

import org.omg.Messaging.SyncScopeHelper;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class GameSettings_Controller extends Controller<GameSettings_Model, GameSettings_View> {

	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();

	// Validity checks for the two text fields
	private boolean portValid = false;
	private boolean playerCountValid = false;

	@FXML
	public TextField txtPlayerCount;

	@FXML
	public CheckBox meepleOption;
	@FXML
	public CheckBox splitCardOption;
	@FXML
	public CheckBox BSideOption;

	@FXML
	public TextField txtChatPort;
	@FXML
	public TextField txtGamePort;

	@FXML
	public Button btnCancel;
	@FXML
	public Button btnSave;
	
	@FXML
	public ToggleButton toggleEnglish;
	@FXML
	public ToggleButton toggleDeutsch;
	

	

	public GameSettings_Controller(GameSettings_Model model, GameSettings_View view) {
		super(model, view);

		/*
		 * // ChangeListener for the text-property of the port number
		 * txtChatPort.textProperty().addListener( // Parameters of any
		 * PropertyChangeListener (observable, oldValue, newValue) -> {
		 * validatePortNumber(newValue,"txtChatPort"); });
		 */

		/*
		 * txtPlayer.textProperty().addListener( // Parameters of any
		 * PropertyChangeListener (observable, oldValue, newValue) -> {
		 * validatePlayerCountNumber(newValue,"txt"); });
		 * 
		 * 
		 * 
		 * btnCancel.setOnAction((event) -> { view.stop(); });
		 * 
		 * 
		 * } /** Validierung der beiden Settingseingaben (muss jeweils eine gültige
		 * Zahl sein, ansonsten kann nicht gespeichert werden)
		 */
	}

	@FXML
	public void initialize() {

		try {
			this.txtPlayerCount.setText(config.getOption("PlayerCount"));
			this.txtChatPort.setText(config.getOption("ChatPort"));
			this.txtGamePort.setText(config.getOption("GamePort"));

			txtGamePort.textProperty().addListener((observable, oldValue, newValue) -> {
				validateGamePortNumber(newValue, "txtChatPort");
			});
			txtChatPort.textProperty().addListener((observable, oldValue, newValue) -> {
				validateChatPortNumber(newValue, "txtGamePort");
			});

			txtPlayerCount.textProperty().addListener((observable, oldValue, newValue) -> {
				validatePlayerCountNumber(newValue, "txtPlayerCount");
			});

			if (config.getOption("Meeple").equals("true")) {
				this.meepleOption.setSelected(true);
			}
			
			if (config.getOption("BSide").equals("true")) {
				this.BSideOption.setSelected(true);
			}
		
			ToggleGroup toggle = new ToggleGroup();
			this.toggleDeutsch.setToggleGroup(toggle);
			this.toggleEnglish.setToggleGroup(toggle);
			
			if (config.getOption("Language").equals("de")) {
				this.toggleDeutsch.setSelected(true);
			}
			if (config.getOption("Language").equals("en")) {
				this.toggleEnglish.setSelected(true);
			}
			
		} catch (Exception e) {
			logger.warning("a configuration entry was not found!");;
		}

	}

	private void validateGamePortNumber(String newValue, String obsElement) {
		boolean valid = model.isValidPortNumber(newValue);
		// Change text color
		if (valid) {
			this.txtGamePort.setStyle("-fx-text-inner-color: green;");
		} else {
			this.txtGamePort.setStyle("-fx-text-inner-color: red;");
		}
		// Save result
		portValid = valid;
		// Enable or disable button, as appropriate
		enableDisableButton();
	}

	private void validateChatPortNumber(String newValue, String obsElement) {
		boolean valid = model.isValidPortNumber(newValue);
		// Change text color
		if (valid) {
			this.txtChatPort.setStyle("-fx-text-inner-color: green;");
		} else {
			this.txtChatPort.setStyle("-fx-text-inner-color: red;");
		}
		// Save result
		portValid = valid;
		// Enable or disable button, as appropriate
		enableDisableButton();
	}

	private void validatePlayerCountNumber(String newValue, String obsElement) {
		boolean valid = model.isValidPlayerCountNumber(newValue);

		// Change text color
		if (valid) {
			this.txtPlayerCount.setStyle("-fx-text-inner-color: green;");
		} else {
			this.txtPlayerCount.setStyle("-fx-text-inner-color: red;");
		}
		// Save result
		playerCountValid = valid;
		// Enable or disable button, as appropriate
		enableDisableButton();
	}

	/**
	 * Enable or disable the Connect button, based on the validity of the two text
	 * controls
	 */
	private void enableDisableButton() {
		boolean valid = portValid;
		boolean valid2 = playerCountValid;
		this.btnSave.setDisable(!(valid || valid2));
	}

	@FXML
	private void handleMeepleOption() {

	}

	@FXML
	private void handleSplitCardOption() {

	}

	@FXML
	private void handleChangetxtPort() {

	}

	@FXML
	private void handleSaveButtonClicked() {
		config.setLocalOption("GamePort", this.txtGamePort.getText());
		config.setLocalOption("ChatPort", this.txtChatPort.getText());
		config.setLocalOption("PlayerCount", this.txtPlayerCount.getText());
		if (this.meepleOption.isSelected()) {
			config.setLocalOption("Meeple", "true");
		} else {
			config.setLocalOption("Meeple", "false");
		}
		if (this.BSideOption.isSelected()) {
			config.setLocalOption("BSide", "true");
		} else {
			config.setLocalOption("BSide", "false");
		}
		if (this.toggleDeutsch.isSelected()) {
			config.setLocalOption("Language", "de");
		}
		if (this.toggleEnglish.isSelected()) {
			config.setLocalOption("Language", "en");
		}
		view.stop();
	}
	@FXML
	private void handleCancelButtonClicked() {

		view.stop();
	}

}
