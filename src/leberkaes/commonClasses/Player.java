package leberkaes.commonClasses;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import leberkaes.commonClasses.*;
import leberkaes.commonClasses.CardType.type;

public class Player implements Serializable{

	public String name;
	public boolean active; // Wars

	public int score;
	public int meeple;
	public int defense;
	public int offense;

	public Location[] locations = new Location[8];

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

	public void makeMove(CharacterCard c, int location) {
		
		//this.locations[location].addCard(c);

		Location l = this.locations[location];
		l.addCard(c);
		this.score += this.getCoins(l);
		System.out.println("Geld bekommen");
		
	}

	public void killCard(CharacterCard c) {

	}

	public CharacterCard reviveCard() {
		return null;

	}

	public int getCoins(Location l) {
		int cards;
		int coinEffect;
		int coinSum = 0;
		
		
		
		for (int i = 0; i < 6; i++) {
			System.out.println(this.locations[i]);
			cards = this.locations[i].getCardCount();
			System.out.println(cards);
			
			type t = this.locations[i].getType();
			coinEffect = l.getEffect(t);
			System.out.println(coinEffect);
			
			coinSum += cards * coinEffect;
			System.out.println(coinSum);
			
			
			
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

	@Override
	public String toString() {
		return "Player [name=" + name + ", active=" + active + ", score=" + score + ", meeple=" + meeple + ", defense="
				+ defense + ", offense=" + offense + ", locations=" + Arrays.toString(locations) + "]";
	}

}
