package leberkaes.gameServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import leberkaes.commonClasses.CharacterCard;
import leberkaes.commonClasses.CardType.*;

public class GameBoard {
	//Spieler
	private static int activePlayerIndex;
	private ArrayList<Player> players;
	private Player activePlayer;
	
	//Karten
	private Stack<CharacterCard> greenCards = new Stack<CharacterCard>();
	private Stack<CharacterCard> redCards = new Stack<CharacterCard>();
	private Stack<CharacterCard> deck = new Stack<CharacterCard>();
	private CharacterCard[] openDeck = new CharacterCard[6];
	
	
	//Dies ist eine TestMain-Methode
	public static void main(String[] args) {

		GameBoard g = new GameBoard(4);
		g.addPlayer("Dani");
		g.addPlayer("Sebi");
		g.addPlayer("Dodi");
		
		g.setActivePLayer();
		
		System.out.println(g.players.get(g.activePlayerIndex));
		CharacterCard c = g.takeCard(5);
		System.out.println(c);
		g.playCard(c);
		System.out.println(c);
		System.out.println(g.players.get(g.activePlayerIndex));
		
		g.nextPlayer();
		System.out.println(g.players.get(g.activePlayerIndex));
		c = g.takeCard(4);
		System.out.println(c);
		g.playCard(c);
		System.out.println(c);
		
		
		System.out.println(g.players.get(0));
		System.out.println(g.players.get(1));
		System.out.println(g.players.get(2));
		
		
		
	//	System.out.println(g.toString());
		
		
		

		
		
	}
	//__________________________________________
	
	public GameBoard(int numOfP) {
		
		
		this.players = new ArrayList<Player>();

		
		
		createStandardCards(back.GREEN, type.GRAIN, 7);
		createStandardCards(back.GREEN, type.BARELL, 4);
		createStandardCards(back.GREEN, type.KEY, 3);
		createStandardCards(back.GREEN, type.SHIELD, 3);
		createStandardCards(back.GREEN, type.POTION, 3);
		createStandardCards(back.GREEN, type.CUTLERY, 2);
		createStandardCards(back.GREEN, type.SWORD, 2);

		createStandardCards(back.RED, type.GRAIN, 2);
		createStandardCards(back.RED, type.BARELL, 2);
		createStandardCards(back.RED, type.KEY, 2);
		createStandardCards(back.RED, type.SHIELD, 2);
		createStandardCards(back.RED, type.POTION, 2);
		createStandardCards(back.RED, type.CUTLERY, 2);
		createStandardCards(back.RED, type.SWORD, 1);

		// TODO createSplitCards();

		createDeck();
		
	}

	public void addPlayer(String name) {
		
			players.add(new Player(name));
			
	}
	/** Diese Methode erzeugt die Spiel-Karten **/
	public void createStandardCards(back b, type t, int count) {

		if (b == back.GREEN) {
			for (int i = 1; i <= count; i++) {
				CharacterCard c = new CharacterCard(t, b);
				this.greenCards.push(c);
			}
		}

		if (b == back.RED) {
			for (int i = 1; i <= count; i++) {
				CharacterCard c = new CharacterCard(t, b);
				this.redCards.push(c);
			}
		}
	}

	/** Diese Methode erzeugt aus den Karten das Deck mit welchem gespielt wird **/
	public void createDeck() {

		deck = new Stack<CharacterCard>();

		int players = 2;

		Collections.shuffle(greenCards);
		Collections.shuffle(redCards);

		switch (players) {
		case 2: {

			/*
			 * 2Spieler Nimm nur die obersten 6 Karten vom grünen Stapel, leg sie in der
			 * Tischmitte aus und packe dann den restlichen grünen Stapel ungesehen zurück
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
			 * 3Spieler Gibt die ersten 19 Karten ungesehen zurück und lege dann die ersten
			 * 6 Karten wie in unserem Beispiel in der Tischmitte aus.
			 */

			break;

		}
		case 4: {
			/*
			 * Gib die ersten 7 Karten ungesehen zurück in die Schachtel. Dann lege die
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
		GameBoard.activePlayerIndex=0;
	}

	public void nextPlayer() {
		
		if (activePlayerIndex+1-players.size()==0) {
			activePlayerIndex= 0;
		}
		else {
			activePlayerIndex++;
		}
	
	}

	
	
	public CharacterCard takeCard(int i) {

		CharacterCard c = openDeck[i];
		this.updateOpenDeck(i);
		return c;

	}
	
	/** Player herausfinden, Kartentypen prüfen? Karten P0sition bestimmmen?**/
	public void playCard(CharacterCard c) {
		
		int i= players.get(this.activePlayerIndex).getValidLocations(c);
		players.get(this.activePlayerIndex).addCard(c, i);
	
		
		
		
		
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
		return "Players:"+ players+"GameBoard\n" + "GreenCards=\n" + greenCards + "\n" + "RedCards=\n" + redCards + "\n" + "Deck=\n" + deck
				+ "\n" + "openDeck=\n" + Arrays.toString(openDeck);
	}

}
