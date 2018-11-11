package leberkaes.gui.EnterGame;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import leberkaes.gui.GameBoard.GameBoardView;
import leberkaes.gui.StartNewGame.StartNewGameView;

public class EnterGameController {
	
	@FXML private javafx.scene.control.Button enterGame;


	@FXML protected void handleEnterGameClicked(ActionEvent event) {
		//TODO was passiert wenn der knopf gedrückt wird 
		// Servername auslesen, Server suchen, verbinden
		// Playername auslesen, speichern und dem GameBoard übergeben
		// GameBoard-Fenster öffnen
		try {
			FXMLLoader fxml = new FXMLLoader(GameBoardView.class.getResource("Gameboard.fxml"));

			Parent parent = (Parent) fxml.load();
			
			Stage newStage = new Stage();
			newStage.setScene(new Scene(parent));
			newStage.show();
			
			Stage currentStage = (Stage) enterGame.getScene().getWindow();
			currentStage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

