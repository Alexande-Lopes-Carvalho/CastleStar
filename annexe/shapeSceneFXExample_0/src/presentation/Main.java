package presentation;

import javafx.application.Application;
import javafx.stage.Stage;
import shapeSceneFX.FXCanvas;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		stage.setResizable(false);
		stage.sizeToScene();
		stage.setScene(new FXCanvas(new MainEventHandler()));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
