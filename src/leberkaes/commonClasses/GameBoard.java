package leberkaes.commonClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import leberkaes.commonClasses.CharacterCard;
import leberkaes.commonClasses.CardType.*;

public class GameBoard implements java.io.Serializable  {
	// Spieler
	private static int activePlayerIndex;
	private int playerCount;
	private ArrayList<Player> players;

	// Karten
	private Stack<CharacterCard> greenCards = new Stack<CharacterCard>();
	private Stack<CharacterCard> redCards = new Stack<CharacterCard>();
	private Stack<CharacterCard> deck = new Stack<CharacterCard>();
	private CharacterCard[] openDeck = new CharacterCard[6];

	// Dies ist eine TestMain-Methode
	public static void main(String[] args) {
		

		GameBoard g = new GameBoard(2);
		g.addPlayer("Dani");
		g.addPlayer("Sebi");

		g.setActivePLayer();

		System.out.println(g.players.get(GameBoard.activePlayerIndex));
		System.out.println(g.toString());
		
		
		CharacterCard c = g.takeCard(2);

		System.out.println(c);
		
		for (int i = 0; i<3; i ++) {
		g.playCard(c, 0);
		System.out.println(c);
		System.out.println(g.players.get(GameBoard.activePlayerIndex));

		g.nextPlayer();
		System.out.println(g.players.get(GameBoard.activePlayerIndex));
		c = g.takeCard(4);
		System.out.println(c);
		g.playCard(c, 2);
		}
		
		System.out.println();
		System.out.println(g.players.get(0));
		System.out.println(g.players.get(1));

		// System.out.println(g.toString());

	}
	// __________________________________________

	
	
	public GameBoard(int numOfP) {
		
		this.playerCount = numOfP;
		this.players = new ArrayList<Player>();

		createCards(back.GREEN, type.GRAIN, null, 7);
		createCards(back.GREEN, type.BARELL, null, 4);
		createCards(back.GREEN, type.KEY,null, 3);
		createCards(back.GREEN, type.SHIELD,null, 3);
		createCards(back.GREEN, type.POTION, null,3);
		createCards(back.GREEN, type.CUTLERY,null, 2);
		createCards(back.GREEN, type.SWORD,null, 2);

		createCards(back.RED, type.GRAIN,null, 2);
		createCards(back.RED, type.BARELL,null, 2);
		createCards(back.RED, type.KEY,null, 2);
		createCards(back.RED, type.SHIELD,null, 2);
		createCards(back.RED, type.POTION,null, 2);
		createCards(back.RED, type.CUTLERY,null, 2);
		createCards(back.RED, type.SWORD,null, 1);

		// TODO createSplitCards();

		createDeck();

	}

	public void addPlayer(String name) {

		players.add(new Player(name));

	}

	/** Diese Methode erzeugt die Spiel-Karten-Objekte **/
	public void createCards(back b, type t1, type t2, int count) {
		
		if (b == back.GREEN) {
			for (int i = 1; i <= count; i++) {
				CharacterCard c = new CharacterCard(t1,null, b);
				this.greenCards.push(c);
			}
		}

		if (b == back.RED) {
			for (int i = 1; i <= count; i++) {
				CharacterCard c = new CharacterCard(t1,null, b);
				this.redCards.push(c);
			}
		}
	}

	/** Diese Methode erzeugt aus den Karten das Deck mit welchem gespielt wird **/
	public void createDeck() {

		deck = new Stack<CharacterCard>();

		int players = 2; //Muss noch variabel werden

		Collections.shuffle(greenCards);
		Collections.shuffle(redCards);

		switch (players) {
		case 2: {

			/*
			 * 2Spieler Nimm nur die obersten 6 Karten vom gruenen Stapel, leg sie in der
			 * Tischmitte aus und packe dann den restlichen gruenen Stapel ungesehen zurueck
			 * in die Schachtel.
			 */

			for (int i = 0; i < 6; i++) {
				this.openDeck[i] = greenCards.pop();
			}

			greenCards.clear();
			deck.addAll(redCards);
			break;
		}

		case 3: {
			/*
			 * 3Spieler Gibt die ersten 19 Karten ungesehen zur�ck und lege dann die ersten
			 * 6 Karten wie in unserem Beispiel in der Tischmitte aus.
			 */

			break;

		}
		case 4: {
			/*
			 * Gib die ersten 7 Karten ungesehen zur�ck in die Schachtel. Dann lege die
			 * ersten 6 Karten wie in unserem Beispiel in der Tischmitte aus.
			 */
			for (int i = 0; i <= 6; i++) {
				greenCards.pop();
			}

			for (int i = 0; i <= 5; i++) {
				this.openDeck[i] = greenCards.pop();
			}
			deck.addAll(redCards);
			deck.addAll(greenCards);
			break;
		}

		}

	}

	public int getActivePlayer() {
		return activePlayerIndex;
	}

	public void setActivePLayer() {
		GameBoard.activePlayerIndex = 0;
	}

	public void nextPlayer() {

		if (activePlayerIndex + 1 - players.size() == 0) {
			activePlayerIndex = 0;
		} else {
			activePlayerIndex++;
		}

	}
	
	
	
	//1. Zugbewegung: Karte vom Opendeck entfernen
	public CharacterCard takeCard(int i) {
		CharacterCard c = openDeck[i];
		this.updateOpenDeck(i);
		return c;

	}
	
	//2. Zugbewegung: Karte in Location legen
	/** Player herausfinden, Kartentypen pr�fen? Karten Position bestimmmen? **/
	public void playCard(CharacterCard c, int location) {
		
		this.players.get(this.activePlayerIndex).makeMove(c, location);
		
	}

	public void updateOpenDeck(int i) {

		for (int o = i; o > 0; o--) {
			this.openDeck[o] = this.openDeck[o - 1];
		}

		this.openDeck[0] = deck.pop();

	}

	public CharacterCard[] getOpenDeck() {
		return openDeck;
		
	}

	@Override
	public String toString() {
		return "Players:" + players + "GameBoard\n" + "GreenCards=\n" + greenCards + "\n" + "RedCards=\n" + redCards
				+ "\n" + "Deck=\n" + deck + "\n" + "openDeck=\n" + Arrays.toString(openDeck);
	}

}
