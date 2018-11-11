package leberkaes.gui.showHighscores;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import leberkaes.gui.home.HomeView;

public class ShowHighscoresController {
//TODO
	@FXML private javafx.scene.control.Button back;


	@FXML protected void handleBackClicked(ActionEvent event) {
		
		try {
			FXMLLoader fxml = new FXMLLoader(HomeView.class.getResource("Home.fxml"));

			Parent parent = (Parent) fxml.load();
			
			Stage newStage = new Stage();
			newStage.setScene(new Scene(parent));
			newStage.show();
			
			Stage currentStage = (Stage) back.getScene().getWindow();
			currentStage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
