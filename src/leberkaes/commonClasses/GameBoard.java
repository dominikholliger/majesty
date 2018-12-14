package leberkaes.commonClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.logging.Logger;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import leberkaes.commonClasses.CharacterCard;
import leberkaes.commonClasses.CardType.*;
import leberkaes.jat2.ServiceLocator;

public class GameBoard implements java.io.Serializable {
	// Spieler
	private static int activePlayerIndex;
	private int playerCount;
	private ArrayList<Player> players;

	// Spiel
	private int roundCount;
	private boolean gameEnd;
	private boolean bSide = false;
	private boolean meeple = false;

	// Karten
	private Stack<CharacterCard> greenCards = new Stack<CharacterCard>();
	private Stack<CharacterCard> redCards = new Stack<CharacterCard>();
	private Stack<CharacterCard> deck = new Stack<CharacterCard>();
	private CharacterCard[] openDeck = new CharacterCard[6];

	public GameBoard(int numOfP) {

		this.playerCount = numOfP;
		this.players = new ArrayList<Player>();

		// ____________________
		// debug Karten zum Testen

		// _________________________
		// Standard Karten
		createCards(back.GREEN, type.GRAIN, null, 7);
		createCards(back.GREEN, type.BARELL, null, 4);
		createCards(back.GREEN, type.KEY, null, 3);
		createCards(back.GREEN, type.SHIELD, null, 3);
		createCards(back.GREEN, type.POTION, null, 3);
		createCards(back.GREEN, type.CUTLERY, null, 2);
		createCards(back.GREEN, type.SWORD, null, 2);

		createCards(back.RED, type.GRAIN, null, 2);
		createCards(back.RED, type.BARELL, null, 2);
		createCards(back.RED, type.KEY, null, 2);
		createCards(back.RED, type.SHIELD, null, 2);
		createCards(back.RED, type.POTION, null, 2);
		createCards(back.RED, type.CUTLERY, null, 2);
		createCards(back.RED, type.SWORD, null, 1);

		// Split-Karten
		createCards(back.GREEN, type.POTION, type.SHIELD, 1);
		createCards(back.GREEN, type.POTION, type.BARELL, 1);
		createCards(back.GREEN, type.GRAIN, type.BARELL, 2);
		createCards(back.GREEN, type.SWORD, type.GRAIN, 1);
		createCards(back.GREEN, type.SWORD, type.CUTLERY, 1);
		createCards(back.GREEN, type.KEY, type.SHIELD, 1);
		createCards(back.GREEN, type.KEY, type.CUTLERY, 1);
		createCards(back.GREEN, type.CUTLERY, type.SHIELD, 1);

		createCards(back.RED, type.POTION, type.SHIELD, 2);
		createCards(back.RED, type.POTION, type.BARELL, 2);
		createCards(back.RED, type.POTION, type.KEY, 1);
		createCards(back.RED, type.POTION, type.CUTLERY, 1);
		createCards(back.RED, type.GRAIN, type.BARELL, 1);
		createCards(back.RED, type.SWORD, type.SHIELD, 2);
		createCards(back.RED, type.SWORD, type.BARELL, 1);
		createCards(back.RED, type.SWORD, type.GRAIN, 1);
		createCards(back.RED, type.SWORD, type.CUTLERY, 2);
		createCards(back.RED, type.KEY, type.CUTLERY, 1);

		createDeck();
		this.gameEnd = false;
		this.roundCount = 1;

	}

	/** Diese Methode erzeugt die Spiel-Karten-Objekte **/
	public void createCards(back b, type t1, type t2, int count) {

		if (b == back.GREEN) {
			for (int i = 1; i <= count; i++) {
				CharacterCard c = new CharacterCard(t1, t2, b);
				this.greenCards.push(c);
			}
		}

		if (b == back.RED) {
			for (int i = 1; i <= count; i++) {
				CharacterCard c = new CharacterCard(t1, t2, b);
				this.redCards.push(c);
			}
		}
	}

