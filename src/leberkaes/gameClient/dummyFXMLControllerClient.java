package leberkaes.gameClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import leberkaes.commonClasses.GameBoard;

public class dummyFXMLControllerClient {

	/** @author: Sebrina Pedrossi including FXML for GUI*/

	/**
	 * Anmerkung D.Holliger:
	 * Vorsicht: FXML Controller versus MVC Controller!
	 * Um den generierten Kontroller von FXML an das MVC Framework von Brad anzudocken habe ich einige Anpassungen vorgenommen Ausgangslage:
	 * - Der FXMLoader lädt die fxml bei der Instanzierung des Views des MVC Modelles. Zu diesem Zeitpunkt existiert noch kein MVC Controller.
	 * - Der FMXLoader will den angegeben Kontrollerklasse instanzieren und erwartert eine statische Klasse ohne Konstruktor
	 *
	 * Implementation:
	 * - Im FXML keine Kontroller Klasse angeben
	 * - Kontrollerklasse (generierte Datei) von FXML mit dem Getter / Setter für ein APP_Controller Objekt (Controller MVC Brad) erstellen
	 * - Im View des MVC Brad den FXML Loader setzen, inkl. definition der ControllerKlasse -> Diese Dumy-Kontroller Klasse angeben (welche vom Scene Builder generiert wird).
	 * - Im View per Lazy Loading eine Instanz des Dummy-Controllers dem FXML Loader übergeben.
	 *
	 * Damit arbeiten:
	 * - In diesem Dummy-Kontroller können nun per this._MvcCtrl-> Methoden auf Methoden im MVC Kontroller von Brad gemappt werden.
	 *
	 * Ausbau:
	 * - Theoretisch könnte man dies noch mit einer Abstrakten Klasse vereinfachen, aber ich glaube wir lassen es aus Zeitgründen.
	 */


	private GameClient_Controller _MvcCtrl;

	public GameClient_Controller get_MvcCtrl() {
		return _MvcCtrl;
	}

	public void set_MvcCtrl(GameClient_Controller _MvcCtrl) {
		this._MvcCtrl = _MvcCtrl;
	}

	// Top controls (Connection)
	@FXML public TextField txtName;
	@FXML public TextField txtChatPort;
	@FXML public TextField txtGamePort;
	@FXML public TextField txtIpAddress;
	@FXML public Button btnConnect;
	@FXML public Text labelRoundCount;

	// Split Cards PopUp
	@FXML public ImageView splitCardImg;
	@FXML public Pane splitPane;


	// Deck Cards
	@FXML public ImageView openDeck5;
	@FXML public ImageView openDeck4;
	@FXML public ImageView openDeck3;
	@FXML public ImageView openDeck2;
	@FXML public ImageView openDeck1;
	@FXML public ImageView openDeck0;
	@FXML public ImageView deckBack;

	// Location Cards
	@FXML public ImageView loc1;
	@FXML public ImageView loc2;
	@FXML public ImageView loc3;
	@FXML public ImageView loc4;
	@FXML public ImageView loc5;
	@FXML public ImageView loc6;
	@FXML public ImageView loc7;
	@FXML public ImageView loc8;

	// Winners Screen Controls
	@FXML public Pane WinnerPane;
	@FXML public Button BackButton;
	@FXML public Text WinnerName;
	@FXML public Text WinnerPoints;

	// Chat-Controls
	@FXML public Button btnSend;
	@FXML public TextArea chatTextArea;
	@FXML public TextField txtChatMessage;

	// Player-Controls
	@FXML public GridPane grid;
	@FXML public Text p1name;
	@FXML public Text p2name;
	@FXML public Text p3name;
	@FXML public Text p4name;

	@FXML public ImageView iconp1;
	@FXML public ImageView iconp2;
	@FXML public ImageView iconp3;
	@FXML public ImageView iconp4;
	@FXML public ImageView iconmeeple;

	@FXML public Text player1points;
	@FXML public Text player2points;
	@FXML public Text player3points;
	@FXML public Text player4points;

	@FXML public Text p1meeple;
	@FXML public Text p2meeple;
	@FXML public Text p3meeple;
	@FXML public Text p4meeple;

