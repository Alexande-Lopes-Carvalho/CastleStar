package presentation;

import javafx.application.Application;
import javafx.stage.Stage;
import shapeSceneFX.FXCanvas;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		/*
		 *On empeche la fenetre de pouvoir etre redimensionable 
		 */
		stage.setResizable(false);
		stage.sizeToScene();
		/*
		 * On crée une Scene contenant un Canvas et on l'ajoute dans notre stage
		 * Dans le constructeur de FXCanvas, la fonction setup de notre EventHandler (ici une instance de MainEventHandler)
		 * va etre appeler 
		 * 
		 * Puis a un rythme régulier les procedures calcEvent(long timePassed) et render() seront appeler dans notre EventHandler (ici l'instance de MainEventHandler)
		 * 
		 */
		stage.setScene(new FXCanvas(new MainEventHandler()));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
