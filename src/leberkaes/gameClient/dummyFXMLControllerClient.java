package leberkaes.gameClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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
	@FXML
	public TextField txtName;
	@FXML
	public TextField txtChatPort;
	@FXML
	public TextField txtGamePort;
	@FXML
	public TextField txtIpAddress;
	@FXML
	public Button btnConnect;
	@FXML
	public ImageView openDeck5;
	@FXML
	public ImageView openDeck4;
	@FXML
	public ImageView openDeck3;
	@FXML
	public ImageView openDeck2;
	@FXML
	public ImageView openDeck1;
	@FXML
	public ImageView openDeck0;
	@FXML public ImageView deckBack;
	
	@FXML public TextArea chatTextArea;
	@FXML public TextArea txtChatMessage;
	

	@FXML public GridPane grid;
	@FXML public Text p1name;
	@FXML public Text p2name;
	@FXML public Text p3name;
	@FXML public Text p4name;
	
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
	
	


	/**
	 * Enable or disable the Connect button, based on the validity of the two text
	 * controls
	 */

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
	protected void takeCard5(MouseEvent event) throws Exception {
		
		this.get_MvcCtrl().takeCard(5);
		
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
		
			// get Number of Players in Game
			int i = g.getPlayers().size();
			

			
			
			//set Numbers for Player 1 and Player 2
			if (i>=2){
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
				this.p2meeple.setText(String.valueOf(g.getPlayers().get(0).getMeeple()));

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
				this.p3meeple.setText(String.valueOf(g.getPlayers().get(0).getMeeple()));

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
				this.p4meeple.setText(String.valueOf(g.getPlayers().get(0).getMeeple()));

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