	@FXML public Text p1GRAIN;
	@FXML public Text p1BARELL;
	@FXML public Text p1POTION;
	@FXML public Text p1SHIELD;
	@FXML public Text p1SWORD;
	@FXML public Text p1CUTLERY;
	@FXML public Text p1KEY;
	@FXML public Text p1HOSPITAL;

	@FXML public Text p2GRAIN;
	@FXML public Text p2BARELL;
	@FXML public Text p2POTION;
	@FXML public Text p2SHIELD;
	@FXML public Text p2SWORD;
	@FXML public Text p2CUTLERY;
	@FXML public Text p2KEY;
	@FXML public Text p2HOSPITAL;

	@FXML public Text p3GRAIN;
	@FXML public Text p3BARELL;
	@FXML public Text p3POTION;
	@FXML public Text p3SHIELD;
	@FXML public Text p3SWORD;
	@FXML public Text p3CUTLERY;
	@FXML public Text p3KEY;
	@FXML public Text p3HOSPITAL;

	@FXML public Text p4GRAIN;
	@FXML public Text p4BARELL;
	@FXML public Text p4POTION;
	@FXML public Text p4SHIELD;
	@FXML public Text p4SWORD;
	@FXML public Text p4CUTLERY;
	@FXML public Text p4KEY;
	@FXML public Text p4HOSPITAL;


	// set all Character-Cards on GameBoard disabled (can't be clicked)
	@FXML
	protected void setCardsDisabled(){
		this.openDeck0.setDisable(true);
		this.openDeck1.setDisable(true);
		this.openDeck2.setDisable(true);
		this.openDeck3.setDisable(true);
		this.openDeck4.setDisable(true);
		this.openDeck5.setDisable(true);
	}

	// set all Character-Cards on GameBoard enabled (can be clicked again)
	@FXML
	protected void setCardsEnabled(){
		this.openDeck0.setDisable(false);
		this.openDeck1.setDisable(false);
		this.openDeck2.setDisable(false);
		this.openDeck3.setDisable(false);
		this.openDeck4.setDisable(false);
		this.openDeck5.setDisable(false);
	}

	// set Meeple-Controls invisible (preparation for Meeple-Option)
	protected void setMeepleInvisible(){
		this.iconmeeple.setVisible(false);
		this.p1meeple.setVisible(false);
		this.p2meeple.setVisible(false);
		this.p3meeple.setVisible(false);
		this.p4meeple.setVisible(false);
	}

	// Button Send: sends message and wipes textField
	@FXML
	protected void handleSendClicked(ActionEvent event) throws Exception {
		get_MvcCtrl().sendMessage(this.txtChatMessage.getText());
		// Eingegebene Nachricht löschen
		this.txtChatMessage.setText("");

	}

	// Button Connect: connects to Server and sets all TextFields and Connect-Button disabled
	// so nothing can be changed after connected to server
	@FXML
	protected void handleConnectClicked(ActionEvent event) throws Exception {
		Boolean connectionStatus = get_MvcCtrl().connectToServer();
		if(!connectionStatus) {
			System.out.println("Verbindungsaufbau nicht möglich. Angaben prüfen.");
			return;
		}
		this.btnConnect.setDisable(true);
		this.txtName.setDisable(true);
		this.txtChatPort.setDisable(true);
		this.txtGamePort.setDisable(true);
		this.txtIpAddress.setDisable(true);
	}

	// Card clicked, takes this card
	// reads position of mouse click (x, y) for Split cards
	@FXML
	protected void handleCardClicked5(MouseEvent event) throws Exception {
		double y = event.getY();
		double x = event.getX();
		this.get_MvcCtrl().takeCard(5, x, y);
	}

	@FXML
	protected void handleCardClicked4(MouseEvent event) throws Exception {
		double y = event.getY();
		double x = event.getX();
		this.get_MvcCtrl().takeCard(4, x, y);
	}

	@FXML
	protected void handleCardClicked3(MouseEvent event) throws Exception {
		double y = event.getY();
		double x = event.getX();
		this.get_MvcCtrl().takeCard(3, x, y);
	}

	@FXML
	protected void handleCardClicked2(MouseEvent event) throws Exception {
		double y = event.getY();
		double x = event.getX();
		this.get_MvcCtrl().takeCard(2, x, y);
	}

