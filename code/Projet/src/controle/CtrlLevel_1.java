package controle;

import abstraction.ElementCollidable;
import abstraction.Player;
import abstraction.Rectangle;
import javafx.scene.image.Image;
import presentation.MainEventHandler;
import presentation.PresImage;
import presentation.PresPlayer;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;

public class CtrlLevel_1 extends CtrlLevel {
	private Image bigBarrel;
	public CtrlLevel_1() {
		loadBackground("./data/Level_1/Background", new Point(29, 83));
		loadWall("./data/Level_1/Wall", new Point(105, 0));

		add(bigBarrel(new Point(110, 55)));
		add(bigBarrel(new Point(145, 55)));
		add(bigBarrel(new Point(213, 55)));
		
		add(new CtrlPlayer(new Player(0, new Point(50, 0), 10, new Point(30, 70)), new PresPlayer()));
	}
	
	public void initImage() {
		bigBarrel = EventHandler.loadPixelatedImage("./data/Level_1/Decor/BigBarrel.png", MainEventHandler.pxSize);
	}
	
	private CtrlElementCollidable bigBarrel(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(31, 37))), new PresImage(bigBarrel));
	}
}
