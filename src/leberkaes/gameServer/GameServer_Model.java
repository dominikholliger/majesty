package leberkaes.gameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import leberkaes.commonClasses.ChatMsg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameServer_Model {
	protected final ObservableList<Game_Client> clients = FXCollections.observableArrayList();

	private final Logger logger = Logger.getLogger("");
	private ServerSocket listener;
	private volatile boolean stop = false;
	private int listSize;


	public void startServer(int port, int playerCount) {
		logger.info("Start server");
		try {
			listener = new ServerSocket(port, 10, null);
			Runnable r = new Runnable() {
				@Override
				public void run() {
					while (!stop) {
						try {
							// Auf neue Verbindungen warten und ein enstsprechendes Objekt in der Liste anlegen
							Socket socket = listener.accept();
							Game_Client client = new Game_Client(GameServer_Model.this, socket);
							clients.add(client);
							listSize = clients.size();
							// Maximale Anzahl Spieler erreicht?
							if(listSize == playerCount){
								// Starten des eigentlichen Spiels
								System.out.println("Das Spiel wird gestartet");
							}
						} catch (Exception e) {
							logger.info(e.toString());
						}
					}
				}
			};
			Thread t = new Thread(r, "ServerSocket");
			t.start();
		} catch (IOException e) {
			logger.info(e.toString());
		}
	}

	public void stopServer() {
		logger.info("Stop all clients");
		for (Game_Client c : clients) c.stop();
		logger.info("Stop server");
		stop = true;
		if (listener != null) {
			try {
				listener.close();
			} catch (IOException e) {
				// Uninteresting
			}
		}
	}

	public void broadcast(ChatMsg outMsg) {
		logger.info("Broadcasting message to clients");
		for (Game_Client c : clients) {
			c.send(outMsg);
		}
	}
}
