package leberkaes.settingsWindows;

import java.util.logging.Logger;

import org.omg.Messaging.SyncScopeHelper;

import leberkaes.jat2.ServiceLocator;
import leberkaes.abstractClasses.Controller;
import leberkaes.appClasses.App_Model;
import leberkaes.appClasses.App_View;
import leberkaes.commonClasses.Configuration;
import leberkaes.commonClasses.Translator;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;

public class GameSettings_Controller extends Controller<GameSettings_Model, GameSettings_View> {

	ServiceLocator sl = ServiceLocator.getServiceLocator();
	Logger logger = sl.getLogger();
	Translator t = sl.getTranslator();
	Configuration config = sl.getConfiguration();

	// Validity checks for the two text fields
	private boolean portValid = false;
	private boolean playerCountValid = false;

	@FXML public TextField txtPlayerCount;
	@FXML public CheckBox meepleOption;
	@FXML public CheckBox splitCardOption;
	@FXML public CheckBox BSideOption;
	@FXML public TextField txtChatPort;
	@FXML public TextField txtGamePort;
	@FXML public Button btnCancel;
	@FXML public Button btnSave;
	@FXML public RadioButton radioEnglish;
	@FXML public RadioButton radioDeutsch;
	@FXML private Text txtLabelPlayerNumber;
	@FXML private Text txtLabelPort;
	@FXML private Text textGamePort;


	public GameSettings_Controller(GameSettings_Model model, GameSettings_View view) {
		super(model, view);
		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            	System.out.println("Settings Screen geschlossen.");
            	view.stop();
            }
        });
	}

	@FXML public void initialize() {
		
		//Labels setzen mit Translator
		this.txtLabelPlayerNumber.setText(t.getString("settings.lblPlayerCount"));
		this.BSideOption.setText(t.getString("settings.lblBSide"));
		this.txtLabelPort.setText(t.getString("settings.lblChatPort"));
		this.textGamePort.setText(t.getString("settings.lblGamePort"));
		this.BSideOption.setText(t.getString("settings.lblBSide"));
		
		this.radioDeutsch.setText(t.getString("settings.btnGerman"));
		this.radioEnglish.setText(t.getString("settings.btnEnglish"));
		this.btnSave.setText(t.getString("settings.btnSave"));
		
		
		//Werte aus Config einlesen
		try {
			this.txtPlayerCount.setText(config.getOption("PlayerCount"));
			this.txtChatPort.setText(config.getOption("ChatPort"));
			this.txtGamePort.setText(config.getOption("GamePort"));
			if (config.getOption("BSide").equals("true")) {
				this.BSideOption.setSelected(true);
			}
			if(config.getOption("Language").equalsIgnoreCase("de")) {
				this.radioDeutsch.setSelected(true);
				this.radioEnglish.setSelected(false);
			} else {
				this.radioDeutsch.setSelected(false);
				this.radioEnglish.setSelected(true);
			}
		} catch (Exception e) {
			logger.warning("No Configuration Found!!!");;
		}

		//Listener auf Labels erzeugen
		txtGamePort.textProperty().addListener((observable, oldValue, newValue) -> {
			validateGamePortNumber(newValue, "txtChatPort");
		});
		txtChatPort.textProperty().addListener((observable, oldValue, newValue) -> {
			validateChatPortNumber(newValue, "txtGamePort");
		});
		txtPlayerCount.textProperty().addListener((observable, oldValue, newValue) -> {
			validatePlayerCountNumber(newValue, "txtPlayerCount");
		});

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

	@FXML private void handleLanguageEnglish() {
		this.radioDeutsch.setSelected(false);
	}
	@FXML private void handleLanguageGerman() {
		this.radioEnglish.setSelected(false);

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

	@FXML private void handleSaveButtonClicked() {
		System.out.println("Save Button clicked");
		config.setLocalOption("GamePort", this.txtGamePort.getText());
		config.setLocalOption("ChatPort", this.txtChatPort.getText());
		config.setLocalOption("PlayerCount", this.txtPlayerCount.getText());
		if (this.BSideOption.isSelected()) {
			config.setLocalOption("BSide", "true");
		} else {
			config.setLocalOption("BSide", "false");
		}
		if (this.radioDeutsch.isSelected()) {
			config.setLocalOption("Language", "de");
		} else {
			config.setLocalOption("Language", "en");
		}

		// System.out.println(toggle.getSelectedToggle().getUserData());

		view.getAppControlInstance().updateTexts();


		view.stop();
	}

	@FXML
	private void handleCancelButtonClicked() {

		view.stop();
	}


	protected void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		System.out.println(t.getCurrentLocale());
		// update Homescreen Buttons.....
		txtLabelPlayerNumber.setText(t.getString("settings.lblPlayerCount"));
		BSideOption.setText(t.getString("settings.lblBSide"));
		txtLabelPort.setText(t.getString("settings.lblChatPort"));
		textGamePort.setText(t.getString("settings.lblGamePort"));
		radioEnglish.setText(t.getString("settings.btnEnglish"));
		radioDeutsch.setText(t.getString("settings.btnGerman"));
		btnSave.setText(t.getString("settings.btnSave"));

	}

}
