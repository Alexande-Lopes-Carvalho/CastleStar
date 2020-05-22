package controle;

import shapeSceneFX.Point;

public class CtrlLevel_1 extends CtrlLevel {
	public CtrlLevel_1() {
		loadBackground("./data/Level_1/Background", new Point(29, 83));
		loadWall("./data/Level_1/Wall", new Point(105, 0));

		add(Decor.bigBarrel(new Point(110, 55)));
		add(Decor.bigBarrel(new Point(145, 55)));
		add(Decor.bigBarrel(new Point(213, 55)));
		
	}
}
