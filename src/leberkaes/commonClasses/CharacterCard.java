package leberkaes.commonClasses;

import static leberkaes.commonClasses.CardType.*;

import java.io.Serializable;
/**Daniel Räber*/
public class CharacterCard implements Serializable {

	private type cardType1;
	private type cardType2;
	private type choosenCardType;

	private back cardBack;
	private String backImgURL;
	private String frontImgURL;
	

	public CharacterCard(type t, back b) {

		this.cardType1 = t;
		this.cardBack = b;
	
	}

	public CharacterCard(type t1, type t2, back b) {
		this.cardType1 = t1;
		this.cardType2 = t2;
		this.cardBack = b;
		
		this.setFrontImgURL(t1, t2);
		this.setBackImgURL(b);
	}

	public void setFrontImgURL(type t1, type t2) {

		// Standard Cards
		if (t2 == null) {
			switch (t1) {
			case GRAIN:
				frontImgURL = new String("leberkaes.GUIsources/characters/Orange.jpg");
				break;
			case BARELL:
				frontImgURL = new String("leberkaes.GUIsources/characters/Brown.jpg");
				break;
			case POTION:
				frontImgURL = new String("leberkaes.GUIsources/characters/Green.jpg");
				break;
			case SHIELD:
				frontImgURL = new String("leberkaes.GUIsources/characters/Blue.jpg");
				break;
			case SWORD:
				frontImgURL = new String("leberkaes.GUIsources/characters/Red.jpg");
				break;
			case CUTLERY:
				frontImgURL = new String("leberkaes.GUIsources/characters/Yellow.jpg");
				break;
			case KEY:
				frontImgURL = new String("leberkaes.GUIsources/characters/Violet.jpg");
				break;
			default:
				break;
			}

		} else {
			if (t1 == type.POTION) {
				switch (t2) {
				case KEY:
					frontImgURL = new String("leberkaes.GUIsources/characters/GreenViolet.jpg");
					break;
				case SHIELD:
					frontImgURL = new String("leberkaes.GUIsources/characters/GreenBlue.jpg");
					break;
				case BARELL:
					frontImgURL = new String("leberkaes.GUIsources/characters/GreenBrown.jpg");
					break;
				case CUTLERY:
					frontImgURL = new String("leberkaes.GUIsources/characters/GreenYellow.jpg");
					break;
				default:
					break;

				}
			}
			if (t1 == type.GRAIN) {
				switch (t2) {
				case BARELL:
					frontImgURL = new String("leberkaes.GUIsources/characters/OrangeBrown.jpg");
					break;
				default:
					break;

				}
			}

			if (t1 == type.SWORD) {
				switch (t2) {
				case GRAIN:
					frontImgURL = new String("leberkaes.GUIsources/characters/RedOrange.jpg");
					break;
				case SHIELD:
					frontImgURL = new String("leberkaes.GUIsources/characters/RedBlue.jpg");
					break;
				case BARELL:
					frontImgURL = new String("leberkaes.GUIsources/characters/RedBrown.jpg");
					break;
				case CUTLERY:
					frontImgURL = new String("leberkaes.GUIsources/characters/RedYellow.jpg");
					break;
				default:
					break;

				}
			}
			if (t1 == type.KEY) {
				switch (t2) {

				case SHIELD:
					frontImgURL = new String("leberkaes.GUIsources/characters/VioletBlue.jpg");
					break;
				case CUTLERY:
					frontImgURL = new String("leberkaes.GUIsources/characters/VioletYellow.jpg");
					break;
				default:
					break;

				}
			}
			if (t1 == type.CUTLERY) {
				switch (t2) {
				case SHIELD:
					frontImgURL = new String("leberkaes.GUIsources/characters/YellowBlue.jpg");
					break;
				default:
					break;
				}
			}
		}
	}

	public void setBackImgURL(back b) {
		try {
			switch (b) {
			case GREEN:
				backImgURL = new String("leberkaes.GUIsources/BackGreen.jpg");
			case RED:
				backImgURL = new String("leberkaes.GUIsources/BackRed.jpg");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public type getCardType1() {
		return cardType1;
	}

	public type getCardType2() {
		return cardType2;
	}


	@Override
	public String toString() {
		return "CharacterCard cardType1=" + cardType1 + " cardType2=" + cardType2 + " cardBack=" + cardBack +  "\n";
	}

	public String getBackImgURL() {
		return backImgURL;
	}

	public String getFrontImgURL() {
		return frontImgURL;
	}
	public type getChoosenCardType() {
		return choosenCardType;
	}

	public void setChoosenCardType(type choosenCardType) {
		this.choosenCardType = choosenCardType;
	}
}
