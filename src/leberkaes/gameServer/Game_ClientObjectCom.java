package leberkaes.gameServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import leberkaes.commonClasses.GameBoard;
import leberkaes.commonClasses.Message;

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
						System.out.println("Object received ------ GameBoard -------- = " + gameboard);
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
			System.out.println("Object written ------ GameBoard -------- = " + gameboard);
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
