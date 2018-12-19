package leberkaes.commonClasses;

import java.io.Serializable;
import java.util.Arrays;

import leberkaes.commonClasses.CardType.type;
import leberkaes.jat2.ServiceLocator;

/**@author Daniel Räber*/
public class Player implements Serializable {

	private String name;
	private int score;
	private int meeple;
	private Location[] locations = new Location[8];
	private String playerGameMessage;
	private boolean bSide;

	public Player(String n, boolean bSide) {
		this.name = n;
		this.score = 0;
		this.meeple = 0;
		this.bSide = bSide;
		this.createLocations(bSide);
	}

	private void createLocations(boolean bSide) {

		locations[0] = new Location(type.GRAIN, bSide);
		locations[1] = new Location(type.BARELL, bSide);
		locations[2] = new Location(type.POTION, bSide);
		locations[3] = new Location(type.SHIELD, bSide);
		locations[4] = new Location(type.SWORD, bSide);
		locations[5] = new Location(type.CUTLERY, bSide);
		locations[6] = new Location(type.KEY, bSide);
		locations[7] = new Location(type.HOSPITAL, bSide);

	}

	/**
	 * die Karte wird in die richtige Location gelegt und die M�nzen werden
	 * ausbezahlt
	 */
	public void makeMove(CharacterCard c, int location) {
		Location l = this.locations[location];
		l.addCard(c);
		int moveCoins = this.getCoins(l);
		this.setScore(moveCoins);
	}

	/** Die M�nzen werden verdient */// TODO
	public int getCoins(Location l) {
		int cards;
		int coinEffect;
		int coinSum = 0;
		type locationType = l.getType();
		this.playerGameMessage = "";
		for (int i = 0; i < 7; i++) {
			cards = this.locations[i].getCardCount();
			type t = this.locations[i].getType();
			coinEffect = l.getCoinEffect(t);
			coinSum += cards * coinEffect;
			ServiceLocator.getServiceLocator().getLogger()
					.info(this.getName() + " erh�lt " + cards * coinEffect + " f�r " + cards + " " + t + "-Karten");
			if (cards * coinEffect > 0) {
				this.setPlayerGameMessage(" + " + cards * coinEffect + " coins for " + cards + " x " + t + "-Card");
			}

		}

		/**
		 * Spezialf�lle f�r die B-Seite werden in diesem if-Statement bearbeitet
		 */
		if (bSide) {

			int coins = 0;
			switch (locationType) {
			case BARELL:
				// F�r mindestens eine Karte in der Taverne sowie im Schloss
				// erh�lt der Spieler 10 M�nzen
				this.playerGameMessage = ("Group effect " + locationType + ": ");
				if ((!this.getLocations()[5].getCharacters().isEmpty())
						&& (!this.getLocations()[6].getCharacters().isEmpty())) {
					coins = 10;

					ServiceLocator.getServiceLocator().getLogger()
							.info(this.getName() + " erh�lt " + coins + " f�r mindestens eine Cutlery- und Key-Karte");
					this.setPlayerGameMessage(" + " + coins + " coins for at least one card in Cutlery & Key");
				}
				coinSum += 10;
				break;

			case CUTLERY:
				// Pro Karte in der Taverne x H�chste Anzahl Karten in einem
				// Geb�ude erh�lt der Spieler 2 M�nzen
				coins = 2;
				int cutleryCardCount = this.getLocations()[5].getCardCount();
				int maxCardCount = 0;
				int tmpCardCount = 0;
				for (int i = 0; i < 8; i++) {
					tmpCardCount = this.getLocations()[i].getCardCount();
					if (tmpCardCount > maxCardCount) {
						maxCardCount = tmpCardCount;
					}
				}

				coinSum += maxCardCount * coins * cutleryCardCount;
				this.setPlayerGameMessage(" + " + coinSum + " coins for highest card count: " + maxCardCount + " and "
						+ cutleryCardCount + " cutlery cards");
				break;

			case KEY:
				// Pro Karte im Schloss sowie im Lazarett erh�lt der Spieler 4
				// M�nzen
				coins = 4;
				coinSum += (this.getLocations()[6].getCardCount() * coins);
				coinSum += (this.getLocations()[7].getCardCount() * coins);
				this.setPlayerGameMessage(" + " + coinSum + " coins for Key and Hospital cards ");
				break;

			default:
				break;
			}
		}
		return coinSum;

	}

	/** Sucht die erste Karte von Links und legt diese ins Lazarett */
	public void killCard() {

		boolean cardFound = false;
		int i = 0;
		while (!cardFound) {

			if (!locations[i].getCharacters().isEmpty()) {
				CharacterCard c = locations[i].removeCard();
				this.locations[7].addCard(c);
				cardFound = true;
			}
			if (i == 6) {
				cardFound = true;
			}
			i++;
		}
	}

	/**
	 * Die Obeste Karte im Lazarett wird wiederbelebt und an Ihre urspr�nliche
	 * Position gelegt. Dies ist anders als beim Original Brettspiel, macht aber
	 * Sinn da wir die urspr�ngliche Position kennen. Beim Brettspiel ist dies
	 * nicht immer der Fall.
	 */
	public void reviveCard() {
		//
		if (!this.locations[7].getCharacters().isEmpty()) {
			CharacterCard c = this.locations[7].removeCard();
			int loc = this.getLocation(c);
			this.locations[loc].addCard(c);
		}

	}

	/**
	 * Die Methode pr�ft in welche Location die Karte gelegt wird und gitb die
	 * Position zur�ck
	 */
	public int getLocation(CharacterCard c) {
		int position = 0;
		for (int i = 0; i < locations.length; i++) {
			if ((c.getChoosenCardType() == locations[i].getType())) {
				position = i;
				this.setPlayerGameMessage(" and revived a card ");
			}
		}
		return position;
	}

	public int getDefenseValue() {
		int defense = this.locations[3].getCardCount();
		return defense;
	}

	public int getOffenseValue() {
		int defense = this.locations[4].getCardCount();
		return defense;
	}

	public Location[] getLocations() {
		return locations;
	}

	public void setScore(int coins) {
		this.score += coins;
	}

	public int getScore() {
		return score;
	}

	public int getMeeple() {
		return meeple;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + "\n score=" + score + "\n meeple=" + meeple + "\n defense=" + "\n locations="
				+ Arrays.toString(locations) + "]";
	}

	public String getPlayerGameMessage() {
		return playerGameMessage;
	}

	public void setPlayerGameMessage(String playerGameMessage) {
		this.playerGameMessage += playerGameMessage;
	}

}
