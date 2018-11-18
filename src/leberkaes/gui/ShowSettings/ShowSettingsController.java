package leberkaes.gui.ShowSettings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import leberkaes.gui.home.HomeView;

public class ShowSettingsController {
//TODO
	@FXML private javafx.scene.control.Button back;

	
	@FXML protected void handleBackClicked(ActionEvent event) throws Exception{
		Stage stage = (Stage) back.getScene().getWindow();
		stage.close();
		HomeView home = new HomeView();
		Stage s = new Stage();
		home.start(s);
		
	}
	
}