	@FXML
	protected void handleCardClicked1(MouseEvent event) throws Exception {
		double y = event.getY();
		double x = event.getX();
		this.get_MvcCtrl().takeCard(1, x, y);
	}

	@FXML
	protected void handleCardClicked0(MouseEvent event) throws Exception {
		double y = event.getY();
		double x = event.getX();
		this.get_MvcCtrl().takeCard(0, x, y);
	}

	// Button Back: closes Window (Button is only visible when game is finished)
	@FXML
	protected void handleBackButtonClicked(MouseEvent event) throws Exception{
		// TODO: Close Window if this Button is clicked
		this.get_MvcCtrl().closeView();
	}

	// calculate winning score by comparing the scores of connected players
	// int j: how many players are connected?
	protected int getWinningScore(int j){
		int winscore = 0;

		if (j >= 2){
			int a = Integer.parseInt(this.player1points.getText());
			int b = Integer.parseInt(this.player2points.getText());

			winscore = Math.max(a, b);
		}

		if (j>=3){
			int c = Integer.parseInt(this.player3points.getText());
			winscore = Math.max(winscore,  c);
		}

		if (j == 4){

			int d = Integer.parseInt(this.player4points.getText());
			winscore = Math.max(winscore, d);
		}

		return winscore;

	}


