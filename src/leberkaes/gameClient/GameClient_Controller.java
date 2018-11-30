package leberkaes.gameClient;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import leberkaes.abstractClasses.Controller;

public class GameClient_Controller extends Controller<GameClient_Model, GameClient_View> {
	
	@FXML TextField textinputipserver;
	@FXML TextField textinputPlayername;
	
	
	public GameClient_Controller(GameClient_Model model, GameClient_View view) {
		super(model, view);

		view.getStage().setOnCloseRequest( event -> model.disconnect() );
		
	}


	@FXML
	public void handleEnterGameClicked() {
		
		
		String ipAddress = textinputipserver.getText();
		System.out.println(ipAddress);
		int port = 8087; //Integer.parseInt(view.txtPort.getText());
		String name = textinputPlayername.getText();
		model.connect(ipAddress, port, name);
	}

	 
		
		/*view.btnSend.setOnAction( event -> model.sendMessage(view.txtChatMessage.getText()));
		
		model.newestMessage.addListener( (o, oldValue, newValue) -> {
			if (!newValue.isEmpty()) // Ignore empty messages
				view.txtChatArea.appendText(newValue + "\n");
		} );
		*/
	}


	

