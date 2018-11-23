package leberkaes.appClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class dummyFXMLControllerHighscore {
	private App_Controller _MvcCtrl;
	
	public App_Controller get_MvcCtrl() {
		return _MvcCtrl;
	}
	public void set_MvcCtrl(App_Controller _MvcCtrl) {
		this._MvcCtrl = _MvcCtrl;
	}
	
	@FXML protected void handleBackClicked(ActionEvent event) throws Exception {
		get_MvcCtrl().goToHome();
	}


}
