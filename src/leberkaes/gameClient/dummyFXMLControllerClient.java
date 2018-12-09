package leberkaes.gameClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import leberkaes.appClasses.App_Controller;
import leberkaes.commonClasses.GameBoard;

public class dummyFXMLControllerClient {

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

	
	
	// Chat-Controls
	@FXML public TextArea chatTextArea;
	@FXML public TextArea txtChatMessage;
	
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
	
	@FXML
	protected void setCardsDisabled(){
		this.openDeck0.setDisable(true);
		this.openDeck1.setDisable(true);
		this.openDeck2.setDisable(true);
		this.openDeck3.setDisable(true);
		this.openDeck4.setDisable(true);
		this.openDeck5.setDisable(true);
	}
	
	@FXML
	protected void setCardsEnabled(){
		this.openDeck0.setDisable(false);
		this.openDeck1.setDisable(false);
		this.openDeck2.setDisable(false);
		this.openDeck3.setDisable(false);
		this.openDeck4.setDisable(false);
		this.openDeck5.setDisable(false);
	}

	@FXML
	protected void handleSendClicked(ActionEvent event) throws Exception {
		get_MvcCtrl().sendMessage(this.txtChatMessage.getText());
		// Eingegebene Nachricht löschen
		this.txtChatMessage.setText("");

	}

	@FXML
	protected void handleConnectClicked(ActionEvent event) throws Exception {
		get_MvcCtrl().connectToServer();
	}
	
	@FXML
	protected void handleCardClicked5(MouseEvent event) throws Exception {
		
		this.get_MvcCtrl().takeCard(5);
		//setCardsDisabled();
		
		
	}
	
	@FXML
	protected void handleCardClicked4(MouseEvent event) throws Exception {
		
		this.get_MvcCtrl().takeCard(4);
		//setCardsDisabled();
		
	}
	
	@FXML
	protected void handleCardClicked3(MouseEvent event) throws Exception {
		
		this.get_MvcCtrl().takeCard(3);
		//setCardsDisabled();
		
	}
	
	@FXML
	protected void handleCardClicked2(MouseEvent event) throws Exception {
		
		this.get_MvcCtrl().takeCard(2);
		//setCardsDisabled();
		
	}
	
	@FXML
	protected void handleCardClicked1(MouseEvent event) throws Exception {
		
		this.get_MvcCtrl().takeCard(1);
		//setCardsDisabled();
		
	}
	
	@FXML
	protected void handleCardClicked0(MouseEvent event) throws Exception {
		
		this.get_MvcCtrl().takeCard(0);
		//setCardsDisabled();
		
	}
	
	
		
	public void setGameBoard(GameBoard g){
		
		
		// set images of open deck and card back of next card to be drawn from stack
			this.openDeck0.setImage(new Image(g.getOpenDeck()[0].getFrontImgURL()));
			this.openDeck1.setImage(new Image(g.getOpenDeck()[1].getFrontImgURL()));
			this.openDeck2.setImage(new Image(g.getOpenDeck()[2].getFrontImgURL()));
			this.openDeck3.setImage(new Image(g.getOpenDeck()[3].getFrontImgURL()));
			this.openDeck4.setImage(new Image(g.getOpenDeck()[4].getFrontImgURL()));
			this.openDeck5.setImage(new Image(g.getOpenDeck()[5].getFrontImgURL()));
			this.deckBack.setImage(new Image(g.getDeck().peek().getBackImgURL()));
			
			setCardsEnabled();
			
			// set correct RoundCount
			this.labelRoundCount.setText(String.valueOf(g.getRoundCount()));
		
			// show active player
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
			
			// get Number of Players in Game
			int i = g.getPlayers().size();
			
			//set Numbers for Player 1 and Player 2
			if (i>=2){
				this.p1name.setText(g.getPlayers().get(0).getName());
				this.player1points.setText(String.valueOf(g.getPlayers().get(0).getScore()));
				this.p1meeple.setText(String.valueOf(g.getPlayers().get(0).getMeeple()));
				this.iconp1.setVisible(true);
				this.iconp2.setVisible(true);

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
			
			
			
		
			
	}


}