	/**
	 * Diese Methode erzeugt aus den Karten das Deck mit welchem gespielt wird
	 **/
	public void createDeck() {

		deck = new Stack<CharacterCard>();
		Collections.shuffle(greenCards);
		Collections.shuffle(redCards);

		switch (playerCount) {
		case 2: {
			/**
			 * 2Spieler Nimm nur die obersten 6 Karten vom gruenen Stapel, leg
			 * sie in der Tischmitte aus und packe dann den restlichen gruenen
			 * Stapel ungesehen zurueck in die Schachtel.
			 */
			for (int i = 0; i < 6; i++) {
				this.openDeck[i] = greenCards.pop();
			}
			greenCards.clear();
			deck.addAll(redCards);
			break;
		}

		case 3: {
			/**
			 * 3Spieler Gibt die ersten 19 Karten ungesehen zurï¿½ck und lege
			 * dann die ersten 6 Karten wie in unserem Beispiel in der
			 * Tischmitte aus.
			 */
			for (int i = 0; i <= 18; i++) {
				greenCards.pop();
			}
			for (int i = 0; i <= 5; i++) {
				this.openDeck[i] = greenCards.pop();
			}
			deck.addAll(redCards);
			deck.addAll(greenCards);
			break;
		}
		case 4: {
			/**
			 * Gib die ersten 7 Karten ungesehen zurï¿½ck in die Schachtel. Dann
			 * lege die ersten 6 Karten wie in unserem Beispiel in der
			 * Tischmitte aus.
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

	/**
	 * 1. Zugbewegung: Entfernt die Karte vom Opendeck und aktualisiert dieses.
	 */
	public CharacterCard takeCard(int i) {
		CharacterCard c = openDeck[i];
		this.updateOpenDeck(i);
		return c;

	}

	/** Die offen liefenden aufschliessen und eine neue Karte ziehen. */
	public void updateOpenDeck(int i) {
		for (int o = i; o > 0; o--) {
			this.openDeck[o] = this.openDeck[o - 1];
		}

		if (!this.deck.isEmpty()) {
			this.openDeck[0] = deck.pop();
		}
	}

	/**
	 * 2. Zugbewegung: Die Karte wird gespielt und löst allfällige
	 * Sonderaktionen aus
	 **/
	public void playCard(CharacterCard c, int location) {

		// Kartentyp der Ziel-Location abfragen
		type type = this.getActivePlayer().getLocations()[location].getType();

		// Sonderaktion der Ziel-Location ausfuehren
		switch (type) {
		case SWORD:
			this.attack();
			break;
		case POTION:
			this.heal();
			break;
		default:
			break;
		}

		// Karte in Location legen
		this.getActivePlayer().makeMove(c, location);

		// Auswirkungen auf Andere Spieler;
		this.otherPlayerEffect(type);

	}

	/** Effekte auf andere Spieler ausführen */
	public void otherPlayerEffect(type t) {
		// aktuell nur A-Seite
		if (bSide) {
			// TODO bSide effects
		} else {
			int coins = 0;
			switch (t) {
			case BARELL:
				coins = 2;
				for (Player p : players) {
					if (!p.getLocations()[0].getCharacters().isEmpty()) {
						p.setScore(coins);
						ServiceLocator.getServiceLocator().getLogger()
								.info("Gruppeneffekt" + p.getName() + " erhählt " + coins + " Münzen");
					}
				}
			case CUTLERY:
				coins = 3;
				for (Player p : players) {
					if (!p.getLocations()[1].getCharacters().isEmpty()) {
						p.setScore(coins);
						ServiceLocator.getServiceLocator().getLogger()
								.info("Gruppeneffekt" + p.getName() + " erhählt " + coins + " Münzen");
					}
				}
			default:
				break;
			}
		}
	}

	/** Sonderaktionen Angriff ausfühern */
	public void attack() {
		int offense = this.getActivePlayer().getOffenseValue() + 1;
		// Plus 1 da Karte noch nicht gelegt wurde

		for (Player p : players) {
			if (!(players.indexOf(p) == this.getActivePlayerIndex())) {
				int defense = p.getDefenseValue();
				if (offense > defense) {
					p.killCard();
					ServiceLocator.getServiceLocator().getLogger().info(p.getName() + " was attacked");

				}
			}
		}
	}

	/** Sonderaktionen Heilung ausfühern */
	public void heal() {

		this.getActivePlayer().reviveCard();

	}

	/**
	 * Diese Methode wird am Ende des Spiels ausgeführt und vergiebt die Letzten
	 * Punkte
	 */
	private void finishGame() {
		this.gameEnd = true;

		// 1. Punkteabzug fï¿½r jede Karte im Lazarett

		if (bSide) {
			for (Player p : players) {
				int count = p.getLocations()[7].getCardCount();
				int effect = -2;
				p.setScore(count * effect);
				ServiceLocator.getServiceLocator().getLogger()
						.info("Punkteabzug Lazarett: " + p.getName() + " verliehrt " + (count * effect) + " Münzen");
			}
		} else {
			for (Player p : players) {
				int count = p.getLocations()[7].getCardCount();
				int effect = -1;
				p.setScore(count * effect);
				ServiceLocator.getServiceLocator().getLogger()
						.info("Punkteabzug Lazarett: " + p.getName() + " verliehrt " + (count * effect) + " Münzen");
			}
		}

		// 2. Punkte fÃ¼r unterschiedliche Personen

		for (Player p : players) {
			int diffCards = 0;

			for (int i = 0; i < 7; i++) {

				if (!p.getLocations()[i].getCharacters().isEmpty()) {
					diffCards++;
				}
			}
			int diffScore = diffCards * diffCards;
			p.setScore(diffScore);
			ServiceLocator.getServiceLocator().getLogger()
					.info(p.getName() + " erhält " + diffScore + " Münzen für " + diffCards + " verschidene Karten");

		}

		// 3. Mehrheitenwertung HÃ¶chste Anzahl Karten aus allen Locations
		// finden

		for (int i = 0; i < 8; i++) {

			int tempHighScore = 0;
			int tempPlayerScore = 0;
			for (Player p : players) {
				tempPlayerScore = p.getLocations()[i].getCardCount();
				if (tempHighScore < tempPlayerScore) {
					tempHighScore = tempPlayerScore;
				}
			}
			for (Player p : players) {
				tempPlayerScore = p.getLocations()[i].getCardCount();
				if (tempHighScore == tempPlayerScore) {
					int finalCoinEffect = p.getLocations()[i].getFinalCoinEffect();
					p.setScore(finalCoinEffect);
					ServiceLocator.getServiceLocator().getLogger()
							.info(p.getName() + " hat die Höchste anzahl Karten im" + p.getLocations()[i].getType()
									+ " und erhält " + finalCoinEffect);
				}
			}
		}
	}

	/** Spieler wird dem GameBoard hinzugefügt */
	public void addPlayer(String name) {
		players.add(new Player(name, bSide));
	}

	/** Der nächste Spieler in der Liste wird als aktiver Spieler eingetragen */
	public void setNextPlayerIndex() {
		if (activePlayerIndex + 1 - players.size() == 0) {
			activePlayerIndex = 0;
			this.nextRound();
		} else {
			activePlayerIndex++;
		}
	}

	/** Wenn alle spieler an der reihe wahren, beginnt eine neue Runde */
	public void nextRound() {
		if (roundCount == 12) {
			this.finishGame();
		} else {
			this.roundCount++;
		}
	}

	public int getActivePlayerIndex() {
		return activePlayerIndex;
	}

	public void setActivePlayerIndex() {
		GameBoard.activePlayerIndex = 0;
	}

	public Player getActivePlayer() {
		return this.players.get(GameBoard.activePlayerIndex);
	}

	public Stack<CharacterCard> getDeck() {
		return deck;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public boolean isbSide() {
		return bSide;
	}

	public boolean isMeeple() {
		return meeple;
	}

	public boolean isGameEnd() {
		return gameEnd;
	}

	public int getRoundCount() {
		return this.roundCount;
	}

	public CharacterCard[] getOpenDeck() {
		return openDeck;
	}

	@Override
	public String toString() {
		return "GameBoard [playerCount=" + playerCount + ", players=" + players + ", roundCount=" + roundCount
				+ ", gameEnd=" + gameEnd + ", bSide=" + bSide + ", meeple=" + meeple + ", greenCards=" + greenCards
				+ ", redCards=" + redCards + ", deck=" + deck + ", openDeck=" + Arrays.toString(openDeck) + "]";
	}

}
