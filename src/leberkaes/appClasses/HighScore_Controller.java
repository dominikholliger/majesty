package leberkaes.appClasses;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import leberkaes.abstractClasses.Controller;
import leberkaes.commonClasses.Highscore;
import leberkaes.jat2.ServiceLocator;
/**
 * @author Dominik Holliger
 */
public class HighScore_Controller extends Controller<HighScore_Model, HighScore_View> {
    ServiceLocator serviceLocator;

    public HighScore_Controller(HighScore_Model model, HighScore_View view) {
        super(model, view);
        // D.Holliger:
        // Vorbereiten für die Kommunikation Controller vs DummyController FXML
        view.get_Ctrl().set_MvcCtrl(this);
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            	System.out.println("HighScore Screen schliessen");
            	view.stop();
            }
        });
        serviceLocator = ServiceLocator.getServiceLocator();
        serviceLocator.getLogger().info("Application controller initialized");
        //
        writeHighScoreListFromDB();
    }
    /**
     * Die einzelnen Events werden an dieser Stelle aus dem Dummy Controller verarbeitet
     */
	public void writeHighScoreListFromDB() {
		ArrayList list = Highscore.getInstance().getHighscore();
		Iterator<String> listIterator = list.iterator();
		while (listIterator.hasNext()) {
			view.get_Ctrl().writeToHighScore(listIterator.next()+"\n");
		}
	}
	public void closeHighScoreWindowFromButton() {
		System.out.println("HighScore Screen schliessen");
    	view.stop();
	}

}