	// Monster-Method for setting the Gameboard on the base of the GameBoard that is sent from server
	public void setGameBoard(GameBoard g){


		//set cardback-color if not empty
		if (g.getDeck().isEmpty()==true){
			this.deckBack.setImage(null);
		} else {
			this.deckBack.setImage(new Image(g.getDeck().peek().getBackImgURL()));
		}

		// set images of cards in open Deck
		this.openDeck0.setImage(new Image(g.getOpenDeck()[0].getFrontImgURL()));
		this.openDeck1.setImage(new Image(g.getOpenDeck()[1].getFrontImgURL()));
		this.openDeck2.setImage(new Image(g.getOpenDeck()[2].getFrontImgURL()));
		this.openDeck3.setImage(new Image(g.getOpenDeck()[3].getFrontImgURL()));
		this.openDeck4.setImage(new Image(g.getOpenDeck()[4].getFrontImgURL()));
		this.openDeck5.setImage(new Image(g.getOpenDeck()[5].getFrontImgURL()));

		// sets Character cards visible for the active player
		// for all other players, cards are inactive and not clickablee
		if(txtName.getText().equals(g.getActivePlayer().getName())){
			setCardsEnabled();
		} else {
			setCardsDisabled();
		}


		// set correct RoundCount
		this.labelRoundCount.setText(String.valueOf(g.getRoundCount()));

		//set Locations for A-Side
		if (g.isbSide() == false){
			this.loc1.setImage(new Image ("leberkaes.GUIsources/locationA/SideA1.jpg"));
			this.loc2.setImage(new Image ("leberkaes.GUIsources/locationA/SideA2.jpg"));
			this.loc3.setImage(new Image ("leberkaes.GUIsources/locationA/SideA3.jpg"));
			this.loc4.setImage(new Image ("leberkaes.GUIsources/locationA/SideA4.jpg"));
			this.loc5.setImage(new Image ("leberkaes.GUIsources/locationA/SideA5.jpg"));
			this.loc6.setImage(new Image ("leberkaes.GUIsources/locationA/SideA6.jpg"));
			this.loc7.setImage(new Image ("leberkaes.GUIsources/locationA/SideA7.jpg"));
			this.loc8.setImage(new Image ("leberkaes.GUIsources/locationA/SideA8.jpg"));
		}

		//set Locations for B-Side
		if (g.isbSide() == true){
			this.loc1.setImage(new Image ("leberkaes.GUIsources/locationB/SideB1.jpg"));
			this.loc2.setImage(new Image ("leberkaes.GUIsources/locationB/SideB2.jpg"));
			this.loc3.setImage(new Image ("leberkaes.GUIsources/locationB/SideB3.jpg"));
			this.loc4.setImage(new Image ("leberkaes.GUIsources/locationB/SideB4.jpg"));
			this.loc5.setImage(new Image ("leberkaes.GUIsources/locationB/SideB5.jpg"));
			this.loc6.setImage(new Image ("leberkaes.GUIsources/locationB/SideB6.jpg"));
			this.loc7.setImage(new Image ("leberkaes.GUIsources/locationB/SideB7.jpg"));
			this.loc8.setImage(new Image ("leberkaes.GUIsources/locationB/SideB8.jpg"));
		}

		//set Meeple invisible if Meeple-Option is false
		//Meeple-Option is not implemented, therefore right now it's always false
		if(g.isMeeple() == false){
			this.setMeepleInvisible();
		}

		// get Number of Players in Game
		int i = g.getPlayers().size();

		//set Numbers for Player 1 and Player 2
		if (i>=2){
			this.iconp1.setVisible(true);
			this.iconp2.setVisible(true);

			this.p1name.setText(g.getPlayers().get(0).getName());
			this.player1points.setText(String.valueOf(g.getPlayers().get(0).getScore()));
			this.p1meeple.setText(String.valueOf(g.getPlayers().get(0).getMeeple()));

			// get Number of LocationCards for Player 1
			this.p1GRAIN.setText(String.valueOf(g.getPlayers().get(0).getLocations()[0].getCardCount()));
			this.p1BARELL.setText(String.valueOf(g.getPlayers().get(0).getLocations()[1].getCardCount()));
			this.p1POTION.setText(String.valueOf(g.getPlayers().get(0).getLocations()[2].getCardCount()));
			this.p1SHIELD.setText(String.valueOf(g.getPlayers().get(0).getLocations()[3].getCardCount()));
			this.p1SWORD.setText(String.valueOf(g.getPlayers().get(0).getLocations()[4].getCardCount()));
			this.p1CUTLERY.setText(String.valueOf(g.getPlayers().get(0).getLocations()[5].getCardCount()));
			this.p1KEY.setText(String.valueOf(g.getPlayers().get(0).getLocations()[6].getCardCount()));
			this.p1HOSPITAL.setText(String.valueOf(g.getPlayers().get(0).getLocations()[7].getCardCount()));

			this.p2name.setText(g.getPlayers().get(1).getName());
			this.player2points.setText(String.valueOf(g.getPlayers().get(1).getScore()));
			this.p2meeple.setText(String.valueOf(g.getPlayers().get(1).getMeeple()));

			// get Number of LocationCards for Player 2
			this.p2GRAIN.setText(String.valueOf(g.getPlayers().get(1).getLocations()[0].getCardCount()));
			this.p2BARELL.setText(String.valueOf(g.getPlayers().get(1).getLocations()[1].getCardCount()));
			this.p2POTION.setText(String.valueOf(g.getPlayers().get(1).getLocations()[2].getCardCount()));
			this.p2SHIELD.setText(String.valueOf(g.getPlayers().get(1).getLocations()[3].getCardCount()));
			this.p2SWORD.setText(String.valueOf(g.getPlayers().get(1).getLocations()[4].getCardCount()));
			this.p2CUTLERY.setText(String.valueOf(g.getPlayers().get(1).getLocations()[5].getCardCount()));
			this.p2KEY.setText(String.valueOf(g.getPlayers().get(1).getLocations()[6].getCardCount()));
			this.p2HOSPITAL.setText(String.valueOf(g.getPlayers().get(1).getLocations()[7].getCardCount()));
		}

		// set Numbers for Player 3
		if (i>=3){
			this.p3name.setText(g.getPlayers().get(2).getName());
			this.player3points.setText(String.valueOf(g.getPlayers().get(2).getScore()));
			this.p3meeple.setText(String.valueOf(g.getPlayers().get(2).getMeeple()));
			this.iconp3.setVisible(true);


			// get Number of LocationCards for Player 3
			this.p3GRAIN.setText(String.valueOf(g.getPlayers().get(2).getLocations()[0].getCardCount()));
			this.p3BARELL.setText(String.valueOf(g.getPlayers().get(2).getLocations()[1].getCardCount()));
			this.p3POTION.setText(String.valueOf(g.getPlayers().get(2).getLocations()[2].getCardCount()));
			this.p3SHIELD.setText(String.valueOf(g.getPlayers().get(2).getLocations()[3].getCardCount()));
			this.p3SWORD.setText(String.valueOf(g.getPlayers().get(2).getLocations()[4].getCardCount()));
			this.p3CUTLERY.setText(String.valueOf(g.getPlayers().get(2).getLocations()[5].getCardCount()));
			this.p3KEY.setText(String.valueOf(g.getPlayers().get(2).getLocations()[6].getCardCount()));
			this.p3HOSPITAL.setText(String.valueOf(g.getPlayers().get(2).getLocations()[7].getCardCount()));
		}

		// set Numbers for Player 4
		if (i>=4){
			this.p4name.setText(g.getPlayers().get(3).getName());
			this.player4points.setText(String.valueOf(g.getPlayers().get(3).getScore()));
			this.p4meeple.setText(String.valueOf(g.getPlayers().get(3).getMeeple()));
			this.iconp4.setVisible(true);

			// get Number of LocationCards for Player 4
			this.p4GRAIN.setText(String.valueOf(g.getPlayers().get(3).getLocations()[0].getCardCount()));
			this.p4BARELL.setText(String.valueOf(g.getPlayers().get(3).getLocations()[1].getCardCount()));
			this.p4POTION.setText(String.valueOf(g.getPlayers().get(3).getLocations()[2].getCardCount()));
			this.p4SHIELD.setText(String.valueOf(g.getPlayers().get(3).getLocations()[3].getCardCount()));
			this.p4SWORD.setText(String.valueOf(g.getPlayers().get(3).getLocations()[4].getCardCount()));
			this.p4CUTLERY.setText(String.valueOf(g.getPlayers().get(3).getLocations()[5].getCardCount()));
			this.p4KEY.setText(String.valueOf(g.getPlayers().get(3).getLocations()[6].getCardCount()));
			this.p4HOSPITAL.setText(String.valueOf(g.getPlayers().get(3).getLocations()[7].getCardCount()));
		}

		// show active player with font-color green
		String act = g.getActivePlayer().getName();
		if (this.p1name.getText().equals(act)){
			this.p1name.setFill(Color.GREEN);

			this.p2name.setFill(Color.BLACK);
			this.p3name.setFill(Color.BLACK);
			this.p4name.setFill(Color.BLACK);
		}

		if (this.p2name.getText().equals(act)){
			this.p2name.setFill(Color.GREEN);

			this.p1name.setFill(Color.BLACK);
			this.p3name.setFill(Color.BLACK);
			this.p4name.setFill(Color.BLACK);
		}

		if (this.p3name.getText().equals(act)){
			this.p3name.setFill(Color.GREEN);

			this.p1name.setFill(Color.BLACK);
			this.p2name.setFill(Color.BLACK);
			this.p4name.setFill(Color.BLACK);
		}

		if (this.p4name.getText().equals(act)){
			this.p4name.setFill(Color.GREEN);

			this.p1name.setFill(Color.BLACK);
			this.p2name.setFill(Color.BLACK);
			this.p3name.setFill(Color.BLACK);

		}


		// show end-game screen with calculated winner-scores
		// finds which player has the winning score and displays his name
		// includes option if two players have same score (although then the layout is ugly)
		if (g.isGameEnd() == true){

			//set cards no longer clickable (because game is finished)
			setCardsDisabled();

			String winner = "";
			int score;
			int j = g.getPlayers().size();
			score = this.getWinningScore(j);

			if (j>=2){
				if (score == Integer.parseInt(this.player1points.getText())) {
					winner = this.p1name.getText();
				}
				if (score == Integer.parseInt(this.player2points.getText())) {
					winner += " " + this.p2name.getText();
				}
			}

			if (j>=3){
				if (score == Integer.parseInt(this.player3points.getText())) {
					winner += " " + this.p3name.getText();
				}
			}

			if (j == 4){
				if (score == Integer.parseInt(this.player4points.getText())) {
					winner += " " + this.p4name.getText();
				}
			}

			this.WinnerPane.setVisible(true);
			this.WinnerName.setText(winner);
			this.WinnerPoints.setText(String.valueOf(score));
			this.BackButton.setDisable(false);

		}

	}


}
