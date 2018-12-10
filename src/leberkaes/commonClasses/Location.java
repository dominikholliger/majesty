package leberkaes.commonClasses;

import static leberkaes.commonClasses.CardType.type.BARELL;
import static leberkaes.commonClasses.CardType.type.CUTLERY;
import static leberkaes.commonClasses.CardType.type.GRAIN;
import static leberkaes.commonClasses.CardType.type.KEY;
import static leberkaes.commonClasses.CardType.type.POTION;
import static leberkaes.commonClasses.CardType.type.SHIELD;
import static leberkaes.commonClasses.CardType.type.SWORD;

import java.io.Serializable;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import leberkaes.commonClasses.CardType.*;

public class Location implements Serializable {

	private type type;
	private Stack<CharacterCard> characters;
	private HashMap<type, Integer> coinEffects = new HashMap<type, Integer>();
	private int finalCoinEffect; // noch implementieren

	public void setFinalCoinEffect(int finalCoinEffect) {
		this.finalCoinEffect = finalCoinEffect;
	}

	public Location(type t) {
		this.type = t;
		this.characters = new Stack<CharacterCard>();
		this.addCardEffects(t);

	}

	// Inizialisierung der Karten Effekte auf eigene Karten
	public void addCardEffects(type t) {

		// A-Side
		switch (t) {
		case GRAIN:
			coinEffects.put(GRAIN, 2);
			this.setFinalCoinEffect(10);

			break;
		case BARELL:
			coinEffects.put(BARELL, 2);
			this.setFinalCoinEffect(11);

			// Auswirkung auf andere
			break;

		case POTION:
			coinEffects.put(GRAIN, 2);
			coinEffects.put(BARELL, 2);
			coinEffects.put(POTION, 2);

			this.setFinalCoinEffect(12);
			break;

		case SHIELD:
			coinEffects.put(SHIELD, 2);
			coinEffects.put(SWORD, 2);
			coinEffects.put(CUTLERY, 2);
			this.setFinalCoinEffect(13);

			break;

		case SWORD:
			coinEffects.put(SWORD, 3);
			this.setFinalCoinEffect(14);

			break;
		case CUTLERY:
			coinEffects.put(CUTLERY, 4);
			this.setFinalCoinEffect(15);

			break;
		case KEY:
			coinEffects.put(KEY, 5);
			this.setFinalCoinEffect(16);

			// plus meeple
			break;
		case HOSPITAL:
			this.setFinalCoinEffect(-1);

			break;

		}
	}

	public int getCardCount() {

		int i = this.characters.size();
		return i;

	}

	public int getCoinEffect(type t) {
		int coins;

		if (this.coinEffects.containsKey(t)) {
			coins = this.coinEffects.get(t);

		} else {
			coins = 0;

		}
		return coins;
	}

	public Stack<CharacterCard> getCharacters() {
		return characters;
	}

	public HashMap<type, Integer> getCoinEffects() {
		return coinEffects;
	}

	public void setCoinEffects(HashMap<type, Integer> coinEffects) {
		this.coinEffects = coinEffects;
	}

	public type getType() {
		return type;
	}

	public void setType(type type) {
		this.type = type;
	}

	public void addCard(CharacterCard c) {
		this.characters.push(c);
	}

	public CharacterCard removeCard() {
		return this.characters.pop();

	}

	@Override
	public String toString() {
		return "Location [type=" + type + ", building=" + characters + "]";
	}

	public int getFinalCoinEffect() {
		return finalCoinEffect;
	}

}