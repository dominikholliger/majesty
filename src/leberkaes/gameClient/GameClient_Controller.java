package leberkaes.gameClient;

import leberkaes.abstractClasses.Controller;

public class GameClient_Controller extends Controller<GameClient_Model, GameClient_View>{
	
	
	public GameClient_Controller(GameClient_Model model, GameClient_View view) {
		super(model, view);
		
		/*view.btnConnect.setOnAction( event -> {
			view.btnConnect.setDisable(true);
			String ipAddress = view.txtIpAddress.getText();
			int port = Integer.parseInt(view.txtPort.getText());
			String name = view.txtName.getText();
			model.connect(ipAddress, port, name);
			// Connect for Object Com
			model.connectObjectCom(ipAddress, 8083, name);
		});
		
		view.stage.setOnCloseRequest( event -> model.disconnect() );
		
		view.btnSend.setOnAction( event -> model.sendMessage(view.txtChatMessage.getText()));
		
		model.newestMessage.addListener( (o, oldValue, newValue) -> {
			if (!newValue.isEmpty()) // Ignore empty messages
				view.txtChatArea.appendText(newValue + "\n");
		} );*/
	}
}
