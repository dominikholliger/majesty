package leberkaes.commonClasses;

import java.util.Deque;
import java.util.LinkedList;

import leberkaes.commonClasses.CardType.type;

public class Location {

	public type type;
	public Deque<CharacterCard> characters;

	public Location(type t) {
		this.type = t;
		this.characters = new LinkedList<CharacterCard>();

	}

	public type getType() {
		return type;
	}

	public void setType(type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Location [type=" + type + ", building=" + characters + "]";
	}
	
	
}