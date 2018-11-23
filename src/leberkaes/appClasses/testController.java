package leberkaes.appClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class testController {

	private App_Controller _MvcCtrl;
	
	public App_Controller get_MvcCtrl() {
		return _MvcCtrl;
	}


	public void set_MvcCtrl(App_Controller _MvcCtrl) {
		this._MvcCtrl = _MvcCtrl;
	}


	@FXML protected void handleNewGameButtonClicked(ActionEvent event) throws Exception {
		get_MvcCtrl().Juhu("BBBB");
		//StartNewGameView newgame = new StartNewGameView();
		//Stage s = new Stage();
		//newgame.start(s);
	}
	
	
	@FXML protected void handleSettingsButtonClicked(ActionEvent event) throws Exception{
		//TODO was passiert wenn der knopf gedrueckt wird 
		//settings-Fenster oeffnet sich, nichts uebergeben
		//ShowSettingsView settings = new ShowSettingsView();
		//Stage s = new Stage();
		//settings.start(s);
		
	}
	@FXML protected void handleHighscoreButtonClicked(ActionEvent event) throws Exception{
		// Highscore-Fenster oeffnet sich, nichts wird uebergeben
		//ShowHighscoresView highscores = new ShowHighscoresView();
		//Stage s = new Stage();
		//highscores.start(s);
		
	}
	@FXML protected void handleEnterGameButtonclicked(ActionEvent event) throws Exception{
		//EnterGameView entergame = new EnterGameView();
		//Stage s = new Stage();
		//entergame.start(s);
		
	}	
	
	public String Hallo() {
		return "Welt";
	}
	
}
