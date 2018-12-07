package leberkaes.commonClasses;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import com.sun.media.jfxmedia.logging.Logger;

import leberkaes.commonClasses.*;
import leberkaes.commonClasses.CardType.type;
import leberkaes.jat2.ServiceLocator;
import leberkaes.jat2.ServiceLocator;

public class Player implements Serializable {
	

	
	private String name;
	private boolean active; // Brauchts das?

	private int score;
	private int meeple;
	private int offense;

	private Location[] locations = new Location[8];

	public Player(String n) {
		this.name = n;
		this.active = false;
		this.score = 0;
		this.meeple = 0;

		this.createLocations();

	}

	private void createLocations() {

		locations[0] = new Location(type.GRAIN);
		locations[1] = new Location(type.BARELL);
		locations[2] = new Location(type.POTION);
		locations[3] = new Location(type.SHIELD);
		locations[4] = new Location(type.SWORD);
		locations[5] = new Location(type.CUTLERY);
		locations[6] = new Location(type.KEY);
		locations[7] = new Location(type.HOSPITAL);

	}

	// 3.Zugbewegung: Ev redundandt?
	public void makeMove(CharacterCard c, int location) {

		type t = c.getCardType1();

		Location l = this.locations[location];
		l.addCard(c);
		this.score += this.getCoins(l);

	}

	public int getDefenseValue() {
		int defense = this.locations[3].getCardCount();
		return defense;
	}

	public int getOffenseValue() {
		int defense = this.locations[4].getCardCount();
		return defense;
	}

	public void killCard() {

		boolean cardFound = false;
		int i = 0;
		while (!cardFound) {
			
			if (!locations[i].getCharacters().isEmpty()) {
				CharacterCard c = locations[i].removeCard();
				this.locations[7].addCard(c);
				cardFound = true;
			}
			if(i==6){
				cardFound = true;
				
			}
			i++;
		}

	}

	public void reviveCard() {

		// revive card if available and put in correct location
		if (!this.locations[7].getCharacters().isEmpty()) {
			CharacterCard c = this.locations[7].removeCard();
			int loc = this.getLocation(c);
			this.locations[loc].addCard(c);
		}

	}

	public int getCoins(Location l) {
		int cards;
		int coinEffect;
		int coinSum = 0;

		for (int i = 0; i < 6; i++) {
			System.out.println(this.locations[i]);
			cards = this.locations[i].getCardCount();

			type t = this.locations[i].getType();
			coinEffect = l.getCoinEffect(t);

			coinSum += cards * coinEffect;

		}
		return coinSum;

	}

	/** Split-Card Handling fehlt **/
	public int getLocation(CharacterCard c) {
		int position = 0;
		for (int i = 0; i < locations.length; i++) {
			if ((c.getCardType1() == locations[i].getType()) || (c.getCardType2() == locations[i].getType())) {
				position = i;
			}

		}
		return position;

	}

	public Location[] getLocations() {
		return locations;
	}
	
	public void setScore(int coins) {
		this.score += coins;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + "\nactive=" + active + "\n score=" + score + "\n meeple=" + meeple
				+ "\n defense=" + "\n offense=" + offense + "\n locations=" + Arrays.toString(locations) + "]";
	}

}
