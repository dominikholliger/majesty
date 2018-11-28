package leberkaes.gameServer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import leberkaes.gameClient.GameClient_Controller;

public class dummyFXMLControllerNewGame {

	
		private GameServer_Controller _NMvcCtrl;

		public GameServer_Controller get_NMvcCtrl() {
			return _NMvcCtrl;
		}
		
	
		public void set_NMvcCtrl(GameServer_Controller _NMvcCtrl) {
			this._NMvcCtrl = _NMvcCtrl;
		}

		@FXML protected void handleStartGameClicked(ActionEvent event) throws Exception {
			// get_NMvcCtrl().goToGameBoard();
		}
		
		@FXML protected void handleTwoPlayersClicked(ActionEvent event) throws Exception {
			// TODO
		}
		
		@FXML protected void handleFourPlayersClicked(ActionEvent event) throws Exception {
			// TODO
		}
	}

	


