package leberkaes.gameClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Logger;

import leberkaes.commonClasses.ChatMsg;
import leberkaes.commonClasses.GameBoard;
import leberkaes.commonClasses.GameMsg;
import leberkaes.commonClasses.JoinMsg;
import leberkaes.commonClasses.Message;
import leberkaes.commonClasses.MessageType;
import javafx.beans.property.SimpleStringProperty;

/**
 * Note: One error in this implementation: The *display* of received messages is triggered
 * by a ChangeListener attached to the SimpleStringProperty. If the newly received message
 * is *identical* to the current contents of the SimpleStringProperty, then there is no
 * change, and the new (duplicate) message is not displayed.
 */
public class GameClient_Model {
	protected SimpleStringProperty newestMessage = new SimpleStringProperty();

	private Logger logger = Logger.getLogger("");
	private Socket socket;
	private Socket socketObjectCom;
	private ObjectInputStream inStream = null;
	private String name;

	public void connect(String ipAddress, int Port, String name) {
		logger.info("Connect");
		this.name = name;
		try {
			socket = new Socket(ipAddress, Port);
			// Create thread to read incoming messages
			Runnable r = new Runnable() {
				@Override
				public void run() {
					while (true) {
							Message msgTemp = Message.receive(socket);
							if(msgTemp.getType() == MessageType.Game) {
								logger.info("GameMsg angekommen");
								/**
								GameMsg msg = (GameMsg) msgTemp;
								GameBoard gameBoard = msg.extractContent();
								// Aus Spass kotzen wir das mal auf die Konsole
								System.out.println(gameBoard.toString());
								**/
							} else if (msgTemp.getType() == MessageType.Chat) {
								ChatMsg msg = (ChatMsg) msgTemp;
								newestMessage.set(""); // erase previous message
								newestMessage.set(msg.getName() + ": " + msg.getContent());
							}
						}
					}
				};
			Thread t = new Thread(r);
			t.start();
			// Send join message to the server
			Message msg = new JoinMsg(name);
			msg.send(socket);
		} catch (Exception e) {
			logger.warning(e.toString());
		}
	}
	
	// Eine zweite Verbindung für die Objektübertragung
	public void connectObjectCom(String ipAddress, int Port, String name) {
		logger.info("Connect to ObjectCom Server");
		this.name = name;
		try {
			socketObjectCom = new Socket(ipAddress, Port);
			// Create thread to read incoming messages
			Runnable r = new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							inStream = new ObjectInputStream(socketObjectCom.getInputStream());
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
		} catch (Exception e) {
			logger.warning(e.toString());
		}
	}
	
	

	public void disconnect() {
		logger.info("Disconnect");
		if (socket != null)
			try {
				socket.close();
			} catch (IOException e) {
				// Uninteresting
			}
	}

	
	
	
	public void sendMessage(String message) {
		logger.info("Send message");
		Message msg = new ChatMsg(name, message);
		msg.send(socket);
	}

	public String receiveMessage() {
		logger.info("Receive message");
		return newestMessage.get();
	}
}
