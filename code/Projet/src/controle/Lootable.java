package controle;

import abstraction.Entity;
import abstraction.Rectangle;
import presentation.PresImage;
import shapeSceneFX.Point;

public class Lootable {
	public static CtrlEntity barrel(Point coord) {
		return new CtrlEntity(new Entity(2, coord, new Rectangle(new Point(0, 15), new Point(13, 4))), new PresImage("./data/Lootable/Barrel.png"));
	}
}
