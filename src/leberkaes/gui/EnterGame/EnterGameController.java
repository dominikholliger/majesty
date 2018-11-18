package leberkaes.gui.EnterGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import leberkaes.gui.GameBoard.GameBoardView;

public class EnterGameController {
	
	@FXML private javafx.scene.control.Button enterGame;

	@FXML protected void handleEnterGameClicked(ActionEvent event) throws Exception{
		//TODO was passiert wenn der knopf gedrueckt wird 
		// Servername auslesen, Server suchen, verbinden
		// Playername auslesen, speichern und dem GameBoard uebergeben
		
		Stage stage = (Stage) enterGame.getScene().getWindow();
		stage.close();
		
		// GameBoard-Fenster oeffnen
	
			GameBoardView gameboard = new GameBoardView();
			Stage s = new Stage();
			gameboard.start(s);
		}
	

}

