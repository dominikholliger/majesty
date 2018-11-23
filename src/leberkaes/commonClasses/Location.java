package leberkaes.commonClasses;

import static leberkaes.commonClasses.CardType.type.BARELL;
import static leberkaes.commonClasses.CardType.type.CUTLERY;
import static leberkaes.commonClasses.CardType.type.GRAIN;
import static leberkaes.commonClasses.CardType.type.KEY;
import static leberkaes.commonClasses.CardType.type.POTION;
import static leberkaes.commonClasses.CardType.type.SHIELD;
import static leberkaes.commonClasses.CardType.type.SWORD;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import leberkaes.commonClasses.CardType.*;

public class Location {

	public type type;
	public Deque<CharacterCard> characters;
	public HashMap<type, Integer> effects = new HashMap<type, Integer>();

	

	public int getEffect(type t) {
		int coins;
			
		if (this.effects.containsKey(t)) {
			coins	= this.effects.get(t);
			System.out.println("Hat EFFEKT");
		}else {
			coins = 0;
			System.out.println("Hat KEIN EFFEKT");
		}
		return coins;
	}

	public Location(type t) {
		this.type = t;
		this.characters = new LinkedList<CharacterCard>();
		this.addCardEffects(t);

	}
	public void addCardEffects(type t) {

		// A-Side
		switch (t) {
		case GRAIN:
			effects.put(GRAIN, 2);
			break;
		case BARELL:
			effects.put(BARELL, 2);
			// Auswirkung auf andere
			break;

		case POTION:
			effects.put(GRAIN, 2);
			effects.put(BARELL, 2);
			effects.put(POTION, 2);
			break;

		case SHIELD:
			effects.put(SHIELD, 2);
			effects.put(SWORD, 2);
			effects.put(CUTLERY, 2);

			break;

		case SWORD:
			effects.put(SWORD, 3);

			break;
		case CUTLERY:
			effects.put(CUTLERY, 4);

			// Auswirkung auf andere
			break;
		case KEY:
			effects.put(KEY, 5);
			// plus meeple
			break;
		case HOSPITAL:
			// pro Karte minus 1
			break;

		}
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

	public int getCardCount() {
		
		int i = this.characters.size();
		return i;

	}



}