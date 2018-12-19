package leberkaes.gameServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import leberkaes.commonClasses.GameBoard;
import leberkaes.commonClasses.Highscore;

public class Game_ClientObjectCom {
	private Socket socket;
	private String name = "<new>";
	private GameServer_Model model;
	private ObjectInputStream inStream = null;
	private ObjectOutputStream outputStream = null;

	protected Game_ClientObjectCom(GameServer_Model model, Socket socket) {
		this.model = model;
		this.socket = socket;

		// Create thread to read incoming messages
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						inStream = new ObjectInputStream(socket.getInputStream());
						GameBoard gameboard = (GameBoard) inStream.readObject();
						System.out.println("Object received ------ GameBoard -------- = ");
						model.broadcastGameBoard(gameboard);
						
						// Das neue Objekt auf Spielschluss überprüfen
						if(gameboard.isGameEnd()) {
							// Spiel fertig, Highscores schreiben
							for (int i = 0; i < gameboard.getPlayers().size(); i++) {
								String pName = gameboard.getPlayers().get(i).getName();
								int pScore = gameboard.getPlayers().get(i).getScore();
								Highscore.getInstance().writeHighscore(pName, pScore);
								System.out.println("-------------- Highscore written for "+pName+"  ---------------");
							}
						}

					} catch (IOException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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


	public void send(GameBoard gameboard) {
		try {
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Object written ------ GameBoard -------- = ");
			outputStream.writeObject(gameboard);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
