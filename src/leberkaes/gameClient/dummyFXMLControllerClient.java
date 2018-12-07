package leberkaes.gameClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	@FXML
	public ImageView deckBack;
	
	@FXML
	public TextArea chatTextArea;
	@FXML
	public TextArea txtChatMessage;
	

	@FXML
	public GridPane grid;
	@FXML
	public Text p1name;
	@FXML
	public Text p2name;
	@FXML
	public Text p3name;
	@FXML
	public Text p4name;
	
	@FXML
	public Text player1points;
	@FXML
	public Text player2points;
	@FXML
	public Text player3points;
	@FXML
	public Text player4points;
	
	@FXML
	public Text p1meeple;
	@FXML
	public Text p2meeple;
	@FXML
	public Text p3meeple;
	@FXML
	public Text p4meeple;
	
	


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
	
		
	public void setGameBoard (GameBoard g){
		
		// set images of open deck and card back of next card to be drawn from stack
			this.openDeck0.setImage(new Image(g.getOpenDeck()[0].getFrontImgURL()));
			this.openDeck1.setImage(new Image(g.getOpenDeck()[1].getFrontImgURL()));
			this.openDeck2.setImage(new Image(g.getOpenDeck()[2].getFrontImgURL()));
			this.openDeck3.setImage(new Image(g.getOpenDeck()[3].getFrontImgURL()));
			this.openDeck4.setImage(new Image(g.getOpenDeck()[4].getFrontImgURL()));
			this.openDeck5.setImage(new Image(g.getOpenDeck()[5].getFrontImgURL()));
			this.deckBack.setImage(new Image(g.getDeck().peek().getBackImgURL()));
		
		// set Playername per Player
			this.p1name.setText(g.getPlayers().get(0).getName());
			this.p2name.setText(g.getPlayers().get(1).getName());
			this.p3name.setText(g.getPlayers().get(2).getName());
			this.p4name.setText(g.getPlayers().get(3).getName());

		// set Player Score per Player
			this.player1points.setText(String.valueOf(g.getPlayers().get(0).getScore()));
			this.player2points.setText(String.valueOf(g.getPlayers().get(1).getScore()));
			this.player3points.setText(String.valueOf(g.getPlayers().get(2).getScore()));
			this.player4points.setText(String.valueOf(g.getPlayers().get(3).getScore()));			
			
		// get Meeple Count per Player
			
			for (int j=0; j<3; j++){
				g.getPlayers().get(0).getMeeple();
			}
			
			
			for (int k=0;k<8; k++){
				// get Number of LocationCards per Location
				g.getPlayers().get(0).getLocations()[0].getCardCount();
				
				
			}
			
			
		
		
	}


}
