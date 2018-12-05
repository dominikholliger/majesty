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
import leberkaes.commonClasses.CardType.*;

public class Location implements Serializable{

	public type type;
	public Deque<CharacterCard> characters;
	public HashMap<type, Integer> coinEffects = new HashMap<type, Integer>();

	

	public Location(type t) {
		this.type = t;
		this.characters = new LinkedList<CharacterCard>();
		this.addCardEffects(t);

	}
	
	//Inizialisierung der Karten Effekte auf eigene Karten
	public void addCardEffects(type t) {

		// A-Side
		switch (t) {
		case GRAIN:
			coinEffects.put(GRAIN, 2);
			break;
		case BARELL:
			coinEffects.put(BARELL, 2);
			// Auswirkung auf andere
			break;

		case POTION:
			coinEffects.put(GRAIN, 2);
			coinEffects.put(BARELL, 2);
			coinEffects.put(POTION, 2);
			break;

		case SHIELD:
			coinEffects.put(SHIELD, 2);
			coinEffects.put(SWORD, 2);
			coinEffects.put(CUTLERY, 2);

			break;

		case SWORD:
			coinEffects.put(SWORD, 3);

			break;
		case CUTLERY:
			coinEffects.put(CUTLERY, 4);

			// Auswirkung auf andere
			break;
		case KEY:
			coinEffects.put(KEY, 5);
			// plus meeple
			break;
		case HOSPITAL:
			// pro Karte minus 1
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
			coins	= this.coinEffects.get(t);
			System.out.println("Hat EFFEKT");
		}else {
			coins = 0;
			System.out.println("Hat KEIN EFFEKT");
		}
		return coins;
	}


	public type getType() {
		return type;
	}

	public void setType(type type) {
		this.type = type;
	}

	public void addCard(CharacterCard c) {
		this.characters.add(c);
	}

	@Override
	public String toString() {
		return "Location [type=" + type + ", building=" + characters + "]";
	}





}