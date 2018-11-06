package leberkaes.gameServer;

import javafx.collections.ListChangeListener;

public class Controller {
	private GameServer_Model model;
	private GameServer_View view;

	public Controller(GameServer_Model model, GameServer_View view) {
		this.model = model;
		this.view = view;

		view.btnStart.setOnAction(event -> {
			view.btnStart.setDisable(true);
			int port = Integer.parseInt(view.txtPort.getText());
			model.startServer(port);
		});

		view.stage.setOnCloseRequest(event -> model.stopServer());

		model.clients.addListener((ListChangeListener) (event -> view.updateClients()));
	}
}
