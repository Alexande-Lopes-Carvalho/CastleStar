package controle;

import abstraction.ElementCollidable;
import abstraction.Rectangle;
import presentation.PresImage;
import shapeSceneFX.Point;

public class CtrlLevel_1 extends CtrlLevel {
	public CtrlLevel_1() {
		CtrlElementCollidable ground = new CtrlElementCollidable(new ElementCollidable(new Point(29, 83), new Rectangle(new Point(0, 160), new Point(2714, 1))), new PresImage("./data/Level_1/Background.png"));
		ground.setRenderPriority(-1);
		add(ground);
		add(new CtrlElementCollidable(new ElementCollidable(new Point(105, 0), new Rectangle(new Point(0, 71),new Point(2505, 12))), new PresImage("./data/Level_1/Wall.png")));
		add(Decor.bigBarrel(new Point(110, 55)));
		add(Decor.bigBarrel(new Point(145, 55)));
		add(Decor.bigBarrel(new Point(213, 55)));
	}
}
