package leberkaes.commonClasses;

import java.io.Serializable;
import java.util.Arrays;
import leberkaes.commonClasses.CardType.type;
import leberkaes.jat2.ServiceLocator;

/** Daniel Räber */
public class Player implements Serializable {

	private String name;
	private int score;
	private int meeple;
	private Location[] locations = new Location[8];
	private String playerGameMessage;

	public Player(String n, boolean bSide) {
		this.name = n;
		this.score = 0;
		this.meeple = 0;
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
	 * die Karte wird in die richtige Location gelegt und die Münzen werden
	 * ausbezahlt
	 */
	public void makeMove(CharacterCard c, int location) {

		Location l = this.locations[location];
		l.addCard(c);
		int moveCoins = this.getCoins(l);
		this.setScore(moveCoins);
	}

	/** Die Münzen werden verdient */// TODO
	public int getCoins(Location l) {
		int cards;
		int coinEffect;
		int coinSum = 0;
		this.playerGameMessage = "";
		for (int i = 0; i < 7; i++) {
			cards = this.locations[i].getCardCount();
			type t = this.locations[i].getType();
			coinEffect = l.getCoinEffect(t);
			coinSum += cards * coinEffect;
			ServiceLocator.getServiceLocator().getLogger()
					.info(this.getName() + " erhält " + cards * coinEffect + " für " + cards + " " + t + "-Karten");
			if (cards *coinEffect > 0) {
				this.setPlayerGameMessage(" + " + cards * coinEffect + " coins for " + cards + " x " + t + "-Card");
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
	 * Die Obeste Karte im Lazarett wird wiederbelebt und an Ihre ursprünliche
	 * Position gelegt. Dies ist anders als beim Original Brettspiel, macht aber
	 * Sinn da wir die ursprüngliche Position kennen. Beim Brettspiel ist dies
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
	 * Die Methode prüft in welche Location die Karte gelegt wird und gitb die
	 * Position zurück
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
