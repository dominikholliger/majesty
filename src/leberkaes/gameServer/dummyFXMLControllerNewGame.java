package leberkaes.gameServer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import leberkaes.gameClient.GameClient_Controller;

public class dummyFXMLControllerNewGame {

	
		private GameServer_Controller _NMvcCtrl;

		public GameClient_Controller get_NMvcCtrl() {
			return _NMvcCtrl;
		}
		
	
		public void set_NMvcCtrl(GameServer_Controller _NMvcCtrl) {
			this._NMvcCtrl = _NMvcCtrl;
		}

		@FXML protected void handleStartGameClicked(ActionEvent event) throws Exception {
			get_NMvcCtrl().goToGameBoard();
		}
	}

	


