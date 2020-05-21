package controle;

import abstraction.ElementCollidable;
import abstraction.Rectangle;
import presentation.PresImage;
import shapeSceneFX.Point;

public class Decor {
	public static CtrlElementCollidable bigBarrel(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(31, 37))), new PresImage("./data/Decor/BigBarrel.png"));
	}
}
