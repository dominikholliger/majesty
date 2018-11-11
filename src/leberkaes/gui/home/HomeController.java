package leberkaes.gui.home;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import leberkaes.gui.EnterGame.EnterGameView;
import leberkaes.gui.ShowSettings.ShowSettingsView;
import leberkaes.gui.StartNewGame.StartNewGameView;
import leberkaes.gui.showHighscores.ShowHighscoresView;
import leberkaes.jat2.JavaFX_App_Template;



public class HomeController {
	@FXML private javafx.scene.control.Button newGame;
	
	@FXML protected void handleNewGameButtonClicked(ActionEvent event) {
		try {
			FXMLLoader fxml = new FXMLLoader(StartNewGameView.class.getResource("StartNewGame.fxml"));

			Parent parent = (Parent) fxml.load();
			
			Stage newStage = new Stage();
			newStage.setScene(new Scene(parent));
			newStage.show();
			
			Stage currentStage = (Stage) newGame.getScene().getWindow();
			currentStage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 	
	
	
	@FXML protected void handleSettingsButtonClicked(ActionEvent event) {
		//TODO was passiert wenn der knopf gedr�ckt wird 
		//settings-Fenster �ffnet sich, nichts �bergeben
		try {
			FXMLLoader fxml = new FXMLLoader(ShowSettingsView.class.getResource("Settings.fxml"));

			Parent parent = (Parent) fxml.load();
			
			Stage newStage = new Stage();
			newStage.setScene(new Scene(parent));
			newStage.show();
			
			Stage currentStage = (Stage) newGame.getScene().getWindow();
			currentStage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML protected void handleHighscoreButtonClicked(ActionEvent event) {
		//TODO was passiert wenn der knopf gedr�ckt wird 
		// Highscore-Fenster �ffnet sich, nichts wird �bergeben
		try {
			FXMLLoader fxml = new FXMLLoader(ShowHighscoresView.class.getResource("Highscore.fxml"));

			Parent parent = (Parent) fxml.load();
			
			Stage newStage = new Stage();
			newStage.setScene(new Scene(parent));
			newStage.show();
			
			Stage currentStage = (Stage) newGame.getScene().getWindow();
			currentStage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML protected void handleEnterGameButtonclicked(ActionEvent event) {
		//TODO was passiert wenn der knopf gedr�ckt wird 
		try {
			FXMLLoader fxml = new FXMLLoader(EnterGameView.class.getResource("EnterGame.fxml"));

			Parent parent = (Parent) fxml.load();
			
			Stage newStage = new Stage();
			newStage.setScene(new Scene(parent));
			newStage.show();
			
			Stage currentStage = (Stage) newGame.getScene().getWindow();
			currentStage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
