package controle;

import abstraction.Entity;
import abstraction.Rectangle;
import javafx.scene.image.Image;
import presentation.MainEventHandler;
import presentation.PresImage;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;

public class Lootable {
	private static Image barrel;
	public static CtrlEntity barrel(Point coord) {
		return new CtrlEntity(new Entity(0, 2, coord, new Rectangle(new Point(0, 15), new Point(13, 4))), new PresImage(barrel));
	}
	
	public static void initImage() {
		barrel = EventHandler.loadPixelatedImage("./data/Lootable/Barrel.png", MainEventHandler.pxSize);
	}
}
