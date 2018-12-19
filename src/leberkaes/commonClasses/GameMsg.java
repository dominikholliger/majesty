package leberkaes.commonClasses;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** --NOT USED-- */
public class GameMsg extends Message {

	private String name;
	private byte[] content;

	public GameMsg(String name) {
		super(MessageType.Game);
		this.name = name;
	}

	public void fillContentWithObject(GameBoard gameBoard) {
		this.content = serializeGameBoard(gameBoard);
	}

	public void fillContentWithString(byte[] serializedgameBoard) {
		this.content = serializedgameBoard;
	}

	public GameBoard extractContent() {
		return unserializeGameBoard();
	}

	private byte[] serializeGameBoard(GameBoard tmpBoard) {
		// Serialize GameBoard Object
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream so = new ObjectOutputStream(bo);
			so.writeObject(tmpBoard);
			so.flush();
			return bo.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private GameBoard unserializeGameBoard() {
		try {
			byte b[] = this.content;
			ByteArrayInputStream bi = new ByteArrayInputStream(b);
			ObjectInputStream si = new ObjectInputStream(bi);
			GameBoard gameBoard = (GameBoard) si.readObject();
			System.out.println(gameBoard.toString());
			return gameBoard;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public byte[] getContent() {
		return content;
	}

	@Override
	public String toString() {
		return type.toString() + '|' + name + '|' + content;
	}

}
