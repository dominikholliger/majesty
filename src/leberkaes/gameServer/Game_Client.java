package leberkaes.gameServer;

import java.io.IOException;
import java.net.Socket;

import leberkaes.commonClasses.ChatMsg;
import leberkaes.commonClasses.GameMsg;
import leberkaes.commonClasses.JoinMsg;
import leberkaes.commonClasses.Message;

public class Game_Client {
	private Socket socket;
	private String name = "<new>";
	private GameServer_Model model;

	protected Game_Client(GameServer_Model model, Socket socket) {
		this.model = model;
		this.socket = socket;

		// Create thread to read incoming messages
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while(true) {
					Message msg;
					try {
						msg = Message.receive(socket);
						if (msg instanceof ChatMsg) {
							model.broadcast((ChatMsg) msg);
						} else if (msg instanceof JoinMsg) {
							Game_Client.this.name = ((JoinMsg) msg).getName();
						} else if (msg instanceof GameMsg) {
							// Dies muss angepasst werden !!!!!!!!!
							model.broadcast(new ChatMsg(name,"Test: Spielbrett empfangen"));
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						//break;
					}

				}
			}
		};
		Thread t = new Thread(r);
		t.start();
	}




	public Socket getSocket() {
		return socket;
	}



	public String getName() {
		return name;
	}


	// Überlagerte Methode
	public void send(ChatMsg msg) {
		msg.send(socket);
	}
	public void send(GameMsg msg) {
		msg.send(socket);
	}
	public void stop() {
		try {
			socket.close();
		} catch (IOException e) {
			// Uninteresting
		}
	}

	@Override
	public String toString() {
		return name + ": " + socket.toString();
	}
}
