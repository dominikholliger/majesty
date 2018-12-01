package leberkaes.gameClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class dummyFXMLControllerEnterGame {
	private static GameClient_Controller _GMvcCtrl;

	public GameClient_Controller get_GMvcCtrl() {
		
		return _GMvcCtrl;
	}
	
	@SuppressWarnings("static-access")
	public void set_GMvcCtrl(GameClient_Controller _GMvcCtrl) {
		this._GMvcCtrl = _GMvcCtrl;
	}

	@FXML protected void handleEnterGameClicked(ActionEvent event) throws Exception {
		get_GMvcCtrl().goToGameBoard();
	}
	
	/*@FXML protected void handleBackClicked(ActionEvent event) throws Exception {
	get_MvcCtrl().goToHome();
	}*/
	
}
