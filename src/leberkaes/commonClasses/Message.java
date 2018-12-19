package leberkaes.commonClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Logger;

public abstract class Message {
	private static Logger logger = Logger.getLogger("");
	protected MessageType type;
	public Message(MessageType type) {
		this.type = type;
	}
	public MessageType getType() {
		return type;
	}
	public void send(Socket socket) {
		OutputStreamWriter out;
		try {
			out = new OutputStreamWriter(socket.getOutputStream());
			//ok, die game msgs nicht auf die konsole printen...
			if(this.type == MessageType.Game) {
				logger.info("Sending GameBoard Object as GameMsg");
			} else {
				logger.info("Sending message: " + this.toString());
			}
			out.write(this.toString() + "\n");
			out.flush();
		} catch (IOException e) {
			logger.warning(e.toString());
		}
	}

	public static Message receive(Socket socket) throws Exception{
		BufferedReader in;
		Message msg = null;
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msgText = in.readLine(); // Will wait here for complete line
			// Parse message
			String[] parts = msgText.split("\\|");
			if (parts[0].equals(MessageType.Join.toString())) {
				logger.info("Receiving message: " + msgText);
				msg = new JoinMsg(parts[1]);
			} else if (parts[0].equals(MessageType.Chat.toString())) {
				logger.info("Receiving message: " + msgText);
				msg = new ChatMsg(parts[1], parts[2]);
			} else if (parts[0].equals(MessageType.Game.toString())) {
				 // Ein GameMsg Obj bauen
				msg = new GameMsg(parts[1]);
				GameMsg tmpGameMsg = new GameMsg(parts[1]);
				tmpGameMsg.fillContentWithString(parts[2].getBytes());
				GameBoard tmpBoard = tmpGameMsg.extractContent();
				System.out.println(tmpGameMsg.toString());
				System.out.println(tmpBoard.toString());
			}
		return msg;
	}


}
