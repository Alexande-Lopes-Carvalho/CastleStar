package presentation;

import abstraction.Lootable;
import abstraction.Rectangle;
import controle.CtrlItem;
import controle.CtrlLootable;
import javafx.scene.image.Image;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;

public class Lootables {
	private static Image barrel;
	public static CtrlLootable barrel(Point coord, CtrlItem ... items) {
		return new CtrlLootable(new Lootable(4, 2, coord, new Rectangle(new Point(0, 15), new Point(13, 4))), new PresImage(barrel), items);
	}
	
	public static void initImage() {
		barrel = EventHandler.loadPixelatedImage("./data/Lootable/Barrel.png", MainEventHandler.pxSize);
	}
}
