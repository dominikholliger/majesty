package leberkaes.gameClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class dummyFXMLControllerGameBoard {
	private static GameClient_Controller _GMvcCtrl;
	
		public static GameClient_Controller get_GMvcCtrl() {
			return _GMvcCtrl;
		}
		@SuppressWarnings("static-access")
		public void set_GMvcCtrl(GameClient_Controller _GMvcCtrl) {
			this._GMvcCtrl = _GMvcCtrl;
		}
		
		/*@FXML protected void handleBackClicked(ActionEvent event) throws Exception {
			get_MvcCtrl().goToHome();
		}*/


	

}
