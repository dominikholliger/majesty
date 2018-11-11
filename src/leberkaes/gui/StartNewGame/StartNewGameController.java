package leberkaes.gui.StartNewGame;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import leberkaes.gui.GameBoard.GameBoardView;

public class StartNewGameController {

	@FXML private javafx.scene.control.Button startGame;

	
	@FXML protected void handleTwoPlayersClicked(ActionEvent event) {
		//TODO was passiert wenn der knopf gedrückt wird
		// set Variable twoPlayers = true
	}
	@FXML protected void handleFourPlayersClicked(ActionEvent event) {
		//TODO was passiert wenn der knopf gedrückt wird 
		// set Variable twoPlayers = false
		// momentan: clickable = false
		// TODO If FourPlayerMode possible, disable Button togglefour = false/delete in fxml-file
	}
	@FXML protected void handleStartGameClicked(ActionEvent event) {
		try {
			FXMLLoader fxml = new FXMLLoader(GameBoardView.class.getResource("GameBoard.fxml"));

			Parent parent = (Parent) fxml.load();
			
			Stage newStage = new Stage();
			newStage.setScene(new Scene(parent));
			newStage.show();
			
			Stage currentStage = (Stage) startGame.getScene().getWindow();
			currentStage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
