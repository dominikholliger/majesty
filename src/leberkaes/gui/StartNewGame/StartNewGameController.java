package leberkaes.gui.StartNewGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import leberkaes.gui.GameBoard.GameBoardView;

public class StartNewGameController {
	
	@FXML private javafx.scene.control.Button startGame;

	@FXML protected void handleTwoPlayersClicked(ActionEvent event) {
		//TODO was passiert wenn der knopf gedrueckt wird
		// set Variable twoPlayers = true
	}
	@FXML protected void handleFourPlayersClicked(ActionEvent event) {
		//TODO was passiert wenn der knopf gedrueckt wird 
		// set Variable twoPlayers = false
		// momentan: clickable = false
		// TODO If FourPlayerMode possible, disable Button togglefour = false/delete in fxml-file
	}
	@FXML protected void handleStartGameClicked(ActionEvent event) throws Exception{
			//TODO was passiert wenn der knopf gedrueckt wird 
			// Playername auslesen, speichern und dem GameBoard uebergeben
			// Variablen für Meeples, Splitcards, a-/b-side auslesen, speichern und dem GameBoard uebergeben
		
		Stage stage = (Stage) startGame.getScene().getWindow();
		stage.close();
			
			// GameBoard-Fenster �ffnen
		
				GameBoardView gameboard = new GameBoardView();
				Stage s = new Stage();
				gameboard.start(s);
			}
	
	
}
