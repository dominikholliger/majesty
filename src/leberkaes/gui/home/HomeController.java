package leberkaes.gui.home;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.stage.Stage;
import leberkaes.gui.EnterGame.EnterGameView;
import leberkaes.gui.ShowSettings.ShowSettingsView;
import leberkaes.gui.StartNewGame.StartNewGameView;
import leberkaes.gui.showHighscores.ShowHighscoresView;



public class HomeController {
	
	@FXML protected void handleNewGameButtonClicked(ActionEvent event) throws Exception {
		StartNewGameView newgame = new StartNewGameView();
		Stage s = new Stage();
		newgame.start(s);
	}
	
	
	@FXML protected void handleSettingsButtonClicked(ActionEvent event) throws Exception{
		//TODO was passiert wenn der knopf gedrueckt wird 
		//settings-Fenster oeffnet sich, nichts uebergeben
		ShowSettingsView settings = new ShowSettingsView();
		Stage s = new Stage();
		settings.start(s);
		
	}
	@FXML protected void handleHighscoreButtonClicked(ActionEvent event) throws Exception{
		// Highscore-Fenster oeffnet sich, nichts wird uebergeben
		ShowHighscoresView highscores = new ShowHighscoresView();
		Stage s = new Stage();
		highscores.start(s);
		
	}
	@FXML protected void handleEnterGameButtonclicked(ActionEvent event) throws Exception{
		EnterGameView entergame = new EnterGameView();
		Stage s = new Stage();
		entergame.start(s);
		
	}
}
