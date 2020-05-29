package abstraction;

import controle.CtrlLance;
import shapeSceneFX.Point;


public class LanceItem extends InventoryItem {
	public LanceItem(CtrlLance _ctrlLance, Point coord) {
		super(_ctrlLance, coord, new Ellipse(new Point(0, 0), new Point(42, 8)));
	}
}
