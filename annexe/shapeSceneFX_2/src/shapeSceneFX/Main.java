package shapeSceneFX;


import javafx.application.Application;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import shapeSceneFX.EventHandling.EventHandler;

public class Main extends Application{
	public void start(Stage stage){
		stage.setResizable(false);
		stage.sizeToScene();
		stage.setScene(new FXCanvas(new E()));
		stage.show();
		//g.setOnMouseMoved((value) -> {System.out.println();});
		
		//fxCanvas.start();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	class E extends EventHandler {
		Image img = loadImage("./data/rouge.jpg");
		Point p = new Point(0, 0);
		public void setup() {
			size(900, 300);
		}
		
		public void calc(long timePassed) {
			//System.out.println(timePassed);
		}
		
		public void render() {
			background(0);
			rect(mouseX(), mouseY(), 60, 30);
			image(img, width()-img.getWidth(), height()-img.getHeight());
			rectMode(CENTER);
			rect(p.getX(), p.getY(), 10, 10);
			rectMode(CORNER);
			stroke(255);
			text("hello", 5, 6);
			line(new Point(15, 15), new Point(90, 90));
			stroke(0);
			//System.out.println("Render");
			//System.out.println();
		}
		
		public void mouseDragged() {
			p.set(mouseX(), mouseY());
		}
		
		public void keyPressed() {
			System.out.println(keyCode() + " keyPressed");
		}
	}
}
