package leberkaes.commonClasses;

import java.io.Serializable;

import leberkaes.commonClasses.CardType.*;

public class CharacterCard implements Serializable{
	
	private type cardType1;
	private type cardType2;
	private back cardBack;
	
	private boolean alive;

	public CharacterCard(type t, back b) {

	
		this.cardType1 = t;
		this.cardBack = b;
		this.alive = true;

	}

	public CharacterCard(type t1, type t2, back b) {
		this.cardType1 = t1;
		this.cardType2 = t2;
		this.cardBack = b;
		this.alive = true;
	}
	
	
	

	
	public type getCardType1() {
		return cardType1;
	}

	public void setCardType1(type cardType1) {
		this.cardType1 = cardType1;
	}

	public type getCardType2() {
		return cardType2;
	}

	public void setCardType2(type cardType2) {
		this.cardType2 = cardType2;
	}

	@Override
	public String toString() {
		return "CharacterCard cardType1=" + cardType1 + " cardType2=" + cardType2 + " cardBack=" + cardBack
				+ " alive=" + alive + "\n";
	}
}

