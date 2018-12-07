package leberkaes.commonClasses;

import static leberkaes.commonClasses.CardType.*;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import leberkaes.commonClasses.CardType.*;
import leberkaes.jat2.ServiceLocator;

public class CharacterCard implements Serializable {

	private type cardType1;
	private type cardType2;
	private back cardBack;
	private URL backImgURL;
	private URL frontImgURL;
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
		this.setFrontImgURL(t1, t2);
		this.setBackImgURL(b);
	}

	public void setFrontImgURL(type t1, type t2) {
			
			//Standard Cards
		try {
			switch (t1) {
			case GRAIN:
				frontImgURL = new URL("leberkaes.GUIsources/characters/Orange.jpg");
				break;
			case BARELL:
				frontImgURL = new URL("leberkaes.GUIsources/characters/Brown.jpg");
				break;
			case POTION:
				frontImgURL = new URL("leberkaes.GUIsources/characters/Green.jpg");
				break;
			case SHIELD:
				frontImgURL = new URL("leberkaes.GUIsources/characters/Blue.jpg");
				break;
			case SWORD:
				frontImgURL = new URL("leberkaes.GUIsources/characters/Red.jpg");
				break;
			case CUTLERY:
				frontImgURL = new URL("leberkaes.GUIsources/characters/Yellow.jpg");
				break;
			case KEY:
				frontImgURL = new URL("leberkaes.GUIsources/characters/Violet.jpg");
				break;
			case HOSPITAL:
				// No CharacterCard of this type
				break;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setBackImgURL(back b) {
		try {
			switch (b) {
			case GREEN:
				backImgURL = new URL("leberkaes.GUIsources/characters/Back 1.jpg");
			case RED:
				backImgURL = new URL("leberkaes.GUIsources/characters/Back 2.jpg");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		return "CharacterCard cardType1=" + cardType1 + " cardType2=" + cardType2 + " cardBack=" + cardBack + " alive="
				+ alive + "\n";
	}

	public URL getBackImgURL() {
		return backImgURL;
	}

	public URL getFrontImgURL() {
		return frontImgURL;
	}


}
